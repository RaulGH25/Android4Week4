package gh25.raul.raulghweek3;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Raul on 09/07/2016.
 */
public class NotificationService extends FirebaseMessagingService {

    // Constructor
    String textoNotificacion;
    String tituloNotificacion;

    public NotificationService(String textoNotificacion, String tituloNotificacion) {
        this.textoNotificacion = textoNotificacion;
        this.tituloNotificacion = tituloNotificacion;
    }

    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        String title = remoteMessage.getNotification().getTitle();
        String text = remoteMessage.getNotification().getBody();
        lanzarNotificacion(title, text);
    }



    public void lanzarNotificacion(String title, String text){

        int requestCode = NOTIFICATION_ID;

        Intent intentPerfil = new Intent(this, MainActivity.class);
        PendingIntent pendingIntentPerfil = PendingIntent.getActivity(this, requestCode, intentPerfil, PendingIntent.FLAG_ONE_SHOT);

        Intent intentFollow = new Intent();
        intentFollow.setAction("FOLLOW");
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this, requestCode, intentPerfil, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentUsuario = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntentUsuario = PendingIntent.getActivity(this, requestCode, intentUsuario, PendingIntent.FLAG_ONE_SHOT);


        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action perfilAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_petface, "string", pendingIntentPerfil)
                        .build();

        NotificationCompat.Action followAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_footprint, "string", pendingIntentFollow)
                        .build();

        NotificationCompat.Action usuarioAction =
                new NotificationCompat.Action.Builder(R.drawable.background, "string", pendingIntentUsuario)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.ic_footprint))
                .setGravity(Gravity.CENTER);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_footprint)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntentPerfil)
                .extend(wearableExtender.addAction(perfilAction))
                .extend(wearableExtender.addAction(followAction))
                .extend(wearableExtender.addAction(usuarioAction));

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(requestCode, notification.build());
    }


}
