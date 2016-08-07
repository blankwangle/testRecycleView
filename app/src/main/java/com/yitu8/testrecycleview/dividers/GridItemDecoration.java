package com.yitu8.testrecycleview.dividers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/8/7.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private final static int[] ATTRS = {
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    public GridItemDecoration(Context context){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if( c == null || parent == null ){
            return;
        }

        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount( RecyclerView parent ){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int spanCount = 0;

        if( layoutManager instanceof  GridLayoutManager ){//网格布局
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if( layoutManager instanceof StaggeredGridLayoutManager ) {//瀑布流
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }

        return spanCount;
    }

    private void drawHorizontal( Canvas c, RecyclerView parent ){

        final int chlidCount = parent.getChildCount();
        for( int i = 0; i < chlidCount; i++ ){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() + params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicHeight();
            final int top = child.getBottom() + params.rightMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical( Canvas c, RecyclerView parent ){
        final int chlidCount = parent.getChildCount();
        for( int i = 0; i < chlidCount; i++ ){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            final int top = child.getTop() + params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private boolean isLastRow( RecyclerView parent, int pos, int spanCount, int chlidCount ){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if( layoutManager == null ){
            return false;
        }

        if( layoutManager instanceof GridLayoutManager ){
            chlidCount = chlidCount - chlidCount % spanCount;
            if( pos >= chlidCount )
                return true;
        } else if( layoutManager instanceof StaggeredGridLayoutManager ){

        }

        return false;
    }

    private boolean isLastCloum( RecyclerView parent, int pos, int spanCount, int chlidCount ){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if( layoutManager == null ){
            return false;
        }

        if( layoutManager instanceof GridLayoutManager ){
            if( (pos + 1) %  spanCount == 0 ){
                return true;
            }
        } else if( layoutManager instanceof StaggeredGridLayoutManager ){

        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


    }
}
