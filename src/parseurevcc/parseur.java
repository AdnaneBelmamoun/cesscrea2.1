package parseurevcc;

import imagezoom.SimpleContentHandler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ecrans.FenetreTraitement;





public class parseur {
	public static File entryFile = null;
	public static XPath xpaEvcc = null;
	public static XPath xpaAgent = null;
	public static Element racine = null;
	@SuppressWarnings("unchecked")
	public static List noeuds ;
	public static  String   CessionId;
	public static  String    codeemet;
	public static  String    periode ;
	public static  String     date ; 
	public static  String    nbrdoss ;
	public static String  personnematricule ;
	public static String personneNom;
	public static String personneCin;
	public static String ndossierprecompte;
	public static String cdrevendeur;
	public static String chapitre;
	public static String montantnet;
	public static String montanttotale;
	public static String montantmensuel;
	public static String nbremois;
	public static String datedebut;
	public static String datefin;
	public static Vector<String> vectAgent = null;
	public static Vector<String> vectinfos = null;
	public static  Vector<Vector<String>> rowData=null;
	public static  Vector<Vector<String>> matricetemp=null;
	public static String codeimage =null;
	public static JScrollPane scrollPane;
//	public static String pathbureau =System.getenv("USERPROFILE")+"\\Bureau\\";
//	public static String pathdosstest = "boulot tgr\\4282EVCC_ Echéance 08-09\\";
//public static org.jdom.Document doc =  parseurevcc.parseur.parse(new File(repath(trouverevccxml(pathbureau+"test00"+"\\"+"4929"+"\\"+"200909"+"\\"))));
	public static org.jdom.Document document = null ;
		//pour rechercher
	public static String  codeemm  ;//= "4929"; 
	public static String   dateemm ;//= "200909";
	public static String chemindepot =System.getenv("WScesscrea").trim()+"\\"+codeemm+"\\"+dateemm;
	public static String respos =null;
	public static String res0 = null; 
	public static String res1 =   null;
	public static String res2 =   null;
	public static String res3 =  null;
   // static String resfichim = null;
	public static String res4 =   null;
	public static String res5 =   null;
	public static String res6 =   null;
	public static String res7 =   null;
	public static String res8 =   null;
    //static String resnbrmois = null;
	public static String res9 =   null;
	public static String res10 =   null;
	public static String residcess = null;
	public static String rescdemet = null;
	public static String resdatemis = null;
	public static String resnbrdoss = null;
    
    
	public parseur(){ }
	public static void SimpleSaxParser(String uri) throws SAXException, IOException {
        XMLReader saxReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        saxReader.setContentHandler(new SimpleContentHandler());
        saxReader.parse(uri);  }

	public static org.jdom.Document parse(File FilePth) {
	     
	        try {
	            /* On crée une instance de SAXBuilder */
	            SAXBuilder sxb = new SAXBuilder();
	            document = sxb.build(FilePth);
	        } catch (IOException e) {
	            System.out.println("Erreur lors de la lecture du fichier " 
					+ e.getMessage() );
	            e.printStackTrace();
	        } catch (JDOMException e){
	            System.out.println("Erreur lors de la construction du fichier JDOM " 
					+ e.getMessage() );
	            e.printStackTrace();
	        }
	 return document;}
	
	
	
	 @SuppressWarnings("unchecked")
  public static Vector<Vector<String>> extractiondesbalises(org.jdom.Document doc){       
	        try {
	    
    	        Element racine = doc.getRootElement();
	 // On va dans un premier temps rechercher l'ensemble des agents du fichier. 
     // Recherche de la liste des agents         
	             xpaAgent = XPath.newInstance("//Agent");   
	            
	// On récupère tous les noeuds répondant au chemin //Agent 
	            List resultsagent = xpaAgent.selectNodes(racine) ;
	            Iterator iterAgent = resultsagent.iterator() ;
	            Element noeudCourant = null;
	            int inc = 0 ;
 rowData = new Vector<Vector<String>>();
 vectinfos =  parseurevcc.parseur.generercdinfo(chemindepot);
    while (iterAgent.hasNext()){
	            	// séquenceur
    	                 inc++;            	
	                /* Pour chaque Agent nous allons chercher son nom puis l'afficher */
	                noeudCourant = (Element) iterAgent.next();
	                
	                /* On récupère l'identifiant de la personne (Agent)                
	                   Noter le . en début du chemin : on part de la position courante 
	                                  */
	               XPath   xpaMatricule = XPath.newInstance("./Matricule");
	              personnematricule = xpaMatricule.valueOf(noeudCourant);
	                /* Nous cherchons à présent la valeur de la balise nom :                */
	               XPath xpaNom =XPath.newInstance("./Nom");
	             personneNom = xpaNom.valueOf(noeudCourant);
	                
	               XPath  xpaCin = XPath.newInstance("./Cin");
	             personneCin = xpaCin.valueOf(noeudCourant); 
	             
	             XPath  xpachap = XPath.newInstance(" ./Chapitre");
	             chapitre = xpachap.valueOf(noeudCourant); 
	             
	               XPath xpaprecompte= XPath.newInstance("./Precompte//NumeroDossier");
	             ndossierprecompte = xpaprecompte.valueOf(noeudCourant);
	              
	               XPath xpacodeimage = XPath.newInstance("./Precompte//FichierImage") ;
		         String res =xpacodeimage.valueOf(noeudCourant);
		              codeimage = res.substring(12);
		              XPath xpacdrevendeur= XPath.newInstance("./Precompte//CodeRevendeur");
			             cdrevendeur = xpacdrevendeur.valueOf(noeudCourant);
			             
			          XPath xpamontnet= XPath.newInstance("./Precompte//MontantNet");
			             montantnet = xpamontnet.valueOf(noeudCourant);
			             
			          XPath xpamonttot= XPath.newInstance("./Precompte//MontantTotal");
			             montanttotale = xpamonttot.valueOf(noeudCourant);
			          
			          XPath xpamontmens= XPath.newInstance("./Precompte//MontantMensuel");
			             montantmensuel = xpamontmens.valueOf(noeudCourant);
			          
			          XPath xpanbrmois= XPath.newInstance("./Precompte//NombreMois");
			             nbremois = xpanbrmois.valueOf(noeudCourant);
			          
			          XPath xpadatedebut= XPath.newInstance("./Precompte//DateDebut");
			             datedebut = xpadatedebut.valueOf(noeudCourant);
			          
			          XPath xpadatefin= XPath.newInstance("./Precompte//DateFin");
			             datefin = xpadatefin.valueOf(noeudCourant);
			          
			             
	
   vectAgent = new Vector<String>();
   String position = Integer.valueOf(inc).toString(); 
  vectAgent.add(position);
  vectAgent.add(personnematricule);
  vectAgent.add(personneNom);
  vectAgent.add(personneCin);
  vectAgent.add(chapitre);
  vectAgent.add(codeimage);
  vectAgent.add(ndossierprecompte);
  vectAgent.add(cdrevendeur);
  vectAgent.add(montantnet);
  vectAgent.add(montanttotale);
  vectAgent.add(montantmensuel);
  vectAgent.add(nbremois);
  vectAgent.add(datedebut);
  vectAgent.add(datefin);
  // ajouter les elements  infos cession dans le vecteur agent 	  
  vectAgent.add(vectinfos.get(0));
  vectAgent.add(vectinfos.get(1));
  vectAgent.add(vectinfos.get(2));
  vectAgent.add(vectinfos.get(4));
  
     //System.out.println(vectAgent);
  // la methode   extracxtion balises() retournera un objet Agent ayant la structure vector<vector<String>>
         
   rowData.addElement(vectAgent);
  
   
     }// fin de boucle while
   // System.out.println("le nombre de dossier est : "+inc);
    
            } // fin du try
        catch (JDOMException e) {
            System.out.println("Erreur JDOM " + e.getMessage() );
            e.printStackTrace();            
           } 
    return rowData;
    }  // fin de la methode extraire des balises()

	 
	 
	 //  ici je fabrique une methode d'extraction des balises infos d'Evcc
	 
	 @SuppressWarnings("unchecked")
  public static Vector<String> extraireinfocd (org.jdom.Document doc){
	
		 Vector<String> vectcdinfos =  new Vector<String>();
			try {			    
				
		         racine = doc.getRootElement();
	             xpaEvcc = XPath.newInstance("//EVCC");   
	            List noeuds = xpaEvcc.selectNodes(racine) ;
	            Iterator iterEVCC = noeuds.iterator() ;
	            Element noeudEVCC = null;
	            while (iterEVCC.hasNext()){
	                                          noeudEVCC = (Element) iterEVCC.next();
	                
	                                 XPath   xpacessid = XPath.newInstance("./Id_Cession");
	                                   CessionId = xpacessid.valueOf(noeudEVCC);
	                                   XPath   xpacodeemet = XPath.newInstance("./CodeEmetteur");
	                                   codeemet = xpacodeemet.valueOf(noeudEVCC);
	                                   XPath   xpaperiode = XPath.newInstance("./Periode");
	                                   periode = xpaperiode.valueOf(noeudEVCC);
	                                   XPath   xpadate = XPath.newInstance("./Date");
	                                   date = xpadate.valueOf(noeudEVCC);
	                                   XPath   xpanbrdoss = XPath.newInstance("./NombreDossier");
	                                   nbrdoss = xpanbrdoss.valueOf(noeudEVCC);
	                   vectcdinfos.addElement(CessionId);
	                   vectcdinfos.addElement(codeemet);
	                   vectcdinfos.addElement(date);
	                   vectcdinfos.addElement(periode);
	                   vectcdinfos.addElement(nbrdoss);   
          // liberer l'instance Xpath
	                   
	            }
			
			}catch (JDOMException e) {
	            System.out.println("Erreur JDOM " + e.getMessage() );
	            e.printStackTrace();            
	           }
			return vectcdinfos;
		}
		
	 public static Vector<String> generercdinfo(String source){
    document = parseurevcc.parseur.parse(new File(repath(trouverevccxml(source))));
       Vector<String> res = parseurevcc.parseur.extraireinfocd(document);
       return res;
     }   
	 
     public static String trouverevccxml(String pathrep){
 	    String res=null;   
 		File f = new File(pathrep);
 		if(f.exists()){
 	    if(f.isDirectory()){
 	    	String str[]= f.list();
             for(int i =0;i<str.length;i++)  {
                 File f2 = new File(pathrep+"\\"+str[i]);
                       if(f2.isDirectory())  {  }
                       if(f2.isFile() && (f2.getName()).endsWith(".xml") && (f2.getName()).startsWith("EVCC") ){res = f2.getPath();  }                      	  
                                       }
 	                        }
 	                  }
 		if(!(f.exists())){
 			if(!(FenetreTraitement.fentemp == null)){
 			JOptionPane.showMessageDialog(FenetreTraitement.fentemp, "aucun fichier XML d'EVCC n'as été trouvée ");}
 		   else{JOptionPane.showMessageDialog(null, "aucun fichier XML d'EVCC n'as été trouvée ");}
 		}
   return res; }
     
     public static String repath(String init){
    	String res= null ;
    	if(init==null) {JOptionPane.showMessageDialog(null, "aucun fichier XML d'EVCC n'as été trouvée "); }
    	if(!(init==null)){
		 String clone = init;
		res   = clone.replace('\\', '/');}
    	 
			      return res ;
	 }
     public static Vector<Vector<String>> loaddata(String path){
    	 document =null;
    	 // ceci est une sequence de test du parseur de fichier
	/*if(path == null){  
		JOptionPane.showMessageDialog(null, "chemin introuvable")  ;      
		return null;     }*/
    if((!(path == null))&&(!(trouverevccxml(path)==null))){		
    	
    	entryFile = new File(repath(trouverevccxml(path)));
					 document = parse(entryFile);
			                  extraireinfocd(document);
			matricetemp =  extractiondesbalises(document);
				vectinfos = generercdinfo(chemindepot);
     return matricetemp;}
    	 else{ matricetemp=null;
    		 //JOptionPane.showMessageDialog(FenetreTraitement.fentemp, " aucune emission n'a été trouvée"); 
    	 }
	
    	 return matricetemp;
     }
    public static String extrairepositionagent(String argument){
    	 String i = null;
    	// Agent ag = new Agent() ;
    	 
    	 matricetemp = loaddata(chemindepot+"\\");
    	    Vector<String> tempag = new Vector<String>();
    	    Iterator<Vector<String>> it = matricetemp.iterator();
    	   // int exister = 0 ; 
    	    while(it.hasNext()){
    	       tempag = (Vector<String>)it.next();
    	       
       for(int j =0 ; j<=tempag.size()-1; j++){
    	   if(argument.equalsIgnoreCase((String)tempag.get(j))){
           //if(tempag.contains(argument)){        // ((String)tempag.get(j)).equalsIgnoreCase(argument)||
    		   System.out.println(tempag);
    		   System.out.println(tempag.get(0));
    		 //  ag = parseurevcc.Agent.agentbyvect(tempag);
    	       //  i = ag.getposition();
    		   i =  (String)tempag.get(0) ;    	       //if(i == null){ i =  tempag.get(0) ; }  
    	        }//fin de boucle if sur element du vecteur agent tempag
    	    	/*else{
    //JOptionPane.showMessageDialog(ecrans.FenetreTraitement.panelrecherche, "agent introuvable"," ERREURE DE Recherche ",JOptionPane.ERROR_MESSAGE);  
                    i= null;}*/ 
    	        }
    	        }
     return i ;}
     public static Vector<String> extrairevecteuragent(String pathdepot,int pos){
    	 //loaddata("C:\\Documents and Settings\\Maestro\\Bureau\\Nouveau dossier\\4929\\200909\\");
    	 Vector<String> res =null;
     if(!(matricetemp==null)) { res =matricetemp.get(pos-1); // accés a la position    
            res.add(vectinfos.get(0));   // ajout des infos cession
            res.add(vectinfos.get(1));
            res.add(vectinfos.get(2));
            res.add(vectinfos.get(4));
     return res;
     }else{res=null;
    	 JOptionPane.showMessageDialog(FenetreTraitement.fentemp, "extraction de vecteur agent null");}
     return res;}
     
     public static String extrairedonne(int pos, String donne){
    	String res=null; 
     Vector<String> vectag = extrairevecteuragent(chemindepot,pos);
          if(donne=="position") { res = vectag.get(0); }
          if(donne=="matricule") { res = vectag.get(1); }
          if(donne=="nom") { res = vectag.get(2); }
          if(donne=="cin") { res = vectag.get(3); }
          if(donne=="numdossier") { res = vectag.get(4); }
          if(donne=="image") { res = vectag.get(5); }
          
          
     return res;}
	
     
     

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
			 
	 String  entryFilexml = repath(trouverevccxml(chemindepot));
	 DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	 domFactory.setNamespaceAware(true); // never forget this!
	 DocumentBuilder builder = null;
	 builder = domFactory.newDocumentBuilder();
	 Document doc = builder.parse(entryFilexml);
	 XPathFactory factory = XPathFactory.newInstance();
	 javax.xml.xpath.XPath xpath = factory.newXPath();
	 
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
	                            	  resultcompile.add(res); 
	                            	  /*puis j'increment le compteur position expression*/    
	                            	  compteur ++;     }
	    		}
	    	}// fin de l'iterator sur boucle while

	    // ici je devrai d'abord donner des valeur aux variables supplémentaires d'infos evcc et de position agent dans evcc
	         	    
	          // info Evcc ---->   a recupérer grace a extraire evcc infos()
		Vector<String>  vectinfos = parseurevcc.parseur.generercdinfo(chemindepot);
	       residcess = vectinfos.get(0);   rescdemet = vectinfos.get(1);  ;  resdatemis =vectinfos.get(2);  resnbrdoss = vectinfos.get(4);
	          
	         // position agent ----> a récupérer par methode get position agent by critére(nom ou cin ou matr  ou numdoss)
	              //parseurevcc.parseur.loaddata(chemindepot);// attention ane pas oublier le loaddata(path) avant tte extraction
	              respos = parseurevcc.parseur.extrairepositionagent(resultcompile.get(0));
	                                    /// c coooooooooooooooool
	                  
	    // ici j'ajoute les positions manquantes pour pouvoir instancié un objet Agent(avec String position + 17 autres String de données)
	    	     resultcompile.add(0,respos);
	       	     resultcompile.add(14,residcess);
	    	     resultcompile.add(15,rescdemet);
	    	     resultcompile.add(16,resdatemis);
	    	     resultcompile.add(17,resnbrdoss);
	   //ici j'instancie mon agent resultant avec le vecteur resultat de compilation
	        agres =	parseurevcc.Agent.agentbyvect(resultcompile);
	   
	   return agres;
	   }
		@SuppressWarnings("static-access")
		public static Agent resultatderecherche(String crt, String arg){

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
       
	    public static int positionrecherche(String crt,String arg){
	    	int res = 1 ;
	    	if(!(crt==null) && !(arg==null)){    
	 res = Integer.parseInt((resultatderecherche(crt, arg)).getposition());
	    }else{}
	return res; }

		@SuppressWarnings("static-access")
		public static void main(String[] args) throws JDOMException {
			codeemm  = "5050"; 
			dateemm = "200904";
			chemindepot =System.getenv("WScesscrea").trim()+"\\"+codeemm+"\\"+dateemm;
	   loaddata(chemindepot+"\\");
			Vector<String>	res = 	extrairevecteuragent(chemindepot+"\\",4);
	      String resdonne = extrairedonne(3,"nom");
	      System.out.println(resdonne);
 // test de creation d'un agent 
      Agent agenttest = new Agent();
       agenttest =   Agent.agentbyvect(res);
       agenttest.tostring(); //afichage de l'agent
		
       System.out.println("************* test recherche *******************");
       
       String pos =extrairepositionagent("GOURNI OMAR");
       System.out.println(pos);
       int r = Integer.parseInt(pos);//extrairepositionagent("GOURNI OMAR"));
       Vector<String> resag = extrairevecteuragent(chemindepot+"\\",r);
       System.out.println(resag);
	                   }
}
