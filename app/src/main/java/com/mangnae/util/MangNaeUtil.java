package com.mangnae.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by pjm on 2015-12-30.
 */
public class MangNaeUtil {

    /**
     * 가로 세로 디멘전 리턴
     * @param activity
     * @return
     */
    public static String getDisplay(Activity activity){
        String result = "";
        if(activity == null)
            return result;
        DisplayMetrics displayMetrics = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
//		Toast.makeText(getApplicationContext(), "width : " + deviceWidth + " height : " + deviceHeight + " density : " + displayMetrics.density + " densityDpi : " + displayMetrics.densityDpi+ " os : " + Build.VERSION.SDK_INT , 1).show();

        result  = "width : " + deviceWidth + " height : " + deviceHeight + " density : " + displayMetrics.density + " densityDpi : " + displayMetrics.densityDpi;

        return result;

    }
}
