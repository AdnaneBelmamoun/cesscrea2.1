package ecrans;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import outils.EvccJtable;
import outils.Test;

import composants.ChargerCd;
import composants.ImageFileView;
import composants.ImagePreview;

import creationdepot.creationfichier;




	@SuppressWarnings("serial")
	public class graphismecesscrea extends JFrame implements  ActionListener{
		public static JFrame fen;
        public static String profilutilisateur = System.getenv("USERPROFILE");
		public static String nomworkspace ;
		 public static  parseurevcc.parseur temp;
		public static String cheminworkspace = profilutilisateur+"\\Bureau\\";/*creationfichier.fpath ;*/
		public static JButton chargercd,parcour,traitementevcc,recherche,trier, creerworkspace ;
		public static String chemincession, cheminorganisme;
		
	    
	    public static String idcess;
	    public static String codeemission ;
	    public static String dateemission ;
	    public static String periodeemission;
	    public static String ndossemission ;
	    public static  JFileChooser fc;
	    public static File dir;
	    public static JFileChooser diropen;
	    
        public final String ws = nomworkspace;

  public graphismecesscrea() {
		
	    	super() ;
		setTitle("céssion créance--------> @ adnane belm.") ;
		setBounds(10,20,1250,720) ;
		
	chargercd= new JButton("Charger le cd ") ;
//	parcour = new JButton("parcourir EVCC/images") ;
	traitementevcc = new JButton("Traiter une emission evcc ") ;
	recherche = new JButton("Chercher Agent") ;
	trier = new JButton("trier le cd dans une table");
	creerworkspace = new JButton("Nouveau Espace de Travail CessCrea");
	//MonTexte = new JLabel ("  *******************:  " ) ;
	
	Container contenu = getContentPane() ;// ici je cree le conteneur des element du menu
	FlowLayout flm = null;//new FlowLayout() ;

	chargercd.setBounds(20, 20, 150, 20);
	//parcour.setBounds(220, 20, 200, 20);
	traitementevcc.setBounds(460, 20, 200, 20);
	recherche.setBounds(700, 20, 150, 20);
	trier.setBounds(20, 90, 200, 20);
	creerworkspace.setBounds(350, 90, 200, 20);
	          contenu.setLayout(flm) ;
	          contenu.add(chargercd) ;
	        //  contenu.add(parcour) ;
	          contenu.add(traitementevcc) ;
	          contenu.add(recherche) ;
	          contenu.add(trier);
	          contenu.add(creerworkspace);
      		//contenu.add(MonTexte);
		    creerworkspace.addActionListener(this);
			chargercd.addActionListener(this);
		    trier.addActionListener(this); 
		   // parcour.addActionListener(this);
		    traitementevcc.addActionListener(this);
	

		
	 
	}
		
		public void actionPerformed(ActionEvent evenement) {
			 if(evenement.getSource()==chargercd)  {  gochargementcd();	}
			 if(evenement.getSource()== creerworkspace){  goworkspace();  }
			 if(evenement.getSource()== trier){  gotrier(300, 200,600, 150);  }
		     if(evenement.getSource()== parcour){goparcour() ;}
		     if(evenement.getSource()== traitementevcc ){ FenetreTraitement.fentemp = FenetreTraitement.creerfenetre2(FenetreTraitement.pathdepot,FenetreTraitement.tete);  }
	}
		
		public static void goparcour() {
			fen.add(Test.fctest());
		}
		public static void gotrier(int x ,int y,int l,int h) {
			File entryFile = new File(System.getenv("USERPROFILE")+"\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\EVCC492920090900.xml ");//testevcc.xml
			org.jdom.Document doc = parseurevcc.parseur.parse(entryFile);
			//parseurevcc.parseur.extractiondesbalises(doc);
			Vector<Vector<String>> matricedonnee = parseurevcc.parseur.extractiondesbalises(doc);// methode d'extraction des balises et d'affichage dans un JTable
			EvccJtable.creerjtable(matricedonnee);// creation de la jtable avec les lignes de données 
			JFrame frame = new JFrame();  // la frame qui contiendra la jtable
		   // frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    frame.add(EvccJtable.scrollPane, BorderLayout.CENTER);
		    frame.setBounds(x,y,l,h);
		    frame.setVisible(true);
		}
					
			
		
		public static void gochargementcd() { ChargerCd.action();		}
		
		public static File sequencefc(String nonfc){
			
			 diropen = new JFileChooser("C:\\Documents and Settings\\Maestro\\Bureau");
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
// System.out.println(file.getParentFile().getPath());
            break;
       case JFileChooser.CANCEL_OPTION:
         {System.out.println("click sur annuler ou icone de fermeture ");
          dir = null; 
         }break;
       case JFileChooser.ERROR_OPTION:
         System.out.println("Erreure");
            break;}
			
		return dir;
		}
		public static void goworkspace() {
			 nomworkspace = sequencefc("definir nouveau espace de travail  ").getName();//JOptionPane.showInputDialog("donner  un nom pour votre espace de travail :", " Espace de Travail CessCrea ");
			
			if(nomworkspace != null){
				creationfichier.creerrepertoir(cheminworkspace+nomworkspace);
				//JOptionPane.showMessageDialog(null,"espace de travail creer avec succées");
				}
					
			
		}
		public static JFrame creerfenetre(){
				
		 fen = new graphismecesscrea();
			   fen.setVisible(true);
			   fen.setEnabled(true);
			   
			return fen;    
		}
		
		public static void main(String[] args) {
		
				creerfenetre();
				
				}
		}
