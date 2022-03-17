package com.ngoctin.intuitionmobile.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.UpdateProfileRequest;

import java.util.List;

public class ApplicationUtils {

    public static void clearEditTextWithToast(EditText editText, String message, Context context){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
        editText.setText("");
    }

    public static void clearEditText(EditText editText, Context context){
        editText.setText("");
    }

    public static void clearAllEditTexts(List<EditText> list){
        for (EditText editText: list) {
            editText.setText("");
        }
    }
    public static AuthenticatedUser getAuthenticatedUser(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store",activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json, AuthenticatedUser.class);
        return authenticatedUser;
    }

    public static AuthenticatedUser getAuthenticatedUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_store",context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json, AuthenticatedUser.class);
        return authenticatedUser;
    }

    public static String getJwt(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_store",context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json, AuthenticatedUser.class);
        String jwt = authenticatedUser.getJwt();
        System.out.println("getJwt : " + jwt);
        return jwt;
    }

    public static String getJwt(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user_store",activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json, AuthenticatedUser.class);
        String jwt = authenticatedUser.getJwt();
        return jwt;
    }

    public static String getLocalhost(){
        String localhost = "192.168.130.2";
        return localhost;
    }

    public static UpdateProfileRequest getProfile(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("userProfile_store", activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user_profile", "");
        UpdateProfileRequest updateProfileRequest = gson.fromJson(json, UpdateProfileRequest.class);
        return updateProfileRequest;
    }

}
