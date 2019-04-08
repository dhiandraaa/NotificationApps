package com.agsatria.notificationapps;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICAITION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "agsatria channel";
    private static final int CAMERA_REQUEST_CODE = 7777;

    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mNotificationManager.notify(NOTIFICAITION_ID, mBuilder.build());
        }
    };

    public void sendNotification(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_my_location_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_location_black_24dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentIntent(pendingIntent)
                .setContentText(getResources().getString(R.string.content_text))
                .setAutoCancel(true);

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICAITION_ID, notification);
        }

        new Handler().postDelayed(runnable, 3000);
    }
}
