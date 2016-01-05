package rdvmedecins.domain;

import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Rv;

import java.io.Serializable;

/**
 * Created by KX on 03/01/2016.
 */
public class CreneauMedecinJour implements Serializable {

    private Creneau creneau;
    private Rv rv;

    public CreneauMedecinJour(){

    }

    public CreneauMedecinJour(Creneau creneau, Rv rv) {
        this.creneau = creneau;
        this.rv = rv;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

    public Rv getRv() {
        return rv;
    }

    public void setRv(Rv rv) {
        this.rv = rv;
    }

    public String toString(){
        return String.format("[%s,%s]",creneau,rv);
    }


}
