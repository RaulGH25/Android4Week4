package gh25.raul.raulghweek3.restApi.model;

/**
 * Created by Raul on 17/07/2016.
 */
public class ServerLikeResponse {

    private String likeID;

    private String fotoID;
    private String usuarioID;
    private String dispositivoID;


    public ServerLikeResponse() {
    }

    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public String getFotoID() {
        return fotoID;
    }

    public void setFotoID(String fotoID) {
        this.fotoID = fotoID;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getDispositivoID() {
        return dispositivoID;
    }

    public void setDispositivoID(String dispositivoID) {
        this.dispositivoID = dispositivoID;
    }
}
