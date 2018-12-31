package com.muhyidinamin.upwork;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muhyidin Amin on 5/14/2017.
 */

public class UserData {
    private static UserData mInstance;
    private static Context mCtx;

    private static final String USER_DATA_NAME = "userdata";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DEPAN = "depan";
    private static final String KEY_BELAKANG = "belakang";
    private static final String KEY_EMAIL = "email";

    private UserData(Context context){
        mCtx = context;
    }

    public static synchronized UserData getInstance(Context context){
        if(mInstance==null){
            mInstance = new UserData(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username, String depan, String belakang, String email){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_DATA_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_DEPAN, depan);
        editor.putString(KEY_BELAKANG, belakang);
        editor.putString(KEY_EMAIL, email);
        editor.apply();

        return true;
    }

    public boolean isLogin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_DATA_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null)!=null){
            return true;
        }
        return false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_DATA_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
