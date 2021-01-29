package com.example.notificationsall;

//import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
//import androidx.co .RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
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

        if(!notificationManager.areNotificationsEnabled()) {
            openNotificationSettings();
            return;
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                isChannelBlocked(CHANNEL_1_ID)) {

            openChannelSettings(CHANNEL_1_ID);
        }

        sendOnChannel1Notification(this);
    }

    private void openNotificationSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_BUBBLE_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    @RequiresApi(26)
    private boolean isChannelBlocked(String channelId) {
        NotificationManager manager = getSystemService(NotificationManager.class);
        NotificationChannel channel = manager.getNotificationChannel(channelId);

        return channel != null &&

                channel.getImportance() == NotificationManager.IMPORTANCE_NONE;
    }

    @RequiresApi(26)
    private  void openChannelSettings(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        startActivity(intent);
    }

    //    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public static void sendOnChannel1Notification(Context context) {

        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, activityIntent, 0 );

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

        String title1 = "Title 1";
        String message1 = "Message 1";

        String title2 = "Title 2";
        String message2 = "Message 2";

        Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID )
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title1)
                .setContentText(message1)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("Example Group")
                .build();

        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL_2_ID )
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title2)
                .setContentText(message2)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("Example Group")
                .build();

        Notification notification3 = new NotificationCompat.Builder(this, CHANNEL_2_ID )
                .setSmallIcon(R.drawable.ic_reply)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(title2 + " " + message2)
                        .addLine(title1 + " " + message2)
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("user@example.com")
                )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("Example Group")
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
                .setGroupSummary(true)
                .build();

        SystemClock.sleep(2000);

        notificationManager.notify(2, notification1);
        SystemClock.sleep(2000);
        notificationManager.notify(3, notification2);
        SystemClock.sleep(2000);
        notificationManager.notify(4, notification3);
    }
}