package com.example.hp.apti5;

        import android.app.NotificationManager;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.app.NotificationManagerCompat;

        import com.google.firebase.messaging.FirebaseMessagingService;
        import com.google.firebase.messaging.RemoteMessage;

public class myMessageService extends FirebaseMessagingService {
    //when a firebase sends a message this class will handle the notification
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotification")
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.nnt)
                .setContentText(message);

        NotificationManagerCompat manager=NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());

    }
}
