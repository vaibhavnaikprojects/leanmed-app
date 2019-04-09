package edu.uta.leanmed.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.google.gson.Gson;

import java.util.Locale;

import edu.uta.leanmed.pojo.User;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class SharedPreferenceService {
    private static String userName="";
    public static void saveObjectToSharedPreference(Context context, String serializedObjectKey, User user) {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences("users", 0).edit();
        final Gson gson = new Gson();
        userName=serializedObjectKey;
        String serializedObject = gson.toJson(user);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static void updateLocale(Context context, String lang){
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences("settings", 0).edit();
        sharedPreferencesEditor.putString("lang",lang);
        sharedPreferencesEditor.apply();
    }

    public static void loadLocale(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", 0);
        setLocale(context,sharedPreferences.getString("lang", ""));
    }
    public static void setLocale(Context context,String lang){
        Locale locale=new Locale(lang);
        locale.setDefault(locale);
        Configuration conf=new Configuration();
        conf.locale=locale;
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
        updateLocale(context,lang);
    }

    public static User getSavedObjectFromPreference(Context context, String preferenceKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("users", 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), User.class);
        }
        return null;
    }
    public static void removePreferences(Context context){
        context.getSharedPreferences("users", 0).edit().clear().commit();
        context.getSharedPreferences("settings", 0).edit().clear().commit();
        userName="";
    }

    public static String getUserName() {
        return userName;
    }
}
