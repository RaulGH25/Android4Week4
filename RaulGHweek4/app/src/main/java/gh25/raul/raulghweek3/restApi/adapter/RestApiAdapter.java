package gh25.raul.raulghweek3.restApi.adapter;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gh25.raul.raulghweek3.restApi.ConstantsRestApi;
import gh25.raul.raulghweek3.restApi.EndpointsApi;
import gh25.raul.raulghweek3.restApi.deserializador.MascotaDeserializador;
import gh25.raul.raulghweek3.restApi.deserializador.UsuarioDeserializador;
import gh25.raul.raulghweek3.restApi.model.MascotaResponse;
import gh25.raul.raulghweek3.restApi.model.UsuarioResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raul on 25/06/2016.
 */
public class RestApiAdapter {

    // RestApiAdapters

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestApiServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.SERVER_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }



    // Deserializadores


    public Gson construyeGsonDeserializadorSelfMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUsuarioID(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioDeserializador());
        return gsonBuilder.create();
    }

}
