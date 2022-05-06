package bandymas_databases.bandymas;

/**
 * @author
 * by https://www.mkyong.com/java/java-enum-example/
 * by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * by https://www.geeksforgeeks.org/enum-in-java/
 */
public enum Menu
{
	Prekes("/prekes")
	, Užsakymai("/uzsakymai")
	, Tiekejai("/tiekejai")
	, Ataskaita("/ataskaita")
	, Atsijungti("/logout");
	
	private final String itemurl;
	
	Menu( String url )
	{
		this.itemurl = url;
	}
	
	public String itemurl()
	{
		return this.itemurl;
	}
}