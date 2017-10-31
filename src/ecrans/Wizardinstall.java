package ecrans;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Wizardinstall extends JFrame implements ActionListener {

	public static final long serialVersionUID = 1L;
	
	public static JFrame test = new JFrame();
	public static JLabel labinfosetape = new JLabel(" Veuillez definir un emplacement et un nom pour votre espace de travaille ") ;
	public static JLabel labchemin = new JLabel(" Chemin  : ") ;
    public static JLabel labnom = new JLabel(" Nom     : ");
    public static JTextField fieldchemin = new JTextField("veuillez choisir un emplacement ");
    public static JTextField fieldnom = new JTextField("veuillez choisir un nom");
	public static JButton parcourir = new JButton("parcourir...");
	public static JButton suivant = new JButton("Suivant");
	public static JButton annuler = new JButton("annuler");
	
	
	public Wizardinstall(){
		super() ;
	setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
	setDefaultCloseOperation(Wizardinstall.EXIT_ON_CLOSE);
	setTitle("creation d'espace de travaille : --------> "+"    @ adnane belm.") ;
	setBounds(60,60,900,500) ;
	labinfosetape.setBounds(20,20,500,30);
	labchemin.setBounds(30,90,90,20);
	labnom.setBounds(30,120,90,20);
	fieldchemin.setBounds(130,90,400,20);
	fieldnom.setBounds(130,120,400,20);
	parcourir.setBounds(680,90,120,20);
	suivant.setBounds(600, 400, 90, 20);
	annuler.setBounds(710, 400, 90, 20);
	 FlowLayout fl =null ;
     Container  contenant = getContentPane();
		contenant.setLayout(fl) ;
		contenant.add(suivant);
		contenant.add(annuler);
	contenant.add(labinfosetape);
	contenant.add(labchemin);
	contenant.add(labnom);
	contenant.add(fieldchemin);
	contenant.add(fieldnom);
	contenant.add(parcourir);
	suivant.setEnabled(false);
	suivant.addActionListener(this);
	annuler.addActionListener(this);
	parcourir.addActionListener(this);
	installersetx();
	}	
	public static void installersetx(){
		//Evenements.exec("Xcopy /D /v /Y \""+System.getProperty("user.dir")+"\\setx.exe\""+System.getenv("SystemRoot")+"\\system32\\");
		String xcopysetxcmd ="Xcopy \""+System.getProperty("user.dir")+"\\setx.exe\""+" \""+System.getenv("systemROOT")+"\\system32\\\" /c /u /Y";
		System.out.println(xcopysetxcmd);
		Evenements.exec(xcopysetxcmd);
	}
	
	public void actionPerformed(ActionEvent even) {
		if(even.getSource()== annuler ){Wizardinstall.test.dispose(); }    
	
		if(even.getSource()== suivant ){ Wizardinstall.test.dispose(); 
		                                 Wizardinstall1.fininstall(fieldnom.getSelectedText(),fieldchemin.getSelectedText());}
		
		if(even.getSource()== parcourir ){ goparcoure();   } 
	}
	
   public static void parcourir(){				 
		 File   file = null;
   JFileChooser   fc = new JFileChooser();
     fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 	
    int returnVal = fc.showOpenDialog(test);
	      
          if (returnVal == JFileChooser.APPROVE_OPTION) {   
             
		           file = fc.getSelectedFile();
		    String cheminWSchoisi = file.getAbsolutePath();
		       fieldnom.setText(file.getName());
               fieldchemin.setText(file.getAbsolutePath());
               //JOptionPane.showMessageDialog(test, "ouverture: " + file.getName() + "---->    "+ file.getAbsolutePath());
              Evenements.exec("setx WScesscrea \""+cheminWSchoisi +"\" -m");
       		  Evenements.exec("setx WScesscrea \""+cheminWSchoisi +"\" ");
       		suivant.setEnabled(true);
                  		}
          }
	 public static void goparcoure(){
		 
		if(System.getenv("WScesscrea")!=null) { 
int reinstchoix =JOptionPane.showConfirmDialog(test,"  Un dépôt éxiste déja à l'emplacement  :  \n \n" +
				"veuillez confirmer votre demande de redefinition de votre chemin d'espace de travail WScesscrea"+System.getenv("WScesscrea")," Attention :  ",JOptionPane.YES_NO_OPTION);
			suivant.setEnabled(false);
	if(reinstchoix == JOptionPane.YES_OPTION){parcourir();}
	if(reinstchoix == JOptionPane.NO_OPTION){/*ne rien faire*/}
			
			}
		else{
				parcourir();      
 	              } 
 	
	 }
	 public static void demarrerwizard(){
		 test = new Wizardinstall();
		 test.setIconImage(
    	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
   	     
			test.setVisible(true); 
			System.out.println(fieldchemin.getSelectedText());	 
	 }
	 
	
	public static void main(String[] args) {
	     
	demarrerwizard();
   //  installersetx();
	//	String xcopysetxcmd ="Xcopy \""+System.getProperty("user.dir")+"\\setx.exe\""+" \""+System.getenv("systemROOT")+"\\system32\\\" /c";
	//System.out.println(xcopysetxcmd);
//	Evenements.exec(xcopysetxcmd);//"Xcopy \"C:\\Documents and Settings\\Maestro\\Bureau\\setx.exe\" \"C:\\WINDOWS\\system32\\\" /c");//setxcmd.toString());
	//File srcsetx = new File(System.getProperty("user.dir")+"\\setx.exe");
	//File destsetx = new File(System.getenv("systemROOT")+"\\system32\\");
	 
   //Process retcmd =
	//Evenements.exec("cmd");
   //System.out.println(retcmd.getOutputStream().toString());
    //ProcessBuilder p = new ProcessBuilder("cmd",setxcmd);
   /* try {
	@SuppressWarnings("unused")
	Process pr = 	p.start();
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}*/
    
	
	//Enumeration<Object> resel = System.getProperties().elements();
	//while(resel.hasMoreElements()){
   // System.out.println(resel.nextElement().toString());}
	
  //  System.out.println("\n host id :"+SystemEnvironment.getSystemEnvironment().getOsArchitecture());
    
 /*   
    
	try {
		composants.Copie.copier(srcsetx,destsetx);
	} catch (IOException e) {
		JOptionPane.showConfirmDialog(null, "erreure de copie du setx.exe dans C:/windows/system32/ \n " +
											"veuillez le faire manuellement... afin de pouvoir installer \n" +
											"la variable d'environnement WScesscrea ....");
		e.printStackTrace();
	} // */
 


//"copy /D /v /Y \""+System.getProperty("user.dir")+"\\setx.exe \""+" \""+System.getenv("systemROOT")+"\\system32\\\"").start();

	//System.out.println(p.getOutputStream().toString());
	//System.out.println(p.exitValue());
	
		
		//	System.getenv().values().remove(System.getenv("Wscesscrea"));
	}

}
