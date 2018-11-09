package com.example.mustafakhaled.hm_project.fragments;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.GridViewAdapter;
import com.example.mustafakhaled.hm_project.helper.CheckPermissions;
import com.example.mustafakhaled.hm_project.helper.Util;
import com.example.mustafakhaled.hm_project.init.Application;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Download extends Fragment {
    public final String READ_STORAGE_PERMISSION= Manifest.permission.READ_EXTERNAL_STORAGE;
    private final int READ_PERMISSION_REQUEST_CODE=200;
    ArrayList<String> files_path = new ArrayList<>();
    GridViewAdapter gridViewAdapter ;
    GridView gridView;
    File [] list_files;
    private final String TAG = "Download";
    public Download() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_download,container,false);
        gridView = view.findViewById(R.id.download_grid);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(!CheckPermissions.isSinglePermissionGranted(getActivity(),READ_STORAGE_PERMISSION)){
                Log.d(TAG,"onCreate(): "+ "Permission Not Granted");
                requestPermissions(new String[]{READ_STORAGE_PERMISSION},READ_PERMISSION_REQUEST_CODE);
                Log.d(TAG,"Request Permission");
            }
            else{
                getImages();
                gridViewAdapter = new GridViewAdapter(files_path,getActivity());
                gridView.setAdapter(gridViewAdapter);
//                getActivity().recreate();
            }

        }
        else {
            getImages();
            gridViewAdapter = new GridViewAdapter(files_path,getActivity());
            gridView.setAdapter(gridViewAdapter);
            Log.d(TAG,"SDK is less than Marshamallow");
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case READ_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0){
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        Log.e(TAG,"onRequestPermissionsResult(): The User Allow Read Storage Permission");
                        getImages();
                        gridViewAdapter = new GridViewAdapter(files_path,getActivity());
                        gridView.setAdapter(gridViewAdapter);
                    }
                    else {
                        Log.d(TAG,"onRequestPermissionsResult(): The User Denied Read Storage Permission");
                        if(shouldShowRequestPermissionRationale(READ_STORAGE_PERMISSION)){
                            Log.e(TAG,"onRequestPermissionsResult(): The User chose Deny, so shouldShowRequestPermissionRationale is true");
                            showAlertDialog(getActivity().getString(R.string.deny_message),getActivity().getString(R.string.retry),getActivity().getString(R.string.cancel));
                        }
                        else {
                            Log.e(TAG,"onRequestPermissionsResult(): The User chose Deny and never ask again, so shouldShowRequestPermissionRationale is true");
                            showAlertDialog(getActivity().getString(R.string.never_message),getActivity().getString(R.string.retry),getActivity().getString(R.string.cancel));

                        }
                    }

                }

                break;


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getImages(){

        File file =new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"/doors");
        if(file.isDirectory()){
            list_files = file.listFiles();
            if(list_files.length!=0 && null!=list_files){

            for (int i = 0; i < list_files.length; i++)
            {
                files_path.add(list_files[i].getAbsolutePath());
            }
//            Log.d(TAG,"getImage(): "+ "ArrayList Value: "+ list_files[0]);
                Log.d(TAG,"getImage(): "+ "ArrayList Size: "+ list_files.length);
        }
        }
    }

    private void showAlertDialog(String message,String positive_btn,String negative_btn){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.custom_alert_dialog,null);
        builder.setView(dialogview);
        TextView msg_Text =  dialogview.findViewById(R.id.alert_msg);
        Button postive_btn = dialogview.findViewById(R.id.postitive_btn);
        Button negButton = dialogview.findViewById(R.id.negat_btn);
        msg_Text.setText(message);
        postive_btn.setText(positive_btn);
        negButton.setText(negative_btn);
        AlertDialog alertDialog = builder.create();
        postive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(positive_btn.equalsIgnoreCase(getActivity().getString(R.string.retry))){
                    requestPermissions(new String[]{READ_STORAGE_PERMISSION},READ_PERMISSION_REQUEST_CODE);
                    alertDialog.dismiss();
                }
                else if(positive_btn.equalsIgnoreCase(getActivity().getString(R.string.never_message))){

                    Util.goToSettings();
                    alertDialog.dismiss();

                }
            }
        });
        negButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
