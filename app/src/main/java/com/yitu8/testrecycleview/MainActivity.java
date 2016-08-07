package com.yitu8.testrecycleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yitu8.testrecycleview.dividers.GridItemDecoration;
import com.yitu8.testrecycleview.dividers.LinearItemDecoration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyRecycleView listview1, listview2, listview3, listview4, listview5, listview6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview1 = (MyRecycleView) findViewById(R.id.listview1);
        listview2 = (MyRecycleView) findViewById(R.id.listview2);
        listview3 = (MyRecycleView) findViewById(R.id.listview3);
        listview4 = (MyRecycleView) findViewById(R.id.listview4);
        listview5 = (MyRecycleView) findViewById(R.id.listview5);
        listview6 = (MyRecycleView) findViewById(R.id.listview6);

        findViewById(R.id.hlist).setOnClickListener(this);
        findViewById(R.id.vlist).setOnClickListener(this);
        findViewById(R.id.hgridview).setOnClickListener(this);
        findViewById(R.id.vgridview).setOnClickListener(this);
        findViewById(R.id.hstagg).setOnClickListener(this);
        findViewById(R.id.vstagg).setOnClickListener(this);

        listview1.setAdapter(new MyAdapter(this));
        listview2.setAdapter(new MyAdapter(this));
        listview3.setAdapter(new MyAdapter(this));
        listview4.setAdapter(new MyAdapter(this));
        listview5.setAdapter(new MyAdapter(this));
        listview6.setAdapter(new MyAdapter(this));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        listview1.setLayoutManager(linearLayoutManager1);
        listview1.addItemDecoration(new LinearItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(linearLayoutManager2.VERTICAL);
        listview2.setLayoutManager(linearLayoutManager2);
        listview2.addItemDecoration(new LinearItemDecoration(this, LinearLayoutManager.VERTICAL));


        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3);
        gridLayoutManager1.setOrientation(GridLayoutManager.HORIZONTAL);
        listview3.setLayoutManager(gridLayoutManager1);
        listview3.addItemDecoration(new GridItemDecoration(this));


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        listview4.setLayoutManager(gridLayoutManager2);
        listview4.addItemDecoration(new GridItemDecoration(this));

        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.HORIZONTAL);
        listview5.setLayoutManager(staggeredGridLayoutManager1);
        listview5.addItemDecoration(new GridItemDecoration(this));

        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.VERTICAL);
        listview6.setLayoutManager(staggeredGridLayoutManager2);
        listview6.addItemDecoration(new GridItemDecoration(this));

    }

    @Override
    public void onClick(View v) {
        listview1.setVisibility(View.GONE);
        listview2.setVisibility(View.GONE);
        listview3.setVisibility(View.GONE);
        listview4.setVisibility(View.GONE);
        listview5.setVisibility(View.GONE);
        listview6.setVisibility(View.GONE);
        switch (v.getId()){
            case R.id.hlist:
                listview1.setVisibility(View.VISIBLE);
                break;
            case R.id.vlist:
                listview2.setVisibility(View.VISIBLE);
                break;
            case R.id.hgridview:
                listview3.setVisibility(View.VISIBLE);
                break;
            case R.id.vgridview:
                listview4.setVisibility(View.VISIBLE);
                break;
            case R.id.hstagg:
                listview5.setVisibility(View.VISIBLE);
                break;
            case R.id.vstagg:
                listview6.setVisibility(View.VISIBLE);
                break;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private LayoutInflater inflater;

        public MyAdapter( Context context ){
            inflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_string, null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.test_tv.setText(position+"");
        }

        @Override
        public int getItemCount() {
            return 58;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView test_tv;

            public MyViewHolder(View itemView) {
                super(itemView);

                test_tv = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }
}
