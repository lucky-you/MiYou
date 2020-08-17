package com.zhowin.base_library.base;

import androidx.multidex.MultiDexApplication;

import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ExecutorsUtils;
import com.zhowin.base_library.utils.SPUtils;


public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;
    private static ExecutorsUtils mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(this);
        application = this;
        mAppExecutors = new ExecutorsUtils();
    }

    public ActivityManager getActivityManager() {
        return ActivityManager.getAppInstance();
    }

    public static BaseApplication getInstance() {
        return application;
    }

    public static ExecutorsUtils getExecutorsUtils() {
        return mAppExecutors;
    }


}
