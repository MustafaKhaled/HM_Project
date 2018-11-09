package com.example.mustafakhaled.hm_project.helper;

import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.init.Application;

import static android.support.constraint.Constraints.TAG;

/*
 * Created by Mustafa Khaled on 11/3/2018.
 *
 */ public class Util {
     static Context mContext;
     static DownloadManager downloadManager;

    public Util() {
        mContext = Application.customGetApplicationContext();
    }

    public static boolean isNetworkAvailable(){
         ConnectivityManager connectivityManager = (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo check  = connectivityManager.getActiveNetworkInfo();

         return check!=null && check.isConnected();
     }
     public static void copyToClipboard(String link){
         ClipboardManager clipboardManager = (ClipboardManager) Application.getApplicationContextInstance.getSystemService(Context.CLIPBOARD_SERVICE);
         ClipData clipData = ClipData.newPlainText("",link);
         clipboardManager.setPrimaryClip(clipData);
         ShowToastMessage.showToast(Application.customGetApplicationContext(),link+"  "+"copied");

     }

     //Download Manager helper function

    public static long downloadFile(String url,String filename){
        downloadManager = (DownloadManager) Application.getApplicationContextInstance.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(Application.customGetApplicationContext().getString(R.string.app_name));
        request.setDescription(Application.getApplicationContextInstance.getString(R.string.downloading_hint));
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES+"/Doors",filename);
        return downloadManager.enqueue(request);
    }

    public static void goToSettings(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", Application.customGetApplicationContext().getPackageName(), null);
        intent.setData(uri);
        Application.customGetApplicationContext().startActivity(intent);
    }

    public void showProgressDialog(String msg,Context context){
        ProgressBar progressBar = new ProgressBar(context);
        

    }

}
