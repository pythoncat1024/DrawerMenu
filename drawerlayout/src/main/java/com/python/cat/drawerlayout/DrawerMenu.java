package com.python.cat.drawerlayout;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * packageName: com.python.cat.drawerlayout
 * Created on 2017/4/30.
 *
 * @author cat
 */

public class DrawerMenu extends ViewGroup {

    private static final int OPEN_LEFT = 0;
    private static final int OPEN_MAIN = 1;
    private Scroller mScroller;

    @SuppressWarnings("WeakerAccess")
    @IntDef({OPEN_LEFT, OPEN_MAIN})
    public @interface DrawerState {
    }

    @DrawerState
    private int currentState = OPEN_MAIN;

    private View mLeftMenuLayout;
    private View mMainLayout;
    private int downX;

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
        mScroller = new Scroller(getContext());
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int leftWidth = mLeftMenuLayout.getMeasuredWidth();
        Log.v("xx", "leftWidth = " + leftWidth);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = Math.round(event.getX());
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = Math.round(event.getX());
                int dx = downX - moveX; // move screen,not the layout !

                float scrollX = getScrollX();
                Log.d("aa", "dx = " + dx + " scrollX = " + scrollX
                        + " | scrollX + dx = " + (dx + scrollX));
                if (scrollX + dx < -leftWidth) {
                    scrollTo(-mLeftMenuLayout.getMeasuredWidth(), 0);
                } else if (scrollX + dx > 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(dx, 0);
                }
                scrollBy(dx, 0);
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (getScrollX() < -leftWidth / 2) {
                    // open left
                    currentState = OPEN_LEFT;
                } else {
                    // open main
                    currentState = OPEN_MAIN;
                }
                switchState();
                break;
        }
        return true;
    }

    private void switchState() {
        switch (currentState) {
            case OPEN_LEFT:
//                        scrollTo(-mLeftMenuLayout.getMeasuredWidth(), 0);
                openLeft();
                break;
            case OPEN_MAIN:
            default:
//                        scrollTo(0, 0);
                openMain();
                break;
        }
    }

    private void openLeft() {
        // startX = getScrollX();
        // endX = -mLeftMenuLayout.getMeasuredWidth();
        // offsetX = endX - startX;
        int offsetX = -mLeftMenuLayout.getMeasuredWidth() - getScrollX();
        int duration = Math.abs(offsetX) * 5;
        if (duration > 1000) {
            duration = 1000;
        }
        mScroller.startScroll(getScrollX(), getScrollY(),
                offsetX, 0, duration);
        invalidate();
    }

    private void openMain() {
        int offsetX = 0 - getScrollX();
        int duration = Math.abs(offsetX) * 5;
        if (duration > 1000) {
            duration = 1000;
        }
        mScroller.startScroll(getScrollX(), getScrollY(),
                offsetX, 0, duration);
        invalidate();
    }

    @DrawerState
    public int changeState() {
        switch (currentState) {
            case OPEN_LEFT:
                // change 2 open main
                openMain();
                currentState = OPEN_MAIN;
                break;
            case OPEN_MAIN:
            default:
                openLeft();
                currentState = OPEN_LEFT;
                break;
        }
        return currentState;
    }

    @SuppressWarnings("unused")
    @DrawerState
    public int getCurrentState() {
        return this.currentState;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            // mScrollX = mScroller.getCurrX();
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

}
