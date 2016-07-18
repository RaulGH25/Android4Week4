package gh25.raul.raulghweek3;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Raul on 14/07/2016.
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // enviarTokenRegistro(refreshedToken); // Comentar si no se quiere recibir el token desde el principio.
    }


    private void enviarTokenRegistro(String token){
        Log.i(TAG, token);
    }


}
