package outils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ecrans.FenetreTraitement;
import ecrans.Traitement;


public class EvccJtable extends JPanel {
	
    public static final long serialVersionUID = 1L;
	//private boolean DEBUG = false;
    //private boolean ALLOW_COLUMN_SELECTION = false;
    public static  boolean ALLOW_ROW_SELECTION = true;
	public static JTable table;
	public static JScrollPane scrollPane;
	public static String codepath;
    public static String datepath;
  
	public static String pathevcxml = trouverevccxml(System.getenv("WScesscrea")+"\\"+codepath+"\\"+datepath);//+"\\EVCC492920090900.xml ";
	public static File entryFile ;//= new File(pathevcxml);//testevcc.xml
	public static org.jdom.Document doc =null;    //= parseurevcc.parseur.parse(entryFile);
	
	
	
	 public static JScrollPane creerjtable(Vector<Vector<String>> row){ 
		
		 //ici les noms des colonnes    
		       Vector<String> columnNames = new Vector<String>();
		    columnNames.addElement("n°");
		    columnNames.addElement("Matricule");
		    columnNames.addElement("Nom&Prenom");
		    columnNames.addElement("Cin");
		    columnNames.addElement("chapitre");
		    columnNames.addElement("image");
		    columnNames.addElement("n°dossier");
		    columnNames.addElement("code revendeur");
		    columnNames.addElement("montant net");
		    columnNames.addElement("montant totale");
		    columnNames.addElement("montant mensuel");
		    columnNames.addElement("nbre de mois");
		    columnNames.addElement("date debut");
		    columnNames.addElement("date fin");
		    columnNames.addElement("idcession");
		    columnNames.addElement("code emmetteur");
		    columnNames.addElement("date emission");
		  
		    
		    table = new JTable(row, columnNames);
		    table.setShowHorizontalLines(true);
		    table.setEnabled(true);
		    table.setAutoCreateRowSorter(true);
		    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	        table.setFillsViewportHeight(true);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setCellSelectionEnabled(false); 		    
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			 if (ALLOW_ROW_SELECTION) { // true by default
		            
		        	ListSelectionModel rowSM = table.getSelectionModel();
		            rowSM.addListSelectionListener(new ListSelectionListener() {
		                public void valueChanged(ListSelectionEvent e) {
		                    //Ignore extra messages.
		                    if (e.getValueIsAdjusting()) return;

		                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		                    lsm.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		                    if (lsm.isSelectionEmpty()) {
		                        System.out.println("Aucune ligne n'as été selectionné");
		                    } else {
		                    	
		                        int selectedRow = lsm.getLeadSelectionIndex();//SelectionIndex());
		                        System.out.println("la ligne selectionné est : " + selectedRow +
		                        		            "          la position : "  + (selectedRow+1)         );
		                        Traitement.codeemm = codepath;
		                        Traitement.dateemm = datepath;
		                        Traitement.pathdepot =System.getenv("WScesscrea")+"\\"+codepath+"\\"+datepath +"\\";
		                        int posag ;
		                        String resnom = getselectedagentnom(table,1);
		                        posag = Integer.parseInt( parseurevcc.parseur.extrairepositionagent((String)resnom));
		                        FenetreTraitement.genereretatagent(codepath, datepath, posag );
		                        Traitement.fentemp.dispose();
		                    }
		                }        	            });}
			scrollPane = new JScrollPane(table);
		    return scrollPane;
		      }
	 public static String getselectedagentnom(JTable table, int i) {
	        String resnom="";
	    	int numligneselec = table.getSelectedRow();
	        //int numColomneselec = 1;
	        javax.swing.table.TableModel model = table.getModel();
	        
	                System.out.println(" argument de l'agent cherhcher " + model.getValueAt(numligneselec,i));
	                 resnom =   (String) model.getValueAt(numligneselec,1);
	                System.out.println("--------------------------");
	            
	       return resnom; }
	 public static String trouverevccxml(String pathrep){
	  	    String res=null;   
	  		File f = new File(pathrep);	 
	  	    if(f.isDirectory()){
	  	    	String str[]= f.list();
	              for(int i =0;i<str.length;i++)  {
	                  File f2 = new File(pathrep+"\\"+str[i]);
	                        if(f2.isDirectory())  {  }
	                        if(f2.isFile() && (f2.getName()).endsWith(".xml") && (f2.getName()).startsWith("EVCC4929") ){res = f2.getPath();  }                      	  
	                                               }
	  	                        }
	                return res;       
	  	  }
	 public static JScrollPane creerjtable(Vector<Vector<String>> matricedonnee, String code,String date) {
			codepath = code;
			datepath = date;
			parseurevcc.parseur.codeemm= codepath;
			parseurevcc.parseur.dateemm= datepath;
			parseurevcc.parseur.chemindepot =System.getenv("WScesscrea").trim()+"\\"+codepath+"\\"+datepath;
					return creerjtable(matricedonnee);
		}
	 
	public static void main(String[] args) {
		codepath ="4929";
		datepath ="200909";
		parseurevcc.parseur.codeemm= codepath;
		parseurevcc.parseur.dateemm= datepath;
		parseurevcc.parseur.chemindepot =System.getenv("WScesscrea").trim()+"\\"+codepath+"\\"+datepath;
		pathevcxml = trouverevccxml(System.getenv("WScesscrea")+"\\"+codepath+"\\"+datepath);
		entryFile = new File(pathevcxml);
	 doc = parseurevcc.parseur.parse(entryFile);
		parseurevcc.parseur.extractiondesbalises(doc);
			Vector<Vector<String>> matricedonnee =  parseurevcc.parseur.extractiondesbalises(doc);
            JScrollPane sp = creerjtable(matricedonnee, codepath, datepath);
            JFrame test =  new JFrame("Jtable EVCC");
              test.add(sp, BorderLayout.CENTER);
            test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            test.setBounds(20, 40, 300, 200);
            test.setVisible(true);
            

	}
	

}
