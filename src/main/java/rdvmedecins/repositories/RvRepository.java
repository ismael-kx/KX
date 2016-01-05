package rdvmedecins.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rdvmedecins.entities.Rv;

import java.util.Date;

/**
 * Created by KX on 03/01/2016.
 */
public interface RvRepository extends CrudRepository<Rv,Long> {

    @Query("select rv from Rv rv left join fetch rv.client c left join fetch rv.creneau cr where cr.medecin.id=?1 and rv.jour=?2")
    public Iterable<Rv> getAllRvMedecinJour(Long idMedecin,Date date);
}
