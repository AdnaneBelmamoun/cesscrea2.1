package ecrans;

public class Mesvariables {

	
	private static String codeemetteur ;
	
	private static String dateemission ;
	
	
	public Mesvariables(){}
	
	public Mesvariables(String code, String date){  setCodeemetteur(code);   setDateemission(date); }
	
	
	
	
	
	
	
	
	
	
	
	
	public static void setDateemission(String dateemission) {
		Mesvariables.dateemission = dateemission;
	}

	public static String getDateemission() {
		return dateemission;
	}

	public static void setCodeemetteur(String codeemetteur) {
		Mesvariables.codeemetteur = codeemetteur;
	}

	public static String getCodeemetteur() {
		return codeemetteur;
	}

	static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
