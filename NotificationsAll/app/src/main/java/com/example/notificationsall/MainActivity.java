package com.example.notificationsall;

//import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

import java.nio.channels.Channel;

import static com.example.notificationsall.App.CHANNEL_1_ID;
import static com.example.notificationsall.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    private MediaSessionCompat mediaSessionCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_messagee);

        mediaSessionCompat = new MediaSessionCompat(this, "tag");
    }

    public void sendOnChannel1(View v) {

        String title = editTextTitle.getText().toString();
        String messsage = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0 );

//        Intent broadCastIntent = new Intent(this, NotificationReceiver.class );
//        broadCastIntent.putExtra("toastMessage", messsage);
//        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


//        Bitmap largeIcon = BitmapFactory.decodeResource( getResources(), R.drawable.lotti );
        Bitmap picture = BitmapFactory.decodeResource( getResources(), R.drawable.lotti2 );


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID )
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(messsage)
                .setLargeIcon(picture)

                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))


                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
//                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String messsage = editTextMessage.getText().toString();

        Bitmap artwork  = BitmapFactory.decodeResource( getResources(), R.drawable.me_test  );

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID )
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(messsage)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_dislike, "Dislike", null)
                .addAction(R.drawable.ic_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_next, "Next", null)
                .addAction(R.drawable.ic_like, "Like", null)

                .setStyle( new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1,2,3)
                        .setMediaSession(mediaSessionCompat.getSessionToken())
                        )

                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)

                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2, notification);
    }
}