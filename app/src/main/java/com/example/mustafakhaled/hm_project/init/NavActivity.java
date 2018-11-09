package com.example.mustafakhaled.hm_project.init;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.NavigationDrawerAdapter;
import com.example.mustafakhaled.hm_project.helper.LocaleHelper;
import com.example.mustafakhaled.hm_project.model.NavigationDrawerModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NavActivity extends BaseActivity
         {
    String [] drawerItemsText;
    View addedView;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    String activityTitle;
    Toolbar toolbar;
    ListView drawer_list;
    ArrayList<NavigationDrawerModel> navDrawerList= new ArrayList<>();
    NavigationDrawerModel [] navigationDrawerModel;
    public void initiate(String title, int res){
    frameLayout = (FrameLayout) findViewById(R.id.content_view);
    layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    activityTitle=title;
    TextView titlee = (TextView) toolbar.findViewById(R.id.toolbar_title);
    titlee.setText(activityTitle);
    addedView = layoutInflater.inflate(res,null,false);
    frameLayout.addView(addedView);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_list = (ListView) findViewById(R.id.drawer_list);
        setSupportActionBar(toolbar);
        drawerItemsText = getResources().getStringArray(R.array.navigation_items);
        navigationDrawerModel = new NavigationDrawerModel[5];



        navigationDrawerModel[0]= new NavigationDrawerModel(drawerItemsText[0],"2131",R.drawable.ic_menu_gallery);
        navigationDrawerModel[1]= new NavigationDrawerModel(drawerItemsText[1],"",R.drawable.ic_menu_slideshow);
        navigationDrawerModel[2]= new NavigationDrawerModel(drawerItemsText[2],"",R.drawable.ic_menu_share);
        navigationDrawerModel[3]= new NavigationDrawerModel(drawerItemsText[3],"",R.drawable.ic_menu_send);
        navigationDrawerModel[4]= new NavigationDrawerModel(drawerItemsText[4],"",R.drawable.ic_menu_manage);



        navDrawerList.add(navigationDrawerModel[0]);
        navDrawerList.add(navigationDrawerModel[1]);
        navDrawerList.add(navigationDrawerModel[2]);
        navDrawerList.add(navigationDrawerModel[3]);
        navDrawerList.add(navigationDrawerModel[4]);



//        navigationDrawerModel[1].setIcon(R.drawable.ic_menu_gallery);
//        navigationDrawerModel[2].setItem_List(drawerItemsText[1]);
//        navDrawerList.add(navigationDrawerModel[1]);

//
//        navigationDrawerModel[2].setIcon(R.drawable.ic_menu_send);
//        navigationDrawerModel[2].setItem_List(drawerItemsText[2]);
//        navDrawerList.add(navigationDrawerModel[2]);


//        NavigationDrawerModel navigationDrawerModel3 = new NavigationDrawerModel();
//        navigationDrawerModel.setIcon(R.drawable.ic_menu_slideshow);
//        navigationDrawerModel.setItem_List(drawerItemsText[3]);
//        navDrawerList.add(navigationDrawerModel);
//
//
//
//        NavigationDrawerModel navigationDrawerModel4 = new NavigationDrawerModel();
//        navigationDrawerModel.setIcon(R.drawable.ic_menu_share);
//        navigationDrawerModel.setItem_List(drawerItemsText[4]);
//        navDrawerList.add(navigationDrawerModel);



        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationDrawerAdapter navigationDrawerAdapter = new NavigationDrawerAdapter(this,R.layout.navigation_drawer_list_item,navDrawerList);
        drawer_list.setAdapter(navigationDrawerAdapter);



//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        change_btn = (Button) findViewById(R.id.change_lang);
//        Log.e("MainActivity","Current Lang: "+ LocaleHelper.getLanguage(NavActivity.this));
//        change_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(LocaleHelper.getLanguage(NavActivity.this).equalsIgnoreCase("en")){
//                    LocaleHelper.setLocale(NavActivity.this,"ar");
//                }
//                else{
//                    LocaleHelper.setLocale(NavActivity.this,"en");
//                }
//
//                Intent intent = new Intent(NavActivity.this,NavActivity.class);
//                finish();
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
