package edu.uta.leanmed.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import edu.uta.leanmed.pojo.UserPojo;

/**
 * Created by Vaibhav's Console on 3/8/2019.
 */

public class SharedPreferenceService {
    private static String userName="";
    public static void saveObjectToSharedPreference(Context context, String serializedObjectKey, UserPojo user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("users", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        userName=serializedObjectKey;
        String serializedObject = gson.toJson(user);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static UserPojo getSavedObjectFromPreference(Context context, String preferenceKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("users", 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), UserPojo.class);
        }
        return null;
    }
    public static void removePreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("users", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.clear().commit();
        userName="";
    }

    public static String getUserName() {
        return userName;
    }
}
