package ecrans;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parseurevcc.Agent;

public class Recherchedansdepot extends JFrame implements ActionListener{

	public  static final long serialVersionUID = 1L;

	public static JFrame fenrecherchetemp= new JFrame();
 public static JPanel panelrecherche;	
 public static JLabel label= new JLabel("argument de recherche");
 public static JTextField field = new JTextField();
 public static JButton bouttonrecherche = new JButton("chercher l'agent");;
 public static Vector<String> restemppathxmlvect = new Vector<String>();
 public static Vector<String> restemp = new Vector<String>();
 public static Agent etatagenttrouve = new Agent();
 public static Vector<Agent> lesetatsagent = new Vector<Agent>();
 public static Vector<Vector<String>> lesvects = new Vector<Vector<String>>();
 public static String  argumentrecherche = ""; 
 public static String coder ="";
 public static String dater ="";
 public static Container contenant;
 public static FlowLayout fl =null;

	//@SuppressWarnings("static-access")
	public Recherchedansdepot(){
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CessCrea : recherche d'agent dans tout le depot : --------> "+"    @ adnane belm.") ;
		//setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setBounds(30,400,505,110) ;
		  panelrecherche = new JPanel();
          panelrecherche.setVisible(true);
          panelrecherche.setBounds(0, 0,505,100);
          panelrecherche.setBackground(Color.cyan);
          label.setBounds(10, 10 , 170 , 20);
          field.setBounds(200, 10 , 200 , 20);
bouttonrecherche.setBounds(360,50 , 90,20);
bouttonrecherche.setText("chercher");
bouttonrecherche.setToolTipText("Chercher Agent dans tout le depot (toutes les Emissions Evcc).");

      contenant = getContentPane();
     
        panelrecherche.add(label);
		panelrecherche.add(field);
		panelrecherche.add(bouttonrecherche);
		
     
     panelrecherche.setLayout(fl);
     contenant.add(panelrecherche);		
	contenant.setLayout(fl);
	
	bouttonrecherche.addActionListener(this);
	
	}
	public static Vector<Vector<String>> parcouriretoutledepot(){
		String arg = (String)field.getText();
		System.out.println(arg);
        Vector<String> codeetdatetemp = new Vector<String>();
        Vector<Vector<String>> toutlescodesetdates = new Vector<Vector<String>>();    
        File racine = new File(System.getenv("WScesscrea"));	 
        File noeudcode = null;
        File noeuddate = null ;
        if(racine.isDirectory()){
 	    	String str[]= racine.list();
 	    
 	    	for(int i =0;i<str.length;i++)  {
            
 	    		 noeudcode = new File(System.getenv("WScesscrea")+"\\"+str[i]);
                
 	    		  if(noeudcode.isDirectory())  { 
                    	   String strcode[]= noeudcode.list();
                  
                    	   for(int j =0;j<strcode.length;j++)  {
                          noeuddate = new File(System.getenv("WScesscrea")+"\\"+str[i]+"\\"+strcode[j]);
                               if(noeuddate.isDirectory())  {
                            	   //j'ajoute ici le str[i]et strcode[j] a codetedatetemp
                            	   codeetdatetemp.add(str[i]);
                                   codeetdatetemp.add(strcode[j]);
                                   toutlescodesetdates.add(codeetdatetemp);
                                   
                            	//ici je dois trouver le fichier xml puis faire le traitement
                          String respathxml = trouverevccxml(System.getenv("WScesscrea")+"\\"+str[i]+"\\"+strcode[j]);
                              restemppathxmlvect.add(respathxml);                       
                               } 
                                                           }// fin de boucle sur lesdateemt
 	    	                                        }//fin if noeudcode.isdirectory()
    	}//fin boucle for pour les codeemmet
 	    	 }//fin if racine.isdirectory()
 	     Iterator<String>   it = restemppathxmlvect.iterator();
 	     while(it.hasNext()) {
 	    	 System.out.println((String)it.next());
 	    	 
 	                          }
 	    
	return toutlescodesetdates;
		
	}
	public static Agent getagent(String pathevcc , int pos){
        Agent ag =null;   
	   Vector<String>	res = 	parseurevcc.parseur.extrairevecteuragent(pathevcc,pos); // extraction d'un vecteur de string les donées d'un agent par position pos
     if(!(res==null)){	 ag =   Agent.agentbyvect(res);  }  // creer un agent avec ces données 
     else{ ag = null;  } 
    return ag; 	 
      }
	
	@SuppressWarnings("static-access")
	public static void  lancerresrech(int i){
		String pathxmltemp ="";
		String tempstr="";
		
		  pathxmltemp =(String)restemppathxmlvect.get(i);
	        argumentrecherche  =    field.getText();
	          System.out.println(" argument : "+ argumentrecherche +" dans l'evcc : "+pathxmltemp+"\n");
	            String rescode   = new File(pathxmltemp).getParentFile().getParentFile().getName();
	            String resdate   = new File(pathxmltemp).getParentFile().getName();
	          // instaciation des attributs du parseur.
	          parseurevcc.parseur.codeemm  = rescode; 
	          parseurevcc.parseur.dateemm = resdate;
	          parseurevcc.parseur.chemindepot =System.getenv("WScesscrea").trim()+"\\"+rescode+"\\"+resdate;
	          
	           tempstr =parseurevcc.parseur.extrairepositionagent((String)argumentrecherche);

	          if(!(tempstr==null)){
	          int r = Integer.parseInt( tempstr);
	                            String pathevcc= System.getenv("WScesscrea")+"\\"+rescode+"\\"+resdate;
	                            SimpleTableSelectionDemo.coderech = rescode;
	                            SimpleTableSelectionDemo.daterech = resdate;
	                            Traitement.codeemm=rescode;
	                            Traitement.dateemm=resdate;
	                               coder = rescode;
	                               dater = resdate;
	                            etatagenttrouve =  getagent(pathevcc,r);
	                            Traitement.agenttemp = getagent(pathevcc,r);
						         etatagenttrouve.tostring();
						  restemp =       parseurevcc.Agent.getvectagent(etatagenttrouve);
			lesetatsagent.add(etatagenttrouve);
			lesvects.add(restemp);
			
	          }//else{JOptionPane.showMessageDialog(FenetreTraitement.fentemp,"agent introuvable dans cette cession" );}
		
	}

public void actionPerformed(ActionEvent e) {
	if(e.getSource()== bouttonrecherche){
	Vector<Vector<String>>	res = parcouriretoutledepot();
	       
	        Vector<String> temp = (Vector<String>)res.get(0);
	        
	        System.out.println(res);
	        System.out.println(temp+ "  nbre d'objets codedate trouver pour cet agent :  "+ (temp.size()+1)/2);
	        for(int i=0;i<restemppathxmlvect.size();i++){
	        lancerresrech(i);
    SimpleTableSelectionDemo.lancerresrech(coder, dater, lesvects, " Resulats de recherche de l'agent :  " + argumentrecherche);
	                                     }
}//fin evenement

	}



@SuppressWarnings("unused")
private void trierresultatrecherchedantable(Vector<Vector<String>> vects,String arg) {
	Vector<String> colomneres =new Vector<String>();
    if(colomneres.isEmpty()){
    	colomneres.add("EVCC ID");
		colomneres.add("Nom & Prénom ");
		colomneres.add("Code emmetteur");
		colomneres.add(" Date emission");}
    SimpleTableSelectionDemo.trierevccdanstable(coder, dater, arg, colomneres);
	
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
		
public static void demarrerfenrecherche(){
	fenrecherchetemp = new Recherchedansdepot();

    fenrecherchetemp.setVisible(true);

	
}		

	
	
	public static void main(String[] args) {
	demarrerfenrecherche();
 	}




	

}
