package com.zhenghaikj.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zhenghaikj.shop.R;

public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper = new NestedScrollingParentHelper(this);
    private View mTop;
    private View mNav;
    private ViewPager mViewPager;
    private int mTopViewHeight;
    private OverScroller mScroller;

    private boolean showTop = true;
    public OnLayoutScrollListener scroll = null;

    /**
     * 暴露接口供外部操作
     * */
    public interface OnLayoutScrollListener {
        abstract void isTopShow(boolean isTopShow);
    }



    public void setOnListener(Fragment fragment) {
        scroll = (OnLayoutScrollListener) fragment;
    }

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.banner);
        mNav = findViewById(R.id.tab_receiving_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTopViewHeight = mTop.getMeasuredHeight();
        //上面测量的结果是viewPager的高度只能占满父控件的剩余空间
        //重新设置viewPager的高度
        ViewGroup.LayoutParams layoutParams = mViewPager.getLayoutParams();
        layoutParams.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        mViewPager.setLayoutParams(layoutParams);
    }

    @Override
    public void scrollTo(int x, int y) {
        //限制滚动范围
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }

        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    //实现NestedScrollParent接口-------------------------------------------------------------------------

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        parentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);
        if (getScrollY() > 0)
            scroll.isTopShow(false);//头部view处于隐藏状态
        else scroll.isTopShow(true);//头部view处于显示状态
        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }

    }


    //boolean consumed:子view是否消耗了fling
    //返回值：自己是否消耗了fling。可见，要消耗只能全部消耗
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e("onNestedFling", "called");
        return false;
    }

    //返回值：自己是否消耗了fling。可见，要消耗只能全部消耗
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.e("onNestedPreFling", "called");
        if (getScrollY() < mTopViewHeight) {
            fling((int) velocityY);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
