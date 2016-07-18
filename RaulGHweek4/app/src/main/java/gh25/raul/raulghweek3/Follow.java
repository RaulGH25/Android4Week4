package gh25.raul.raulghweek3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Raul on 17/07/2016.
 */
public class Follow extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Follow Endpoint
        String actionKey = "FOLLOW";
        String accion = intent.getAction();

        if(actionKey.equals(accion)){
            // Follow Endpoint
            Toast.makeText(context, "Follow", Toast.LENGTH_LONG).show();
        }

    }
}
