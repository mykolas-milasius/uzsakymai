package bandymas_databases.bandymas;

import java.util.List;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Preke
{
	public Preke(Integer id, Integer id_tiekejo, String pavadinimas, String data, Double kaina, String bar_kodas,
			Tiekejas tiekejas)
	{
		super();
		this.id = id;
		this.id_tiekejo = id_tiekejo;
		this.pavadinimas = pavadinimas;
		this.data = data;
		this.kaina = kaina;
		this.bar_kodas = bar_kodas;
		this.tiekejas = tiekejas;
	}
	
	public Preke()
	{
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private Integer id_tiekejo;
	private String pavadinimas;
	private String data;
	private Double kaina;
	private String bar_kodas;
	
	@ManyToOne
	@JoinColumn(name="id_tiekejo", referencedColumnName="id", insertable=false, updatable=false)
	private Tiekejas tiekejas;

	public Double getKaina() {
		return kaina;
	}

	public void setKaina(Double kaina) {
		this.kaina = kaina;
	}

	public String getBar_kodas() {
		return bar_kodas;
	}

	public void setBar_kodas(String bar_kodas) {
		this.bar_kodas = bar_kodas;
	}

	public Tiekejas getTiekejas()
	{
		return tiekejas;
	}
	
	public void setTiekejas(Tiekejas tiekejas_duotas)
	{
		tiekejas = tiekejas_duotas;
	}
	
	public Integer getIdtiekejo() {
		return id_tiekejo;
	}

	public void setIdtiekejo(Integer tiekejo_id) {
		this.id_tiekejo = tiekejo_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
