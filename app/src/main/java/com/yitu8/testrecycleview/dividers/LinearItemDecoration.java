package com.yitu8.testrecycleview.dividers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/8/7.
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private int[] ATTRS = {
            android.R.attr.listDivider
    };

    public final static int HORIZONTAL = OrientationHelper.HORIZONTAL;
    private final static int VERTICAL = OrientationHelper.VERTICAL;

    private Drawable mDivier;
    private int orientation = HORIZONTAL;

    public LinearItemDecoration( Context context, int orientation ){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivier = a.getDrawable(0);
        this.orientation = orientation;
        a.recycle();
    }

    public void setOrientation( int orientation ){
        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if( orientation == HORIZONTAL ){
            drawVertical(c, parent);
        } else if( VERTICAL == orientation ){
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if( orientation == HORIZONTAL ){
            outRect.set( 0, 0, 0, mDivier.getIntrinsicHeight());
        } else if( VERTICAL == orientation ){
            outRect.set( 0, 0, mDivier.getIntrinsicHeight(), 0);
        }
    }

    private void drawHorizontal( Canvas canvas, RecyclerView parent  ){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int chlidCount = parent.getChildCount();
        for( int i = 0; i < chlidCount; i++ ){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivier.getIntrinsicHeight();

            mDivier.setBounds( left, top, right, bottom);
            mDivier.draw(canvas);
        }

    }

    private void drawVertical( Canvas canvas, RecyclerView parent ){
        final int top = parent.getPaddingTop();

        final int chlidCount = parent.getChildCount();
        for( int i = 0; i < chlidCount; i++ ){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivier.getIntrinsicHeight();
            final int bottom = child.getHeight() - params.bottomMargin;

            mDivier.setBounds( left, top, right, bottom);
            mDivier.draw(canvas);
        }
    }
}
