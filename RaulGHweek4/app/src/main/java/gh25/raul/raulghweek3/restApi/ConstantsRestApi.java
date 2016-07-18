package gh25.raul.raulghweek3.restApi;

/**
 * Created by Raul on 25/06/2016.
 */
public final class ConstantsRestApi {

    // Constantes para nodejs y firebase

    public static final String SERVER_ROOT_URL = "https://obscure-scrubland-24023.herokuapp.com/";
    public static final String SERVER_DEVICE_USER_KEY = "token-devices/";

    public static final String SERVER_KEY_DAR_LIKE = "like/";
    public static final String SERVER_KEY_RECIBIR_LIKE = "notificacion/{likeID}";


    // De aqui hacia abajo están las costantes de instagram

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;

    // TODO RELLENAR ESTOS CAMPOS PARA LA PRIMERA VEZ QUE SE INICIE LA APLICACIÓN. SOBRE TODO EL ACCESS_TOKEN
    public static final String ACCESS_TOKEN = "3448615353.ea8cab4.608f1f7f9f504e5bb09218742c3befbd";
    public static final String USER_NAME = "mikedogpet";
    public static final String USER_ID = "3448615353";
    public static final String DEFAULT_PICTURE_URL = "https://scontent.cdninstagram.com/t51.2885-19/s150x150/13392913_1081926781861706_242801515_a.jpg";

    // Devolver información de los usuarios a partir de su NOMBRE (devuelve tb la ID):
    // https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN

    public static final String KEY_USER_NAME_1 = "users/search?q=";
    public static final String KEY_USER_NAME_2 = "&access_token=";

    public static final String URL_GET_USER_INF_BY_NAME =
            ROOT_URL + KEY_USER_NAME_1 + USER_NAME + KEY_USER_NAME_2 + ACCESS_TOKEN;


    // Devolver data de un usuario conociendo su ID:
    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN

    public static final String KEY_USER_DATA_1 = "users/";
    public static final String KEY_USER_DATA_2 = "/media/recent/?access_token=";

    public static final String URL_GET_USER_DATA_BY_ID =
            ROOT_URL + KEY_USER_DATA_1 + USER_ID + KEY_USER_DATA_2 + ACCESS_TOKEN;


    // Devolver datos del propio usuario a partir de su access token
    // https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN

    public static final String KEY_SELF_USER = "users/self/?access_token=";

    public static final String URL_GET_SELF_USER_DATA =
            ROOT_URL + KEY_SELF_USER + ACCESS_TOKEN;

    // Devolver media reciente del propio usuario
    // https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN

    public static final String KEY_SELF_MEDIA = "users/self/media/recent/?access_token=";

    public static final String URL_GET_SELF_USER_MEDIA =
            ROOT_URL + KEY_SELF_MEDIA + ACCESS_TOKEN;

}
