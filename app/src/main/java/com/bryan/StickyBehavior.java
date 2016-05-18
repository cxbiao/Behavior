package com.bryan;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by bryan on 2016-05-14.
 * <p/>
 * 研究失败
 */
public class StickyBehavior extends CoordinatorLayout.Behavior<View> {

    private static final String TAG = "StickyBehavior";

    private int totalChange;
    private int maxDist;


    public StickyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        maxDist = ScreenUtils.dip2px(context, 200);


    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "dyConsumed:" + dyConsumed);
        //dy>0 上拉
        //dy<0下拉
        totalChange += dyConsumed;
        Log.e(TAG, "totalChange:" + totalChange);
        int tranY = Math.max(0, maxDist - totalChange);
        //移动距离超过maxDist，就定在0处
       // child.setTranslationY(tranY);

    }

    //    //设置RecyclerView为依赖
//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        return dependency instanceof NestedScrollView;
//    }
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        float tranY=dependency.getTranslationY();
//        float scrolly=dependency.getY();
//        float y=dependency.getY();
//        Log.e(TAG,"tranY:"+tranY);
//        Log.e(TAG,"scrolly:"+y);
//        return true;
//    }
}
