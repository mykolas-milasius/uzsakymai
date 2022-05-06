package bandymas_databases.bandymas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;																						// import org.springframework.boot.test.autoconfigure.orm.jpa.*; - nereikia iš pvz. !
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PrekeRepositoryTest
{
	@Autowired
	private PrekeRepository preke_repository;
	
	@Test
	public void testSaveGetPrekes()
	{
		Preke preke = new Preke(555, 1, "Plaktukas", "2021-07-17", 157.25, "ADD576", null); //(Integer id, Integer id_tiekejo, String pavadinimas, String data, Double kaina, String bar_kodas, Tiekejas tiekejas)
		Preke preke_2 = preke_repository.save(preke);
		Preke preke_3 = preke_repository.findByPavadinimas("Plaktukas");
		
		assertNotNull(preke);
		assertNotNull(preke_3);
		assertEquals(preke_3.getPavadinimas(), preke.getPavadinimas());
		assertEquals(preke_3.getKaina(), preke.getKaina());
		preke_repository.deleteById((preke.getId()));
	}
}
