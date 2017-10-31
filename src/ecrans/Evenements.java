package ecrans;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import outils.EvccJtable;
import composants.ChargerCd;
import composants.ImageFileView;
import composants.ImagePreview;
//import creationdepot.creationfichier;


public class Evenements {

	   //public static String profilutilisateur = System.getenv("USERPROFILE");
		public static String nomworkspace  ;// depot.getpathdepot();
		public static String cheminworkspace ;//= profilutilisateur+"\\Bureau\\";/*creationfichier.fpath ;*/
	    public static File dir;
	    public static JFileChooser diropen;
	    public static String chemincession, cheminorganisme;
	    public static String idcess;
	    public static String codeemission ;
	    public static String dateemission ;
	    public static String periodeemission;
	    public static String ndossemission ;
	    public static  JFileChooser fc;
	   // public static JTextArea read = new JTextArea("",10,25);
	    
     public final String ws = nomworkspace;


	
	public static void goparcour() {
		//ecrans.FenetreTraitement.fentemp.add(Test.fctest());
		File file = null;
		 JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnVal = fc.showOpenDialog(FenetreTraitement.fentemp);
	 
	if (returnVal == JFileChooser.APPROVE_OPTION) {
             file = fc.getSelectedFile();	 
              
             if(System.getenv("WScesscrea").contains(file.getAbsolutePath())
       				||(System.getenv("WScesscrea")!=null))  {
       			
           	  JOptionPane.showMessageDialog(FenetreTraitement.fentemp,"Un depot existe déja :\n"+System.getenv("WScesscrea"));
             }
             
             if(System.getenv("WScesscrea")==null){
           	  //Evenements.exec("setx WScesscrea "+ " \" C:\\Documents and Settings\\Maestro\\Bureau\\test00\" -m");
           	 // Evenements.exec("setx WScesscrea \" C:\\Documents and Settings\\Maestro\\Bureau\\test00\" ");
           	Evenements.exec("setx WScesscrea \" "+file.getAbsolutePath() +"\" -m");
         	Evenements.exec("setx WScesscrea \" "+file.getAbsolutePath() +"\" ");
         		
             }
         		
         	}// fin de cas ouvrir
	else { System.out.println(" annulée par l'utilisateur "); }
	               // fieldnom.setText(file.getName());
                   //fieldchemin.setText(file.getAbsolutePath());
    System.out.println("ouverture: " + file.getName() + "---->    "+ file.getAbsolutePath()); 
                   }
	public static void goevcc() {
		
		
			Hierarchiedepot.lancerarbre();
		
	
	}
	
	public static String trouverevccxml(String pathrep){
 	    String res=null;   
 		File f = new File(pathrep);
 		if(f.exists()){
 	    if(f.isDirectory()){
 	    	String str[]= f.list();
             for(int i =0;i<str.length;i++)  {
                 File f2 = new File(pathrep+"\\"+str[i]);
                       if(f2.isDirectory())  {  }
                       if(f2.isFile() && (f2.getName()).endsWith(".xml") && (f2.getName()).startsWith("EVCC") ){res = f2.getPath();  }                      	  
                                       }
 	                        }
 	                  }
 		if(!(f.exists())){JOptionPane.showMessageDialog(FenetreTraitement.fentemp, "le fichier .xml de cette evcc est introuvable ");}
   return res; }
     
     public static String repath(String init){
		 String clone = init;
		 String res   = clone.replace('\\', '/');
			      return res ;
	 }
	
	public static JScrollPane gotrierdansunetable(int x ,int y,int l,int h, String code, String date) {
		JScrollPane matableSP =new JScrollPane();
		String pathevcc =trouverevccxml(System.getenv("WScesscrea")+"\\"+code+"\\"+date);//"\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\EVCC492920090900.xml ");//testevcc.xml
		if(!(pathevcc==null)){
			File entryFile = new File(pathevcc);
		org.jdom.Document doc = parseurevcc.parseur.parse(entryFile);
		//parseurevcc.parseur.extractiondesbalises(doc);
		Vector<Vector<String>> matricedonnee = parseurevcc.parseur.extractiondesbalises(doc);// methode d'extraction des balises et d'affichage dans un JTable
		 EvccJtable.codepath = code;
		 EvccJtable.datepath = date;
		matableSP = EvccJtable.creerjtable(matricedonnee,code,date);// creation de la jtable avec les lignes de données --->retourne Jscrollpane
		JFrame frame = new JFrame();  // la frame qui contiendra la jtable
		 frame.setIconImage(
    	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
	    frame.add(EvccJtable.scrollPane, BorderLayout.CENTER);
	    frame.setTitle("Table de l'emission Evcc : " + " Code organisme  :" + code+"  Date Emission : "+date);
	    frame.setBounds(x,y,l,h);
	    frame.setVisible(true);
		}
		else{JOptionPane.showMessageDialog(FenetreTraitement.fentemp, " veuillez revoir votre selection d'emission ");}
	return matableSP;}
	
	public static JScrollPane gotrierdansunetable2(int x ,int y,int l,int h, String code, String date) {
		JScrollPane matableSP =new JScrollPane();
		String pathevcc =trouverevccxml(System.getenv("WScesscrea")+"\\"+code+"\\"+date);//"\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\EVCC492920090900.xml ");//testevcc.xml
		System.out.println(" le path de creation de la table :"+pathevcc);
		if(!(pathevcc==null)){File entryFile = new File(pathevcc);
		org.jdom.Document doc = parseurevcc.parseur.parse(entryFile);
		//parseurevcc.parseur.extractiondesbalises(doc);
		Vector<Vector<String>> matricedonnee = parseurevcc.parseur.extractiondesbalises(doc);// methode d'extraction des balises et d'affichage dans un JTable
		 matableSP = EvccJtable.creerjtable(matricedonnee,code,date);// creation de la jtable avec les lignes de données --->retourne Jscrollpane
		JFrame frame = new JFrame();  // la frame qui contiendra la jtable
	    frame.add(EvccJtable.scrollPane, BorderLayout.CENTER);
	    frame.setBounds(x,y,l,h);
	    frame.setVisible(true);
		}
		else{JOptionPane.showMessageDialog(FenetreTraitement.fentemp, " veuillez revoir votre selection d'emission ");}
	return matableSP;}
				
				
		
	
	public static void gochargementcd() { ChargerCd.action();		}
	
	public static File sequencefc(String nonfc){
		
		 diropen = new JFileChooser(FenetreTraitement.cheminworkspace);
        diropen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               
           diropen.setFileView(new ImageFileView());
               // Add the preview pane.
            diropen.setAccessory(new ImagePreview(diropen));

 int ret = diropen.showDialog(null, nonfc);
switch (ret) {
   case JFileChooser.APPROVE_OPTION:
                       dir = diropen.getSelectedFile();
            System.out.println(dir.getName());
            System.out.println(dir.getPath());
//System.out.println(file.getParentFile().getPath());
        break;
   case JFileChooser.CANCEL_OPTION:
     {System.out.println("click sur annuler ou icone de fermeture ");
      dir = null;
     diropen.setEnabled(false);
     }break;
   case JFileChooser.ERROR_OPTION:
     System.out.println("Erreure");
        break;}
		
	return dir;
	}
	public static void goworkspace() {
		if((System.getenv("WScesscrea").isEmpty())){
			
			int choix = JOptionPane.showConfirmDialog(Traitement.fentemp, "votre variable d'environement CessCrea" +
					"n'existe pas ou sa valeure est invalide ", "Alerte CessCrea Env.", JOptionPane.OK_CANCEL_OPTION);
		if(choix== JOptionPane.OK_OPTION){Wizardinstall.demarrerwizard();}
		if(choix== JOptionPane.CANCEL_OPTION){System.exit(-1);  System.gc(); }
			/*File choix = sequencefc("definir nouveau espace de travail  ");
		 nomworkspace = choix.getName();
		 cheminworkspace =  choix.getParentFile().getPath();
		if(!(nomworkspace == null)){
			creationfichier.creerrepertoir(cheminworkspace+nomworkspace);
		// ici je doit implémenter la variable d'envirenoment pathdepot	
			creerWSvaenv(cheminworkspace,nomworkspace);
		                     }
			//JOptionPane.showMessageDialog(null,"espace de travail creer avec succées");
		*/	}// fin if
		else{ afficherws(System.getenv("WScesscrea"));
			JOptionPane.showMessageDialog(null,"Un Espace de travail existe deja   : \n"+"  "
					+System.getenv("WScesscrea"));  
			
		}
		}
	public static void afficherws(String chemin){
		try {
			@SuppressWarnings("unused")
			Process p = new ProcessBuilder("explorer.exe",chemin).start();
			
		} catch (IOException e) {
			 JOptionPane.showConfirmDialog(null, "erreure d'ouverture du depot"); 
			e.printStackTrace();
		}
					}
		
		public static Process exec(String cmd) {
			Process pret = null;
			  if(cmd.compareToIgnoreCase("")!=0)  {
			          	  try {
			        //int reponse= JOptionPane.showConfirmDialog(null,
			          //                      "Confirmer la creation de la variable d'environnement WScesscrea :"+cmd,
			            //                    "creation d'une variable d'environnement pour CessCrea ---> <<autheur: bel.adnane>>",
			              //                     JOptionPane.YES_NO_OPTION);
			 //     if(reponse==JOptionPane.YES_OPTION){
			 
			  Process           p = Runtime.getRuntime().exec(cmd);
			  pret = p ;
			  InputStream      in = p.getInputStream();
			  StringBuilder build = new StringBuilder();
			  //on demarre la lecture
			  char c = (char) in.read();
			  while (c != (char) -1)
			  {
			  build.append(c);
			  c = (char) in.read();
			  }
			  //on recupere le tout
			  String OutPut = build.toString();
			  System.out.append("\n"+"commande saisie:"+cmd+"\n"+OutPut);
			 //attente jusqua la fin du processus: on sait jamais
			  p.wait();
			 // p.waitFor();
			 
			 
			 // }// fin du dernier if 
			 
			  } // fin du try
			   catch(Exception e) {/*System.out.append("erreure d'execution :" + cmd +"\n"+e.toString());*/ }
			  } // fin du premier if
			  else { System.out.append("\n"+"Il faut taper une commande !");  }
			 // fin de exec()
			  return pret;
}
		public static void creerWSvaenv(String chemin, String nom) {
			exec("setx WScesscrea \" "+chemin+"\\"+nom+" \" -m "); // ajout de variable Systeme WScesscrea
			exec("setx WScesscrea \" "+chemin+"\\"+nom+" \" ");    // ajout de variable utilisateur WScesscrea
	//System.getenv().put("WorkSpacecesscrea", cheminworkspace+"//"+nomworkspace+"//");
		}
		
		 public static String repathplus(String init){
			 String clone = init;
			 String res = null;
			 if(clone.contains("/")){  res  = clone.replace("\\", "/");    }
			 if(clone.contains("\\")){ res = clone.replace("/", "\\"); }
				      return res ;
		 } 
		public static void main(String[] args) {
		
			//System.out.println(repathplus(" C:/Documents and Settings\\Maestro/Bureau/test00/ "));
			//exec("setx WScesscrea "+ " \" C:\\Documents and Settings\\Maestro\\Bureau\\test00\\ \" -m");
			//exec("setx WScesscrea \" C:\\Documents and Settings\\Maestro\\Bureau\\test00\\\" ");
			//System.out.println((String)FenetreTraitement.cheminworkspace);
		}
	
	
}
