package bandymas_databases.bandymas;

import javax.persistence.*;

@Entity
public class UzsakymaiPrekes
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private Integer preke_id;
	private Integer uzsakymai_id;
	private Integer kiekis;
	
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name="preke_id", referencedColumnName="id", insertable=false, updatable=false)
	private Preke preke;
	
	public UzsakymaiPrekes(String preke_id, String uzsakymai_id, String kiekis)
	{
		super();
		this.preke_id = Integer.parseInt(preke_id);
		this.uzsakymai_id = Integer.parseInt(uzsakymai_id);
		this.kiekis = Integer.parseInt(kiekis);
	}
	
	public UzsakymaiPrekes()
	{
		super();
	}
	
	public Integer getKiekis() {
		return kiekis;
	}

	public void setKiekis(Integer kiekis) {
		this.kiekis = kiekis;
	}

	public Preke getPreke() {
		return preke;
	}

	public void setPreke(Preke preke) {
		this.preke = preke;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPreke_id() {
		return preke_id;
	}

	public void setPreke_id(Integer preke_id) {
		this.preke_id = preke_id;
	}

	public Integer getUzsakymai_id() {
		return uzsakymai_id;
	}

	public void setUzsakymai_id(Integer uzsakymai_id) {
		this.uzsakymai_id = uzsakymai_id;
	}
}