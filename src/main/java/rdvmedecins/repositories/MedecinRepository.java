package rdvmedecins.repositories;

import org.springframework.data.repository.CrudRepository;
import rdvmedecins.entities.Medecin;

/**
 * Created by KX on 03/01/2016.
 */
public interface MedecinRepository extends CrudRepository<Medecin,Long> {
}
