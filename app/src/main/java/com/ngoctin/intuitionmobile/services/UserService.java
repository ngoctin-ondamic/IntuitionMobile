package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.InforToUpdate;
import com.ngoctin.intuitionmobile.models.UpdateProfileRequest;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    public static void getUserInfo(String jwt, Context context) {
        UserAPI.userAPI.getUserInfo(jwt).enqueue(new Callback<UpdateProfileRequest>() {
            @Override
            public void onResponse(Call<UpdateProfileRequest> call, Response<UpdateProfileRequest> response) {
                if(response.body() != null) {
                    UpdateProfileRequest updateProfileRequest = response.body();
                    updateProfileRequest.setJwt(jwt);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("userProfile_store", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String userProfile = gson.toJson(updateProfileRequest);
                    editor.putString("user_profile", userProfile);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileRequest> call, Throwable t) {

            }
        });
    }

    public static void update(String jwt, String username , String message, InforToUpdate userInfo, Context context) {
        UserAPI.userAPI.updateUser(jwt, username , userInfo)
                .enqueue(new Callback<InforToUpdate>() {
                    @Override
                    public void onResponse(Call<InforToUpdate> call, Response<InforToUpdate> response) {
                        System.out.println(response);
                        if(response.code() == 200){
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Update Failed!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InforToUpdate> call, Throwable t) {

                    }
                });
    }

    public static void getUpdateProfile(Activity activity, EditText editUsername,
                                        EditText editFullname, EditText editPhonenumber, EditText editEmail){
        String jwt = ApplicationUtils.getJwt(activity);
        UserAPI.userAPI
                .getUserInfo(jwt)
                .enqueue(new Callback<UpdateProfileRequest>() {
                    @Override
                    public void onResponse(Call<UpdateProfileRequest> call, Response<UpdateProfileRequest> response) {
//                        System.out.println("getUpdateProfile : " + response.body().toString());
                        editUsername.setText(response.body().getUsername());
                        editUsername.setFocusable(false);
                        editFullname.setText(response.body().getFullname());
                        editPhonenumber.setText(response.body().getPhoneNumber());
                        editEmail.setText(response.body().getEmail());
                     }

                    @Override
                    public void onFailure(Call<UpdateProfileRequest> call, Throwable t) {

                    }
                });
    }


    public static int validate(InforToUpdate inforUser){

        if(!inforUser.getFullname().matches("[a-zA-Z\\s]*")
                || inforUser.getFullname().length() == 0){
            return 1;
        }

        if(!inforUser.getPhoneNumber().matches("^\\d{10,12}$")){
            return 2;
        }

        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!inforUser.getEmail().matches(emailRegex)){
            return 3;
        }
        return 0;
    }

//    public static int validate(RegisterUserRequest registerUserRequest, String confirmPassword){
//        if(!registerUserRequest.getUsername()
//                .matches("^[a-zA-Z]*([._](?![._])|[a-zA-Z0-9]){3,12}[a-zA-Z0-9]$")){
//            // ngoctin.1908
//            return 1;
//        }
//        if(!registerUserRequest.getPassword().
//                matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
//            // 123456Tin@
//            return 2;
//        }
//        if(!registerUserRequest.getPassword().equals(confirmPassword)){
//            return 3;
//        }
//        if(!registerUserRequest.getFullname().matches("[a-zA-Z\\s]*")
//                || registerUserRequest.getFullname().length() == 0
//        ){
//            return 4;
//        }
//        if(!registerUserRequest.getPhoneNumber().matches("^\\d{10,12}$")){
//            return 5;
//        }
//        return 0;
//    }
}
