/* SharedPref.java
 * Author     : xbella01
 * Handling shared preferences
 */

package com.example.wis.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * public class for handling shared preferences
 */
public class SharedPref {

    final static String FileName = "SharedPref";

    /**
     * Read shared preferences
     * @param ctx
     * @param settingName
     * @param defaultValue
     * @return String shared preferences
     */
    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    /**
     * Save shared preferences
     * @param ctx
     * @param settingName
     * @param settingValue
     */
    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
        }

}
