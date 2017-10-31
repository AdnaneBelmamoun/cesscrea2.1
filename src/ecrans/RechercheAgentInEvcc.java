package ecrans;



	import java.io.File;
	import java.io.IOException;
	import java.util.Iterator;
	import java.util.Vector;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.xpath.XPath;
	import javax.xml.xpath.XPathConstants;
	import javax.xml.xpath.XPathExpressionException;
	import javax.xml.xpath.XPathFactory;

	import org.jdom.JDOMException;
	import org.jdom.input.SAXBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import parseurevcc.Agent;




	public class RechercheAgentInEvcc {

	        	
		public static org.jdom.Document documentrech = null ;        
        public static DocumentBuilder builder = null;
        public static Document doc = null;
		public static String chemindepot = System.getenv("WScesscrea")+"\\"+FenetreTraitement.codeemm+"\\"+FenetreTraitement.dateemm+"\\";//"C:\\Documents and Settings\\Maestro\\Bureau\\test00\\4929\\200909\\";
		public static Vector<String> resultcompile = new Vector<String>();
		public static Vector<String> vectexpressions = new Vector<String>();
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
	    public static String pathevcc = System.getenv("WScesscrea")+"\\"+FenetreTraitement.codeemm+"\\"+FenetreTraitement.dateemm+"\\";   
		public static Vector<String>  vectinfos = new Vector<String>();// parseurevcc.parseur.generercdinfo(pathevcc);
		
	    public static org.jdom.Document parse(File _FilePath) {
		     
	        try {
	            /* On crée une instance de SAXBuilder */
	            SAXBuilder sxb = new SAXBuilder();
	            documentrech = sxb.build(_FilePath);
	        } catch (IOException e) {
	            System.out.println("Erreur lors de la lecture du fichier " 
					+ e.getMessage() );
	            e.printStackTrace();
	        } catch (JDOMException e){
	            System.out.println("Erreur lors de la construction du fichier JDOM " 
					+ e.getMessage() );
	            e.printStackTrace();
	        }
	 return documentrech;}
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


	    public static String repath(String init){
			 String clone = init;
			 String res  = clone.replace('\\', '/');
				      return res ;
		 }

	    public static Agent trouveragentBy(String critere, String s) throws SAXException, IOException, XPathExpressionException, ParserConfigurationException{
	    	vectinfos = parseurevcc.parseur.generercdinfo(pathevcc);
	    	Agent agres = new Agent();
	    	chemindepot = System.getenv("WScesscrea")+"\\"+FenetreTraitement.codeemm+"\\"+FenetreTraitement.dateemm+"\\";//"C:\\Documents and Settings\\Maestro\\Bureau\\test00\\4929\\200909\\";
		    
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
	    	
			 
  	 String  entryFilexml = repath(trouverevccxml(chemindepot));
 	 DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	 domFactory.setNamespaceAware(true); // never forget this!
	 
	 builder = domFactory.newDocumentBuilder();
	 doc = builder.parse(entryFilexml);
	 XPathFactory factory = XPathFactory.newInstance();
	 XPath xpath = factory.newXPath();
	 
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
	public static void main(String[] args) throws ParserConfigurationException, SAXException
	                                                 ,IOException, XPathExpressionException {
		 parseurevcc.parseur.codeemm = "4929";
		 parseurevcc.parseur.dateemm = "200909";
		 FenetreTraitement.codeemm = "4929";
		 FenetreTraitement.dateemm ="200909";
		   
	                  parseurevcc.Agent resagent = trouveragentBy("Nom", "GOURNI OMAR");
	                   System.out.println("l'agent est :\n ");
	                    resagent.tostring();
	                //   System.out.println( Integer.parseInt(resagent.getposition()));
	                   // ecrans.FenetreTraitement.fentemp =                 
	    // ecrans.FenetreTraitement.creerfenetre2(chemindepot, Integer.parseInt(resagent.getposition()));              
	   }}






		   /* 
		   
		chemindepot = "C:\\Documents and Settings\\Maestro\\Bureau\\test00\\4929\\200909\\";
			
			 String  entryFilexml = repath(trouverevccxml(chemindepot));
		       
	  DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	  domFactory.setNamespaceAware(true); // never forget this!
	  DocumentBuilder builder = domFactory.newDocumentBuilder();
	  Document doc = builder.parse(entryFilexml);

	  XPathFactory factory = XPathFactory.newInstance();
	  XPath xpath = factory.newXPath();
	  javax.xml.xpath.XPathExpression expr;
	  
	try {
		expr = xpath.compile("//EVCC/Agent["+"Nom"+"='"+"HAMINI BOUCHAIB"+"']/Cin/text()");
		
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    
	    NodeList nodes = (NodeList) result;

	  for (int i = 0; i < nodes.getLength(); i++) {System.out.println(nodes.item(i).getNodeValue());  }
		

	} catch (XPathExpressionException e) {
		System.out.println(" Agent non existant dans cette Evcc ");}

	   */
		//}
		
	//}

