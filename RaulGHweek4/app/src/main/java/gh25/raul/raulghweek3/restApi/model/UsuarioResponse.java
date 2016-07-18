package gh25.raul.raulghweek3.restApi.model;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Usuario;

/**
 * Created by Raul on 26/06/2016.
 */
public class UsuarioResponse {

    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
