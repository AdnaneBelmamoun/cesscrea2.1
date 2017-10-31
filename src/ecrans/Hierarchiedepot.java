package ecrans;

    import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;


	public class Hierarchiedepot extends JPanel implements ActionListener {
	public static final long serialVersionUID = 1L;
	public int newNodeSuffix = 1;
	public static String respathevcc =""; 

	public static ArbreDynamique treePanel;
	public static JFrame frame;

	public Hierarchiedepot() {
	
		super(new BorderLayout());

	//Create the components.
	   treePanel = new ArbreDynamique();
	    populateTree(treePanel);

	    JButton bouttonok = new JButton("atteindre Evcc");
        bouttonok.setActionCommand("atteindre");
        bouttonok.addActionListener(this);
	    
	//Lay everything out.
	      treePanel.setPreferredSize(new Dimension(300, 150));
	      add(treePanel, BorderLayout.CENTER);

	          JPanel panel = new JPanel(new GridLayout(0,3));
	          panel.add(bouttonok);
	         add(panel, BorderLayout.SOUTH);
	}

	public static void populateTree(ArbreDynamique treePanel) {
		
		DefaultMutableTreeNode  p = null  ;
	String cheminrepertoire =  System.getenv("WScesscrea");//"C:\\Documents and Settings\\Maestro\\Bureau\\vide";

	File repertoire  = new File(cheminrepertoire);
	ArbreDynamique.laracine = repertoire.getName();
	Vector<String> mesemmet= new Vector<String>();
	//Vector<String> mesdate= new Vector<String>();
	if(repertoire.isDirectory()) {
	                                  String str[]= repertoire.list();
	     for(int i =0;i<str.length;i++)  {
	    File sourepertoire = new File(cheminrepertoire+"/"+str[i]+"/");
	       if(sourepertoire.isDirectory())  { 
	    	         mesemmet.add((String)(str[i]));
	                 p =treePanel.addObject(null,str[i]);    
	               String str2[]= sourepertoire.list();
	               for(int j=0;j<str2.length ;j++){
	             File sourep = new File(cheminrepertoire+"/"+str[i]+"/"+str2[j]);
	                if(sourep.isDirectory()){   
	                	treePanel.addObject(p,str2[j]);   }
	                	
	                       }
	                                        }// fin in sourep 
	   
	   }}}
	                 	  
	                      
	public void actionPerformed(ActionEvent e) {
	String command = e.getActionCommand();
		if ("atteindre".equals(command)) {
            
            
      System.out.println((String)ArbreDynamique.getselectionevcc().get(0)+"\n");
      System.out.println((String)ArbreDynamique.getselectionevcc().get(1)+"\n");
      System.out.println((String)ArbreDynamique.getselectionevcc().get(2)+"\n"); 
    
      respathevcc = (System.getenv("WScesscrea")+"\\"+(String)ArbreDynamique.getselectionevcc().get(1)+"\\"
                                                       +(String)ArbreDynamique.getselectionevcc().get(2)).trim();                 
      
      String code =  (String)ArbreDynamique.getselectionevcc().get(1);
      String date =  (String)ArbreDynamique.getselectionevcc().get(2);
      FenetreTraitement.codeemm =code;
      FenetreTraitement.dateemm =date;
      FenetreTraitement.pathdepot = FenetreTraitement.construirepathevcc(code, date);
     if(new File(FenetreTraitement.construirepathevcc(code, date)).exists()){
    	 parseurevcc.parseur.codeemm= code;
     
		parseurevcc.parseur.dateemm= date;
		parseurevcc.parseur.chemindepot =respathevcc;
      FenetreTraitement.suivant.setEnabled(true);
      FenetreTraitement.precedent.setEnabled(true);
      FenetreTraitement.genereretatevcc(code, date);
       Traitement.fentemp.dispose();
       System.out.println(respathevcc);
       frame.dispose();
                                                              }  else{
             JOptionPane.showMessageDialog(this, "votre selection d'Evcc n'est pas assez claire ");
                                                              }  //  fin du if   
           		}
		} 
	
	public static void lancerarbre() {

		frame = new JFrame("veuillez choisir une Evcc");
		 frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
    	     
		                   frame.setAlwaysOnTop(true);
		Hierarchiedepot panelevcc = new Hierarchiedepot();
	                    panelevcc.setOpaque(true); //content panes must be opaque
                        frame.setBounds(465, 275, 350, 200);
                        panelevcc.setAutoscrolls(true);
	    frame.add(panelevcc);

	//Display the window.
	frame.pack();
	frame.setVisible(true);
	}

public static String getpathevcc(){
	return respathevcc;
}
	public static void main(String[] args) {
	//Schedule a job for the event-dispatching thread:
	//creating and showing this application's GUI.
		
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	public void run() {
	lancerarbre();
	}
	});	


	}
	}
