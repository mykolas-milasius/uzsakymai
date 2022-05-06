package bandymas_databases.bandymas;

import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;

public class Ataskaita
{
	  protected Session sesija;

	public Ataskaita(Session session)
	{
		super();
		this.sesija = session;
	}

	public Ataskaita()
	{
		super();
	}
	
	public List<Ataskaita> ataskaita(String kaina_nuo, String kaina_iki)
	{
		String uzklausa = "SELECT `uzsakymai`.*"
				+ "	SUM(`preke`.`kaina`) AS bendra_kaina"
				+ "    FROM `uzsakymai`"
				+ "	LEFT JOIN uzsakymai_prekes"
				+ "    	ON(`uzsakymai`.`id` = `uzsakymai_prekes`.`uzsakymai_id`)"
				+ "    LEFT JOIN preke"
				+ "        ON(uzsakymai_prekes.preke_id = `preke`.`id`)"
				+ "GROUP BY `uzsakymai`.`id`"
				+ "HAVING	bendra_kaina > 50 AND bendra_kaina < 250";
		System.out.println(uzklausa);
		Query query = sesija.createNativeQuery(uzklausa);
		return (List<Ataskaita>) query.getResultList();
	}
}