package gh25.raul.raulghweek3.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Mascota;
import gh25.raul.raulghweek3.restApi.JsonKeys;
import gh25.raul.raulghweek3.restApi.model.MascotaResponse;

/**
 * Created by Raul on 25/06/2016.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));

        return mascotaResponse;
    }


    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size() ; i++) {

            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            JsonObject mascotaUser = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);

            String id       = mascotaUser.get(JsonKeys.USER_ID).getAsString();
            String name     = mascotaUser.get(JsonKeys.USER_FULL_NAME).getAsString();

            JsonObject mascotaImageObject = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA);
            JsonObject mascotaImageObjectStandarResolution = mascotaImageObject.getAsJsonObject(JsonKeys.MEDIA_IMAGE_STANDAR_R);

            String imageUrl = mascotaImageObjectStandarResolution.get(JsonKeys.MEDI_URL).getAsString();

            JsonObject mascotaLikesObject = mascotaResponseDataObject.getAsJsonObject(JsonKeys.LIKES);
            int likes = mascotaLikesObject.get(JsonKeys.LIKES_COUNT).getAsInt();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setName(name);
            mascotaActual.setPhotoID(imageUrl);
            mascotaActual.setLikes(likes);

            mascotas.add(mascotaActual);
        }

        return mascotas;
    }


}
