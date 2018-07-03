
/**
 * 
 */
YugenLogo.input.press = (function( window ) {
	
	var document = window.document;
	
	var IMPULSE_RADIUS = 150;
	var IMPULSE_STRENGTH_MOUSE = 20;
	var IMPULSE_STRENGTH_TOUCH = 40;
	
	var mouse = {
		x: 0,
		y: 0
	}
	
	var interval = -1;
	
	var canvas = null;
	
    function mouseDownHandler( event ) {
		
		mouse.x = event.clientX;
		mouse.y = event.clientY;
		
		applyImpulse( IMPULSE_STRENGTH_MOUSE );
		
		// Trigger a change in color
		YugenLogo.changeColor();
		
		// Start an interval that will run for as long as the mouse is down
		interval = setInterval( mouseDownUpdate, 200 );
		
		mouseDownUpdate();
		
    }
	
	function mouseDownUpdate() {
		YugenLogo.applyTurbo( 1, 3, 4 );
	}
	
	function mouseUpHandler( event ) {
		clearInterval( interval );
	}
    
    function touchStartHandler( event ) {
		
		if (event.touches[0]) {
			mouse.x = event.touches[0].pageX;
			mouse.y = event.touches[0].pageY;
			
			applyImpulse( IMPULSE_STRENGTH_TOUCH );
			
			// Trigger a change in color
			YugenLogo.changeColor();
			
			// Start an interval that will run for as long as there is an active touch
			interval = setInterval( touchDownUpdate, 200 );
			
			touchDownUpdate();
			
			event.preventDefault();
		}
		
    }
	
	function touchDownUpdate() {
		YugenLogo.applyTurbo( 1, 5, 4 );
	}
	
	function touchEndHandler( event ) {
		clearInterval( interval );
	}
	
	function applyImpulse( strength ) {
		
		var local = {
			x: mouse.x - canvas.position().left,
			y: mouse.y - canvas.position().top
		};
		
		var scale = YugenLogo.getScale();
		
		YugenLogo.applyImpulse( local.x, local.y, strength * scale, IMPULSE_RADIUS * scale );
		
	}
	
	// Return our public API
	return {
		initialize: function() {
			canvas = $( "#" + YugenLogo.getOptions().logoCanvasID );
			
			canvas.get(0).addEventListener("mousedown", mouseDownHandler, false);
			addEventListener("mouseup", mouseUpHandler, false);
			
			canvas.get(0).addEventListener("touchstart", touchStartHandler, false);
			addEventListener("touchend", touchEndHandler, false);
		}
	}
	
	
})( window ); // Self-execute


