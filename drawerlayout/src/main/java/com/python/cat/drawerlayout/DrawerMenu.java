package com.python.cat.drawerlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * packageName: com.python.cat.drawerlayout
 * Created on 2017/4/30.
 *
 * @author cat
 */

public class DrawerMenu extends ViewGroup {

    private View mLeftMenuLayout;
    private View mMainLayout;

    public DrawerMenu(Context context) {
        this(context, null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = this.getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("you can not fuck me! you must have two children !");
        }
        mLeftMenuLayout = getChildAt(0);
        mMainLayout = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // sorts : measure() -> layout() -> draw()
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    int measureSpec(int oldMeasureSpec) {
        int size = MeasureSpec.getSize(oldMeasureSpec);
        return MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int leftWidth = mLeftMenuLayout.getMeasuredWidth();
        int leftHeight = mLeftMenuLayout.getMeasuredHeight();
        Log.e("bbb", "leftWidth = " + leftWidth + " , leftHeight = " + leftHeight); // ok
        int menuLeft = -leftWidth;
        int menuTop = 0;
        int menuRight = 0;
        //noinspection UnnecessaryLocalVariable
        int menuBottom = leftHeight;
        mLeftMenuLayout.layout(menuLeft, menuTop, menuRight, menuBottom);

        int contentWidth = mMainLayout.getMeasuredWidth();
        int contentHeight = mMainLayout.getMeasuredHeight();
        int contentLeft = 0;
        int contentTop = 0;
        @SuppressWarnings("UnnecessaryLocalVariable") int contentRight = contentWidth;
        @SuppressWarnings("UnnecessaryLocalVariable") int contentBottom = contentHeight;
        mMainLayout.layout(contentLeft, contentTop, contentRight, contentBottom);
    }
}
