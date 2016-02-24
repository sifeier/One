package com.one.util;

import com.demo.common.logger.Log;
import com.one.LauchActivity;
import com.one.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by buke on 15/9/23.
 */
public class PollingService extends Service {

    public static final String ACTION = "com.one.util.PollingService";

    private Notification mNotification;

    private NotificationManager mManager;


    @Override
    public void onCreate() {
        super.onCreate();
        initNotifyManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new PollingThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(ACTION, "onDestroy");
    }


    private void initNotifyManager() {
        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification = new Notification();
        mNotification.icon = R.drawable.ic_launcher;
        mNotification.tickerText = "New Message";
        mNotification.defaults |= Notification.DEFAULT_SOUND;
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
    }

    private void showNotification() {
        mNotification.when = System.currentTimeMillis();

        Intent intent = new Intent(this, LauchActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        mNotification.setLatestEventInfo(this, getResources().getString(R.string.app_name),
//                "Receive new message", pendingIntent);


        mManager.notify(0, mNotification);
    }


    int count = 0;

    class PollingThread extends Thread {

        @Override
        public void run() {
            Log.d(ACTION, "start polling...");
            count ++;
            if(count % 5 == 0) {
                showNotification();
                Log.e(ACTION, "new message");
            }
        }

    }
}
