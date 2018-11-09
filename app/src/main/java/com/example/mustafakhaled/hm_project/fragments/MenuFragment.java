package com.example.mustafakhaled.hm_project.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.mustafakhaled.hm_project.Interfaces.MainCategoriesInterface;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.activities.MainActivity;
import com.example.mustafakhaled.hm_project.activities.SubCategoryActivity;
import com.example.mustafakhaled.hm_project.adapter.Category_RecyclerView_Adapter;
import com.example.mustafakhaled.hm_project.adapter.MainCategoriesAdapter;
import com.example.mustafakhaled.hm_project.helper.Util;
import com.example.mustafakhaled.hm_project.model.MainCategoriesModel;
import com.example.mustafakhaled.hm_project.model.SubCategoryItem;
import com.example.mustafakhaled.hm_project.retrofit.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private final String TAG = "MenuFragment";
    View view;
    boolean category_or_sub =false;
    MainCategoriesModel mainCategoriesModel;
    ArrayList<String> headers;
    RecyclerView categories;
    Category_RecyclerView_Adapter category_recyclerView_adapter;
    LinearLayoutManager layoutManager;
    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_menu, container, false);
        headers = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        categories = view.findViewById(R.id.main_cat_recycler_view);
//        Util.isNetworkAvailable(getActivity());
        Call<MainCategoriesModel> apiClient;
        final MainCategoriesInterface mainCategoriesInterface = RetrofitClient.retrofitClient().create(MainCategoriesInterface.class);
        apiClient = mainCategoriesInterface.getAllCategories();
        apiClient.enqueue(new Callback<MainCategoriesModel>() {
            @Override
            public void onResponse(Call<MainCategoriesModel> call, Response<MainCategoriesModel> response) {

                if(response.isSuccessful()){
                    Log.e(TAG,"onResponse(): "+ response.body().toString());
                    mainCategoriesModel = response.body();
                    for (int i=0;i<mainCategoriesModel.getCount();i++){
                        headers.add(mainCategoriesModel.getCategoryItemModels().get(i).getName());
                    }
                    categories.setLayoutManager(layoutManager);
                    categories.setAdapter(new Category_RecyclerView_Adapter(getActivity(),headers, new Category_RecyclerView_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String item,int position) {
                            ArrayList<SubCategoryItem> subCat = mainCategoriesModel.getCategoryItemModels().get(position).getSubCategoryModels();
                            Intent intent = new Intent(getContext(), SubCategoryActivity.class);
                            intent.putExtra("categoryName",headers.get(position));
                            intent.putParcelableArrayListExtra("subCategoryDetail",subCat);
                            startActivity(intent);

                        }
                    }));
//                    expandableLestView.setAdapter(mcAdapter);
//                    expandableListView.setGroupIndicator(null);
//                    expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//                        @Override
//                        public boolean onChildClick(ExpandableListView expandableListView, View view, int listPosition, int expandedPosition, long id) {
//                            String test = mainCategoriesModel.getCategoryItemModels().get(listPosition).getSubCategoryModels().get(expandedPosition).getName();
//                            Toast.makeText(getActivity(),test,Toast.LENGTH_LONG).show();
//                            return true;
//                        }
//                    });

                }


            }

            @Override
            public void onFailure(Call<MainCategoriesModel> call, Throwable t) {

            }
        });
        super.onViewCreated(view, savedInstanceState);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
    private void setRecyclerViewDivider(LinearLayoutManager layoutManager){

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        categories.addItemDecoration(dividerItemDecoration);
    }
}
