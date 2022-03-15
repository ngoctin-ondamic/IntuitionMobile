package com.ngoctin.intuitionmobile.services;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.InforToUpdate;
import com.ngoctin.intuitionmobile.models.RegisterUserRequest;
import com.ngoctin.intuitionmobile.models.UpdateUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    public static void getUserInfo(String jwt, Context context) {
        UserAPI.userAPI.getUserInfo(jwt).enqueue(new Callback<UpdateUser>() {
            @Override
            public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                UpdateUser user = response.body();
                Toast.makeText(context, "Response: " + user, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UpdateUser> call, Throwable t) {

            }
        });
    }

    public static void update(String jwt, String username , String message, InforToUpdate userInfo, Context context) {
        UserAPI.userAPI.updateUser(jwt, username , userInfo)
                .enqueue(new Callback<InforToUpdate>() {
                    @Override
                    public void onResponse(Call<InforToUpdate> call, Response<InforToUpdate> response) {
                        System.out.println("update : " + message);
                        if(response.code() == 200){
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InforToUpdate> call, Throwable t) {

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
