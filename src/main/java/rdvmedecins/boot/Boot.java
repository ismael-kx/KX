package rdvmedecins.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import rdvmedecins.config.DomainAndPersistenceConfig;
import rdvmedecins.entities.Client;
import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Rv;
import rdvmedecins.metier.IMetier;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KX on 07/01/2016.
 */
public class Boot {
    // le boot
    public static void main(String[] args) {
// on prépare la configuration
        SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
        app.setLogStartupInfo(false);
// on la lance
        ConfigurableApplicationContext context = app.run(args);
// métier
        IMetier metier = context.getBean(IMetier.class);
        try {
// ajouter un RV
            Date jour = new Date();
            System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau 1 pour le client 1", new SimpleDateFormat("dd/MM/yyyy").format(jour)));
                    Client client = (Client) new Client().build(1L, 1L);
            Creneau creneau = (Creneau) new Creneau().build(1L, 1L);
            Rv rv = metier.ajouterRv(jour, creneau, client);
            System.out.println(String.format("Rv ajouté = %s", rv));
// vérification
            creneau = metier.getCreneauByid(1L);
            long idMedecin = creneau.getIdMedecin();
            display("Liste des rendez-vous", metier.getAllRvMedecinJour(idMedecin, jour));
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getCause());
        }
// fermeture du contexte Spring
        context.close();
    }

    // méthode utilitaire - affiche les éléments d'une collection
    private static <T> void display(String message, Iterable<T> elements) {
        System.out.println(message);
        for (T element : elements) {
            System.out.println(element);
        }
    }

}