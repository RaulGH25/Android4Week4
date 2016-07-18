package gh25.raul.raulghweek3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Mascota;
import gh25.raul.raulghweek3.restApi.ConstantsRestApi;
import gh25.raul.raulghweek3.restApi.EndpointsApi;
import gh25.raul.raulghweek3.restApi.adapter.RestApiAdapter;
import gh25.raul.raulghweek3.restApi.model.ServerLikeResponse;
import gh25.raul.raulghweek3.restApi.model.ServerUsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Raul on 15/05/2016.
 */
public class RVAdapterPerfilMascota extends RecyclerView.Adapter<RVAdapterPerfilMascota.PetnViewHolder>{

    private Activity activity;
    private ArrayList<Mascota> datasetMascotas  = new ArrayList<>();

    String likeID;
    String fotoID;
    String usuarioID;
    String dispositivoID;

    public static class PetnViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView petLikes;
        ImageView yellowBone;
        ImageView petPhoto;

        PetnViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.cardViewOurPet);
            petPhoto = (ImageView)itemView.findViewById(R.id.imageView_PhotoOurPet);
            petLikes = (TextView)itemView.findViewById(R.id.TextView_LikesNumnerOurPet);
            yellowBone = (ImageView)itemView.findViewById(R.id.imageView_YellowBoneOurPet);

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
*/
        }

    }


    RVAdapterPerfilMascota(ArrayList<Mascota> datasetMascotas, Activity activity){
        this.activity = activity;
        this.datasetMascotas = datasetMascotas;
    }


    @Override
    public int getItemCount() {
        return datasetMascotas.size();
    }

    @Override
    public PetnViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_perfil, viewGroup, false);
        PetnViewHolder pvh = new PetnViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PetnViewHolder petnViewHolder, final int i) {
        final Mascota pet = datasetMascotas.get(i);
        petnViewHolder.petLikes.setText(String.valueOf(pet.getLikes()));
        petnViewHolder.yellowBone.setImageResource(R.drawable.huesodorado);

        Picasso.with(activity)
                .load(pet.getPhotoID())
                .placeholder(R.drawable.huesoblanco)
                .into(petnViewHolder.petPhoto);

        petnViewHolder.yellowBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageURL = pet.getPhotoID();
                String userID;
                String deviceID;


                SharedPreferences sharedPref = activity.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
                userID = sharedPref.getString("SharedVariableUserId", "noHayUsuarioGuardado");
                deviceID = sharedPref.getString("SharedVariableDeviceId", "noHayUsuarioGuardado");

                darlikenotification(imageURL, userID, deviceID);

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Dar Like
    public void darlikenotification(String imageURL, String userID, String deviceID){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiServer();

        Call<ServerLikeResponse> serverLikeResponseCall = endpointsApi.darlikenotificationenviar(imageURL, userID, deviceID);
        serverLikeResponseCall.enqueue(new Callback<ServerLikeResponse>() {
            @Override
            public void onResponse(Call<ServerLikeResponse> call, Response<ServerLikeResponse> response) {
                ServerLikeResponse serverLikeResponse = response.body();
                String likeID = serverLikeResponse.getLikeID();
                Log.i("-- likeID --", likeID);
                recibirlikenotification(likeID);
            }

            @Override
            public void onFailure(Call<ServerLikeResponse> call, Throwable t) {
            }
        });
    }

    // Recibir Notificacion
    public void recibirlikenotification(String localLikeID){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiServer();

        Call<ServerLikeResponse> serverLikeResponseCall = endpointsApi.darlikenotificationrecibir(localLikeID);
        serverLikeResponseCall.enqueue(new Callback<ServerLikeResponse>() {
            @Override
            public void onResponse(Call<ServerLikeResponse> call, Response<ServerLikeResponse> response) {
                ServerLikeResponse serverLikeResponse = response.body();
                String fotoID = serverLikeResponse.getFotoID();
                String usuarioID = serverLikeResponse.getUsuarioID();

                Log.i("-- FOTOID --", fotoID);
                Log.i("-- USERID --", usuarioID);

                // enviar notificacion android wear
                enviarNotificacion();

            }
            @Override
            public void onFailure(Call<ServerLikeResponse> call, Throwable t) {
                Log.i(" --- FAIL ---", String.valueOf(t));
            }
        });
    }


    // Android wear
    public void enviarNotificacion(){
        Log.i(" --- FAIL ---", "--- entro ---");
    }


}
