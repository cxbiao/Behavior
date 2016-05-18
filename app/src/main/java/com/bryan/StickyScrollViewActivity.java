package com.bryan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Author：Cxb on 2016/5/16 10:14
 */
public class StickyScrollViewActivity extends AppCompatActivity {

    private static final String TAG = "StickyHeaderActivity";
    NestedScrollView scrollView;


    View stickyView;

    //普通header的高度
    private int maxDist;

    AppBarLayout appbar;

    private int barHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_scrollview);

        //200dp是普通header的高度
        maxDist=ScreenUtils.dip2px(this,200);
        stickyView=findViewById(R.id.sticky_header);
        scrollView= (NestedScrollView) findViewById(R.id.rv_scrollview);

        appbar= (AppBarLayout) findViewById(R.id.appbar);
        appbar.post(new Runnable() {
            @Override
            public void run() {
                barHeight= appbar.getMeasuredHeight();
                stickyView.setTranslationY(maxDist+barHeight);
            }
        });


      scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
          @Override
          public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
              Log.e(TAG,"scrollY:"+scrollY);
              int tranY=Math.max(0,maxDist-scrollY);
              //移动距离超过maxDist，就定在0处  ,barHeight是appbar的高度，必须加上
              stickyView.setTranslationY(tranY+barHeight);
          }
      });



    }


}
