package com.example.mustafakhaled.hm_project.helper;

/*
 * Created by Mustafa Khaled on 10/27/2018.
 *
 */ import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.activities.MainActivity;
import com.example.mustafakhaled.hm_project.activities.ShowProducts;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public abstract class RecyclerViewPagination extends RecyclerView.OnScrollListener {
    private final String TAG = "RecyclerViewPagination";
    LinearLayoutManager mlinearLayoutManager;
    Context mContext;
    public RecyclerViewPagination(LinearLayoutManager layoutManager, Context context) {
        this.mlinearLayoutManager = layoutManager;
        this.mContext=context;
        Log.e(TAG,"Default Constructor: "+ "Parameters received");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = mlinearLayoutManager.getChildCount();
        int totalItemCount = mlinearLayoutManager.getItemCount();
        int firstVisibleItemPosition = mlinearLayoutManager.findFirstVisibleItemPosition();

        if(!isLoading() && !isLastPage()){
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {

                loadMoreItems();
//                LayoutInflater vi = (LayoutInflater) mContext
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = vi.inflate(R.layout.activity_main,null,false);
//                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
                ProgressBar progressBar = ((ShowProducts) mContext).findViewById(R.id.progress);
                progressBar.setVisibility(View.VISIBLE);
//                View view = ((MainActivity) mContext).findViewById(R.id.view);
//                view.setVisibility(View.VISIBLE);
                Log.e(TAG,"onScroll(): Reach the end of List");
            }

        }
        else {
            Log.e(TAG,"onScroll(): isLoading is false");

        }

    }

    protected abstract void loadMoreItems();


    public abstract boolean isLoading();

    public abstract boolean isLastPage();

    public abstract int getTotalPageCount();
}
