package ecrans;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import composants.ImporterEvcc;

import parseurevcc.Agent;
import parseurevcc.Evcc;

public class Traitement extends JFrame implements ActionListener{
	public static final long serialVersionUID = 1L;
	
	
	public final static String cheminworkspace =System.getenv("WScesscrea");
	public static String  codeemm  ;      
	public static String   dateemm ;    
	public static String pathdepot;     
  
    public static parseurevcc.Agent agenttemp = new Agent();
    
    
   	public static JFrame fentemp; 
    public static int tete ; //= 1 ;
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
// fin des elements de recherche
	//
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
// du menu a ses elements 
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

	
	public Traitement()  {
    	super() ;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
		setTitle("Bienvenu dans CessCrea logiciel de traitement des cessions de creances : --------> "+"  @ adnane belm.") ;
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
		importerevcc = new JMenuItem("Importer Evcc") ;
		elementsdemarage.add(chargercd) ;
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
		rechercher.add(chercheragent) ;
		chercheragentdansdepot = new JMenuItem("chercher Agent dans tout le depot") ;
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
	     
       
	    labposition.setBounds(170,60, 80, 20);
	    tfposition.setBounds(290, 60, 120, 20);
				infosemission.add(labposition);
				infosemission.add(tfposition);
	
				
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

	          FlowLayout fl = null ;
        	    contenant   = getContentPane();
		
        infosagent.setLayout(fl);
		infosprecompte.setLayout(fl);
		infosemission.setLayout(fl);
		panelrecherche.setLayout(fl);
		//affichage d'un label de demarage
		infosimage = new JLabel("Bienvenue sur CessCrea logiciel de gestion des cessions de creances ...");
	 	infosimage.setBounds(700, 2, 550, 700);
	    infosimage.setBackground(Color.CYAN);	
	    infosimage.repaint();
	    
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
	
	
	@SuppressWarnings("static-access")
	public Traitement(String code, String datem,int pos){
		
		this.codeemm = code;
		this.dateemm = datem;
		this.pathdepot = construirepathevcc(this.codeemm, this.dateemm);
		Evcc evc = Evcc.charger_evcc(code, datem);
		agenttemp =  parseurevcc.Agent.agentbyvect(evc.getListagents().get(pos-1));
		inc = pos-1;
		//tete = pos-1;
		genererimage(agenttemp, this.pathdepot);
		genereragent(agenttemp);
		fentemp = new Traitement();
		fentemp.setTitle("fenetre traitement de l'emission :  "+"----->"+"organisme  : "+codeemm+"     date emission : "+dateemm+"  -------> "+"Cesscrea2.0");
		fentemp.setVisible(true);
		fentemp.setEnabled(true);
		   
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
else{//JOptionPane.showMessageDialog(panelrecherche, "agent introuvable"," Aucne emission ou agent trouvé ",JOptionPane.ERROR_MESSAGE); }
}
    }
    
    public static  JLabel getimagelabel(String path, String nom){
    	icon = createImageIcon(path+nom, nom);
    	image = icon.getImage();
    	imagezoom = new ImageIcon(image. getScaledInstance(500, -1,Image.SCALE_DEFAULT));
    	labelzoom.setIcon(imagezoom);
    return labelzoom;
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
	 // if(!(pathevcc.endsWith("\\"))){
	 infosimage = getimagelabel(pathevcc+"\\EVCC_Images\\", ag.getfichim());
      	infosimage.setBounds(700, 2, 550, 700);
        infosimage.setBackground(Color.CYAN);	
        infosimage.repaint();
       //          }else{infosimage = getimagelabel(pathevcc+"EVCC_Images\\", ag.getfichim());
         //      	infosimage.setBounds(700, 2, 550, 700);
           //     infosimage.setBackground(Color.CYAN);	
             //   infosimage.repaint();}
	  }
 else{  
	  infosimage = getimagelabel("/meslogos.gif", " aucune image n'a été trouvé");
	infosimage.setBounds(700, 2, 550, 700);
   infosimage.setBackground(Color.CYAN);	
    infosimage.repaint();    }
 }

     public void paintComponent(Graphics g) {
      super.paintComponents(g);
      if (image != null) {
       g.drawImage(image, 0, 0,infosimage );
      }
      
     
    }

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== suivant ){  
			if(inc < Integer.parseInt(agenttemp.getnbredossier())){
                            inc = inc +1;
		            agenttemp = getagent(pathdepot,inc);
		                Agent.tostring();
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
if(e.getSource() == chercheragent){
               panelrecherche.setVisible(true);
               panelrecherche.setEnabled(true);
}

if(e.getSource()== chercheragentdansdepot){trouveragentdansdepot();}

if(e.getSource() == bouttonrecherche){     
				critererecherche   = (String) MaCombo.getSelectedItem();
				argumentrecherche  =    field.getText();
				System.out.println("critére :  "+  critererecherche +"\n argument : "+ argumentrecherche +"\n");
				int r = Integer.parseInt( parseurevcc.parseur.extrairepositionagent(argumentrecherche));
				if(!(String.valueOf(r)==null)){ 				
				agenttemp =  getagent(pathdepot,r); 

				genereragent(agenttemp);
				genererimage(agenttemp,pathdepot);}
if(String.valueOf(r)==null){ 
JOptionPane.showMessageDialog(this.panelrecherche, " Agent Introuvable dans Cette EVCC "," Erreure De Recherche " +
													" ",JOptionPane.ERROR_MESSAGE);  }

}// fin evenement recherche

if(e.getSource() == triercd){}//Evenements.gotrierdansunetable(200, 240, 250, 400,FenetreTraitement.codeemm,FenetreTraitement.dateemm);}
}
public static  void trouveragentdansdepot() {
	   
	
	if( Recherchedansdepot.fenrecherchetemp==null){
		 Recherchedansdepot.fenrecherchetemp = new Recherchedansdepot();
		 Recherchedansdepot.fenrecherchetemp.setVisible(true);}
		else{  Recherchedansdepot.field.setText("");
			Recherchedansdepot.fenrecherchetemp.dispose();
			Recherchedansdepot.fenrecherchetemp = new Recherchedansdepot();
			Recherchedansdepot.fenrecherchetemp.setVisible(true);}
		    
		 //Recherchedansdepot.fenrecherchetemp = new Recherchedansdepot();
		 //Recherchedansdepot.fenrecherchetemp.setVisible(true);
		 }


public static Agent getagent(String pathevcc , int pos){
Agent ag =null;   
Vector<String>	res = 	parseurevcc.parseur.extrairevecteuragent(pathevcc,pos); // extraction d'un vecteur de string les donées d'un agent par position pos
if(!(res==null)){	 ag =   Agent.agentbyvect(res);  }  // creer un agent avec ces données 
else{ ag = null;  } 
return ag; 	 
}
		
public static void toutinvisible(){
	suivant.setEnabled(false);		precedent.setEnabled(false);	tfnom.setEnabled(false);	    tfmatricule.setEnabled(false);
	tfcin.setEnabled(false); 	  	tfchapitre.setEnabled(false);   tfnumdoss.setEnabled(false);    tfcdvendeur.setEnabled(false);
	tfmontnet.setEnabled(false);    tfmonttot.setEnabled(false);	tfmontmens.setEnabled(false);   tfnbrmois.setEnabled(false);
	tfdatedebut.setEnabled(false);  tfdatefin.setEnabled(false);    tfidemission.setEnabled(false); tfcodemet.setEnabled(false);
	tfposition.setEnabled(false);   tfdate.setEnabled(false);       triercd.setEnabled(false);      chercheragent.setEnabled(false);
	/*
	tfnom.setOpaque(true);			tfmatricule.setOpaque(true);    tfcin.setOpaque(true);         	tfchapitre.setOpaque(true);
	tfnumdoss.setOpaque(true);  	tfcdvendeur.setOpaque(true);    tfmontnet.setOpaque(true);      tfmonttot.setOpaque(true);
	tfmontmens.setOpaque(true);     tfnbrmois.setOpaque(true);  	tfdatedebut.setOpaque(true);    tfdatefin.setOpaque(true);
	tfidemission.setOpaque(true);   tfcodemet.setOpaque(true);      tfposition.setOpaque(true);     tfdate.setOpaque(true);
	*/
	tfnom.setVisible(false);		tfmatricule.setVisible(false);    tfcin.setVisible(false);         	tfchapitre.setVisible(false);
	tfnumdoss.setVisible(false);  	tfcdvendeur.setVisible(false);    tfmontnet.setVisible(false);      tfmonttot.setVisible(false);
	tfmontmens.setVisible(false);     tfnbrmois.setVisible(false);    tfdatedebut.setVisible(false);    tfdatefin.setVisible(false);
	tfidemission.setVisible(false);   tfcodemet.setVisible(false);    tfposition.setVisible(false);     tfdate.setVisible(false);

}
	
public static JFrame creerfenetre3(String pathevcc ,int pos){
	inc =tete;
	if(SysTray.tray == null) {new SysTray();}
	else{}
Vector<Vector<String>> evcc =parseurevcc.parseur.loaddata(pathevcc);
	if(!(evcc==null)){
		infosimage.setVisible(true);
	    agenttemp = getagent(pathevcc,pos);
	    
	    genererimage(agenttemp,pathdepot);
	    genereragent(agenttemp);
	    fentemp = new Traitement();   // construction de la fenetre 

       	       fentemp.setVisible(true);
			   fentemp.setEnabled(true);
			   fentemp.setTitle("fenetre traitement de l'emission :  "+"----->"+"organisme  : "+codeemm+"     date emission : "+dateemm+"  -------> "+"Cesscrea2.0");
			  
	inc = pos;
       tete = pos; }
	else{  //etatnull();
	fentemp = new Traitement();   // construction de la fenetre
	toutinvisible();
    fentemp.setVisible(true);
    fentemp.setEnabled(true);
            
inc = pos;
tete = pos;}
		return fentemp;    
	}
	
public static String construirepathevcc(String code,String date ){
	 String res =null;
	 res = System.getenv("WScesscrea")+"\\"+code+"\\"+date+"\\";
	 pathdepot = res;
return res;
} 

public static void main(String[] args) {
	  //codeemm ="4929";
      //dateemm ="200909";
      //pathdepot = construirepathevcc(codeemm, dateemm);
	  	tete =2;
	    fentemp = creerfenetre3(pathdepot,tete); //new Traitement("4929","200909",tete) ;    
	    

	}

	

}
