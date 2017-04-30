package com.python.cat.drawermenu.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

/**
 * packageName: com.python.cat.drawermenu.base
 * Created on 2017/4/30.
 *
 * @author cat
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig().configShowBorders(false);
    }
}
