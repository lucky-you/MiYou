package com.zhowin.miyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.miyou.db.manager.DBManager;
import com.zhowin.miyou.im.IMClient;
import com.zhowin.miyou.im.manager.CacheManager;
import com.zhowin.miyou.im.service.RTCNotificationService;

import cn.rongcloud.rtc.api.RCRTCEngine;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.sight.SightExtensionModule;

/**
 * author : zho
 * date  ：2020/9/19
 * desc ：
 */
public class MiApplication extends BaseApplication {


    private int activeCount = 0;
    private int aliveCount = 0;
    private boolean isActive;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            return;
        }
        DBManager.initDao();
        //初始化IM
        IMClient.getInstance().init(getApplicationContext());
        registerLifecycleCallbacks();
//        connectIM();
    }

    private void registerLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                aliveCount++;
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                activeCount++;
                notifyChange();
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                activeCount--;
                notifyChange();
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                aliveCount--;
                if (aliveCount == 0) {
                    stopNotificationService();
                }
            }
        });
    }

    private void notifyChange() {
        if (activeCount > 0) {
            if (!isActive) {
                isActive = true;
                // AppForeground
                stopNotificationService();
            }
        } else {
            if (isActive) {
                isActive = false;
                // AppBackground
                if (RCRTCEngine.getInstance().getRoom() != null) {
                    startService(new Intent(this, RTCNotificationService.class));
                }
            }
        }
    }

    private void stopNotificationService() {
        if (RCRTCEngine.getInstance().getRoom() != null) {
            stopService(new Intent(MiApplication.this, RTCNotificationService.class));
        }
    }

    public void connectIM() {
        String token = CacheManager.getInstance().getToken();
        Log.e(IMClient.TAG, "token: " + token);
        IMClient.getInstance().connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e(IMClient.TAG, "IM连接成功");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                Log.e(IMClient.TAG, "IM连接失败，错误码为: " + connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {

            }
        });
    }

}
