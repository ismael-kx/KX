package rdvmedecins.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rdvmedecins.domain.AgendaMedecinJour;
import rdvmedecins.domain.CreneauMedecinJour;
import rdvmedecins.entities.Client;
import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Medecin;
import rdvmedecins.entities.Rv;
import rdvmedecins.repositories.ClientRepository;
import rdvmedecins.repositories.CreneauRepository;
import rdvmedecins.repositories.MedecinRepository;
import rdvmedecins.repositories.RvRepository;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
/**
 * Created by KX on 03/01/2016.
 */
@Service("metier")
public class Metier implements IMetier {

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CreneauRepository creneauRepository;
    @Autowired
    RvRepository rvRepository;


    @Override
    public List<Client> getAllClients() {
        return Lists.newArrayList(clientRepository.findAll());
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return Lists.newArrayList(medecinRepository.findAll());
    }

    @Override
    public List<Creneau> getAllCreneaux(Long IdMedecin) {
        return Lists.newArrayList(creneauRepository.getAllCreneaux(IdMedecin));
    }

    @Override
    public List<Rv> getAllRvMedecinJour(Long idMedecin, Date jour) {
        return Lists.newArrayList(rvRepository.getAllRvMedecinJour(idMedecin,jour));
    }

    @Override
    public Client getClientById(Long idClient) {
        return clientRepository.findOne(idClient);
    }

    @Override
    public Medecin getMedecinById(Long idMedecin) {
        return medecinRepository.findOne(idMedecin);
    }

    @Override
    public Rv getRvById(Long idRv) {
        return rvRepository.findOne(idRv);
    }

    @Override
    public Creneau getCreneauByid(Long idCreneau) {
        return creneauRepository.findOne(idCreneau);
    }

    @Override
    public Rv ajouterRv(Date jour, Creneau creneau, Client client) {
        return rvRepository.save(new Rv(jour,client,creneau));
    }

    @Override
    public void supprimerRv(Rv rv) {

        rvRepository.delete(rv.getId());
    }

    @Override
    public AgendaMedecinJour getAgendaMedecinJour(Long idMedecin, Date jour) {

        List<Creneau> creneauxHoraires = getAllCreneaux(idMedecin);
        //liste des réservations de ce même médecin pour ce même jour
        List<Rv> reservations = getAllRvMedecinJour(idMedecin, jour);
        // on crée un dictionnaire à partir des Rv pris
        Map<Long, Rv> hReservations = new Hashtable<Long, Rv>();
        for (Rv resa : reservations) {
            hReservations.put(resa.getCreneau().getId(), resa);
        }
        // on crée l'agenda pour le jour demandé
        AgendaMedecinJour agenda = new AgendaMedecinJour();
        // le médecin
        agenda.setMedecin(getMedecinById(idMedecin));
        // le jour
        agenda.setJour(jour);
        // les créneaux de réservation
        CreneauMedecinJour[] creneauxMedecinJour = new CreneauMedecinJour[creneauxHoraires.size()];
        agenda.setCreneauxMedecinJour(creneauxMedecinJour);
        // remplissage des créneaux de réservation
        for (int i = 0; i < creneauxHoraires.size(); i++) {
            // ligne i agenda
            creneauxMedecinJour[i] = new CreneauMedecinJour();
            // créneau horaire
            Creneau créneau = creneauxHoraires.get(i);
            long idCreneau = créneau.getId();
            creneauxMedecinJour[i].setCreneau(créneau);
            // le créneau est-il libre ou réservé ?
            if (hReservations.containsKey(idCreneau)) {
                // le créneau est occupé - on note la résa
                Rv resa = hReservations.get(idCreneau);
                creneauxMedecinJour[i].setRv(resa);
            }
        }
        // on rend le résultat
        return agenda;
    }

}