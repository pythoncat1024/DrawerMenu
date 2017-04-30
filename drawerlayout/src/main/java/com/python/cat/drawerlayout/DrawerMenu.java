package com.python.cat.drawerlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * packageName: com.python.cat.drawerlayout
 * Created on 2017/4/30.
 *
 * @author cat
 */

public class DrawerMenu extends ViewGroup {
    public DrawerMenu(Context context) {
        this(context,null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
