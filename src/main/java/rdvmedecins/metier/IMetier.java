package rdvmedecins.metier;

import rdvmedecins.domain.AgendaMedecinJour;
import rdvmedecins.entities.Client;
import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Medecin;
import rdvmedecins.entities.Rv;

import java.util.Date;
import java.util.List;

/**
 * Created by KX on 03/01/2016.
 */
public interface IMetier {
    List<Client> getAllClients();

    List<Medecin> getAllMedecins();

    List<Creneau> getAllCreneaux(Long IdMedecin);

    List<Rv> getAllRvMedecinJour(Long idMedecin, Date jour);

    Client getClientById(Long idClient);

    Medecin getMedecinById(Long idMedecin);

    Rv getRvById(Long idRv);

    Creneau getCreneauByid(Long idCreneau);

    Rv ajouterRv(Date jour, Creneau creneau,Client client);

    void supprimerRv(Rv rv);

    AgendaMedecinJour getAgendaMedecinJour(Long idMedecin,Date jour);

}
