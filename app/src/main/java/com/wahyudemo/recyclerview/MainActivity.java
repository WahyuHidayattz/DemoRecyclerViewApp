package com.wahyudemo.recyclerview;

import android.content.res.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import java.util.*;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private FloatingActionButton mFab;
    private int mViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFab = findViewById(R.id.fab);

        mViewType = 0;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mFab.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    if (mViewType == 0) {
                        mViewType = 1;
                        mFab.setImageResource(R.drawable.ic_toolbar_list);
                        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    } else {
                        mViewType = 0;
                        mFab.setImageResource(R.drawable.ic_toolbar_gird);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                }
            });

        mRecyclerView.addOnScrollListener(new 
            RecyclerView.OnScrollListener(){
                @Override
                public void onScrolled(RecyclerView mRecycler, int dx, int dy)
                {
                    if (dy > 0) {
                        mFab.hide();
                    } else if (dy < 0) {
                        mFab.show();
                    }
                }
			});

        new AsyncLogin().execute();
    }

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params)
        {
            return null;
        }
        @Override
        protected void onPostExecute(String result)
        {
            List<Data> data=new ArrayList<>();
            String[] titles;
            int[] images;

            titles = getResources().getStringArray(R.array.titles);
            TypedArray a = getResources().obtainTypedArray(R.array.images);
            images = new int[a.length()];

            try {
                for (int i = 0; i < titles.length; i++) {
                    Data itemData = new Data();
                    images[i] = a.getResourceId(i, 0);
                    itemData.mTitle = titles[i];
                    itemData.mImage = images[i];
                    data.add(itemData);
                }
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mAdapter = new Adapter(MainActivity.this, data);
                mRecyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
            }
        }
    }
}
