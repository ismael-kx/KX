package rdvmedecins.repositories;

import org.springframework.data.repository.CrudRepository;
import rdvmedecins.entities.Client;

/**
 * Created by KX on 03/01/2016.
 */
public interface ClientRepository extends CrudRepository<Client,Long> {
}
