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

/**
 * 网格布局的分割线
 * Created by Administrator on 2016/8/7.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private final static int[] ATTRS = {//可以在res文件中配置线的颜色和宽高
            android.R.attr.listDivider
    };

    private Drawable mDivider;//划线的类

    public GridItemDecoration(Context context){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);//获取到res分割线的配置
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if( c == null || parent == null ){
            return;
        }

        drawHorizontal(c, parent);//绘制横线
        drawVertical(c, parent);//绘制竖线
    }

    /**
     *分别获取网格布局和瀑布流的列数
     * */
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

    /**
     * 绘制横线
     * */
    private void drawHorizontal( Canvas c, RecyclerView parent ){

        final int chlidCount = parent.getChildCount();//获取子控件的个数
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

    /**
     * 绘制竖线
     * */
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

    /**
     * 最后一行或者最后一列是不进行划线的
     *
     * 当先是否绘制到最后一行
     * */
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
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if( orientation == StaggeredGridLayoutManager.VERTICAL ){
                if( orientation == StaggeredGridLayoutManager.VERTICAL ){
                    chlidCount = chlidCount - chlidCount % spanCount;
                    if( pos > chlidCount ) {
                        return true;
                    }
                } else {
                    if( (pos+1) % spanCount == 0 ){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
    * 当先是否绘制到最后一列
    * */
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
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if( orientation == StaggeredGridLayoutManager.VERTICAL ){
                if( (pos+1) % spanCount == 0 ){
                    return true;
                }
            } else {
                chlidCount = chlidCount - chlidCount % spanCount;
                if( pos > chlidCount ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置偏移量
     * */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        int spanCount = getSpanCount(parent);
        int childCount = parent.getChildCount();

        if( isLastCloum(parent, itemPosition, spanCount, childCount)){
            outRect.set( 0, 0, 0, mDivider.getIntrinsicHeight());
        } else if( isLastRow(parent, itemPosition, spanCount, childCount)){
            outRect.set( 0, 0, mDivider.getIntrinsicHeight(), 0);
        } else {
            outRect.set( 0, 0, mDivider.getIntrinsicHeight(), mDivider.getIntrinsicHeight());
        }
    }
}
