package com.vlara.aboutscreen;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.DisplayMetrics;
import android.graphics.Point;
import android.view.*;
import android.content.Context;
import java.lang.Math;




/**
 * This class echoes a string called from JavaScript.
 */
public class AboutScreen extends CordovaPlugin {

    private static final String ACTION_GET_INFO = "getInfo";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_GET_INFO)) {
            this.getInfo(callbackContext);
            return true;
        }
        return false;
    }

    private void getInfo(CallbackContext callbackContext) {

        
        
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
       
        (this.cordova.getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int absoluteHeightInPx = displayMetrics.heightPixels;
        int absoluteWidthInPx = displayMetrics.widthPixels;
        double diagonalPixels = Math.sqrt((absoluteHeightInPx * absoluteHeightInPx) + (absoluteWidthInPx * absoluteWidthInPx));
        double diagonalInInches = diagonalPixels / displayMetrics.densityDpi;
   
        float heightInches = displayMetrics.heightPixels / displayMetrics.xdpi;
        float widthInches = displayMetrics.widthPixels / displayMetrics.ydpi;
        double diagonalInches = Math.sqrt( (heightInches * heightInches) + (widthInches * widthInches) );
        
        JSONObject info = new JSONObject();
        try {
            info.put("width",widthInches);
            info.put("height",heightInches);
            info.put("screenDiagonal",diagonalInches);
        } catch (JSONException e) {
            callbackContext.error("Unexpected JSONException.");
            e.printStackTrace();
        }
        callbackContext.success(info);
    }
}