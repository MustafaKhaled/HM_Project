package com.example.mustafakhaled.hm_project.helper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/*
 * Created by Mustafa Khaled on 10/22/2018.
 *
 */ public class ShowToastMessage extends Activity {

      public void showOnUIToast(Context context,String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            }
        });

     }
     public static void showToast(Context context , String msg){
         Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
     }
}
