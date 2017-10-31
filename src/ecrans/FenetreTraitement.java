package ecrans;


import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import composants.ImporterEvcc;

import parseurevcc.Agent;


public class FenetreTraitement extends JFrame implements ActionListener {
	
	public static final long serialVersionUID = 1L;
	 
	 // les variable qui servent acreer cette fenetre traitement
	
 
	public final static String cheminworkspace =System.getenv("WScesscrea");
	public static String  codeemm  ;  
	public static String   dateemm ; 
	public static String pathdepot;
  
	
    public static parseurevcc.Agent agenttemp = new Agent();
    public static JFrame fentemp;
    public static int tete ; 
    public static int inc = tete ;
    public static String nomim;
    public static JButton  suivant =  new JButton(" suivant ");
    public static JButton  precedent = new JButton(" precedent ");
    public static ImageIcon icon = new ImageIcon();
    public static Image image ;
    public static ImageIcon imagezoom; 
    public static JLabel labelzoom = new JLabel(imagezoom, JLabel.CENTER);
	public static Container contenant = new Container();
	
	public static JPanel panelrecherche;
	public static JPanel infosagent;
	public static JPanel infosprecompte;
	public static JPanel infosemission;
	public static JPanel panelim ;
	public static JLabel infosimage = new JLabel()  ;
	
	public static JLabel labnom = new JLabel(" nom ") ;
    public static JLabel labcin = new JLabel(" cin ");
    public static JLabel labmatricule = new JLabel(" matricule ");
    public static JLabel labchapitre = new JLabel(" Chapitre ") ;
    public static JLabel labnumdoss  = new JLabel("N°dossier precompte");
    public static JLabel labcdvendeur   = new JLabel(" code vendeur");
    public static JLabel labmontnet = new JLabel(" montant net");
    public static JLabel labmonttot = new JLabel(" montant totale");
    public static JLabel labmontmens = new JLabel(" montant mensuel");
    public static JLabel labnbrmois = new JLabel("nombre de mois");
    public static JLabel labdatedebut = new JLabel("date debut");
    public static JLabel labdatefin = new JLabel("date fin");
    public static JLabel labidemission = new JLabel(" ID  emission ");
    public static JLabel labposition = new JLabel("position ");
    public static JLabel labcodemet = new JLabel("code emmetteur");
    public static JLabel labdate = new JLabel("Date emission");
    //public static JLabel labnbrdossier = new JLabel();

    // ici j'ai tout mes element de recherche
    public static JLabel label = new JLabel("argument recherche :");
	public static JTextField field = new JTextField("");
    public static JButton bouttonrecherche = new JButton(); 
	public static  String[] criteres = {"Nom", "Cin", "Matricule", "N°dossier"};
	public static JComboBox MaCombo = new JComboBox(criteres) ;
    
	public static String critererecherche  ;
	public static String argumentrecherche ;
	public static Agent resagent = null;
	

public static DocumentBuilderFactory domFactory =null;
	static DocumentBuilder builder = null;
	  static Document doc = null;
	static XPathFactory factory = null; 
	static XPath xpath = null;
	javax.xml.xpath.XPathExpression expr =	null;

	static String respos =null;
    static String res0 = null; 
    static String res1 =   null;
    static String res2 =   null;
    static String res3 =  null;
   // static String resfichim = null;
    static String res4 =   null;
    static String res5 =   null;
    static String res6 =   null;
    static String res7 =   null;
    static String res8 =   null;
    //static String resnbrmois = null;
    static String res9 =   null;
    static String res10 =   null;
    static String residcess = null;
    static String rescdemet = null;
    static String resdatemis = null;
    static String resnbrdoss = null;
       

 // fin des attributs de la section recherche
  
	
	public static JTextField tfnom = new JTextField(20) ;
    public static JTextField tfcin = new JTextField(20) ;
    public static JTextField tfmatricule = new JTextField(20) ;
    public static JTextField tfchapitre = new JTextField(20) ;
    public static JTextField tfnumdoss = new JTextField(20) ;
    public static JTextField tfcdvendeur = new JTextField(20) ; 
    public static JTextField tfmontnet = new JTextField(20) ;
    public static JTextField tfmonttot = new JTextField(20) ;
    public static JTextField tfmontmens = new JTextField(20) ;
    public static JTextField tfnbrmois = new JTextField(20) ;
    public static JTextField tfdatedebut = new JTextField(20) ;
    public static JTextField tfdatefin = new JTextField(20) ;
    public static JTextField tfidemission = new JTextField(20) ;
    public static JTextField tfposition = new JTextField(20) ;
    public static JTextField tfcodemet = new JTextField(20) ;
    public static JTextField tfdate = new JTextField(20) ;

	public JMenuBar barreMenus;
	public JMenu elementsdemarage;
	public JMenu rechercher;

	public static  JMenuItem creerdepot;
    public static JMenuItem chargercd;
	public static JMenuItem importerevcc;
	public static JMenuItem triercd;
	public static JMenuItem versEvcc;
	public static JMenuItem chercheragent;
	public static JMenuItem chercheragentdansdepot;
   
   
    public static String getvaenvir(){
    	String res = "";
    	if(System.getenv("WScesscrea")== null)  {JOptionPane.showMessageDialog(fentemp, "veuillez reconfiguer votre espace de travail WScesscrea" );}//Evenements.creerWSvaenv(); res = System.getenv("WScesscrea");}
    	else{res =System.getenv("WScesscrea");}
    		
    	return res;
    }
    
    
	public FenetreTraitement()  {
    	super() ;
    	setDefaultLookAndFeelDecorated(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  ImageIcon monIcon= new ImageIcon(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.gif".trim());
   	    setIconImage(monIcon.getImage());
    //	setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.gif")); 
		setTitle("CessCrea : Traitement de l'emission : "+"organisme emetteur : "+codeemm+"\t date  : "+dateemm+  "--------------> "+"    @ adnane belm.") ;
		setBounds(10,20,1250,720) ;
        
		//ici j'introduit mon Jmenu :
		//création d'une barre de menu
		barreMenus = new JMenuBar() ;
		setJMenuBar(barreMenus) ;
		//ajout de la barre de menu dans la fenêtre
		//création Du menu et de ses options 
		elementsdemarage = new JMenu("démarage") ;
		barreMenus.add(elementsdemarage) ;
		creerdepot = new JMenuItem("Acceder au WorkSpace CessCrea") ;
		elementsdemarage.add(creerdepot) ;
		elementsdemarage.addSeparator() ; ;
		//ajout d'une barre séparatrice avant l'option suivante
		chargercd = new JMenuItem("charger CD") ;
		elementsdemarage.add(chargercd) ;
		importerevcc = new JMenuItem("Importer Evcc") ;
		elementsdemarage.add(importerevcc) ;
		triercd = new JMenuItem("Trier Evcc courante dans une Table") ;
		elementsdemarage.add(triercd) ;
		
		//création d'un menu Rechercher et de ses options
		
		rechercher = new JMenu("Recherche") ;
		barreMenus.add(rechercher) ;
		versEvcc = new JMenuItem("atteindre cession") ;
		rechercher.add(versEvcc) ;
		rechercher.addSeparator() ;
		chercheragent = new JMenuItem("chercher Agent dans la cession courante") ;
		chercheragentdansdepot = new JMenuItem("chercher Agent dans tout le depot") ;
		rechercher.add(chercheragent) ;
		rechercher.add(chercheragentdansdepot) ;
		//***************************** ici j'instancie mon jpanel recherche et je lui ajoute ces elements ********
		                  panelrecherche = new JPanel();
		                  panelrecherche.setVisible(false);
		                  panelrecherche.setBounds(165, 230, 500, 200);
		                  panelrecherche.setBackground(Color.cyan);
		                  label.setBounds(170, 30 , 130 , 20);
		                  field.setBounds(170, 70 , 200 , 20);
		       bouttonrecherche.setBounds(380,70 , 90, 20);
		       bouttonrecherche.setText("chercher");
		       bouttonrecherche.setToolTipText("Chercher Agent dans cette remise (Emission Evcc).");
		                MaCombo.setBounds(170, 110, 130, 30);
		                MaCombo.setSelectedIndex(0);
		                MaCombo.setMaximumRowCount(3) ;
		panelrecherche.add(label);
		panelrecherche.add(field);
		panelrecherche.add(bouttonrecherche);
		panelrecherche.add(MaCombo);
		bouttonrecherche.addActionListener(this);
		
		//**************************************************************************
		//************************ ici on contsruit le jpanel  infos agent *********
							infosagent = new JPanel();
							infosagent.setBounds(5, 20, 300, 200);
		//**************************************************************************     
	    labnom.setBounds(5, 25, 50, 20);
	    tfnom.setBounds(105, 25, 140, 20);
		    infosagent.add(labnom);
		    infosagent.add(tfnom);
		
	    labcin.setBounds(5, 55, 50, 20);
	    tfcin.setBounds(105, 55, 140, 20);
		    infosagent.add(labcin);
		    infosagent.add(tfcin);
		
	
		labmatricule.setBounds(5, 85, 70, 20);
	    tfmatricule.setBounds(105, 85, 140, 20);
	    	infosagent.add(labmatricule);
	    	infosagent.add(tfmatricule);
		
		
		 labchapitre.setBounds(5, 115, 70, 20);
	     tfchapitre.setBounds(105, 115, 140, 20);
	     	infosagent.add(tfchapitre,"East");
	     	infosagent.add(labchapitre);

	    // ******************************ici on contsruit le jpanel  infos precompte agent
	    infosprecompte = new JPanel();
		infosprecompte.setBounds(5, 150, 300, 460);
		//*******************************************************************************************  
		    labnumdoss.setBounds(5, 150, 130, 20);
		    tfnumdoss.setBounds(135, 150, 140, 20);
		    	infosprecompte.add(labnumdoss);
		    	infosprecompte.add(tfnumdoss);
		
		  labcdvendeur.setBounds(5, 180, 100, 20);
		tfcdvendeur.setBounds(135, 180, 140, 20);
				infosprecompte.add(labcdvendeur);
				infosprecompte.add(tfcdvendeur);
		
	
		labmontnet.setBounds(5, 210, 130, 20);
		tfmontnet.setBounds(135, 210, 140, 20);
				infosprecompte.add(labmontnet);
				infosprecompte.add(tfmontnet);
		
		
		 labmonttot.setBounds(5, 240, 130, 20);
		tfmonttot.setBounds(135, 240, 140, 20);
	    		infosprecompte.add(tfmonttot);
	    		infosprecompte.add(labmonttot);
	 
	    
	    labmontmens.setBounds(5, 300, 130, 20);
	    tfmontmens.setBounds(135, 300, 140, 20);
	    		infosprecompte.add(tfmontmens);
	    		infosprecompte.add(labmontmens);
	    
	    labnbrmois.setBounds(5, 330, 130, 20);
	    tfnbrmois.setBounds(135, 330, 140, 20);
	    		infosprecompte.add(tfnbrmois);
	    		infosprecompte.add(labnbrmois);
	    
	    labdatedebut.setBounds(5, 360, 130, 20);
	    tfdatedebut.setBounds(135, 360, 140, 20);
	    		infosprecompte.add(tfdatedebut);
	    		infosprecompte.add(labdatedebut);
	    
	    labdatefin.setBounds(5, 390, 130, 20);
	    tfdatefin.setBounds(135, 390, 140, 20);
	    		infosprecompte.add(tfdatefin);
	    		infosprecompte.add(labdatefin);
	     
	    //********************** maintenant j'ajoute un jpanel qui contiendra les infos de la cession
	    infosemission = new JPanel();
		infosemission.setBounds(165, 25, 500, 160);
		infosemission.setBackground(Color.GRAY);
		//****************************************************************************************   
		 labidemission.setBounds(170, 30, 80, 20);
		tfidemission.setBounds(290, 30, 120, 20);
				infosemission.add(labidemission);
				infosemission.add(tfidemission);
	     
    //  labnbrdossier.setBounds(270, 60, 40, 20);    
	    labposition.setBounds(170,60, 80, 20);
	    tfposition.setBounds(290, 60, 120, 20);
				infosemission.add(labposition);
				infosemission.add(tfposition);
			//  infosemission.add(labnbrdossier);
				
	    labcodemet.setBounds(170,90, 100, 20);
	     tfcodemet.setBounds(290, 90, 120, 20);
	     		infosemission.add(labcodemet);
	     		infosemission.add(tfcodemet);
	
	    labdate.setBounds(170,120, 90, 20);
	    tfdate.setBounds(290, 120, 120, 20);
				infosemission.add(labdate);
				infosemission.add(tfdate);
	    
	        suivant.setBounds(170, 640, 100, 20);
	        precedent.setBounds(40, 640, 100, 20);
// ***********cette methode renvoi une jtable contenant ttes les donnéés de la matrice agent 
	//        graphismecesscrea.gotrier(300,160 , 100,140 );    
//*************************************************************	
	        FlowLayout fl =null ;

	    contenant = getContentPane();
		infosagent.setLayout(fl);
		infosprecompte.setLayout(fl);
		infosemission.setLayout(fl);
		panelrecherche.setLayout(fl);
		//infosimage = new JLabel("Bienvenue sur CessCrea logiciel de gestion des cessions de creances ...");
	 	//infosimage.setBounds(700, 2, 550, 700);
	    //infosimage.setBackground(Color.CYAN);	
	    //infosimage.repaint();
	    
		contenant.setLayout(fl) ;
		contenant.add(suivant);
		contenant.add(precedent);
		contenant.add(infosagent) ;
		contenant.add(infosprecompte);
		contenant.add(infosemission);
		contenant.add(infosimage);
		contenant.add(panelrecherche);
		
		// ajout des ecouteures d'actions 
		chargercd.addActionListener(this);
		importerevcc.addActionListener(this);
		versEvcc.addActionListener(this);
		chercheragent.addActionListener(this);
		chercheragentdansdepot.addActionListener(this);
		creerdepot.addActionListener(this);
		triercd.addActionListener(this);
		suivant.addActionListener(this);
		precedent.addActionListener(this);
		bouttonrecherche.addActionListener(this);
	
	
	}	
		
	public static void etatnull(){
		// instanciation  de l'etat de l'agent a null
		tfnom.setText("");
	    tfmatricule.setText("");
		tfcin.setText("");
		tfchapitre.setText("");
		tfnumdoss.setText("");
		tfcdvendeur.setText("");
		tfmontnet.setText("");
		tfmonttot.setText("");
		tfmontmens.setText("");
		tfnbrmois.setText("");
		tfdatedebut.setText("");
		tfdatefin.setText("");
		tfidemission.setText("");
		tfcodemet.setText("");
		tfposition.setText("");
		tfdate.setText("");
		//image vide initiale
	//infosimage.setVisible(false);
		//	infosimage = new JLabel("Bienvenue sur CessCrea logiciel de gestion des cessions de creances ...");
 	//infosimage.setBounds(700, 2, 550, 700);
    //infosimage.setBackground(Color.CYAN);	
    //infosimage.repaint();
	//	infosimage.setEnabled(false);
		suivant.setEnabled(false);
		precedent.setEnabled(false);		
  } 
	
    @SuppressWarnings("static-access")
	public static void genereragent(Agent ag){
  if(!(ag == null )){
	            tfnom.setText(ag.getnom());
      			tfmatricule.setText(ag.getmatricule());
    			tfcin.setText(ag.getcin());
    			tfchapitre.setText(ag.getchap());
    			tfnumdoss.setText(ag.getnumdoss());
    			tfcdvendeur.setText(ag.getcdvendeur());
    			tfmontnet.setText(ag.getmontnet());
    			tfmonttot.setText(ag.getmonttotale());
    			tfmontmens.setText(ag.getmontmensuel());
    			tfnbrmois.setText(ag.getnbremois());
    			tfdatedebut.setText(ag.getdatedebut());
    			tfdatefin.setText(ag.getdatefin());
    			tfidemission.setText(ag.getidcession());
    			tfcodemet.setText(ag.getcodemetteur());
    			tfposition.setText(ag.getposition()+" / "+ag.getnbredossier()+" dossier");
    			tfdate.setText(ag.getdatemission());
    			
    			             
  
  }
else{JOptionPane.showMessageDialog(panelrecherche, "agent introuvable"," Aucne emission ou agent trouvé ",JOptionPane.ERROR_MESSAGE); }
  
    }
    
    public static  JLabel getimagelabel(String path, String nom){
    	FenetreTraitement.icon = createImageIcon(path+nom, nom);
    	FenetreTraitement.image = icon.getImage();
    	FenetreTraitement.imagezoom = new ImageIcon(FenetreTraitement.image. getScaledInstance(500, -1,Image.SCALE_DEFAULT));
        //labelzoom = new JLabel(imagezoom, JLabel.CENTER);
       //labelzoom = new JLabel(imagezoom, JLabel.CENTER);
    	FenetreTraitement.labelzoom.setIcon(imagezoom);
    return FenetreTraitement.labelzoom;
    }
       
     public static ImageIcon createImageIcon(String path, String description) {
        
        if (path != null) {
            return new ImageIcon(path, description);
        } else {
            System.err.println("fichier introuvable: " + path);
            return null;
        }
    }
     
     public  static void genererimage(Agent ag, String pathevcc){
    	 //   construire la nouvelle image del'agent
    	// contenant.add(infosimage);
 if(!(ag.getfichim() == null)){  
	  
	 infosimage = getimagelabel(pathevcc+"\\EVCC_Images\\", ag.getfichim());
      	infosimage.setBounds(700, 2, 550, 700);
        infosimage.setBackground(Color.CYAN);	
        infosimage.repaint();
                 }
 else{  
	  infosimage = getimagelabel("/meslogos.gif", " aucune image n'a été trouvé");
	infosimage.setBounds(700, 2, 550, 700);
   infosimage.setBackground(Color.CYAN);	
    infosimage.repaint();    }
 }

     public void paintComponent(Graphics g) {
      super.paintComponents(g);
      if (FenetreTraitement.image != null) {
       g.drawImage(FenetreTraitement.image, 0, 0,FenetreTraitement.infosimage );
      }
      
     
    }

     public static void chargerevcc(String pathevcc){
    	 parseurevcc.parseur.loaddata(pathevcc);
         agenttemp =  getagent(pathevcc, 1);
         infosimage.setVisible(true);
              genereragent(agenttemp);
              genererimage(agenttemp,pathevcc);
              System.out.println(agenttemp.getfichim());
        
     }
     public String construirepathdepot(String code, String date ){
    	String res =(System.getenv("WScesscrea").trim()+"\\"+code+"\\"+date).trim();
    	
    	 
    return res; }
 	public static Agent trouveragentBy(String critere, String s) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException{
     	Agent agres = new Agent();
     	/*chemindepot = "C:\\Documents and Settings\\Maestro\\Bureau\\test00\\4929\\200909\\";*/
 	    Vector<String>   vectexpressions =   new Vector<String>();
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Nom/text()");
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Matricule/text()");
 	    	             vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Cin/text()");
 	    	             vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Chapitre/text()");
 	    	             vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/FichierImage/text()");
 	    	    	     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/NumeroDossier/text()");
 	    	    	     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/CodeRevendeur/text()");
 	    	    	     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/MontantNet/text()");
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/MontantTotal/text()");
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/MontantMensuel/text()");
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/NombreMois/text()");
 	                     vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/DateDebut/text()");
     	                 vectexpressions.add("//EVCC/Agent["+critere+"='"+ s +"']/Precompte/DateFin/text()");
     	                
     	String res = null;
     	Vector<String> resultcompile = new Vector<String>();
 		 String  entryFilexml = repath(trouverevccxml(pathdepot));

 		 
   domFactory = DocumentBuilderFactory.newInstance();
  domFactory.setNamespaceAware(true); // never forget 
  builder = null;
  builder = domFactory.newDocumentBuilder();
   doc =  builder.parse(entryFilexml);
   factory = XPathFactory.newInstance();
   xpath = factory.newXPath();
  
    Iterator<String> iter =  vectexpressions.iterator();
             int compteur = 0 ;
while(iter.hasNext()){    
     	if (iter!=null){
  
     		javax.xml.xpath.XPathExpression expr = xpath.compile((String)iter.next());
                                Object result = expr.evaluate(doc, XPathConstants.NODESET);
                               NodeList nodes = (NodeList) result;
       
                               for (int i = 0; i < nodes.getLength(); i++) {
                             	  res = nodes.item(i).getNodeValue();
                             	  //System.out.println(res);
                             	  resultcompile.add(res);  /*puis j'increment le compteur position expression*/    compteur ++;     }
     		}
     	}// fin de l'iterator sur boucle while
     

    // ici je devrai d'abord donner des valeures aux variables supplémentaires d'infos evcc et de position agent dans evcc
           // info Evcc ---->   a recupérer grace a extraire evcc infos()
 	Vector<String>  vectinfos = parseurevcc.parseur.generercdinfo(pathdepot);
        residcess = vectinfos.get(0);   rescdemet = vectinfos.get(1);  ;  resdatemis =vectinfos.get(2);  resnbrdoss = vectinfos.get(4);
           // position agent ----> a récupérer par methode get position agent by critére(nom ou cin ou matr  ou numdoss)
               parseurevcc.parseur.loaddata(pathdepot);// attention ane pas oublier le loaddata(path) avant tte extraction
               respos = parseurevcc.parseur.extrairepositionagent(resultcompile.get(0));
                                     /// c coooooooooooooooool
                   
     // ici j'ajoute les positions manquantes pour pouvoir instancié un objet Agent(avec String position + 17 autres String de données)
     	     resultcompile.add(0,respos);
        	     resultcompile.add(14,residcess);
     	     resultcompile.add(15,rescdemet);
     	     resultcompile.add(16,resdatemis);
     	     resultcompile.add(17,resnbrdoss);
    //ici j'instancie mon agent resultant avec le vecteur resultat de compilation
         agres =	Agent.agentbyvect(resultcompile);
    
    return agres;
    }
     public static String trouverevccxml(String pathrep){
  	    String res=null;   
  		File f = new File(pathrep);	 
  	    if(f.isDirectory()){
  	    	String str[]= f.list();
              for(int i =0;i<str.length;i++)  {
                  File f2 = new File(pathrep+"\\"+str[i]);
                        if(f2.isDirectory())  {  }
                        if(f2.isFile() && (f2.getName()).endsWith(".xml") && (f2.getName()).startsWith("EVCC") ){res = f2.getPath();  }                      	  
                                               }
  	                        }
                return res;       
  	  }
     public static String repath(String init){
 		 String clone = init;
 		 String res  = clone.replace('\\', '/');
 			      return res ;
 	 }

     @SuppressWarnings("static-access")
	public Agent resultatderecherche(String crt, String arg){

         Agent resagent= new Agent();
 		try {
 			resagent = trouveragentBy(crt, arg);
 		} catch (XPathExpressionException e) {
 		} catch (SAXException e) {
 		} catch (IOException e) {
 		} catch (ParserConfigurationException e) {
 		}
 		         System.out.println("l'agent est :\n ");
          resagent.tostring();
 return resagent;
     }
     public static  void trouveragentdansdepot() {
 		if( Recherchedansdepot.fenrecherchetemp==null){
		 Recherchedansdepot.fenrecherchetemp = new Recherchedansdepot();
		 Recherchedansdepot.fenrecherchetemp.setIconImage(
    	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
		 Recherchedansdepot.fenrecherchetemp.setVisible(true);}
 		else{  Recherchedansdepot.field.setText("");
 			//Recherchedansdepot.fenrecherchetemp.dispose();
 			Recherchedansdepot.fenrecherchetemp = new Recherchedansdepot();
 			Recherchedansdepot.fenrecherchetemp.setIconImage(
    	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
 			 Recherchedansdepot.fenrecherchetemp.setVisible(true);}
		 }
     

       @SuppressWarnings("static-access")
public void actionPerformed(ActionEvent e) {
		if(e.getSource()== suivant ){     if(inc < Integer.parseInt(agenttemp.getnbredossier())){
			                                 inc = inc +1;
										agenttemp = getagent(pathdepot,inc);
											agenttemp.tostring();
										genereragent(agenttemp);
									    genererimage(agenttemp,pathdepot);	}  }
									  		
		if(e.getSource()== precedent ){  
										if(inc>=2){
													inc =inc -1;
		                                           agenttemp = getagent(pathdepot,inc);
    	                                           genereragent(agenttemp); 
    	                                           genererimage(agenttemp,pathdepot);    }
				                      }
		
		if(e.getSource() == creerdepot){Evenements.goworkspace();}
		if(e.getSource() == chargercd){Evenements.gochargementcd();}
		if(e.getSource() == importerevcc){
		
			if( composants.ImporterEvcc.tempimportframe==null){
				
				composants.ImporterEvcc.tempimportframe  =  new ImporterEvcc();
				composants.ImporterEvcc.tempimportframe.setVisible(true);}
		 		
			else{  composants.ImporterEvcc.fieldSRC.setText("");
		 		
		 		composants.ImporterEvcc.tempimportframe  =  new ImporterEvcc();
				composants.ImporterEvcc.tempimportframe.setVisible(true);}
				 
		      }

		if(e.getSource() == versEvcc){Evenements.goevcc();}
		if(e.getSource() == chercheragentdansdepot){   trouveragentdansdepot();}
	        
		if(e.getSource() == chercheragent){
                     panelrecherche.setVisible(true);
                     panelrecherche.setEnabled(true);
                     }
		if(e.getSource() == bouttonrecherche){     
             			 critererecherche   = (String) MaCombo.getSelectedItem();
             	         argumentrecherche  =    field.getText();
          System.out.println("critére :  "+  critererecherche +"\n argument : "+ argumentrecherche +"\n");
        int r = Integer.parseInt( parseurevcc.parseur.extrairepositionagent((String)argumentrecherche));
                             agenttemp =  getagent(pathdepot,r); 
					         agenttemp.tostring();
						   	genereragent(agenttemp);
					  		genererimage(agenttemp,pathdepot);
     if(String.valueOf(r)==null){ 
    	 JOptionPane.showMessageDialog(this.panelrecherche, "agent introuvable"," ERREURE DE Recherche ",JOptionPane.ERROR_MESSAGE);  }
         	    		
		}// fin evenement recherche
       
		if(e.getSource() == triercd){
			       System.out.println("  code  :  "+codeemm+"          "+"  date  :   "+dateemm);
			          
			       Evenements.gotrierdansunetable(310, 575, 950, 150,codeemm,dateemm);
			                          
		                    //  SimpleTableSelectionDemo.lancerjtableevcc(codeemm, dateemm)  ; 
		
		                          }
		}
		
   public static Agent getagent(String pathevcc , int pos){
           Agent ag =null;   
	   Vector<String>	res = 	parseurevcc.parseur.extrairevecteuragent(pathevcc,pos); // extraction d'un vecteur de string les donées d'un agent par position pos
        if(!(res==null)){	 ag =   Agent.agentbyvect(res);  }  // creer un agent avec ces données 
        else{ ag = null;  } 
       return ag; 	 
         }
     public static String construirepathevcc(String code,String date ){
    	 String res =null;
    	 res = System.getenv("WScesscrea")+"\\"+code+"\\"+date+"\\";
    	 pathdepot = res;
     return res;
     } 
    
	
	public static JFrame creerfenetre2(String pathevcc ,int pos){
		inc =tete;
		parseurevcc.parseur.codeemm= codeemm;
		parseurevcc.parseur.dateemm= dateemm;
		parseurevcc.parseur.chemindepot =pathevcc;//System.getenv("WScesscrea").trim()+"\\"+codep+"\\"+datepath;
Vector<Vector<String>> retour =parseurevcc.parseur.loaddata(pathevcc);
		if(!(retour==null)){   infosimage.setVisible(true);
			//fentemp = new FenetreTraitement();   // construction de la fenetre
		                agenttemp = getagent(pathevcc,pos);
           	             genereragent(agenttemp);
           	             genererimage(agenttemp,pathdepot);
           	          fentemp = new FenetreTraitement();   // construction de la fenetre
   
 //fentemp.setExtendedState(fentemp.PROPERTIES);
           	       fentemp.setIconImage(
           	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
           	     
           	            
      	     //  ImageIcon monIcon= new ImageIcon(System.getProperty("user.dir")+"\\icone.gif".trim());
     	     // fentemp.setIconImage(monIcon.getImage());
     	      //fentemp.repaint();
   //  	     System.out.println(fentemp.getIconImage());
       
  //fentemp.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.gif".trim()));
            	       //fentemp.setBackground(Color.blue);
           	       fentemp.setVisible(true);
    			   fentemp.setEnabled(true);
    			  
		inc = pos;
	       tete = pos; }
		else{  JOptionPane.showMessageDialog(fentemp, "retour du parseur.loaddata null \n ----> extraction d'agent null" );
			etatnull();
		fentemp = new FenetreTraitement();   // construction de la fenetre
		//fentemp.setBackground(Color.blue);
        fentemp.setVisible(true);
        fentemp.setEnabled(true);
		        
inc = pos;
tete = pos;}
			return fentemp;    
		}
	public static String ultrarepath(String old){
	
		String chg1 = old.replace("\\", "8");
	    String chg2 = chg1.replace('8', '/');
	System.out.println(chg2); 
	return chg2;
		
	}
	public static void  govisibleim(){
		
		 infosimage.setVisible(true);
	}
	
	public static void genereretatevcc(String code, String date){
		
        codeemm =code;
        dateemm =date;
        pathdepot = construirepathevcc(codeemm, dateemm);
		if((new File(pathdepot).exists())){
			if(!(fentemp==null)){
				fentemp.dispose();
			fentemp = creerfenetre2(pathdepot,1);
			      }if(fentemp==null){ 
				fentemp = creerfenetre2(pathdepot,1);	
				                     }
			                }else{ JOptionPane.showMessageDialog(fentemp, "EVCC introuvable");    }
	        }

	public static void genereretatagent(String code, String date, int posag){
		    genereretatevcc(code, date);
		inc = posag;
        agenttemp = getagent(pathdepot,inc);
        genereragent(agenttemp); 
        genererimage(agenttemp,pathdepot);    
        
	}	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException,IOException, XPathExpressionException{
		if((new File(System.getProperty("user.dir")+"\\icone.gif")).exists()){
	    System.out.println(System.getProperty("user.dir")+"\\icone.gif"+"\t existe");
	    
	    genereretatevcc("5050", "200904");
		}else{
	    System.out.println(System.getProperty("user.dir")+"\\icone.gif"+"\t n'existe pas");}
		
		  
		 
		  
		  
		  
		  
		  
		  
		  
	}
	
}
		  
		  
		  
		  
		  
        //codeemm ="4929";
        //dateemm ="200909";
        //pathdepot = construirepathevcc(codeemm, dateemm);
		
	 //   fentemp = creerfenetre2(pathdepot,2);    
	    
       // infosimage.setVisible(false);
      
	    /*
		try {
			@SuppressWarnings("unused")
	//		Process p = new ProcessBuilder("explorer.exe", pathdepot).start();
		} catch (IOException e) {
			 JOptionPane.showConfirmDialog(null, "erreure d'ouverture du depot");
			e.printStackTrace();
		}
	*/      
		 
		//System.out.println(System.getenv().put("Path","C:\\Documents and Settings\\Maestro\\Bureau\\test00"));
		//Process  p = Runtime.getRuntime().exec();
		//System.out.println(System.getenv());
		//System.out.println(System.getenv("cesscrea"));
	   //System.out.println(cheminworkspace);
		//parseurevcc.Agent resagt = ecrans.RechercheAgentInEvcc.trouveragentBy("Nom", "GOURNI OMAR");
       // System.out.println("l'agent est :\n ");
     //  resagt.tostring();
      //System.out.println(resagt);
      // System.out.println( Integer.parseInt(resagt.getposition()));
    	
//	}
// }	
