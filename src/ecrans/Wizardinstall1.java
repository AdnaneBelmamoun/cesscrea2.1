package ecrans;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 


public class Wizardinstall1 extends JFrame implements ActionListener{
 
	public static final long serialVersionUID = 1L;
	
	
	
	public static JFrame test1 = new JFrame();
	public static JLabel labinfosetape = new JLabel(" CessCrea a ete installer avec succees  ") ;
	public static JButton terminer = new JButton("Terminer");
	public static JButton annuler = new JButton("Annuler");
	public static JCheckBox affichws = new JCheckBox("Afficher l'espace de travail crée ");
	public static JCheckBox lancer = new JCheckBox(" Lancer CessCrea");
	public static JCheckBox loger = new JCheckBox(" Afficher l'emplacement d'installation de CessCrea \n veuillez Cliquer sur terminer");
	public static JCheckBox link = new JCheckBox(" Creer un  Racourci de CessCrea2.1 dans le Bureau");
	public static String Wschemin = "";

	public Wizardinstall1(){
		
		super() ;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
		setTitle("Fin d'installation de cesscrea : --------> "+"    @ adnane belm.") ;
		setBounds(60,60,900,500) ;
		labinfosetape.setBounds(20,20,500,70);
		terminer.setBounds(600, 400, 90, 20);
		annuler.setBounds(490, 400, 90, 20);
		affichws.setBounds(40, 150, 320, 20);
		lancer.setBounds(40, 200, 320, 20);
		loger.setBounds(40, 250, 320, 20);
		link.setBounds(40, 300, 320, 20);
		affichws.setSelected(false);
		lancer.setSelected(true);
		loger.setSelected(false);
		link.setSelected(true);
 FlowLayout fl =null ;
 Container  contenant = getContentPane();
			contenant.setLayout(fl) ;
			contenant.add(terminer);
			contenant.add(annuler);
			contenant.add(labinfosetape);
			contenant.add(affichws);
			contenant.add(lancer);
			contenant.add(loger);
			contenant.add(link);
			terminer.addActionListener(this);
			annuler.addActionListener(this);
			
	}
	public static void afficherws(){
		try {
			@SuppressWarnings("unused")
			Process p = new ProcessBuilder("explorer.exe", System.getenv("WScesscrea")).start();
		} catch (IOException e) {
			 JOptionPane.showConfirmDialog(test1, "erreure d'ouverture du depot"); 
			e.printStackTrace();
		}
					}
	public static void Afficherlogementcesscrea(){
		try {
			@SuppressWarnings("unused")
			Process p = new ProcessBuilder("explorer.exe", System.getenv("ProgramFiles")+"\\"+"CessCrea2.1").start();
		} catch (IOException e) {
			 JOptionPane.showConfirmDialog(test1, "erreure d'ouverture du depot"); 
			e.printStackTrace();
		}
	}
	
	public static void lancercesscrea(){
	
	Traitement.creerfenetre3(Traitement.pathdepot,1);
	
	}
	public static void creerlesracourci() {
			  creerracourcidansbureau();
		
	}
    public static void logercesscrea() {
    	File f = new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\bin\\");
    	f.mkdirs();
    	/* copie du .jar dans \bin tous dans c:\programmes files\CessCrea2.1*/
    	 copier(new File(System.getProperty("user.dir")+"\\CessCrea2.1.jar"),new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+
                 "\\bin\\"+"CessCrea2.1.jar"));
  try {
    		copiedossier(new File(System.getProperty("user.dir")+"\\images\\"),new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images\\"));
    	} catch (IOException e) {	e.printStackTrace(); }
   try {
		copiedossier(new File(System.getProperty("user.dir")+"\\"),new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\"));
	} catch (IOException e) {	e.printStackTrace(); } 
 

 try {
		copiedossier(new File(System.getProperty("user.dir")),new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1\\"));

	 } catch (IOException e1) {	e1.printStackTrace();}

       /* copie du dossier des images dans /prgrammes files/images     */
 


       //copie l'executable .Exe fais avec le language c dans le dossier programmes files
// File logexe = new File(System.getProperty("user.dir")+"\\CessCrea2.1.exe");
// if(logexe.exists()){
//copier(new File(System.getProperty("user.dir")+"\\CessCrea2.1.exe"),new File(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"
  //     											+"\\CessCrea2.1.exe"));

 //}
 //else{
    	// creation du racourci du .exe dans /programmesfiles/CessCrea2.1/ 
    	
Evenements.exec("Shortcut.exe /f:\""+System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\"+"CessCrea.exe.lnk\" /A:C /t:"+
			"\""+System.getProperty("user.dir")+"\\CessCrea2.1.jar\" /r:1 /i:\""+System.getProperty("user.dir")+"\\images\\icone.ico"+"\" /h:846");
   //  }
	
}

	public void actionPerformed(ActionEvent even) {
		if(even.getSource()== terminer ){
			logercesscrea();
			if(link.isSelected()){creerlesracourci();}
			
                int resoption =JOptionPane.showConfirmDialog(test1,"veuillez redemarrer votre ordinateur " +
                 		                                 "\n a fin que CessCrea soit correctement installé ", "redemarage de votre ordinateur"
                 		                                 ,JOptionPane.YES_NO_OPTION);
                 
                // Wizardinstall1.test1.dispose();
                 System.out.println(resoption);
                 if(resoption == -1){ /*ne rien faire*/      }
                 if(resoption == JOptionPane.NO_OPTION){
                	 JOptionPane.showMessageDialog(test1, "Alerte CessCrea" +" \n veuillez le redemare manuellement");
                	 Wizardinstall1.test1.dispose();
                	 if(loger.isSelected()){Afficherlogementcesscrea();}
                	// if(link.isSelected()){creerlesracourci();}
               	 	if (affichws.isSelected()){ afficherws();  }
               	 	if (lancer.isSelected()){lancercesscrea();}    
		                                                }
         		
                 if(resoption == JOptionPane.YES_OPTION){
                	 System.out.println("c okkk");
                	 
                	 //logercesscrea();
                	 //creerlesracourci();
                	 Wizardinstall1.test1.dispose();
                	 try {
         			@SuppressWarnings("unused")
        		//	Process p = new ProcessBuilder("shutdown"," -r -c \" test de redemarrage du system \" ").start();
         			Process p = Runtime.getRuntime().exec("shutdown -r -c \" CessCrea va maintenant procéder au redemarrage du system\"");
         			
        		          } catch (IOException e) {
        			               JOptionPane.showMessageDialog(test1, "Erreure lors du redemarage du systeme..." +
        			               		                                 " \n veuillez le redemarer manuellement");
        			                        e.printStackTrace();
        			                        }
        		         
        		}
                 else{  /*rien faire*/ }  
		 	}
		if(even.getSource()==annuler){
			int rescancel = JOptionPane.showConfirmDialog(test1, " Etes vous sure de vouloir annuler" +
					"\n l'installation de CessCrea 2.1  ","Annulation d'installation de cesscrea",JOptionPane.YES_NO_OPTION);
			if(rescancel==JOptionPane.YES_OPTION){ System.exit(0);   }
			if(rescancel==JOptionPane.NO_OPTION){   /* ne rien faire*/ }
		}
			
		
	}//System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\bin\\"+"CessCrea2.1.jar"
	
	public static void creerracourcidansbureau(){
		
Evenements.exec("Shortcut.exe /f:\""+System.getProperty("user.home")+"\\Bureau\\CessCrea.exe.lnk\" /A:C /t:"+
   			"\""+System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\bin\\"+"CessCrea2.1.jar"+"\" /r:1 /i:\""+System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images\\icone.ico"+"\" /h:846");
		
	//	Evenements.exec("Shortcut.exe /f:\""+System.getProperty("user.home")+"\\Bureau\\CessCrea.exe.lnk\" /A:C /t:"+"\""+System.getProperty("user.dir")+"\\bin\\CessCrea2.1.jar\" /h:846");

	
	}
	public static void fininstall(String nom , String chemin){
		test1 = new Wizardinstall1();
		 test1.setIconImage(
    	    		Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\images\\icone.jpg"));
    	     
		test1.setVisible(true);
		
	}

	public static void main(String[] args) {
//		Evenements.exec("Shortcut.exe /f:\""+System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\"+"CessCrea.exe.lnk\" /A:C /t:"+
 //   			"\""+System.getProperty("user.dir")+"\\CessCrea2.1.jar\" /r:1 /i:\""+System.getProperty("user.dir")+"\\icone.ico"+"\" /h:846");
	//	fininstall(null, null);
	logercesscrea();
	
	creerracourcidansbureau();
	Afficherlogementcesscrea();
		System.out.println(System.getProperty("user.dir"));
		//System.out.println(System.getProperty("user.home"));//System.getenv("ProgramFiles"));
	 	//Evenements.exec("Shortcut.exe /f:\""+System.getProperty("user.home")+"\\Bureau\\CessCrea.exe.lnk\" /A:C /t:"+"\""+System.getProperty("user.dir")+"\\CessCrea2.1.jar\" /h:846");
		//	test1 = new Wizardinstall1(); 
		//test1.setVisible(true);
		//String jarFileName = "C:\\Documents and Settings\\Maestro\\Bureau\\Testusersetx.jar";//JShellLink.getDirectory("program_files")+
		 
		//This might be a good place to put your program
		//JShellLink link = new JShellLink();  
		//link.setFolder(JShellLink.getDirectory("desktop")); 
	//	link.setName("test rac");
	//	link.setPath(jarFileName); 
	//	link.save();

		
	}
	 public static void copiedossier(File from, File to) throws IOException {
		
		   to.mkdirs();
		  
		  final File[] inDir = from.listFiles();
		  for (int i = 0; i < inDir.length; i++) {
		    File file = inDir[i];
		   copier(file, new File(to, file.getName()));
		      }
		
		 }
		
	public static boolean copier( File source, File destination ){ //Methode permettant la copie d'un fichier
	boolean resultat = false;

	// Declaration des flux
	java.io.FileInputStream sourceFile=null;
	java.io.FileOutputStream destinationFile=null;
	try {
	// Création du fichier :
	destination.createNewFile();
	// Ouverture des flux
	sourceFile = new java.io.FileInputStream(source);
	destinationFile = new java.io.FileOutputStream(destination);
	// Lecture par segment de 0.5Mo
	byte buffer[]=new byte[512*1024];
	int nbLecture;
	while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
	destinationFile.write(buffer, 0, nbLecture);
	}

	// Copie réussie
	resultat = true;
	} catch( java.io.FileNotFoundException f ) {
	} catch( java.io.IOException e ) {
	} finally {
	// Quoi qu'il arrive, on ferme les flux
	try {
	sourceFile.close();
	} catch(Exception e) { }
	try {
	destinationFile.close();
	} catch(Exception e) { }
	}
	return( resultat );
	}
	
}
