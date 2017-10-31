package parseurevcc;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

public class Evcc {

	// infos EVCC
	public static String idcess;
	public static String codeemmet;
	public static String dateemm;
	public static String nbrdoss;
	//public static String pathdepot =System.getenv("WScesscrea");
	                             

	public static Vector <Vector<String>>  listagents= new Vector<Vector<String>>() ;
	
	//construction de mon evcc
	public Evcc(){	}
	public Evcc(String id, String emmet, String date, String nbdos, Vector<Vector<String>> list){	
		setIdcess(id);                      setDateemm(date);
		setCodeemmet(emmet);               setListagents(list);
		setnbrdoss(nbdos);       
	}
	
	public static Vector<String> getinfos(){
		Vector<String> temp = new Vector<String>();
		temp.add(getIdcess());
		temp.add(getCodeemmet());
		temp.add(getDateemm());
		temp.add(getnbrdoss());
		System.out.println(temp);
		return temp;  }
		
	public static void setIdcess(String idcess) {
		Evcc.idcess = idcess;
	}
	public static String getIdcess() {
		return idcess;
	}
	public static void setCodeemmet(String codeemmet) {
		Evcc.codeemmet = codeemmet;
	}
	public static String getCodeemmet() {
		return codeemmet;
	}
	public static void setDateemm(String dateemm) {
		Evcc.dateemm = dateemm;
	}
	public static String getDateemm() {
		return dateemm;
	}
	public static void setnbrdoss(String nbdos) {
		Evcc.nbrdoss = nbdos;
	}
	public static String getnbrdoss() {
		return nbrdoss;
	}
	public static void setListagents(Vector<Vector<String>> listagents) {
		
		Evcc.listagents.addAll(listagents);
	}
	public static Vector<Vector<String>> getListagents() {
		return Evcc.listagents;
	}
	public static Evcc charger_evcc(String emetteur,String date){
		String path = System.getenv("WScesscrea")+"\\"+emetteur+"\\"+date+"\\";
	    File entryFile = new File(parseurevcc.parseur.repath(parseurevcc.parseur.trouverevccxml(path)));
		org.jdom.Document doc = parseurevcc.parseur.parse(entryFile);
		parseurevcc.parseur.codeemm  = emetteur; 
		parseurevcc.parseur.dateemm = date;
		parseurevcc.parseur.chemindepot =System.getenv("WScesscrea").trim()+"\\"+emetteur+"\\"+date;
		Vector<Vector<String>>	matdonnees = 	parseurevcc.parseur.extractiondesbalises(doc);
	    
		Iterator<Vector<String>> it =  matdonnees.iterator();
	    
	    Vector<Vector<String>> mattemp = new Vector<Vector<String>>();
	     while(it.hasNext()){ 
	    	   Vector<String> vecttemp = (Vector<String>) it.next();
	    	   mattemp.add(vecttemp);
	   	      //listagents.add((Vector<String>) it.next());//vecttemp);// ici je charge aussi tout les vecteures agent dans la matrice Evcc.listagents 
		      }	
         Vector<String> listinfos = new Vector<String>();
         listinfos.addAll(parseurevcc.parseur.extraireinfocd(doc));
         String id = listinfos.get(0);
         String cdetmp = listinfos.get(1);
         String datetmp = listinfos.get(2);
         String nbdossier = listinfos.get(4);
         // construction de l'evcc
         Evcc evctemp = new Evcc(id, cdetmp , datetmp, nbdossier ,mattemp);
    return evctemp;	
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		String emetteur = "4929";
		String date = "200909";
		
		
		Evcc evc = charger_evcc(emetteur,date);
	    evc.getinfos();
	//*********************************	
	    Agent agenttemp = new Agent();
	      int pos = 11;
          agenttemp =  parseurevcc.Agent.agentbyvect(evc.getListagents().get(pos-1));
          agenttemp.tostring();//affichage de l'agent

   //************************************************
                
          Iterator<Vector<String>> itag =evc.getListagents().iterator();
          while(itag.hasNext()){
          System.out.println( (Vector<String>)itag.next()+"\n");
          }
        
            
            
            
            
            
            
            
            

	}
	
}
