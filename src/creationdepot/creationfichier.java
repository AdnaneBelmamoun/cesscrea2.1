package creationdepot;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class creationfichier {
	public static String fpath = "C:\\Documents and Settings\\Maestro\\Bureau\\";

	// exemple
	     static int nombreChoisi = 45;
	     static String testutf ="ceci est un test d'ecriture utf";
	     static String testdonnée = "ceci est un test";
	     static String nomfichier ="fichier_texte1";
	     static String  extensionfichier =".txt";
	     
	     static String   repcontenantfichier = fpath;
	     static String Fichier = repcontenantfichier+nomfichier+extensionfichier;
	     static DataOutputStream fluxSortietexte;

	     public void creerFichierBinaire(String chemin) throws IOException
	     {        
	          // ouverture du flux
	          fluxSortietexte = new DataOutputStream( new FileOutputStream( chemin ) );
	          fluxSortietexte.writeInt( nombreChoisi );
	          fluxSortietexte.writeChars(testdonnée);
	          System.out.print(fluxSortietexte.toString());

	          fluxSortietexte.close(); // fermeture du flux
	     }
	     public static  void creerfichier(String chemin) throws IOException{
	    	 fluxSortietexte = new DataOutputStream(new FileOutputStream(Fichier)) ;
             fluxSortietexte.writeInt(nombreChoisi)	;    
             fluxSortietexte.writeChars(testdonnée);
             fluxSortietexte.writeUTF(testutf);
             System.out.print(fluxSortietexte.toString());

             fluxSortietexte.close();
	     }
	     public static void creerrepertoir(String chemin){
	    	 File f = new File(chemin); //Indique les dossiers à créer (si ils n'existent pas déjà).
	    	 f.mkdirs(); //Créer les dossiers inexistant.
             
	     }
	     public static String repath(String init){
			 String clone = init;
			 String res  = clone.replace('\\', '/');
				      return res ;
		 }
	     public static void main(String[] args) throws IOException{
	    	 
	    	 //creerrepertoir(fpath+"\\"+"test creation depot cesscrea");
	    	 //creerfichier(Fichier);
	   // Runtime.getRuntime().exec("cd C:\\Documents and Settings\\Maestro\\Bureau\\");
          //(new PrintStream(repath(fpath+"\\test creation depot cesscrea")));
	    	 //OpenFileInputStreamAction test = new OpenFileInputStreamAction(fpath+"test creation depot cesscrea\\");
	    	 
			//Process p = new ProcessBuilder("explorer.exe", "C:\\Documents and Settings\\Maestro\\Bureau").start();	    
	     }
			
	    	 
	     

}
