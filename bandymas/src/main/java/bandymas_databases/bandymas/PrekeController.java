package bandymas_databases.bandymas;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;

@Controller
public class PrekeController {
	
	@Autowired
	private PrekeRepository preke_repository;
	
	@Autowired
	private TiekejasRepository tiekejai_repository;
	
	@Autowired
	private UzsakymaiRepository uzsakymai_repository;
	
	@Autowired
	private UzsakymaiPrekesRepository uzsakymai_prekes_repository;

	@RequestMapping(path="/prekes", method={ RequestMethod.GET, RequestMethod.POST })
    public String miestai(
    		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
			, @RequestParam(name="pav", required=false, defaultValue="") String pav
			, @RequestParam(name="ikurimo_data", required=false, defaultValue="") String ikurimo_data
			, @RequestParam(name="id_tiekejo", required=false, defaultValue="") String id_tiekejo	
			, @RequestParam(name="kaina", required=false, defaultValue="") String kaina	
			, @RequestParam(name="bar_kodas", required=false, defaultValue="") String bar_kodas	
			, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
			, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
			, Model model) {
		
		String res = "Neatlikta";
		Preke preke = new Preke();
		if(prideti.equals("prideti"))
		{
			Optional <Preke> found = preke_repository.findById(id);
			
			if(found.isPresent())
			{
				preke = found.get();
				preke.setId(id);
			}
	
			preke.setData(ikurimo_data);
			preke.setPavadinimas(pav);
			preke.setIdtiekejo(Integer.parseInt(id_tiekejo));
			preke.setKaina(Double.parseDouble(kaina));
			preke.setBar_kodas(bar_kodas);
			preke_repository.save(preke);
		}
		
    	model.addAttribute("prekes", preke_repository.findAll() );
    	model.addAttribute("res", res );
    	model.addAttribute("lst_menu", Menu.values() ); 
   
		return "prekes";	
	}
	
	@RequestMapping(path="/preke")	
	public @ResponseBody Preke prekesDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		String res;
		Preke preke = new Preke();
		
		if (id > 0)
		{
			Optional <Preke> found = preke_repository.findById( id );
		
			if (found.isPresent())
			{
			   preke = found.get();
			   preke.setId ( id );
			}
			else
			{
				res = "Klaida įrašas nerastas ar buvo pašalintas";
			}
		}
		return preke;
	}
	
	@RequestMapping(path="/salinti-preke")
	public String salintiTiekeja (
			@RequestParam Integer id_prekes,
			@RequestParam(name="", required=false, defaultValue="") String salinti
			)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Preke> found = preke_repository.findById( id_prekes );
			if (found.isPresent())
			{
				   Preke n = found.get();
				   preke_repository.deleteById(id_prekes);
			}
		}
		
		return "redirect:prekes";
	}
	
	@RequestMapping(path="/tiekejai", method={ RequestMethod.GET, RequestMethod.POST })
	public String tiekejai(@RequestParam(name="", required=false, defaultValue="") String salinti,
			Model model)
	{
		Tiekejas tiekejas = new Tiekejas();
		model.addAttribute("tiekejai", tiekejai_repository.findAll());
		model.addAttribute("lst_menu", Menu.values() );
		return "tiekejai";
	}
	
	@RequestMapping(path="/uzsakymai", method={ RequestMethod.GET, RequestMethod.POST })
	public String uzsakymai(
			//@RequestParam(name="salinti", required=false, defaultValue="") String salinti, // removinti salinti jei ka
			@RequestParam(name ="id", required=false, defaultValue="") String id_s,
			@RequestParam(name ="data", required=false, defaultValue="") String data,
			@RequestParam(name ="pirkejas", required=false, defaultValue="") String pirkejas,
			@RequestParam(name ="adresas", required=false, defaultValue="") String adresas,
			@RequestParam(name ="telefono_nr", required=false, defaultValue="") String telefono_nr,
			@RequestParam(name ="uzsakymo_busena", required=false, defaultValue="") String uzsakymo_busena,
			@RequestParam(name ="prideti_name", required=false, defaultValue="neprideti") String prideti,
			Model model)
	{
		Uzsakymai uzsakymas = new Uzsakymai();
		if(prideti.equals("prideti"))
		{
			Integer id = Integer.parseInt(id_s);
			Optional <Uzsakymai> found = uzsakymai_repository.findById(id);
			
			if(found.isPresent())
			{
				uzsakymas = found.get();
				uzsakymas.setId(id);
			}
			uzsakymas.setPirkejas(pirkejas);
			uzsakymas.setAdresas(adresas);
			uzsakymas.setTelefono_numeris(telefono_nr);
			uzsakymas.setUzsakymo_laikas(data);
			uzsakymas.setUzsakymo_busena(uzsakymo_busena);
			uzsakymai_repository.save(uzsakymas);
		}
		
		model.addAttribute("uzsakymai", uzsakymai_repository.findAll());
		model.addAttribute("lst_menu", Menu.values() );
		
		return "uzsakymai";
	}

	@RequestMapping("/uzsakymas1json")
	public @ResponseBody Uzsakymai uzsakymas1json(@RequestParam(name="i", required=true, defaultValue="0") String id, Model model)
	{
		Optional<Uzsakymai> uzsakymas = uzsakymai_repository.findById(Integer.parseInt(id));
		Uzsakymai uzsakymas2 = null;
		if(!uzsakymas.isEmpty()) {
			
			uzsakymas2 = uzsakymas.get();
			
		}
		return uzsakymas2;
	}
	
	@RequestMapping("/uzsakymas1")
	public String uzsakymas1(@RequestParam(name="i", required=true, defaultValue="0") String id
			, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
			, @RequestParam(name="prekiu_kiekis", required=false, defaultValue="0") String prekiu_kiekis
			, @RequestParam(name="uzsakymai_prekes_id", required=false, defaultValue="0") String prekes_id
			, Model model
			)
	{
		Uzsakymai uzsakymas2 = new Uzsakymai();
		Optional <Uzsakymai> uzsakymas2_row = uzsakymai_repository.findById(Integer.parseInt(id));
		if(uzsakymas2_row.isPresent())
		{
			uzsakymas2 = uzsakymas2_row.get();
		}
		if(prideti.equals("prideti"))
		{
			System.out.println(prekes_id);
			UzsakymaiPrekes uzsakymai_prekes = new UzsakymaiPrekes(prekes_id, id, prekiu_kiekis);
			uzsakymai_prekes_repository.save(uzsakymai_prekes); /*preke_repository.save(preke);*/
			return "redirect:uzsakymas1?i="+id;//uzsakymas2.getUzsakymai_prekes().add(uzsakymai_prekes);
		}
		model.addAttribute("uzsakymas", uzsakymas2);
		model.addAttribute("lst_prekes", preke_repository.findAll());
		model.addAttribute("lst_menu", Menu.values() );
		return "uzsakymas";
	}
	
	@RequestMapping(path="/salinti-preke-uzsakyme") // pasalinti preke is uzsakymo
	public String salintiPrekeUzsakyme (
			@RequestParam(name="i", required=true, defaultValue="0") String id, // uzsakymai_prekes_id
			@RequestParam(name="uzsakymo_id", required=true, defaultValue="0") String uzsakymo_id,
			@RequestParam(name="salinti", required=false, defaultValue="") String salinti
			)
	{
		try
		{
			Integer id_x = Integer.parseInt(id);
			Optional <UzsakymaiPrekes> found = uzsakymai_prekes_repository.findById(id_x);
			if(salinti.equals("salinti"))
			{
				if (found.isPresent())
				{
					   UzsakymaiPrekes n = found.get();
					   uzsakymai_prekes_repository.deleteById(id_x);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "redirect:uzsakymas1?i="+uzsakymo_id;//return "redirect:uzsakymai";
	}
	
	@RequestMapping(path="/uzsakymas_red")	
	public @ResponseBody Uzsakymai uzsakymasRedagavimas(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException
	{
		Uzsakymai uzsakymas = new Uzsakymai();
		
		if (id > 0)
		{
			Optional <Uzsakymai> found = uzsakymai_repository.findById( id );
		
			if (found.isPresent())
			{
				uzsakymas = found.get();
				uzsakymas.setId ( id );
			}
		}
		return uzsakymas;
	}
	//---------------------------------------------------------------------------------------
	@Autowired 
	EntityManagerFactory factory;
	
	public SessionFactory sessionFactory()
	{

        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
}
	
	@RequestMapping("/ataskaita")
	public String uzsakymaiAtaskaita(@RequestParam(name="i", required=true, defaultValue="0") String id
			, @RequestParam(name="prekiu_kiekis", required=false, defaultValue="0") String prekiu_kiekis
			, @RequestParam(name="uzsakymai_prekes_id", required=false, defaultValue="0") String prekes_id
			, Model model
			)
	{
		/*
		Session session = this.sessionFactory().openSession();
		Ataskaita ataskaita = new Ataskaita(session);
		Uzsakymai uzsakymas2 = new Uzsakymai();
		
		Optional <Uzsakymai> uzsakymas2_row = uzsakymai_repository.findById(Integer.parseInt(id));
		if(uzsakymas2_row.isPresent())
		{
			uzsakymas2 = uzsakymas2_row.get();
		}
		
		model.addAttribute("uzsakymas", uzsakymas2);
		//model.addAttribute("lst_ataskaita", top_patieklai_ataskaita.topPatiekalai(laikotarpis_nuo, laikotarpis_iki) ); 
		//model.addAttribute("lst_menu", Menu.values() );*/
		return "ataskaita";
	}
	
	@GetMapping("/login")
	public String login(Model model)
	{		
		return "login";
	}
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		return "redirect:login";
	}
}