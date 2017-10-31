package composants;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import parseurevcc.parseur;
import creationdepot.creationfichier;
import ecrans.Evenements;
import ecrans.FenetreTraitement;

public class ImporterEvcc extends JFrame  implements ActionListener{
	
	public static final long serialVersionUID = 1L;
	public static String nomworkspace = null;
	public static parseur temp;
	public static File jfcimport = null;
	public static File source=null;
	

	public static JFrame tempimportframe = new JFrame();

	public static JLabel labSRC = new JLabel(" Source Evcc  : ") ;
    
    public static JTextField fieldSRC = new JTextField("veuillez choisir un emplacement ");
    
	public static JButton parcourirsource = new JButton("parcourir source Evcc");
	
	public static JButton importerevcc = new JButton(" Importer L'Evcc Vers WorkSpace CessCrea ");
	
	public ImporterEvcc() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
		//setDefaultCloseOperation(ImporterEvcc.DISPOSE_ON_CLOSE);
		setTitle("Importer Evcc vers WScesscrea : --------> "+"    @ adnane belm.") ;
		setBounds(200,300,650,200) ;
		

		labSRC.setBounds(15,40,90,20);
	
		fieldSRC.setBounds(115,40,300,20);
		//fieldSRC.setText(System.getProperty("user.dir"));
		
		parcourirsource	.setBounds(430,40,190,20);
		
		importerevcc.setBounds(230, 120, 350, 20);
		
		FlowLayout fl =null ;
		Container  contenant = getContentPane();
		contenant.setLayout(fl) ;
		contenant.add(labSRC);
		
		contenant.add(fieldSRC);
		
		contenant.add(parcourirsource);
		contenant.add(importerevcc);
		
		parcourirsource.addActionListener(this);
		importerevcc.addActionListener(this);
		
	
	}

	public static void importevccaction(String srcEvcc){
if(!(srcEvcc==null))	{	
		temp = new parseur();
		source = new File(srcEvcc);

		//jfc =	 Evenements.sequencefc("Atteindre Espace de Travail"); 
        
	   	//   Evenements.diropen.setCurrentDirectory(new File(System.getenv("WScesscrea")));
//		   System.out.println("nomselection : "+Evenements.diropen.getSelectedFile());
	   //if(!(Evenements.diropen.getSelectedFile()==null)){
		 //  nomworkspace = jfc.getName();		                       
	    // if(nomworkspace != null){
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

	// copie du contenu du cd ou de le source d'import d'evcc  dans le depot WScesscrea
//File WorkSp = new File(System.getenv("WScesscrea"));
//if(!(destination.exists())){
		if(!(source==null)){
if(!(source.getPath()==destination.getPath())){			
	try {  
		copier(source,destination);
		} catch (IOException e) {	e.printStackTrace();	}
		}else{JOptionPane.showMessageDialog(null," Cette Evcc Existe deja dans Work Space cesscrea");}        
          }else{ JOptionPane.showMessageDialog(null," Cet Emplacement ne contient aucune Remise Evcc " );}
		}else{/** aucune selection ou selection null ----> ne rien faire   **/}
}
	
	
	   
	public static void main(String[] args) {
tempimportframe  =  new ImporterEvcc();
tempimportframe.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
	if(e.getSource()== importerevcc ){ 
		System.out.println("selection field src :"+  fieldSRC.getText());
		importevccaction(fieldSRC.getText());}
	if(e.getSource()== parcourirsource ){
		        jfcimport =  Evenements.sequencefc("Importer Source Evcc ");
 	                    Evenements.diropen.setCurrentDirectory(new File(System.getProperty("user.home")));//new File(System.getenv("WScesscrea")));
	                    System.out.println("nomselection : "+Evenements.diropen.getSelectedFile());
	                     
            if(!(Evenements.diropen.getSelectedFile()==null)){fieldSRC.setText(Evenements.diropen.getSelectedFile().getPath());}
	
	}
	
		
	}

	 public static void copiedossier(File from, File to) throws IOException {
		
		   to.mkdir();
		  
		  final File[] inDir = from.listFiles();
		  for (int i = 0; i < inDir.length; i++) {
		    File file = inDir[i];
		   copier(file, new File(to, file.getName()));
		  }
		
		 }
		
		 public static void copierFichier(File src,  File dest) throws IOException {
		  InputStream inStream = new FileInputStream(src);
		  OutputStream outStream = new FileOutputStream(dest);
		  Copie.copier(inStream, outStream, (int) Math.min(src.length(), 4*1024));
		  inStream.close();
		  outStream.close();
		 }
		
		 public static void copier( File src,  File dest) throws IOException {
		  if (src.isFile()) {
		   copierFichier(src, dest);
		  } else if (src.isDirectory()){
		   copiedossier(src, dest);
		  } else {
		   throw new FileNotFoundException(src.toString() + " Source Introuvable" );
		  }
		 } 
		
	
}
