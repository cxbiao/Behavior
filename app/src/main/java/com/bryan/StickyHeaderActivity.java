package com.bryan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Cxb on 2016/5/16 10:14
 */
public class StickyHeaderActivity extends AppCompatActivity {

    private static final String TAG = "StickyHeaderActivity";
    RecyclerView recyclerView;

    List<String> data=new ArrayList<>();

    View stickyView;
    private int totalChange;

    //普通header的高度
    private int maxDist;

    AppBarLayout appbar;

    private int barHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        //200dp是普通header的高度
        maxDist=ScreenUtils.dip2px(this,200);
        stickyView=findViewById(R.id.sticky_header);
        recyclerView= (RecyclerView) findViewById(R.id.rv_behavior);
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setItemAnimator(new FadeInLeftAnimator());
        recyclerView.setAdapter(new StickyHeaderAdapter(this,data));
        appbar= (AppBarLayout) findViewById(R.id.appbar);
        appbar.post(new Runnable() {
            @Override
            public void run() {
                barHeight= appbar.getMeasuredHeight();
                stickyView.setTranslationY(maxDist+barHeight);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalChange+=dy;
                Log.e(TAG,"totalChange:"+totalChange);
                int tranY=Math.max(0,maxDist-totalChange);
                //移动距离超过maxDist，就定在0处  ,barHeight是appbar的高度，必须加上
                stickyView.setTranslationY(tranY+barHeight);

            }
        });

    }

    private void initData(){

        for(int i=0;i<50;i++){
            data.add("item"+i);
        }

    }
}
