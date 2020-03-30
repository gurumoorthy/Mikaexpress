/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.gurumoorthy.mikaexpress.domain.api;

import android.content.Context;
import android.os.AsyncTask;

import android.view.View;

import com.gurumoorthy.mikaexpress.R;
import com.gurumoorthy.mikaexpress.domain.mock.FakeWebServer;
import com.gurumoorthy.mikaexpress.util.AppConstants;
import com.gurumoorthy.mikaexpress.util.Utils;
import com.gurumoorthy.mikaexpress.view.activities.ECartHomeActivity;
import com.gurumoorthy.mikaexpress.view.adapter.CategoryListAdapter;
import com.gurumoorthy.mikaexpress.view.adapter.ProductListAdapter;
import com.gurumoorthy.mikaexpress.view.fragment.ProductOverviewFragment;

import androidx.recyclerview.widget.RecyclerView;


/**
 * The Class ImageLoaderTask.
 */
public class ProductCategoryLoaderTask extends AsyncTask<String, Void, Void> {

    private static final int NUMBER_OF_COLUMNS = 2;
    private Context context;
    private RecyclerView recyclerView;

    public ProductCategoryLoaderTask(RecyclerView listView, Context context) {

        this.recyclerView = listView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.VISIBLE);

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (null != ((ECartHomeActivity) context).getProgressBar())
            ((ECartHomeActivity) context).getProgressBar().setVisibility(
                    View.GONE);

        if (recyclerView != null) {
            CategoryListAdapter simpleRecyclerAdapter = new CategoryListAdapter(
                    context);

            recyclerView.setAdapter(simpleRecyclerAdapter);

            simpleRecyclerAdapter
                    .SetOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {

                            AppConstants.CURRENT_CATEGORY = position;

                            Utils.switchFragmentWithAnimation(
                                    R.id.frag_container,
                                    new ProductOverviewFragment(),
                                    ((ECartHomeActivity) context), null,
                                    Utils.AnimationType.SLIDE_LEFT);

                        }
                    });
        }

    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FakeWebServer.getFakeWebServer().addCategory();

        return null;
    }

}