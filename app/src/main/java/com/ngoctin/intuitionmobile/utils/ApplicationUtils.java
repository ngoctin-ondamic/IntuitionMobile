package com.ngoctin.intuitionmobile.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

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


}
