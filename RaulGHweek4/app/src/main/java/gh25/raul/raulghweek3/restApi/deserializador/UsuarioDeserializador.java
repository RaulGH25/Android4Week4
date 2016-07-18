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

import gh25.raul.raulghweek3.pojo.Usuario;
import gh25.raul.raulghweek3.restApi.JsonKeys;
import gh25.raul.raulghweek3.restApi.model.UsuarioResponse;

/**
 * Created by Raul on 26/06/2016.
 */
public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {
    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.USUARIO_RESPONSE_ARRAY);

        usuarioResponse.setUsuarios(deserializarUsuariosDeJson(usuarioResponseData));
        return usuarioResponse;
    }


    private ArrayList<Usuario> deserializarUsuariosDeJson(JsonArray UsuarioResponse){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < UsuarioResponse.size() ; i++) {

            JsonObject UsuarioResponseDataObject = UsuarioResponse.get(i).getAsJsonObject();

            String id       = UsuarioResponseDataObject.get(JsonKeys.USUARIO_RESPONSE_ARRAY_ID).getAsString();
            String name     = UsuarioResponseDataObject.get(JsonKeys.USUARIO_RESPONSE_ARRAY_USERNAME).getAsString();
            String picture  = UsuarioResponseDataObject.get(JsonKeys.USUARIO_RESPONSE_ARRAY_PROFILE_PICTURE).getAsString();

            Usuario usuarioActual = new Usuario();
            usuarioActual.setId(id);
            usuarioActual.setName(name);
            usuarioActual.setProfilePicture(picture);

            usuarios.add(usuarioActual);
        }

        return usuarios;
    }





}
