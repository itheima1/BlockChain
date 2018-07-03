/**
 * This class is the main controller in charge of running
 * the Yugen logo animation. The animation consists of 
 * fluid shapes that morph in an organic way.
 * 
 * The primary, non-degraded, version of this animation is
 * rendered on HTML5's canvas element. If no support for
 * canvas is detected, a fallback consisting of images only
 * will be used.
 * 
 * The logo can be manipulated via an input plugin mechanic.
 * These plugins are defined in the initialization options 
 * and work via the public API of the YugenLogo class.
 * 
 * @author Hakim El Hattab
 */

var YugenLogo = (function( window ) {
	
	// The pointst that build up our shapes will always be in
	// one of the following states
	var INTRO_STATE = 0;
	var MORPH_STATE = 1;
	var OUTRO_STATE = 2;
	
	// The original dimensions, from which scale is calculated
	var ORIGINAL_WIDTH = 370;
	var ORIGINAL_HEIGHT = 250;
	
	// The framerate the logo was developed for. If a different
	// framerate is set via configuration we need to compare it
	// with this value and determine how to move properties as
	// fast with the less frequent update rate.
	var ORIGINAL_FRAMERATE = 60;
	
	// The vertical scale of the shadow, 1 means as high as the
	// logo canvas itself
	var SHADOW_SCALE_Y = 0.5;
	
	// The original dimensions of the logo word, this is relative
	// to the full original dimensions defined above
	var ORIGINAL_WORD_WIDTH = 144;
	var ORIGINAL_WORD_HEIGHT = 41;
	
	// The number of pixels that the individual shape points may
	// offset from their normal. This number is based on the 
	// original width/height of the logo and will scale up/down
	// depending on the currently rendered resolution.
	var MORPH_RANGE = 20;
	var MORPH_RANGE2 = MORPH_RANGE * 2; // Calculate only once, memory is cheaper than processing
	
	// Turbo mode means increased morph and color transition speed,
	// this value is used to apply decay to the turbo value so that
	// it dies out over time
	var TURBO_DECAY = 0.01;
	
	// Friction that gets applied to the velocity of the individual 
	// shape points
	var FRICTION = 0.95;
	
	// The number of pixels that shape layers will be offset from
	// each other
	var SHAPE_OFFSET = 10;
	
	// The maximum number of pixels of perspective distortion that
	// the parallax interaction may apply 
	var PARALLAX_AMOUNT = 10;
	
	// Flags if we are on a touch device
	var IS_TOUCH_DEVICE = 	!!navigator.userAgent.match(/Android/i) || 
							!!navigator.userAgent.match(/iPhone/i) || 
							!!navigator.userAgent.match(/iPad/i) || 
							!!navigator.userAgent.match(/iPod/i);
	
	// This will act as the public API for this object and is
	// returned at the end of construction. This object will
	// be assigned to the global YugenLogo variable.
	var API = {
		
		// Define the utilities namespace
		util: {},
		
		// Define the input namespace
		input: {}
		
	};
	
	//
	var document = window.document;
	
	// Flags if the logo has been initialized or not 
	// (initialization may only happen once)
	var initialized = false;
	
	// Holds the options that define how the logo is rendered.
	// These are the default values and may be overridden in
	// the intialize method. 
	var options = {
		
		// DOM element ID's
		containerID: "yugen-logo",
		logoCanvasID: "yugen-logo-canvas",
		shadowCanvasID: null,
		
		// A URL pointing to the image to use for the logo text
		// (i.e. "Yugen")
		wordImageURL: "images/yugen-word.png",
		
		// An offset in pixel that will be applied to the position
		// of the word image
		wordOffsetX: -25,
		wordOffsetY: 0,
		
		// The scale of the wordmark
		wordScale: 1.5,
		
		// The number of times to redraw the logo per second
		framerate: IS_TOUCH_DEVICE ? 30 : ORIGINAL_FRAMERATE,
		
		// Flags if debug mode is enabled or not
		debug: true,
		
		// The size at which the logo will render
		width: 400,
		height: 300,
		
		// Scales to apply to the shape layers (1 means 100% of 
		// available width/height)
		foregroundScaleX: 0.39,
		foregroundScaleY: 0.23,
		backgroundScaleX: 0.39,
		backgroundScaleY: 0.23,
		
		// The maximum amount of rotation that can be applied to the
		// shape layers (in radians)
		maxShapeRotation: 0.2,
		
		// Speeds of animations
		colorSpeedFactor: 1.0,
		introSpeedFactor: 3.0,
		outroSpeedFactor: 3.0,
		morphSpeedFactor: 1.0,
		
		// Controls the speed at which the base shape, or normals, are
		// moved around 
		morphBaseSpeedFactor: 0.2,
		
		// A multiplier for point offsets during the morph animation
		morphStrengthFactor: 0.8,
		
		// A multiplier for the distributed offsets applied to the normal 
		// position of the shape points 
		normalOffsetFactor: 1.5,
		
		// Determines the strength of the drop shadow alpha
		shadowAlpha: 0.1,
		
		// The number of points that defines each shape layer
		shapeQuality: 9,
		
		// A list of colors that the logo shape will loop through
		colors: [  ],
		
		// A list of all currently active input types
		inputs: [  ],
		
		// A list images that will be rotated throguh for the fallback
		fallbackImages: [  ]
		
	};
	
	// DOM elements and respective API's
	var container = null,
		logoCanvas = null,
		logoContext = null,
		shadowCanvas = null,
		shadowContext = null;
	
	// Image and alpha used to render the "Yugen" word
	var wordImage = null;
	
	// The udpate interval used to redraw the canvas
	var updateInterval = -1;
	
	// The current scale of the logo (based on ORIGINAL_WIDTH/HEIGHT)
	var scale = 1;
	
	// The current global alpha of the logo
	var alpha = 0;
	
	// Turbo mode can be activated to increase the speed at which
	// the shape morphs and transistions between colors
	var turboMorphStrengthFactor = 1;
	var turboMorphSpeedFactor = 1;
	var turboColorSpeedFactor = 1; // Since these are multipliers, a value of 1 means no change
	
	// Flags if the logo is currently shown or hidden
	var visible = true;
	
	var tilt = { current: 0, target: 0 };
	
	// Logo shape definitions
	var shapeForeground = null,
		shapeBackground = null,
		shapeForegroundColor = null,
		shapeBackgroundColor = null;
	
	// The outer bounds of the shapes
	var shapeBounds = {
		left: 0,
		right: 0
	};
	
	var colorQueue = [];
	var colorTween = 0;
	
	// Performance (FPS) tracking
	var performance = { 
		fps: options.framerate, 
		lastSecond: new Date().getTime(),
		frameCount: 0,
		factor: 1
	};
	
	/**
	 * Initializes the logo animation. Will use feature
	 * detection to detection to determine what type of
	 * animation to run.
	 * 
	 * @param overrideOptions {Object} an object containing
	 * properties that should be overwritten in the default
	 * options
	 * 
	 * [public method]
	 */
	API.initialize = function( overrideOptions ) {
		
		if( !initialized ) {
			// Extend our default options with the override options
			YugenLogo.util.extendObject( options, overrideOptions );
			
			// Fetch a reference to the container DOM element
			container = $( "#" + options.containerID );
			
			// Set up a reference to the canvas element inside of the
			// main container
			logoCanvas = $( "#" + options.logoCanvasID ).get(0);
			shadowCanvas = $( "#" + options.shadowCanvasID ).get(0);
			
			// Is there support for canvas?
			if( logoCanvas && logoCanvas.getContext ) {
				startLogoCanvas();
				
				// Nested in here since we should never draw a shadow
				// if the main canvas is unavailable.
				if( shadowCanvas && shadowCanvas.getContext ) {
					startShadowCanvas();
				}
			}
			else {
				startFallback();
			}
			
			// Enable debugging if required
			if( options.debug ) {
				enableDebugging();
			}
			
			initialized = true;
		}
		else {
			// If we're already initialized, at least set the new options
			API.configure( overrideOptions );
		}
		
	}
	
	/**
	 * Configures the logo with a new set of options. 
	 * 
	 * @param overrideOptions {Object} an object containing
	 * properties that should be overwritten in the default
	 * options
	 * 
	 * [public method]
	 */
	API.configure = function( overrideOptions ) {
		// Extend our default options with the override options
		YugenLogo.util.extendObject( options, overrideOptions );
		
		// Make sure the size is up to date with the new options
		resize();
		
		// Some updates should only be made if canvas is supported
		if ( logoContext ) {
			// Create the shape color queue
			createColorQueue();
			
			// Make sure an initial color is set
			shapeForegroundColor = colorQueue[0][0].clone();
			shapeBackgroundColor = colorQueue[0][1].clone();
			
			shapeForeground = createShape( ORIGINAL_WIDTH * options.foregroundScaleX, ORIGINAL_HEIGHT * options.foregroundScaleY, 0.2 );
			shapeBackground = createShape( ORIGINAL_WIDTH * options.backgroundScaleX, ORIGINAL_HEIGHT * options.backgroundScaleY, 3.3 );
			
			// Clear any currently running interval
			clearInterval( updateInterval );
			
			// Start up a new interval at the updated framerate
			updateInterval = setInterval( loop, 1000 / options.framerate );
			
			// Reset alpha and run intro animation
			alpha = 0;
			API.show();
		}
	}
	
	/**
	 * Provides a way to read the options from an external
	 * script, primaily used for inputs.
	 * 
	 * @return {Object} An object containing the currently set
	 * options for the logo animation
	 * 
	 * [public method]
	 */
	API.getOptions = function() {
		return options;
	}
	
	/**
	 * Returns the current scale of the logo animation.
	 * 
	 * [public method]
	 */
	API.getScale = function() {
		return scale;
	}
	
	/**
	 * Applies a an impulse to the logo world, this will add 
	 * velocity to all points within a certain radius. Points
	 * get pushed back using the angle between the point itself
	 * and impulse position.
	 * 
	 * @param x {Number} The impulse insertion X position
	 * @param y {Number} The impulse insertion Y position
	 * @param strength {Number} The pushback strength
	 * @param radius {Number} The radius to apply the force to
	 * 
	 * [public method]
	 */
	API.applyImpulse = function( x, y, strength, radius ) {
		
		// We need to run this operation on both the foreground
		// and background shapes so we make it an inline function
		function applyForceToShape( shape ) {
			
			var center = centerOfShape( shape );
			var target = { x: x - center.x, y: y - center.y };
			
			for (var i = 0, len = shape.points.length; i < len; i++) {
				var p = shape.points[i];
				
				// Determine the distance, in pixels, between this point
				// and the force insertion position
				var distance = YugenLogo.util.distanceBetween( target, p );
				
				if( distance < radius ) {
					
					// The distance factor by which force is scaled, this value
					// is higher if the distance is small
					var factor = 1 - ( distance / radius );
					
					p.velocity.x += ( ( p.x - target.x ) / options.width ) * strength * factor;
					p.velocity.y += ( ( p.y - target.y ) / options.height ) * strength * factor;
					
					
				}
				
			}
		}
		
		applyForceToShape( shapeForeground );
		applyForceToShape( shapeBackground );
		
	}
	
	/**
	 * Applies a directional force in the logo world, this
	 * will add velocity to all shape points within the 
	 * affected radius.
	 * 
	 * @param x {Number} The force insertion X position
	 * @param y {Number} The force insertion Y position
	 * @param fx {Number} The horizontal force to apply
	 * @param fy {Number} The vertical force to apply
	 * @param radius {Number} The radius to apply the force to
	 * 
	 * [public method]
	 */
	API.applyForce = function( x, y, fx, fy, radius ) {
		
		// We need to run this operation on both the foreground
		// and background shapes so we make it an inline function
		function applyForceToShape( shape ) {
			
			var center = centerOfShape( shape );
			var target = { x: x - center.x, y: y - center.y };
			
			for (var i = 0, len = shape.points.length; i < len; i++) {
				var p = shape.points[i];
				
				// Determine the distance, in pixels, between this point
				// and the force insertion position
				var distance = YugenLogo.util.distanceBetween( target, p );
				
				if( distance < radius ) {
					
					// The distance factor by which force is scaled, this value
					// is higher if the distance is small
					var factor = 1 - ( distance / radius );
					
					p.velocity.x += fx * factor * scale;
					p.velocity.y += fy * factor * scale;
					
					
				}
				
			}
		}
		
		applyForceToShape( shapeForeground );
		applyForceToShape( shapeBackground );
		
	}
	
	/**
	 * Applies parallax offsets to the shape layers (foreground
	 * and background).
	 * 
	 * @param fx {Number} Foreground X offset
	 * @param fy {Number} Foreground Y offset
	 * @param bx {Number} Foreground X offset
	 * @param by {Number} Foreground Y offset
	 * 
	 * [public method]
	 */
	API.applyParallax = function( fx, fy, bx, by ) {
		shapeForeground.offset.tx = Math.cos( shapeForeground.offset.angle ) * PARALLAX_AMOUNT + fx;
		shapeForeground.offset.ty = Math.sin( shapeForeground.offset.angle ) * PARALLAX_AMOUNT + fy;
		
		shapeBackground.offset.tx = Math.cos( shapeBackground.offset.angle ) * PARALLAX_AMOUNT + bx;
		shapeBackground.offset.ty = Math.sin( shapeBackground.offset.angle ) * PARALLAX_AMOUNT + by;
	}
	
	/**
	 * Applies tilt rotation to the logo.
	 * 
	 * [public method]
	 */
	API.applyTilt = function( t ) {
		tilt.target = t;
	}
	
	/**
	 * Activates turbo mode which increases the speed at which 
	 * the shapes morph and change color.
	 * 
	 * [public method]
	 */
	API.applyTurbo = function( morphStrength, morphSpeed, colorSpeed ) {
		turboMorphStrengthFactor = morphStrength;
		turboMorphSpeedFactor = morphSpeed;
		turboColorSpeedFactor = colorSpeed;
	}
	
	/**
	 * Changes the shapes to a random color in the queue. 
	 * 
	 * [public method]
	 */
	API.changeColor = function() {
		colorQueue.shift();
		
		if( !colorQueue[0] ) {
			createColorQueue();
		}
	}
	
	/**
	 * Runs the intro animation and enables the logo.
	 * 
	 * [public method]
	 */
	API.show = function() {
		visible = true;
		
		var points = shapeForeground.points.concat( shapeBackground.points );
		
		points.map( function( p ) {
			p.state = INTRO_STATE;
			
			p.target.x = p.normal.x;
			p.target.y = p.normal.y;
		} );
	}
	
	/**
	 * Runs the outro animation and disables the logo.
	 * 
	 * [public method]
	 */
	API.hide = function() {
		visible = false;
		
		var points = shapeForeground.points.concat( shapeBackground.points );
		
		points.map( function( p ) {
			p.state = OUTRO_STATE;
			
			p.target.x = 0;
			p.target.y = 0;
		} );
		
	}
	
	/**
	 * Starts up the canvas based logo animation.
	 */
	function startLogoCanvas() {
		
		// Pick up the canvas 2d context
		logoContext = logoCanvas.getContext("2d");
		
		// Force an initial resize to adjust the canvas
		resize();
		
		// Create the shape color queue
		createColorQueue();
		
		// Make sure an initial color is set
		shapeForegroundColor = colorQueue[0][0].clone();
		shapeBackgroundColor = colorQueue[0][1].clone();
		
		shapeForeground = createShape( ORIGINAL_WIDTH * options.foregroundScaleX, ORIGINAL_HEIGHT * options.foregroundScaleY, 0.2 );
		shapeBackground = createShape( ORIGINAL_WIDTH * options.backgroundScaleX, ORIGINAL_HEIGHT * options.backgroundScaleY, 3.3 );
		
		// Make sure the canvas is visible (it's originally hidden
		// to avoid flicker)
		$( logoCanvas ).show();
		
		// Load in the word image
		wordImage = $( "<img>" ).load( function() {
			// Start up our render interval
			updateInterval = setInterval( loop, 1000 / options.framerate );
			
		} );
		
		// Set the source of the IMG element to start loading
		wordImage.attr( "src", options.wordImageURL );
		
		// Initialize all of our inputs
		options.inputs.map( function( input ) {
			input.initialize();
		} );
		
	}
	
	/**
	 * Starts up the canvas based shadow animation for the
	 * logo.
	 */
	function startShadowCanvas() {
		
		// Pick up the canvas 2d context
		shadowContext = shadowCanvas.getContext("2d");
		
		// Make sure the canvas is visible (it's originally hidden
		// to avoid flicker)
		$( shadowCanvas ).css( "display", "block" );
		
		// Force an initial resize to adjust the canvas
		resize();
		
	}
	
	/**
	 * Starts up the fallback, sans-canvas, logo animation.
	 */
	function startFallback() {
		
		$( "img", container ).show().mouseover( function( event ) {
			var shiftee = options.fallbackImages.shift();
			
			$(this).attr( "src", shiftee );
			
			options.fallbackImages.push( shiftee );
		} );
		
	}
	
	/**
	 * Enables debugging scripts such as mouse tracking logs,
	 * runtime script execution etc.
	 */
	function enableDebugging() {
		options.debug = true;
		
		// Inline helper method
		function createDebugProperty( property, wrap ) {
			return "\t " + property + ": " + (wrap||"") + options[property] + (wrap||"") + ",\n";
		}
		
		// Define the original context of the configuration box
		var configureScript = "YugenLogo.initialize({\n";
		configureScript += 		createDebugProperty( "framerate" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "width" );
		configureScript += 		createDebugProperty( "height" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "colorSpeedFactor" );
		configureScript += 		createDebugProperty( "morphSpeedFactor" );
		configureScript += 		createDebugProperty( "morphStrengthFactor" );
		configureScript += 		createDebugProperty( "morphBaseSpeedFactor" );
		configureScript += 		createDebugProperty( "introSpeedFactor" );
		configureScript += 		createDebugProperty( "outroSpeedFactor" );
		configureScript += 		createDebugProperty( "normalOffsetFactor" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "maxShapeRotation" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "wordOffsetX" );
		configureScript += 		createDebugProperty( "wordOffsetY" );
		configureScript += 		createDebugProperty( "wordScale" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "foregroundScaleX" );
		configureScript += 		createDebugProperty( "foregroundScaleY" );
		configureScript += 		createDebugProperty( "backgroundScaleX" );
		configureScript += 		createDebugProperty( "backgroundScaleY" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "shadowAlpha" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "shapeQuality" );
		configureScript += 		"\t\n";
		configureScript += 		createDebugProperty( "containerID", '"' );
		configureScript += 		createDebugProperty( "logoCanvasID", '"' );
		configureScript += 		createDebugProperty( "shadowCanvasID", '"' );
		configureScript += 		"\t\n";
		configureScript += 		"\t	colors: [\n";
		configureScript +=	"\t\t\t // Foreground: \t\t\t\t\t\t\t Background: \n";
		
		for( var i = 0; i < options.colors.length; i++ ) {
			var ca = options.colors[i][0];
			var cb = options.colors[i][0];
			
			configureScript +=	"\t\t\t [ new YugenLogo.util.Color( "+ca.r+", "+ca.g+", "+ca.r+" ), \t new YugenLogo.util.Color( "+ca.r+", "+ca.g+", "+ca.r+" ) ],\n";
		}
		
		configureScript += 		"\t\t ]\n";
		configureScript += 		"});";
		
		$( "#debug-panel textarea" ).text( configureScript );
		
		
		$( "#debug-panel .run" ).click( function( event ) {
			event.preventDefault();
			
			eval( $( "#debug-panel textarea" ).val() );
		} );
		
		$( "#debug-panel .save" ).click( function( event ) {
			event.preventDefault();
			
			window.open( logoCanvas.toDataURL("image/png") );
		} );
	}
	
	/**
	 * Uses the colors specified in the options object to 
	 * create a randomly structured queue.
	 */
	function createColorQueue() {
		function random_sort( item ) {
		      return 0.5 - Math.random();
		}
		
		colorQueue = options.colors.concat();
		colorQueue.sort( random_sort );
	}
	
	/**
	 * Creates a new morph shape definition based on
	 * the specified parameters.
	 * 
	 * @param radiusx {Number} the width of the shape
	 * @param radiusy {Number} the height of the shape
	 * @param angle {Number} the angle at which this shape
	 * should be offset from the mid point
	 */
	function createShape( radiusx, radiusy, offsetAngle ) {
		
		// Define the shape foundation
		var shape = {
			offset: {
				// Current offset
				x: 0,
				y: 0,
				
				// Target offset
				tx: Math.cos( offsetAngle ) * SHAPE_OFFSET,
				ty: Math.sin( offsetAngle ) * SHAPE_OFFSET,
				
				// Offset angle
				angle: offsetAngle
			},
			
			// We need this later to generate new normals
			radiusx: radiusx,
			radiusy: radiusy,
			
			// The radians at which the shape will be rendered
			rotation: ( -( options.maxShapeRotation * 0.5 ) + Math.random() * options.maxShapeRotation ),
			
			points: []
		};
		
		// The total number of points this shape will be built out of
		var count = options.shapeQuality;
		
		// Create the shape points
		while( --count ) {
			
			var point = {
				
				// The position of this point prior to any offsets
				normal: createShapeNormal( shape, count, options.shapeQuality ), 
				
				// Even the normal morphs, and hence we need a target for it
				// to ease towards.
				normalTarget: {
					x: 0,
					y: 0
				},
				
				// An offset in pixels that is combined with the normal
				// to determine the final position
				offset: {
					x: ( -MORPH_RANGE + Math.random() * MORPH_RANGE2 ) * scale,
					y: ( -MORPH_RANGE + Math.random() * MORPH_RANGE2 ) * scale
				},
				
				// The position to which this point is animating
				target: {
					x: 0,
					y: 0
				},
				
				velocity: {
					x: 0,
					y: 0
				},
				
				// The speed at which this point will animate towards
				// its target
				speed: 0.010 + Math.random() * 0.015,
				
				// See the shape type definitions for more info
				state: INTRO_STATE,
				
				// The current position of this point, this is what gets
				// rendered on canvas and shown to the user
				x: 0,
				y: 0
			};
			
			point.target.x = point.normal.x;
			point.target.y = point.normal.y;
			
			point.normalTarget.x = point.normal.x;
			point.normalTarget.y = point.normal.y;
			
			shape.points.push( point );
			
		}
		
		return shape;
	}
	
	// This variable is inlined since it's specifically used
	// in the function below to pick between two different
	// types of shape normals. Added in an attempt to control
	// the randomness and make sure the generated shapes look
	// good. 
	var shapeTemplate = 1 + Math.floor(Math.random()*3);
	
	/**
	 * 
	 */
	function createShapeNormal( shape, index, total ) {
		// A scaling amount that is used to contract 
		var scaleOffsetX = 1;
		var scaleOffsetY = 1;
		
		var scaleOffsetXRange = 0.1 * options.normalOffsetFactor;
		var scaleOffsetYRange = 0.2 * options.normalOffsetFactor;
		
		// For X% of the points we want to apply a native offset
		// (i.e. applied directly to the normal)
		if( Math.random() > 0.3 ) {
			
			// Always reduce the horizontal offset since there is less
			// padding on that axis
			scaleOffsetX -= scaleOffsetXRange + Math.random() * scaleOffsetXRange;
			
			if( shapeTemplate == 1 ) {
				// Alternate between reducing and increasing the vertical offset
				if (index % 2 == 0) {
					scaleOffsetY += scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
				else {
					scaleOffsetY -= scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
			}
			else if( shapeTemplate == 2 ) {
				// Alternate between reducing and increasing the vertical offset
				if (index % 2 == 0) {
					scaleOffsetY -= scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
				else {
					scaleOffsetY += scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
			}
			else {
				// Alternate between reducing and increasing the vertical offset
				if (Math.random() > 0.5) {
					scaleOffsetY -= scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
				else {
					scaleOffsetY += scaleOffsetYRange + Math.random() * scaleOffsetYRange;
				}
			}
		}
		
		// Determine at which angle this point should appear
		var angle = ( index / total ) * Math.PI * 2;
		
		return {
			x: ( Math.cos( angle ) * ( shape.radiusx * scaleOffsetX ) ) * scale,
			y: ( Math.sin( angle ) * ( shape.radiusy * scaleOffsetY ) ) * scale
		}
	}
	
	/**
	 * Calculates the center position of a shape, factoring
	 * in both shape offset and scale.
	 */
	function centerOfShape( shape ) {
		return {
			x: ( options.width * 0.5 ) + ( shape.offset.x * scale ),
			y: ( options.height * 0.5 ) + ( shape.offset.y * scale )
		};
	}
	
	/**
	 * Updates the size of the UI to match that of the options
	 * OR whatever is specified in the arguments of this 
	 * function.
	 */
	function resize( width, height ) {
		
		// If the width/height is specified we need to make sure 
		// to store those in the options
		options.width = width || options.width;
		options.height = height || options.height;
		
		// Update the global scale
		scale = Math.min( options.width / ORIGINAL_WIDTH, options.height / ORIGINAL_HEIGHT );
		
		// Update the size of the logo canvas element (if there is one)
		if( logoCanvas ) {
			logoCanvas.width = options.width;
			logoCanvas.height = options.height;
		}
		
		// Update the size of the shadow canvas element (if there is one)
		if( shadowCanvas ) {
			shadowCanvas.width = options.width;
			shadowCanvas.height = options.height * SHADOW_SCALE_Y;
		}
		
	}
	
	/**
	 * Called on every frame to update properties and render
	 * the current logo state to the canvas element.
	 */
	function loop() {
		
		// Ease towards tilt target
		tilt.current += ( tilt.target - tilt.current ) * 0.2;
		
		turboColorSpeedFactor += ( 1 - turboColorSpeedFactor ) * TURBO_DECAY;
		turboMorphSpeedFactor += ( 1 - turboMorphSpeedFactor ) * TURBO_DECAY;;
		turboMorphStrengthFactor += ( 1 - turboMorphStrengthFactor ) * TURBO_DECAY;;
		
		// Run our property updates
		updateColor();
		updatePerformance();
		updateShape( shapeForeground );
		updateShape( shapeBackground );
		
		// Render elements to the canvas
		renderLogo();
		renderWord();
		renderShadow();
		
	}
	
	/**
	 * Updates the performance tracking variables.
	 */
	function updatePerformance() {
		
		// Fetch the current time for this frame
		var frameTime = new Date().getTime();
		
		// Increase the frame count
		performance.frameCount ++;
		
		// Check if a second has passed since the last time we updated 
		// the FPS
		if( frameTime > performance.lastSecond + 1000 ) {
			// Establish the current frames per second
			performance.fps = Math.max( Math.min( Math.round( ( performance.frameCount * 1000 ) / ( frameTime - performance.lastSecond ) ), options.framerate ), 1 );
			
			// Establish the factor that should be applied to speeds 
			// in the animation based on performance
			performance.factor = Math.min( ORIGINAL_FRAMERATE / options.framerate, 1 ) + ( 1 - performance.fps / ORIGINAL_FRAMERATE );
			
			performance.lastSecond = frameTime;
			performance.frameCount = 0;
		}
		
	}
	
	/**
	 * Updates the color properties of our shape as well
	 * as the global alpha values (for intro/outro)
	 */
	function updateColor() {
		
		// Set references to the current background and foreground 
		// colors
		var shapeForegroundColorTarget = colorQueue[0][0];
		var shapeBackgroundColorTarget = colorQueue[0][1];
		
		// Make the current foreground and background colors 
		// approach their respective targets
		shapeForegroundColor.approach( shapeForegroundColorTarget, 0.015 * options.colorSpeedFactor * performance.factor * turboColorSpeedFactor );
		shapeBackgroundColor.approach( shapeBackgroundColorTarget, 0.015 * options.colorSpeedFactor * performance.factor * turboColorSpeedFactor );
		
		var foregroundDone = shapeForegroundColor.isWithinRangeOf( 0.1 * options.colorSpeedFactor * turboColorSpeedFactor, shapeForegroundColorTarget );
		var backgroundDone = shapeBackgroundColor.isWithinRangeOf( 0.1 * options.colorSpeedFactor * turboColorSpeedFactor, shapeBackgroundColorTarget );
		
		// If both the foreground and background have reached their
		// target colors, it's time to tint towards a new one
		if( foregroundDone && backgroundDone ) {
			
			colorQueue.shift();
			
			if( !colorQueue[0] ) {
				createColorQueue();
			}
			
		}
		
		// Fade in
		if( visible ) {
			alpha = Math.min( alpha + 0.01 * options.introSpeedFactor, 1 );
		}
		// Fade out
		else {
			alpha = Math.max( alpha - 0.01 * options.outroSpeedFactor, 0 );
		}
		
	}
	
	/**
	 * Updates all properties of one shape by one step/frame,
	 * including morphing and positioning.
	 */
	function updateShape( shape ) {
		
		shape.offset.x += ( shape.offset.tx - shape.offset.x ) * 0.1;
		shape.offset.y += ( shape.offset.ty - shape.offset.y ) * 0.1;
		
		for( var i = 0, len = shape.points.length; i < len; i++ ) {
			
			p1 = YugenLogo.util.getLoopedArrayElement( shape.points, i );
			
			// Inflate the bounds if needed
			shapeBounds.left = Math.min( shapeBounds.left, p1.x );
			shapeBounds.right = Math.max( shapeBounds.right, p1.x );
			
			// The default morph speed
			var speedFactor = options.morphSpeedFactor;
			
			// Switch speed depending on scenario
			switch( p1.state ) {
				case INTRO_STATE:
					speedFactor = options.introSpeedFactor;
					break;
				case OUTRO_STATE:
					speedFactor = options.outroSpeedFactor;
					break;
			}
			
			// Ease towards the normal target
			p1.normal.x += ( p1.normalTarget.x - p1.normal.x ) * 0.01;
			p1.normal.y += ( p1.normalTarget.y - p1.normal.y ) * 0.01;
			
			// Ease towards target
			p1.x += (p1.target.x - p1.x) * p1.speed * speedFactor * performance.factor * turboMorphSpeedFactor;
			p1.y += (p1.target.y - p1.y) * p1.speed * speedFactor * performance.factor * turboMorphSpeedFactor;
			
			// Apply velocity to the position
			p1.x += p1.velocity.x;
			p1.y += p1.velocity.y;
			
			// Apply friction to the velocity
			p1.velocity.x *= FRICTION;
			p1.velocity.y *= FRICTION;
			
			// If the current position is too far away from the normal we
			// need to drastically reduce the velocity
			if( YugenLogo.util.distanceBetween( p1, p1.normal ) > MORPH_RANGE2 ) {
				p1.velocity.x *= Math.pow(FRICTION,16);
				p1.velocity.y *= Math.pow(FRICTION,16);
			}
			
			// Set a new horizontal offset if the current one is minimal
			if (Math.abs(p1.x - p1.target.x) < 1 ) {
				if (p1.state !== OUTRO_STATE) {
					p1.target.x = p1.normal.x;
					p1.target.x += (-MORPH_RANGE + Math.random() * MORPH_RANGE2) * options.morphStrengthFactor * turboMorphStrengthFactor * scale;
					
					p1.state = MORPH_STATE;
				}
			}
			
			// Set a new vertical offset if the current one is minimal
			if (Math.abs(p1.y - p1.target.y) < 1) {
				if (p1.state !== OUTRO_STATE) {
					p1.target.y = p1.normal.y;
					p1.target.y += (-MORPH_RANGE + Math.random() * MORPH_RANGE2) * options.morphStrengthFactor * turboMorphStrengthFactor * scale;
					
					p1.state = MORPH_STATE;
				}
			}
			
			// If the current normal position matches the normal target,
			// we need to update it
			if ( Math.abs(p1.normal.x - p1.normalTarget.x) < 1 && Math.abs(p1.normal.y - p1.normalTarget.y) < 1 ) {
				p1.normalTarget = createShapeNormal( shape, shape.points.length - i, options.shapeQuality );
			}
			
		}
		
	}
	
	/**
	 * 
	 */
	function renderShadow() {
		
		if( shadowCanvas && shadowContext ) {
			
			var width = shadowCanvas.width;
			var height = shadowCanvas.height;
			
			// Horizontal scale is based on the horizontal bounds of 
			// the shape
			var scaleX = 1 + Math.abs( shapeBounds.right - shapeBounds.left ) / options.width;
			
			// Vertical scale is a constant which scales up with the 
			// size of the container
			var scaleY = 0.2;
			
			// An offset that is applied to match the current centering
			// of the shape based on its bounds
			var offsetX = ( Math.abs( shapeBounds.left ) - Math.abs( shapeBounds.right ) ) / 2;
			
			// Clear the entirity of the canvas
			shadowContext.clearRect( 0, 0, width, height );
			
			shadowContext.save();
			
			// Apply the global alpha to this canvas (used during intro/outro)
			shadowContext.globalAlpha = alpha;
			
			// Apply scaling to the shadow, this also means we need to apply
			// an offset to the position for it to remain centered
			shadowContext.translate( - ( width * ( scaleX - 1 ) / 2 ) - offsetX, - ( height * ( scaleY - 1 ) / 2 ) );
			shadowContext.scale( scaleX, scaleY );
			
			// Define the gradient fill for the shadow
			var shadowGradient = shadowContext.createRadialGradient( width * 0.5, height * 0.5, 0, width * 0.5, height * 0.5, height / 2 );
			shadowGradient.addColorStop( 0.3, "rgba(0, 0, 0, " + options.shadowAlpha + ")" );
			shadowGradient.addColorStop( 1, "rgba(0, 0, 0, 0.0)" );
			
			// Drawing time!
			shadowContext.fillStyle = shadowGradient;
			shadowContext.fillRect( 0, 0, width, height );
			
			shadowContext.restore();
			
		}
		
	}
	
	/**
	 * 
	 */
	function renderLogo() {
		
		// Reset the shape bounds
		shapeBounds.left = options.width;
		shapeBounds.right = 0;
		
		// Clear the entirity of the canvas
		logoContext.clearRect( 0, 0, options.width, options.height );
		
		// Save (stack #1)
		logoContext.save();
		
		logoContext.globalAlpha = alpha;
		
		// Save (stack #2)
		logoContext.save();
		
		// Only apply tilting when needed
		if (tilt.current !== 0 || tilt.target !== tilt.current) {
			logoContext.translate(Math.round(options.width * 0.5), Math.round(options.height * 0.5));
			logoContext.rotate(tilt.current);
			logoContext.translate(-Math.round(options.width * 0.5), -Math.round(options.height * 0.5));
		}
		
		// First we render the foreground
		renderShape( shapeForeground, shapeForegroundColor );
		
		// Save (stack #3)
		logoContext.save();
		
		// Next we render a transparent black version of the 
		// background, this shape is only rendered INSIDE of the
		// foreground and this effectively creates the third shape.
		logoContext.globalCompositeOperation = "source-atop";
		renderShape( shapeBackground, new YugenLogo.util.Color( 0, 0, 0, 0.5 ) );
		
		// Finally we draw the background behind the two previously
		// rendered shapes using the destination-over operation
		logoContext.globalCompositeOperation = "destination-over";
		renderShape( shapeBackground, shapeBackgroundColor );
		
		// Restore to the original state
		logoContext.restore();
		logoContext.restore();
		logoContext.restore();
		
	}
	
	/**
	 * 
	 */
	function renderWord() {
		
		// Save (for globalAlpha)
		logoContext.save();
		
		// Apply the global alpha for the word rawing
		logoContext.globalAlpha = alpha;
		
		// Scaled width and height
		var sw = ORIGINAL_WORD_WIDTH * scale * options.wordScale;
		var sh = ORIGINAL_WORD_HEIGHT * scale * options.wordScale;
		
		// Offset x and y to center
		var ox = Math.round( ( options.width - sw ) * 0.5 ) + ( options.wordOffsetX * scale );
		var oy = Math.round( ( options.height - sh ) * 0.5 ) + ( options.wordOffsetY * scale );
		
		// Draw the image using the determined size and offset
		logoContext.drawImage( wordImage.get(0), ox, oy, sw, sh );
		
		// Restore (from globalAlpha)
		logoContext.restore();
		
	}
	
	/**
	 * 
	 */
	function renderShape( shape, color ) {
		
		// Determine what the center position of the rendered
		// shape will be
		var shapeCenter = centerOfShape( shape )
		
		logoContext.save();
		
		// Offset to center
		logoContext.translate( shapeCenter.x, shapeCenter.y );
		
		// Apply shape rotation
		logoContext.rotate( shape.rotation );
		
		logoContext.beginPath();
		
		var p1 = YugenLogo.util.getLoopedArrayElement( shape.points, -1 );
		var p2 = YugenLogo.util.getLoopedArrayElement( shape.points, 0 );
		
		logoContext.moveTo( p1.x + ( p2.x - p1.x ) / 2, p1.y + ( p2.y - p1.y ) / 2 );
		
		for( var i = 0, len = shape.points.length; i <= len; i++ ) {
			
			p1 = YugenLogo.util.getLoopedArrayElement( shape.points, i );
			p2 = YugenLogo.util.getLoopedArrayElement( shape.points, i+1 );
			
			logoContext.quadraticCurveTo(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y + (p2.y - p1.y) / 2);
			
		}
		
		logoContext.fillStyle = color.toRGBAString();
		logoContext.fill();
		
		logoContext.restore();
		
	}
	
	// Return our public API object
	return API;
	
}( window )) // Self execute
















