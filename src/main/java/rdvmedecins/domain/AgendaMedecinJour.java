package rdvmedecins.domain;

import rdvmedecins.entities.Medecin;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KX on 03/01/2016.
 */
public class AgendaMedecinJour implements Serializable {

    private CreneauMedecinJour[] creneauxMedecinJour;
    private Medecin medecin ;
    private Date jour;

    public AgendaMedecinJour() {
    }

    public AgendaMedecinJour(CreneauMedecinJour[] creneauMedecinJour, Medecin medecin, Date date) {
        this.creneauxMedecinJour = creneauMedecinJour;
        this.medecin = medecin;
        this.jour = date;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public CreneauMedecinJour[] getCreneaxuMedecinJour() {
        return creneauxMedecinJour;
    }

    public void setCreneauxMedecinJour(CreneauMedecinJour[] creneauMedecinJour) {
        this.creneauxMedecinJour = creneauMedecinJour;
    }
    public String toString() {
        StringBuffer str = new StringBuffer("");
        for (CreneauMedecinJour cr : creneauxMedecinJour) {
            str.append(" ");
            str.append(cr.toString());
            }
        return String.format("Agenda[%s,%s,%s]", medecin, new
            SimpleDateFormat("dd/MM/yyyy").format(jour), str.toString());
        }
}
