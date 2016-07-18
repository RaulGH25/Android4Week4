package gh25.raul.raulghweek3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Mascota;
import gh25.raul.raulghweek3.restApi.ConstantsRestApi;
import gh25.raul.raulghweek3.restApi.EndpointsApi;
import gh25.raul.raulghweek3.restApi.IRVAdapterPerfilMascotaPresenter;
import gh25.raul.raulghweek3.restApi.adapter.RestApiAdapter;
import gh25.raul.raulghweek3.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Raul on 13/05/2016.
 */
public class PerfilFragment extends Fragment  implements IRVAdapterPerfilMascotaPresenter {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CircularImageView circularImageView;
    private TextView titleTextView;

    private String userIdString;
    private String mascotaName;
    private String profilePictureUrl;
    private String deviceIdString;
    private String firebaseIdString;

    private ArrayList<Mascota> datasetNuestraMascota;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);



        // Evaluate if there is any user saved  SHARED PREFERENCES.
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        // read shared preference
        userIdString = sharedPref.getString(getResources().getString(R.string.strSharedVariableUserId), "noHayUsuarioGuardado");
        mascotaName = sharedPref.getString(getResources().getString(R.string.strSharedVariableUserMascotaName), ConstantsRestApi.USER_NAME);
        profilePictureUrl = sharedPref.getString(getResources().getString(R.string.strSharedVariableUserProfilePicture), ConstantsRestApi.DEFAULT_PICTURE_URL);

        deviceIdString = sharedPref.getString(getResources().getString(R.string.strSharedVariableDeviceId), "noHayUsuarioGuardado");
        firebaseIdString = sharedPref.getString(getResources().getString(R.string.strSharedVariableFirebaseId), "noHayUsuarioGuardado");

        // Creates a default user if there is no one saved
        if(userIdString == "noHayUsuarioGuardado"){
            userIdString = ConstantsRestApi.USER_ID;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getResources().getString(R.string.strSharedVariableUserId), userIdString);
            editor.commit();
        }



        // Circular Image Configuration
        circularImageView = (CircularImageView) v.findViewById(R.id.circularImageView);
        circularImageView.setBorderWidth(getResources().getDimension(R.dimen.perfilCircleImageBorderSize) * (int) getResources().getDisplayMetrics().density);
        circularImageView.setShadowRadius(getResources().getDimension(R.dimen.perfilCircleImageShadowSize));
        circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        circularImageView.setShadowColor(getResources().getColor(R.color.colorPrimaryLight));

        Picasso.with(this.getActivity())
                .load(profilePictureUrl)
                .placeholder(R.drawable.huesoblanco)
                .into(circularImageView);


        // Title of the Circular Image
        titleTextView = (TextView) v.findViewById(R.id.textViewOurPetName);
        titleTextView.setText(mascotaName);


        ArrayList<Mascota> datasetNuestraMascota = new ArrayList<>();
        obtenerDataReciente();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView_ListOurPet);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);


        return v;


    }



    @Override
    public void obtenerDataReciente() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSelfMediaRecent = restApiAdapter.construyeGsonDeserializadorSelfMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonSelfMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia(userIdString);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                datasetNuestraMascota = mascotaResponse.getMascotas();
                mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Log.i("----Error---", t.toString());
            }
        });
    }


    public void mostrarContactosRV() {
        // specify an adapter
        mAdapter = new RVAdapterPerfilMascota(datasetNuestraMascota, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }


}
