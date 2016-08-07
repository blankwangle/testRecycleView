package com.yitu8.testrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/7.
 */
public class MyRecycleView extends RecyclerView {
    public MyRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        Canvas

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if( myOnScroll != null ){
            myOnScroll.onCurrentY(t);
        }

    }

    private MyOnScroll myOnScroll;

    public void setMyOnScroll(MyOnScroll myOnScroll) {
        this.myOnScroll = myOnScroll;
    }

    public interface MyOnScroll{
        void onCurrentY( int y );
    }
}
