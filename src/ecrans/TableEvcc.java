	package ecrans;

	import java.awt.Dimension;
	import java.awt.GridLayout;
import java.awt.Toolkit;
	import java.util.Iterator;
	import java.util.Vector;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.ListSelectionModel;
	import javax.swing.event.ListSelectionEvent;
	import javax.swing.event.ListSelectionListener;

	import parseurevcc.Agent;
import parseurevcc.Evcc;

	public class TableEvcc extends JPanel {
	    public static final long serialVersionUID = 1L;
		//private boolean DEBUG = false;
	    //private boolean ALLOW_COLUMN_SELECTION = false;
	    private boolean ALLOW_ROW_SELECTION = true;
	    
	    @SuppressWarnings("unused")
		private static int position;
	    
	    public static String coderech;
	    public static String daterech;
	  
	    public static Vector<Vector<String>> matriceevcc = new Vector<Vector<String>>();
	    public static Vector<String> colomne = new Vector<String>();
	    public static JFrame frame = new JFrame();
	    public static Vector<Agent>  lesagents = new Vector<Agent>();
	    public static Vector<Vector<String>> mattemp = new Vector<Vector<String>>();
	    public static Agent agtemp = new Agent();
	    public static int selectedRow ;
	    public static  JTable table = new JTable();
	    public static    javax.swing.table.TableModel model ;
	    public static Boolean agentexistantdanstable = false;
	    
	   
		public TableEvcc(Vector<String> colomnes, Vector<Vector<String>> evcc) {
	        super(new GridLayout(1,0));
	       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     matriceevcc = evcc;
	     model = table.getModel();
	     
	    /* Iterator<Vector<String>> iag = matriceevcc.iterator();
	     while(iag.hasNext()){
	     for (int i=0; i < table.getRowCount(); i++) {
	         System.out.print("    ligne " + i + ":");
	               agentexistantdanstable =((Vector<String>) iag.next()).contains(model.getValueAt(i, 0)) ;// le 0 pouracceder a l'id cess
	                          if(agentexistantdanstable){agentexistantdanstable =!(matriceevcc.remove((Vector<String>) iag.next()));}              
	         			}// fin du  for
	         System.out.println();  }// fin du while
	     */
	     

	         table = new JTable(matriceevcc,colomnes);// (String[]) colomnes.toArray(new String[colomnes.capacity()]));
	        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	        table.setFillsViewportHeight(true);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setCellSelectionEnabled(false);
	       
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
	                    	
	                         selectedRow = lsm.getLeadSelectionIndex();//SelectionIndex());
	                        System.out.println("la ligne selectionné est : " + selectedRow +
	                        		            "          la position : "  + (selectedRow+1)         );
	                        Traitement.codeemm = coderech;
	                        Traitement.dateemm = daterech;
	                        Traitement.pathdepot =System.getenv("WScesscrea")+"\\"+coderech+"\\"+daterech +"\\";
	                        int posag ;
	                        String resnom = getselectedagentnom(table,1);
	                        posag = Integer.parseInt( parseurevcc.parseur.extrairepositionagent((String)resnom));
	                        FenetreTraitement.genereretatagent(coderech, daterech, posag );
	                        Traitement.fentemp.dispose();
	                       
	                        }
	                }
	            });
	        } else {
	            table.setRowSelectionAllowed(false);
	        }

	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane);
	    }

	    
	    public String getselectedagentnom(JTable table, int i) {
	        String resnom="";
	    	int numligneselec = table.getSelectedRow();
	        //int numColomneselec = 1;
	        javax.swing.table.TableModel model = table.getModel();
	        
	                System.out.println(" argument de l'agent cherhcher " + model.getValueAt(numligneselec,i));
	                 resnom =   (String) model.getValueAt(numligneselec,1);
	                System.out.println("--------------------------");
	            
	       return resnom; }

	    @SuppressWarnings("static-access")
		private static void createAndShowGUI(Vector<String> colomnes, Vector<Vector<String>> evccdata) {
	       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        SimpleTableSelectionDemo newContentPane = new SimpleTableSelectionDemo(colomnes,evccdata);
	        newContentPane.setOpaque(true); //content panes must be opaque
	        //newContentPane.setBounds(0, 101, 510, 80);
	       // if(Recherchedansdepot.fenrecherchetemp==null){
	        frame.setContentPane(newContentPane);
	        frame.setAlwaysOnTop(true);
	        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\icone.jpg"));
	        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	        
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);}
	        //else{ 
	        	//Recherchedansdepot.fenrecherchetemp.setContentPane(newContentPane);
	        	//Recherchedansdepot.fenrecherchetemp.setAlwaysOnTop(true);
	            
	            //Display the window.
	        //	Recherchedansdepot.fenrecherchetemp.pack();
	        //	Recherchedansdepot.fenrecherchetemp.setVisible(true);
	        	//}	
	        
	    //}

	public static void creertabledynamique(Vector<Vector<String>> matevcc,String arg,Vector<String> colomnes){
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg"));
		frame.setTitle("resultats des Evcc contenant l'agent : " +  arg);
	/*	if(colomne.isEmpty()){
		colomne.add("EVCC ID");
		colomne.add("Nom & Prénom ");
		colomne.add("Code emmetteur");
		colomne.add(" Date emission");}*/
		Vector<Vector<String>> mesdata = new Vector<Vector<String>>();
		Vector<String> agtmp = new Vector<String>();
		          Iterator<Vector<String>> itag = matevcc.iterator();
		while(itag.hasNext()){
			    agtmp = (Vector<String>)itag.next();
			    Vector<String> ligne = new Vector<String>();
			    ligne.add(agtmp.get(14));
			    ligne.add(agtmp.get(2));
			    ligne.add(agtmp.get(15));
			    ligne.add(agtmp.get(16));
			    //System.out.println(tmp);
			mesdata.add(ligne);
		}
	    createAndShowGUI(colomnes,mesdata);
	    

		
	}
	public static void creertabledynamique2(Vector<Vector<String>> matevcc,String arg,Vector<String> colomnes){
		

		Vector<Vector<String>> mesdata = new Vector<Vector<String>>();
		Vector<String> agtmp = new Vector<String>();
		          Iterator<Vector<String>> itag = matevcc.iterator();
		while(itag.hasNext()){
			    agtmp = (Vector<String>)itag.next();
			    frame.setTitle("Table de l'emission Evcc : " + " Code organisme  :" + agtmp.get(15)+"  Date emission : "+agtmp.get(16));
			    Vector<String> ligne = new Vector<String>();
			    ligne.add(agtmp.get(0));
			    ligne.add(agtmp.get(1));
			    ligne.add(agtmp.get(2));
			    ligne.add(agtmp.get(3));
			    ligne.add(agtmp.get(6));
			    ligne.add(agtmp.get(12));
			    ligne.add(agtmp.get(13));
			    //System.out.println(tmp);
			mesdata.add(ligne);
		}
	    createAndShowGUI(colomnes,mesdata);
	    

		
	}
	@SuppressWarnings("static-access")
	public static SimpleTableSelectionDemo getjtablepanel(String code,String date,String arg){

		Vector<Vector<String>> matriceevcctemp= new Vector<Vector<String>>();
		Evcc evctemp = parseurevcc.Evcc.charger_evcc(code, date);
	    
	    mattemp = evctemp.getListagents();
	    
	Vector<String>  monvecttemp = new Vector<String>();

	for(int i =0;i<=mattemp.size()-1;i++){    

	           monvecttemp = (Vector<String>) mattemp.get(i);

			  if(!(matriceevcctemp==null)){  matriceevcctemp.removeAllElements();
		                        matriceevcctemp.add( monvecttemp);
			  									}
			  }	
		Vector<String> colomneres =new Vector<String>();
	    if(colomneres.isEmpty()){
	    	colomneres.add("EVCC ID");
			colomneres.add("Nom & Prénom ");
			colomneres.add("Code emmetteur");
			colomneres.add(" Date emission");}
	    
	    Vector<Vector<String>> mesdata = new Vector<Vector<String>>();
		Vector<String> agtmp = new Vector<String>();
		        
				Iterator<Vector<String>> itag = matriceevcctemp.iterator();
		while(itag.hasNext()){
			    agtmp = (Vector<String>)itag.next();
			    Vector<String> ligne = new Vector<String>();
			    ligne.add(agtmp.get(14));
			    ligne.add(agtmp.get(2));
			    ligne.add(agtmp.get(15));
			    ligne.add(agtmp.get(16));
			    //System.out.println(tmp);
			mesdata.add(ligne);
		}

	    SimpleTableSelectionDemo resSTS = new SimpleTableSelectionDemo(colomneres,mesdata);
	    resSTS.setOpaque(true); //content panes must be opaque
	    resSTS.setBounds(0, 101, 510, 80); 
	return resSTS;
	} 


	@SuppressWarnings("static-access")
	public static void trierevccdanstable(String code, String date, String arg,Vector<String> colomnes){
		// lesagents = new Vector<Agent>();
	    Evcc evctemp = parseurevcc.Evcc.charger_evcc(code, date);
	    
	     mattemp = evctemp.getListagents();
	     
	 Vector<String>  monvecttemp = new Vector<String>();
	 for(int i =0;i<=mattemp.size()-1;i++){    
//		   System.out.println((Vector<String>)mattemp.get(i));
		            monvecttemp = (Vector<String>) mattemp.get(i);
		            // ici if(colomne.get(0)not.exist
	 		  if(!(matriceevcc==null)){  matriceevcc=null;
		                        matriceevcc.add( monvecttemp);
	 		  									}
	 		  }
	 int pos =5;
	 System.out.println(matriceevcc.get(pos-1));
		
	 creertabledynamique(matriceevcc,arg,colomnes);
	 
	}
	@SuppressWarnings("static-access")
	public static void trierevccdanstable2(String code, String date, String arg,Vector<String> colomnes){
		// lesagents = new Vector<Agent>();
	    Evcc evctemp = parseurevcc.Evcc.charger_evcc(code, date);
	    
	     mattemp = evctemp.getListagents();
	     
	 Vector<String>  monvecttemp = new Vector<String>();
	 for(int i =0;i<=mattemp.size()-1;i++){    
//		   System.out.println((Vector<String>)mattemp.get(i));
		            monvecttemp = (Vector<String>) mattemp.get(i);
	 		  matriceevcc.add( monvecttemp);
	     }
	 int pos =5;
	 System.out.println(matriceevcc.get(pos-1));
		
	 creertabledynamique2(matriceevcc,arg,colomnes);
	 
	}
	public static void lancerresultatrecherche(String code, String date){
		coderech=code;
		daterech=date;
		 Vector<String> colomneres =new Vector<String>();
		    if(colomneres.isEmpty()){
		    	colomneres.add("EVCC ID");
				colomneres.add("Nom & Prénom ");
				colomneres.add("Code emmetteur");
				colomneres.add(" Date emission");}
		    trierevccdanstable(coderech,daterech,"  ",colomneres);
		
	}
	    public static void lancerjtableevcc(String code,String date){
	    	coderech=code;
			daterech=date;
	    	 Vector<String> colomne2 =new Vector<String>();
	 	    if(colomne2.isEmpty()){
	 			colomne2.add("Position");
	 			colomne2.add("Matricule");
	 			colomne2.add("    Nom & Prénom    ");
	 			colomne2.add("Cin");
	 			colomne2.add("N°Dossier");
	 			colomne2.add("Date Debut");
	 			colomne2.add("Date Fin");}
	 	    trierevccdanstable2(coderech,daterech,"  ",colomne2);
	 		
	    }
		
		public static void main(String[] args) {
			String code="4929";
			String date="200909";
			
			lancerjtableevcc(code,date);

			
		}	
	
	}
	
			
			
			
			
			
			
			
			
			/*		if(colomne.isEmpty()){
				colomne.add("EVCC ID");
				colomne.add("Nom & Prénom ");
				colomne.add("Code emmetteur");
				colomne.add(" Date emission");}
				
		    trierevccdanstable(code,date,"test",colomne);
		*/    
		   
	    	/*
	    	 Evcc evctemp = parseurevcc.Evcc.charger_evcc("4929","200909");
	    	 Vector<Vector<String>> mattemp = evctemp.getListagents();
	    	
	    	final Vector<Vector<String>> mesdata = new Vector<Vector<String>>();
	    	Vector<String> vecttemp =new Vector<String>();
	     	
	    	String agnom = "";
	    	             Iterator<Vector<String>> itmat = mattemp.iterator();
	    	             while(itmat.hasNext()){

		            	agnom  = (String)( (Vector<String>) itmat.next() ).get(2);
	    	            	vecttemp.add(0,evctemp.getIdcess());
	    	            	vecttemp.add(1, agnom);
	    	            	vecttemp.add(2,evctemp.getCodeemmet());
	    	            	vecttemp.add(3,evctemp.getDateemm().substring(6)+evctemp.getDateemm().subSequence(3, 5));
	    	mesdata.add(vecttemp);
	    	            	            	
	    	             }
	    	  // Iterator<Vector<String>> ittemp = mesdata.iterator();
	    	    //         while(ittemp.hasNext()){             
	    	//System.out.println((Vector<String>)ittemp.next());   }
		    //evc.getinfos();
	*/    	
	    	             
	    	
	    	//Vector<Agent> lesagents = new Vector<Agent>();
	    	
	    	 //creertabledynamique(lesagents,"");
	    	  //  }
	       // });
	        
	        
	
