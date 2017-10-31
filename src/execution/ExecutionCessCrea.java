package execution;

import javax.swing.JOptionPane;

import ecrans.Evenements;
import ecrans.Wizardinstall;

public class ExecutionCessCrea {
     public static int confirmer_install ;
	
     
     public static void executercesscrea(String[] args){
		ecrans.Traitement.tete = 1 ;
	    ecrans.Traitement.fentemp = ecrans.Traitement.creerfenetre3(ecrans.Traitement.pathdepot,ecrans.Traitement.tete);
	}
	public static void installersetx(){
	
		String xcopysetxcmd ="Xcopy \""+System.getProperty("user.dir")+"\\setx.exe\""+" \""+System.getenv("systemROOT")+"\\system32\\\" /c /u /Y";
		System.out.println(xcopysetxcmd);
		Evenements.exec(xcopysetxcmd);
	}
	
	public static void installershortcut(){

		String xcopyshortcutcmd1 ="Xcopy \""+System.getProperty("user.dir")+"\\Shortcut.exe\""+" \""+System.getenv("systemROOT")+"\\system32\\\" /c /u /Y";
		System.out.println(xcopyshortcutcmd1);
		Evenements.exec(xcopyshortcutcmd1);

		String xcopyshortcutcmd2 ="Xcopy \""+System.getProperty("user.dir")+"\\SHORTCUT.DLL\""+" \""+System.getenv("systemROOT")+"\\system32\\\" /c /u /Y";
		System.out.println(xcopyshortcutcmd2);
		Evenements.exec(xcopyshortcutcmd2);

		
	}
	public static void installeroutils(){
		String lancerbatcmd = System.getProperty("user.dir")+"\\cesscreatools.bat";
				
	Evenements.exec(lancerbatcmd);
		System.out.println(System.getProperty("user.dir"));
		
	}
	
	public static void DemarageCessCrea(String[] args){

	     if(!(System.getenv("WScesscrea")== null)){ 
	    	 executercesscrea(args);     
	     } else{ 
	    	 confirmer_install =JOptionPane.showConfirmDialog(null, "Votre variable  d'environement WScesscrea n'existe pas \n" +
	     											"       veuillez installer CessCrea  ","confirmer l'installation",JOptionPane.OK_CANCEL_OPTION); 
	     if(confirmer_install== JOptionPane.OK_OPTION){  
	    	 										installersetx();
	    	 										installershortcut();
	    	 										installeroutils();
	    	 										Wizardinstall.demarrerwizard();   }
	     if(confirmer_install== JOptionPane.NO_OPTION){System.exit(-1);   }
	     }	
		
	}
	public static void main(String[] args) {
		
        //    installeroutils();
		 DemarageCessCrea(args); 
         //installersetx();
		 //installershortcut();
		 
		/* try {
			Copie.copierFichier(new File(System.getProperty("user.dir")+"\\setx.exe"),new File( System.getenv("systemROOT")+"\\system32\\"));
		} catch (IOException e) {
			System.out.println("erreure de copie de fichier");
			e.printStackTrace();
		}*/
	}

}
