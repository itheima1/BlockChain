
/**
 *
 */
YugenLogo.input.orientation = (function(window){

    var document = window.document;
    
	var MOBILE_TILT_FACTOR = 8;
	var DESKTOP_TILT_FACTOR = 4;
	
    function handleOrientation(e){
    
        var beta = e.beta;
        var gamma = e.gamma;
        
		// Rotate by the inverse gamma on a small scale
		YugenLogo.applyTilt( gamma * -1 / 180 * DESKTOP_TILT_FACTOR );
		
    }
    
    function handleMotion(e){
    
		var beta = -e.accelerationIncludingGravity.y;
        var gamma = e.accelerationIncludingGravity.x;
		
		// Rotate by the inverse gamma on a small scale
		YugenLogo.applyTilt( gamma * -1 / 180 * MOBILE_TILT_FACTOR );
		
		e.preventDefault();
		return false;
        
    }
	
	// Return our public API
	return {
		initialize: function() {
			addEventListener("deviceorientation", handleOrientation, false);
   			addEventListener("devicemotion", handleMotion, false);
		}
	}
    
})(window); // Self-execute


