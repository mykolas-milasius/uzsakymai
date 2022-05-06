package bandymas_databases.bandymas;

import java.util.List;

import javax.persistence.*;

@Entity
public class Uzsakymai
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private String pirkejas;
	private String adresas;
	private String telefono_numeris;
	private String uzsakymo_laikas;
	private String uzsakymo_busena;
	
	public Uzsakymai(Integer id, String pirkejas, String adresas, String telefono_numeris, String uzsakymo_laikas, String uzsakymo_busena)
	{
		super();
		this.id = id;
		this.pirkejas = pirkejas;
		this.adresas = adresas;
		this.telefono_numeris = telefono_numeris;
		this.uzsakymo_laikas = uzsakymo_laikas;
		this.uzsakymo_busena = uzsakymo_busena;
	}
	
	public Uzsakymai()
	{
		super();
	}
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="uzsakymai_id", referencedColumnName="id", insertable=false, updatable=false)    
    private List<UzsakymaiPrekes> uzsakymai_prekes; 
    
	public List<UzsakymaiPrekes> getUzsakymai_prekes() {
		return uzsakymai_prekes;
	}

	public void setUzsakymai_prekes(List<UzsakymaiPrekes> uzsakymai_prekes) {
		this.uzsakymai_prekes = uzsakymai_prekes;
	}
    
	public int bendrasUzsakymoPrekiuKiekis()
	{
		int kiekis = 0;
		
		if(uzsakymai_prekes == null)
		{
			return 0;
		}
		else
		{
			for (int i = 0; i < uzsakymai_prekes.size(); i++)
			{
				kiekis += uzsakymai_prekes.get(i).getKiekis();
			}
		}
		
		return kiekis;
	}
	
	public double bendraUzsakymoPrekiuKaina()
	{
		double kaina = 0;
		
		if(uzsakymai_prekes == null)
		{
			return 0;
		}
		else
		{
			for (int i = 0; i < uzsakymai_prekes.size(); i++)
			{
				kaina += uzsakymai_prekes.get(i).getPreke().getKaina();
			}
		}
		
		return kaina;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPirkejas() {
		return pirkejas;
	}

	public void setPirkejas(String pirkejas) {
		this.pirkejas = pirkejas;
	}

	public String getUzsakymo_laikas() {
		return uzsakymo_laikas;
	}

	public void setUzsakymo_laikas(String uzsakymo_laikas) {
		this.uzsakymo_laikas = uzsakymo_laikas;
	}

	public String getAdresas() {
		return adresas;
	}

	public void setAdresas(String adresas) {
		this.adresas = adresas;
	}

	public String getTelefono_numeris() {
		return telefono_numeris;
	}

	public void setTelefono_numeris(String telefono_numeris) {
		this.telefono_numeris = telefono_numeris;
	}

	public String getUzsakymo_busena() {
		return uzsakymo_busena;
	}

	public void setUzsakymo_busena(String uzsakymo_busena) {
		this.uzsakymo_busena = uzsakymo_busena;
	}
}