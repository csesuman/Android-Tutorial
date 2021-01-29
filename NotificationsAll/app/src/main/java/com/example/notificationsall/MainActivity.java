package com.example.notificationsall;

//import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.PendingIntent;
//import androidx.co .RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.notificationsall.App.CHANNEL_1_ID;
import static com.example.notificationsall.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    static List<Message>  MESSAGES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_messagee);

        MESSAGES.add(new Message("Good Morning", "jim")) ;
        MESSAGES.add(new Message("Hello", null)) ;
        MESSAGES.add(new Message("Hi!", "Jenny")) ;
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void sendOnChannel1(View v) {
        sendOnChannel1Notification(this);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public static void sendOnChannel1Notification(Context context) {

        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, activityIntent, 0 );

//        Intent broadCastIntent = new Intent(this, NotificationReceiver.class );
//        broadCastIntent.putExtra("toastMessage", messsage);
//        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer...")
                .build();

        Intent replyIntent = new Intent( context, DirectReplyReceiver.class);
        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(context, 0, replyIntent, 0);

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_reply,
                "Reply",
                replyPendingIntent
        ).addRemoteInput(remoteInput).build();

        Person person =  (new Person.Builder())
                .setName("Me")
                .build();

        NotificationCompat.MessagingStyle messagingStyle =
                new NotificationCompat.MessagingStyle( person );

        messagingStyle.setConversationTitle("Group Chat");

        for (Message chatMessage : MESSAGES) {

            Person temp = null ;
            if( null != chatMessage.getSender()   ) {
                Log.i("Suman","always Come >>");
                temp = (new Person.Builder()).setName(chatMessage.getSender()).build();
            }


            NotificationCompat.MessagingStyle.Message notificationMessage
                    = new NotificationCompat.MessagingStyle.Message(

                    chatMessage.getText(),
                    chatMessage.getTimestamp(),
//                    chatMessage.getSender()
                    temp
            );
            messagingStyle.addMessage(notificationMessage);
        }


        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID )
                .setSmallIcon(R.drawable.ic_one)

                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setColor(Color.BLUE)

                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
//                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View v) {

        final int progressMax = 100;

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_2_ID )
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setOnlyAlertOnce(true )
                .setProgress(progressMax, 0, false)

                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        notificationManager.notify(2, notification.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for(int progress =  0; progress <= progressMax; progress += 20) {
//                    notification.setProgress(progressMax, progress, false);
//                    notificationManager.notify(2, notification.build());
                    SystemClock.sleep(1000);
                }

                notification.setContentText("Download finished")
                        .setProgress(0,0,false)
                        .setOngoing(false);

                notificationManager.notify(2, notification.build());
            }
        }).start();
    }
}