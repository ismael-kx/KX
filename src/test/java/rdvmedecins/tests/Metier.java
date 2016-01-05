package rdvmedecins.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rdvmedecins.config.DomainAndPersistenceConfig;
import rdvmedecins.domain.AgendaMedecinJour;
import rdvmedecins.entities.Client;
import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Medecin;
import rdvmedecins.entities.Rv;
import rdvmedecins.metier.IMetier;

import java.util.Date;
import java.util.List;

/**
 * Created by KX on 04/01/2016.
 */
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Metier {

    @Autowired
    IMetier metier;

    @Test
    public void test1(){
        List<Client> clients = metier.getAllClients();
            display("Voici l'ensemble des clients :", clients);
        //tous les medecins
        List<Medecin> medecins = metier.getAllMedecins();
            display("Voici la liste des medecins :",medecins);
        //TOus les creneaux d'un medecin
        Medecin medecin = medecins.get(0);
        List<Creneau> creneaux = metier.getAllCreneaux(medecin.getId());
            display(String.format("Liste des creneaux du medecin %s", medecin),creneaux);
        // liste des Rv d'un medecin, un jour donné
        Date jour = new Date();
        display(String.format("Liste des rv du medecin %s, le [%s]", medecin, jour),
                metier.getAllRvMedecinJour(medecin.getId(), jour));
        // ajouter un RV
        Rv rv = null;
        Creneau créneau = creneaux.get(2);
        Client client = clients.get(0);
        System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau,
                client));
        rv = metier.ajouterRv(jour, créneau, client);
        // vérification
        Rv rv2 = metier.getRvById(rv.getId());
        Assert.assertEquals(rv, rv2);
        display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
                metier.getAllRvMedecinJour(medecin.getId(), jour));
        // ajouter un RV dans le même créneau du même jour
        // doit provoquer une exception
        System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau,
                client));
        Boolean erreur = false;
        try {
            rv = metier.ajouterRv(jour, créneau, client);
            System.out.println("Rv ajouté");
        } catch (Exception ex) {
            Throwable th = ex;
            while (th != null) {
                System.out.println(ex.getMessage());
                th = th.getCause();
            }
            // on note l'erreur
            erreur = true;
        }
        // on vérifie qu'il y a eu une erreur
        Assert.assertTrue(erreur);
        // liste des RV
        display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
                metier.getAllRvMedecinJour(medecin.getId(), jour));
        // affichage agenda
        AgendaMedecinJour agenda = metier.getAgendaMedecinJour(medecin.getId(), jour);
        System.out.println(agenda);
        Assert.assertEquals(rv, agenda.getCreneaxuMedecinJour()[2].getRv());
        // supprimer un RV
        System.out.println("Suppression du Rv ajouté");
        metier.supprimerRv(rv);
        // vérification
        rv2 = metier.getRvById(rv.getId());
        Assert.assertNull(rv2);
        display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
                metier.getAllRvMedecinJour(medecin.getId(), jour));
    }

    public void display(String message, Iterable<?> elements){
        System.out.println(message);
        for(Object element : elements){
            System.out.println(element);
        }
    }
}
