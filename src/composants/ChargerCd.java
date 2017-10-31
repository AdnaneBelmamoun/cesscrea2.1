package composants;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import parseurevcc.Evcc;
import parseurevcc.parseur;
import creationdepot.DetecterCDROM;
import creationdepot.creationfichier;
import ecrans.Evenements;
import ecrans.FenetreTraitement;

public class ChargerCd {
public static String nomworkspace = null;// de^pot.getpathdepot()
public static parseur temp;
public static File jfc = null;
public static boolean Concordancenbrdoss = false;
	
	public ChargerCd(){action();}
	
	
	
	@SuppressWarnings("static-access")
	public static void action(){
		temp = new parseur();
 
		
//*********ici j'implante ma séquence de détéction du nom du lecteur cdrom

		String lettrecdrom =DetecterCDROM.detecterlettrelecteurcd();


		
//************ séquence de copie du DOSSIER a partir du cdrom ou chemin dans le depot choisi ou creer
	
		File source=null;
   if(lettrecdrom == null){
		   source = new File("C:\\Documents and Settings\\Maestro\\Bureau\\depot_test\\4929\\200909");//lettrecdrom	
		   //JOptionPane.showMessageDialog(null,"veuillez Insérer Le CD Remise Evcc" );
		   Vector<String>	vectinfos = parseur.generercdinfo(source.getPath());
		     String idcess =  vectinfos.get(0);//temp.CessionId;
		     String codeemission = vectinfos.get(1); //temp.codeemet;
		     String dateemission = vectinfos.get(2);//temp.date;
		     String periodeemission = vectinfos.get(3);//temp.periode;
		     String nbrdossemission = vectinfos.get(4);//temp.nbrdoss;
		  		  
	    	 Evenements.cheminorganisme = System.getenv("WScesscrea")+"\\"+codeemission+"\\" ;
	    		           creationfichier.creerrepertoir(Evenements.cheminorganisme);
	    		           Evenements.chemincession   = Evenements.cheminorganisme+idcess.subSequence(4,10)+"\\";
                         creationfichier.creerrepertoir(Evenements.chemincession);
                         creationfichier.creerrepertoir(Evenements.chemincession+"confirmations\\");
	 
JOptionPane.showMessageDialog(FenetreTraitement.chargercd, "id cession : "+idcess +"\n"+"code emetteure  :" + codeemission +"\n"
                         +"periode d'emission :" + periodeemission +"\n"+" Date d'emission :" + dateemission +"\n"+"Nombre de Dossier  :" + nbrdossemission +"\n" 
			               ,"information sur l'emission " ,JOptionPane.PLAIN_MESSAGE);

String emetteur = codeemission;
String date = (String) idcess.subSequence(4,10);

  int compteur =0; 
       Evcc evc = parseurevcc.Evcc.charger_evcc(emetteur,date);
             evc.getinfos(); 
      Iterator<Vector<String>> itag =evc.getListagents().iterator();
         File tmp = null;
     Vector<String>  agsansim =  new Vector<String>();
     Vector<String>  agtemp =  new Vector<String>();  
  while(itag.hasNext()){  
	agtemp =   (Vector<String>)itag.next();  
            System.out.println( agtemp.get(0)+"       "+agtemp.get(5)+"      ");
    tmp = new File(Evenements.chemincession+"EVCC_Images\\"+((Vector<String>)itag.next()).get(5));
         if(tmp.exists()){  
        	 System.out.println( tmp.getAbsolutePath());  
         }else{	agsansim.add(((Vector<String>)itag.next()).get(1));
         System.out.println("fichier image n'existe pas : "+  tmp.getPath()); }
       }//fin du while

  if(agsansim.isEmpty()){
	System.out.println("le dossier /images est conforme au données du fichier Xml ");
	JOptionPane.showMessageDialog(FenetreTraitement.chargercd, "le dossier /images est conforme au données du fichier Xml ");
	}
    else{System.out.println("Les images correspondantes aux Agents ayant les matricules suivants n'existe pas dans /EVCC_images : \n");
JOptionPane.showMessageDialog(FenetreTraitement.chargercd, "Les images correspondantes aux Agents ayant les matricules suivants n'existe pas dans /EVCC_images : \n");
	Iterator<String> i = agsansim.iterator();
	     while(i.hasNext()){ System.out.println((String)i.next()+"\n");
	                         JOptionPane.showMessageDialog(FenetreTraitement.chargercd, (String)i.next()+"\n");
	                        }
 
  if(Integer.parseInt(nbrdossemission)== compteur ){Concordancenbrdoss = true;  System.out.println("nbre de dossiers est correcte : "+  compteur);}


File destination  = new File(Evenements.chemincession);
	// copie du contenu du cd  dans le depot	
		if(!(source==null)){
			if(!(source.getPath()==destination.getPath())){			
			//	try {  
			//		Copie.copier(source,destination);
			//		} catch (IOException e) {	e.printStackTrace();	}          
			}else{JOptionPane.showMessageDialog(null,"Cette Evcc Existe deja dans votre Work Space cesscrea");}		
			}
		if(source == null){ JOptionPane.showMessageDialog(null,"veuillez Insérer Un CDROM De Remise Evcc " );}
	// fin du if workspace != null 

    }
  }

   
   
   else{
		source = new File(lettrecdrom );
		   System.out.println(source.getPath());
		      		 		     // debut de sequence de choix
     jfc =	 Evenements.sequencefc("Atteindre Espace de Travail"); 
        
 			   Evenements.diropen.setCurrentDirectory(new File(System.getenv("WScesscrea")));
 			   System.out.println("nomselection : "+Evenements.diropen.getSelectedFile());
 		   if(!(Evenements.diropen.getSelectedFile()==null)){
 			   nomworkspace = jfc.getName();		                       
		     if(nomworkspace != null){
		    	 Vector<String>	vectinfos = parseur.generercdinfo(source.getPath());
			     String idcess =  vectinfos.get(0);//temp.CessionId;
			     String codeemission = vectinfos.get(1); //temp.codeemet;
			     String dateemission = vectinfos.get(2);//temp.date;
			     String periodeemission = vectinfos.get(3);//temp.periode;
			     String nbrdossemission = vectinfos.get(4);//temp.nbrdoss;
			  		  
		    	 Evenements.cheminorganisme = System.getenv("WScesscrea")+"\\"+codeemission+"\\" ;
		    		           creationfichier.creerrepertoir(Evenements.cheminorganisme);
		    		           Evenements.chemincession   = Evenements.cheminorganisme+idcess.subSequence(4,10)+"\\";
                               creationfichier.creerrepertoir(Evenements.chemincession);
                               creationfichier.creerrepertoir(Evenements.chemincession+"confirmations\\");
		 
 JOptionPane.showMessageDialog(FenetreTraitement.chargercd, "id cession : "+idcess +"\n"+"code emetteure  :" + codeemission +"\n"
                               +"periode d'emission :" + periodeemission +"\n"+" Date d'emission :" + dateemission +"\n"+"Nombre de Dossier  :" + nbrdossemission +"\n" 
				               ,"information sur l'emission " ,JOptionPane.PLAIN_MESSAGE);
  File destination  = new File(Evenements.chemincession);
		// copie du contenu du cd  dans le depot	
			if(!(source==null)){
				if(!(source.getPath()==destination.getPath())){			
					try {  
						Copie.copier(source,destination);
						} catch (IOException e) {	e.printStackTrace();	}          
				}else{JOptionPane.showMessageDialog(null,"Cette Evcc Existe deja dans votre Work Space cesscrea");}		
				}
			if(source == null){ JOptionPane.showMessageDialog(null,"veuillez Insérer Un CDROM De Remise Evcc " );}
		     }
		     
		     // fin du if workspace != null 
		    if(nomworkspace == ""|| !( jfc.getPath()==System.getenv("WScesscrea"))){       Evenements.diropen.removeAll();   }
		   }else {}
	}
	}
	
	public static void main(String[] args) {
	 //action();	
	 ChargerCd cc = new ChargerCd();
	   
	 try {	cc.finalize();  } catch (Throwable e) {	e.printStackTrace();}
    
/*      String emetteur = "4929";
		String date = "200909";
		
		
		Evcc evc = charger_evcc(emetteur,date);
	    evc.getinfos();
	    Iterator<Vector<String>> itag =evc.getListagents().iterator();
        while(itag.hasNext()){
        System.out.println( (Vector<String>)itag.next()+"\n");
          
                  */
	}

}
