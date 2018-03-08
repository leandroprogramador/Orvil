package br.com.whereit.orvil.Helper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jonas.vieira on 02/02/2018.
 */

public class SharedHelper {

    public static void saveData(Activity activity, String key, String value){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getData(Activity activity, String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        String value = sharedPref.getString(key, "");
        return value;
    }

    public static void deleteData(Activity activity, String key){
        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
