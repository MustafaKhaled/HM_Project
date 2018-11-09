package com.example.mustafakhaled.hm_project.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/*
 * Created by Mustafa Khaled on 11/6/2018.
 *
 */ public class CheckPermissions extends Activity {
     private final static String TAG = "CheckPermissions";
     public static boolean isSinglePermissionGranted(Context mContext,final String permission){
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             if(mContext.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED){
                 return true;
             }
             else {
                 Log.e(TAG,"checkSinglePermission(): Read Storage Permission is Denied.");
                 return false;
             }
         }
         else {

         }

         return false;
     }
}
