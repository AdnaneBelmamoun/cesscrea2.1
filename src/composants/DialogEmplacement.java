package composants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class DialogEmplacement {
	@SuppressWarnings("unused")
	 public static void main(String []arg)  { 
		  try {
			 
			String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			   
			String metal = "javax.swing.plaf.metal.MetalLookAndFeel";
			 
		    String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					 
			String XPlook="com.stefankrause.xplookandfeel.skin;";
			 
        	  UIManager.setLookAndFeel(windows);
   }     catch (Exception err){ testdialog.read2.append("erreur"+err.toString());  }
	 
	 
	testdialog re = new testdialog();
	 
	 } //***********> fin de la classe main 
	  
	  } //*************>  fin de la classe test00
	  
	  
	 //******************************classe FileReaders ************************* 
	  @SuppressWarnings("serial")
	class testdialog extends JFrame implements ActionListener   {
		  	  
	  // On commence d'abord par La declaration de nos variables globales
     public static char c;
     public static InputStream in;	  
	 public static JButton repertoir = new JButton("repertoire racine",new ImageIcon(("closedFolder.gif")));
	 public static JButton fermer = new JButton("Fermer",new ImageIcon(("world2.gif"))); 
	 public static JTextArea read2 = new JTextArea("",10,25);
	 public static JTextField nomdoss = new JTextField(30);
	 public static String[] lecteures = {"c:", "d:", "e:", "f:",	"g:", "h:","i:","j:","k:"}; 	
	 public static JComboBox lecteurCombo =new JComboBox(lecteures)  ;
	 public static String choixcombo ;
	  public Container contenant = getContentPane();
  	   
//   ceci est le constructeur de la classe test
	       
public testdialog()   {
  super("afficheur de dossiers  ....-----> autheur :  bel. adnane") ;
	  setSize(390,360);
	  setIconImage(Toolkit.getDefaultToolkit().getImage("fic.gif"));
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setVisible(true);
//*********************************************************************************************************************************
Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	  setLocation((screen.width - getSize().width)/2,(screen.height - getSize().height)/2);
//*********************************************************************************************************************************	 
FlowLayout flm = new FlowLayout();
	  contenant.setLayout(flm);
	  contenant.setBackground(Color.orange);
//**********************************************************************************************************************************	 
JPanel est = new JPanel();
   est.setLayout(flm);
    JScrollPane sp = new JScrollPane(read2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

//**********************************************************************************************************************************	  
  repertoir.setSize(50, 15);
  lecteurCombo.setMaximumRowCount(3) ;
  lecteurCombo.setEditable(true);
  contenant.add(sp);       // ajout de scroll pane(sp) au container (pr)
  contenant.add(repertoir);
  contenant.add(lecteurCombo);
  contenant.add(nomdoss);
            est.add(fermer);	  
      repertoir.addActionListener(this);
      fermer.addActionListener(this);
      read2.setEditable(false);
      contenant.add(est,BorderLayout.EAST);
           setContentPane(contenant);
	  }   // fin du constructeur de la classe test

  public void setFont(java.awt.Font f)  {
	                        read2.setFont(new Font("Times new Roman",Font.PLAIN, 13));
	                        read2.setForeground(Color.green);
	                        read2.setBackground(Color.BLACK);
	  }
	  
	 // ********************methode void repGo() qui fait un listage du repertoir courant*************************
  public void repGo(String pathrepertoire) {
	  effacerafficheur();
	  	  read2.append("\n"+"Debut du listage du repertoire   "+pathrepertoire);
	  	     File fichier1  = new File(pathrepertoire);
	          if(fichier1.isDirectory()) {
	                           read2.append("\n"+pathrepertoire+"******repertoire a lister "+"\n");
	                           String str[]= fichier1.list();
	                        for(int i =0;i<str.length;i++)  {
	                            File fichier2 = new File(pathrepertoire+"/"+str[i]);
	                                  if(fichier2.isDirectory())  {read2.append("\n" +"  "+str[i]+"\n");}
	                                 // else {}//read2.append("\n"+"fichier :  "+ str[i]+"\n"); }
	                                     }// fin du for
	                             }// fin du if
	 
	  }//Fin du listeur
  public static  void effacerafficheur() {
	 		if(read2.getText()!=null){
		 		 read2.setText("");
		       }
	 		else if(read2.getText() == "") { read2.append("Il n'ya rien à supprimer !"); }
		    }
	  public static String concatenechoix(String chdepart, String subchaine){
		  String temp = chdepart.concat(subchaine);
		  
		  return temp;
 }

 //******* methode actionperformed(ActionEvent e) pour programmer les evenement d'appui sur boutton ou autres***	 
	  
  public void actionPerformed(ActionEvent evenement){   
	  
	  if(evenement.getSource()==fermer){  System.exit(0);  } 
	  else if(evenement.getSource()==repertoir)  {	 choixcombo = (String) lecteurCombo.getSelectedItem(); 
	                                                  String ch = nomdoss.getText();
	                                                  String res =choixcombo+"\\"+ch+"\\"; 
		                                             repGo(res);
		                                             nomdoss.setText(ch+"\\");}
	}  






  
	  }  //********************** Fin de la classe test *******************
	  
	 

	
	


