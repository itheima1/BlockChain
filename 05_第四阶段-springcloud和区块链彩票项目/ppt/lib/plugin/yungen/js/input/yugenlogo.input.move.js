
/**
 * 
 */
YugenLogo.input.move = (function( window ) {
	
	var document = window.document;
	
	var PARALLAX_STRENGTH = 14;
	var FORCE_RADIUS = 60;
	var FORCE_STRENGTH = 30;
	
	var mouse = {
		x: 0,
		y: 0,
		previousX: 0,
		previousY: 0
	};
	
	var canvas = null;
	
    function mouseMoveHandler( event ) {
		
		mouse.x = event.clientX;
		mouse.y = event.clientY;
		
		applyForce();
		applyParallax();
        
		mouse.previousX = mouse.x;
		mouse.previousY = mouse.y;
		
    }
    
    function touchMoveHandler( event ) {
		
		mouse.x = event.touches[0].pageX;
		mouse.y = event.touches[0].pageY;
		
		applyForce();
        
		mouse.previousX = mouse.x;
		mouse.previousY = mouse.y;
		
    }
	
	function applyForce() {
		
		// Difference between the current and previous mouse 
		// positions in pixels
		var diff = {
			x: mouse.x - mouse.previousX,
			y: mouse.y - mouse.previousY
		}; 
		
		// Determine the force on a -1 to +1 scale
		var force = {
			x: diff.x / window.innerWidth,
			y: diff.y / window.innerHeight
		};
		
		var local = {
			x: mouse.x - canvas.position().left,
			y: mouse.y - canvas.position().top
		};
		
		// 
		YugenLogo.applyForce( local.x, local.y, force.x * FORCE_STRENGTH, force.y * FORCE_STRENGTH, FORCE_RADIUS );
		
	}
	
	function applyParallax() {
		
		// Foreground offset
		var fx = ( -0.5 + ( mouse.x / window.innerWidth ) ) * PARALLAX_STRENGTH;
		var fy = ( -0.5 + ( mouse.y / window.innerHeight ) ) * PARALLAX_STRENGTH;
		
		// Background offset
		var bx = fx * 0.2;
		var by = fy * 0.2;
		
		YugenLogo.applyParallax( fx, fy, bx, by );
		
	}
	
	// Return our public API
	return {
		initialize: function() {
			addEventListener("mousemove", mouseMoveHandler, false);
			addEventListener("touchmove", touchMoveHandler, false);
			
			canvas = $( "#" + YugenLogo.getOptions().logoCanvasID );
		}
	}
	
	
})( window ); // Self-execute


