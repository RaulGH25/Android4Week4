package gh25.raul.raulghweek3.restApi.model;

/**
 * Created by Raul on 16/07/2016.
 */
public class ServerUsuarioResponse {

    private String id_dispositivo;
    private String id_usuario_instagram;
    private String id_firebase;

    public ServerUsuarioResponse() {
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }
}
