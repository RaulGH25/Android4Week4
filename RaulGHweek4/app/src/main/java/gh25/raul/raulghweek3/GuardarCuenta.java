package gh25.raul.raulghweek3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Usuario;
import gh25.raul.raulghweek3.restApi.ConstantsRestApi;
import gh25.raul.raulghweek3.restApi.EndpointsApi;
import gh25.raul.raulghweek3.restApi.adapter.RestApiAdapter;
import gh25.raul.raulghweek3.restApi.model.MascotaResponse;
import gh25.raul.raulghweek3.restApi.model.ServerUsuarioResponse;
import gh25.raul.raulghweek3.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Raul on 24/06/2016.
 */
public class GuardarCuenta extends AppCompatActivity {

    private Toolbar toolbar;

    private Button button;

    private TextView textView;

    private ArrayList<Usuario> datasetUsuarios;
    private String usuarioID;
    private String usuarioName;
    private String mascotaName;
    private String profilePictureUrl;
    private String firebaseID;
    private String deviceID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_cuenta);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_footprint);


        textView = (TextView) findViewById(R.id.editTextNameCuenta);

        button = (Button) findViewById(R.id.buttonGuardarCuentaID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioName = textView.getText().toString();
                obtenerUsuario();
            }
        });


    }




    public void obtenerUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUserID = restApiAdapter.construyeGsonDeserializadorUsuarioID();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUserID);

        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getUserID(usuarioName, ConstantsRestApi.ACCESS_TOKEN);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                datasetUsuarios = usuarioResponse.getUsuarios();
                devolverUsuarioID();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.i("----Error---", t.toString());
            }
        });
    }



    public void registrarUsuarioIDs(){
        String token = FirebaseInstanceId.getInstance().getToken(); // Obtiene el id del dispositivo
        // Registrar información en firebase y obtener la respuesta
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiServer();
        Call<ServerUsuarioResponse> serverUsuarioResponseCall = endpointsApi.registrarTokenID(token , usuarioID);
        // usuarioID es la ID de usuario de instagram
        // token es la id del dispositivo

        serverUsuarioResponseCall.enqueue(new Callback<ServerUsuarioResponse>() {
            @Override
            public void onResponse(Call<ServerUsuarioResponse> call, Response<ServerUsuarioResponse> response) {
                ServerUsuarioResponse serverUsuarioResponse = response.body();
                firebaseID = serverUsuarioResponse.getId_firebase();
                deviceID = serverUsuarioResponse.getId_dispositivo();

                Log.i("-- DISP -- ", serverUsuarioResponse.getId_dispositivo());
                Log.i("-- INST -- ", serverUsuarioResponse.getId_usuario_instagram());
                Log.i("-- FIRE -- ", serverUsuarioResponse.getId_firebase());

                // Save the user informaton in the shared preferences
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getResources().getString(R.string.strSharedVariableUserId), usuarioID);
                editor.putString(getResources().getString(R.string.strSharedVariableUserMascotaName), mascotaName);
                editor.putString(getResources().getString(R.string.strSharedVariableUserProfilePicture), profilePictureUrl);
                editor.putString(getResources().getString(R.string.strSharedVariableDeviceId), deviceID);
                editor.putString(getResources().getString(R.string.strSharedVariableFirebaseId), firebaseID);
                editor.commit();
            }

            @Override
            public void onFailure(Call<ServerUsuarioResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error registrar el usuario", Toast.LENGTH_LONG).show();
            }
        });
    }



    public void devolverUsuarioID(){
        int numeroUsuarios = datasetUsuarios.size();
        if (numeroUsuarios == 0) {
            Toast.makeText(getApplicationContext(), "No hay usuarios con ese nombre", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Usuario guardado", Toast.LENGTH_LONG).show();
            usuarioID = datasetUsuarios.get(0).getId();
            mascotaName = datasetUsuarios.get(0).getName();
            profilePictureUrl = datasetUsuarios.get(0).getProfilePicture();

            registrarUsuarioIDs();
        }
    }


}
