
/**
 * Extends an object with a new set of values, any 
 * conflicting values will be overwritten in the base
 * object.
 * 
 * <p>Note: should not be used with nested objects</p>
 * 
 * @param base {Object} The object which will be extended
 * @param extension {Object} All properties of this object
 * will be copied to the base object
 */
YugenLogo.util.extendObject = function( base, extension ) {
	for( var i in extension ) {
		base[i] = extension[i];
	}
}



/**
 * 
 * @param {Object} p1
 * @param {Object} p2
 */
YugenLogo.util.distanceBetween = function(p1, p2) {
	var dx = p1.x-p2.x;
	var dy = p1.y-p2.y;
	return Math.sqrt(dx*dx + dy*dy);
}


/**
 * 
 */
YugenLogo.util.randomBetween = function( min, max, abs ) {
	var number = min + ( Math.random() * ( max - min ) );
	
	if( absMin !== undefined && min < -absMin && max > absMin ) {
		if( Math.random() > 0.5 ) {
			number = absMin + ( Math.random() * ( max - absMin ) );
		}
		else {
			number = -absMin + ( Math.random() * ( min + absMin ) );
		}
	}
	
	return number;
}



/**
 * 
 */
YugenLogo.util.getLoopedArrayElement = function( array, index ) {
	if( array[index] ) {
		return array[index];
	}
	
	var l = array.length;
	
	if( index > l-1 ) {
		return array[index - l];
	}
	
	if( index < 0 ) {
		return array[l + index];
	}
}



/**
 * 
 */
YugenLogo.util.Color = function( r, g, b, a ) {
	this.r = r || 0;
	this.g = g || 0;
	this.b = b || 0;
	this.a = a || 1;
}

YugenLogo.util.Color.prototype.approach = function( rgba, amp ) {
	this.r += ( rgba.r - this.r ) * amp;
	this.g += ( rgba.g - this.g ) * amp;
	this.b += ( rgba.b - this.b ) * amp;
	this.a += ( rgba.a - this.a ) * amp;
}

YugenLogo.util.Color.prototype.isWithinRangeOf = function( range, rgba ) {
	
	if( Math.abs( this.r - rgba.r ) < range ||
		Math.abs( this.g - rgba.g ) < range ||
		Math.abs( this.b - rgba.b ) < range ) {
		return true;
	}
	
	return false;
}

YugenLogo.util.Color.prototype.clone = function() {
	return new YugenLogo.util.Color( this.r, this.g, this.b, this.a );
}

YugenLogo.util.Color.prototype.toHEX = function() {
	return ( this.r << 16 | this.g << 8 | this.b ).toString( 16 );
}

YugenLogo.util.Color.prototype.toRGBAString = function() {
	return 'rgba('+Math.round(this.r)+','+Math.round(this.g)+','+Math.round(this.b)+','+this.a+')';
}



/**
 * Defines a 2D position.
 */
YugenLogo.util.Point = function( x, y ) {
	this.x = x || 0;
	this.y = y || 0;
}

YugenLogo.util.Point.prototype.distanceTo = function(p) {
	var dx = p.x-this.x;
	var dy = p.y-this.y;
	return Math.sqrt(dx*dx + dy*dy);
};

YugenLogo.util.Point.prototype.clonePosition = function() {
	return { x: this.x, y: this.y };
};



/**
 * Defines of a rectangular region.
 */
YugenLogo.util.Region = function() {
	this.left = Number.POSITIVE_INFINITY; 
	this.top = Number.POSITIVE_INFINITY; 
	this.right = 0; 
	this.bottom = 0;
}

YugenLogo.util.Region.prototype.reset = function() {
	this.left = Number.POSITIVE_INFINITY; 
	this.top = Number.POSITIVE_INFINITY; 
	this.right = 0; 
	this.bottom = 0; 
};

YugenLogo.util.Region.prototype.inflate = function( x, y ) {
	this.left = Math.min(this.left, x);
	this.top = Math.min(this.top, y);
	this.right = Math.max(this.right, x);
	this.bottom = Math.max(this.bottom, y);
};

YugenLogo.util.Region.prototype.contains = function( x, y ) {
	return x > this.left && x < this.right && y > this.top && y < this.bottom;
};


/**
 * Copyright (c) Mozilla Foundation http://www.mozilla.org/
 * This code is available under the terms of the MIT License
 */
if (!Array.prototype.map) {
    Array.prototype.map = function(fun /*, thisp*/) {
        var len = this.length >>> 0;
        if (typeof fun != "function") {
            throw new TypeError();
        }

        var res = new Array(len);
        var thisp = arguments[1];
        for (var i = 0; i < len; i++) {
            if (i in this) {
                res[i] = fun.call(thisp, this[i], i, this);
            }
        }

        return res;
    };
}