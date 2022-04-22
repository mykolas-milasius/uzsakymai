package bandymas_databases.bandymas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tiekejas
{
	private String pav;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_tiekejo", referencedColumnName="id", insertable=false, updatable=false)    
    private List<Preke> preke; 
	
	public List<Preke> getPreke() {
		return preke;
	}

	public void setPreke(List<Preke> preke) {
		this.preke = preke;
	}

	public Tiekejas()
	{
		super();
	}
	
	public Tiekejas(String pav)
	{
		super();
		this.pav = pav;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPav() {
		return pav;
	}

	public void setPav(String pav) {
		this.pav = pav;
	}
}