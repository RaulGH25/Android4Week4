package gh25.raul.raulghweek3.restApi.model;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Mascota;

/**
 * Created by Raul on 25/06/2016.
 */
public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
