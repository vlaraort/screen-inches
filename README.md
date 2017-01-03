# About screen #

Very simple Cordova plugin, used to get information related to device screen.

With this plugin you shoud be able to get:
  Screen width,
  screen height,
  screen diagonal screen size (in inches),
  screen resolution PPI (also known as DPI)
  
 ## Values ##
 ======
 width
 
 height
 
 screenDiagonal
 
 ### Code ###
 ```javascript
 window.plugins.aboutScreen.getInfo(
    function (e) {
        console.log("width", e.width);
        console.log("height", e.height);
        console.log("screenDiagonal", e.screenDiagonal);
        
    }
 )
```
 
 ## Usage ##

 Make yourself a function like this, and edit the MAX_INCHES_TEST_USER_AGENT variable to set the limit between tablet and mobile, the function will return a string in a promise with the value "tablet" or "smartphone" 
 ```javascript
 var MAX_INCHES_TEST_USER_AGENT = 6.0;
 
 detectDevice = function () {

var deferred = $.Deferred();

    //No cordova found, navigator screen size
    if (!window.cordova) {
        var w = $(document).width() * window.devicePixelRatio,
            h = $(document).height() * window.devicePixelRatio,
            diagonalSize = Math.sqrt(w * w + h * h);
    
        console.log("Diagonal Size: " + diagonalSize);
    
        var maxDiagonal = 1280;
    
        if (diagonalSize >= maxDiagonal) {
            deferred.resolve("tablet");
        } else {
            deferred.resolve("smartphone");
        }
    //Test IOS devices based on userAgent    
    } else if (navigator.userAgent.match(/iPad/i)) {
        deferred.resolve("tablet");
    
    } else if (navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i)) {
        deferred.resolve("smartphone");
    //Android, retrieving size from plugin
    } else {
    
        window.plugins.aboutScreen.getInfo(
            function (e) {
                console.log(e);
                if (e.screenDiagonal >= MAX_INCHES_TEST_USER_AGENT) {
    
                    deferred.resolve("tablet");
                } else if (e.screenDiagonal < MAX_INCHES_TEST_USER_AGENT) {
                    deferred.resolve("smartphone");
                //In case that the plugin can't retrieve the info     
                } else {
                    if (!navigator.userAgent.match(/Mobile/i)) {
                        deferred.resolve("tablet");
                    } else {
                        deferred.resolve("smartphone");
                    }
                }
            },
            function () {
                console.log(Error retrieving Screen Info");
                deferred.resolve("smartphone");
            });
    }
    
    return deferred.promise();
};
 ```
