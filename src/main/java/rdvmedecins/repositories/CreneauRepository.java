package rdvmedecins.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rdvmedecins.entities.Creneau;

/**
 * Created by KX on 03/01/2016.
 */
public interface CreneauRepository extends CrudRepository<Creneau,Long> {

    @Query("select c from Creneau c where c.medecin.id=?1")
    public Iterable<Creneau> getAllCreneaux (Long idMedecin);
}
