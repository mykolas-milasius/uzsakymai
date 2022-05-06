package bandymas_databases.bandymas;

import org.springframework.data.repository.CrudRepository;

public interface PrekeRepository extends CrudRepository<Preke, Integer>
{
	Preke findByPavadinimas(String pavadinimas);
}