package parseurevcc;

import java.util.Vector;

public class Agent {
    public static String position = ""; 
	public static String nom = "";
	public static String cin = "";
	public static String matricule = "";
	public static String chapitre = "";
	public static String fichierimage = "";
	public static String numerodedossier = "";
	public static String codevendeur = "";
	public static String montantnet = "";
	public static String montanttotal = "";
	public static String  montantmesuel = "";
	public static String nombremois = "";
	public static String datedebut = "";
	public static String datefin = "";
	
	public static String idcession = "";
	public static String codemet = "";
	public static String  datemis = "";
	public static String nbredoss = "";
	
	public Agent(){}
	@SuppressWarnings("static-access")
	public Agent(String pos,      String mat,       String nom  ,     String cin,
	             String chap,     String fich,      String numdoss,   String cdv ,
	             String montnet,  String monttot,   String montmens,  String nbrmois,  
	             String datedeb,  String datefin,   String idcess,    String cde , String de, String nbrdoss)
	{this.position =pos;         
	this.matricule = mat; 	       this.nom =nom  ;             this.cin =cin    ;
	this.chapitre=chap;            this.fichierimage=fich;      this.numerodedossier=numdoss; 
	this.codevendeur = cdv ;       this.montantnet=montnet;     this.montanttotal=monttot; 
	this.montantmesuel= montmens;  this.nombremois=nbrmois;     this.datedebut=datedeb;      
	this.datefin= datefin;         this.idcession =idcess;      this.codemet = cde;        this.datemis = de;		
	              this.nbredoss = nbrdoss;}
	public static Agent agentbyvect(Vector<String> vect){
		Agent ag = new Agent();
	if(!(vect.isEmpty())){	 ag = new Agent(vect.get(0),
				             vect.get(1 ), vect.get(2),  vect.get(3),
				             vect.get(4),  vect.get(5),  vect.get(6),
				             vect.get(7),  vect.get(8),  vect.get(9),
				             vect.get(10), vect.get(11), vect.get(12),
				             vect.get(13), vect.get(14), vect.get(15),vect.get(16),vect.get(17));
		return ag;}
	else{	ag = new Agent(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null); 
	return ag;}
	}
	public  String getposition(){	return position;}
	public static String getnom(){	return nom;}
	public static String getcin(){	return cin;}
	public static String getmatricule(){	return matricule;}
	public static String getchap(){	return chapitre;}
	public String getfichim(){	return fichierimage;}
	public static String getcdvendeur(){return codevendeur;}
	public static String getnumdoss(){	return numerodedossier;}
	public static String getmontnet(){	return montantnet;}
	public static String getmonttotale(){	return montanttotal;}
	public static String getmontmensuel(){	return montantmesuel;}
	public static String getnbremois(){	return nombremois;}
	public static String getdatedebut(){	return datedebut;}
	public static String getdatefin(){	return datefin;}
	
	public static String getidcession(){return idcession;}
	public static String getcodemetteur(){return codemet;}
	public static String getdatemission(){return datemis;}
	public  String getnbredossier(){return nbredoss;}
	
	/*public static void setnom(String lenom){nom = lenom; }
	public static void setcin(String lacin){cin = lacin; }
	public static void setmatricule(String mat){matricule = mat; }
	public static void setchap(String chap){chapitre = chap; }
	public static void setfichim(String fich){fichierimage = fich; }
	public static void setnumdoss(String numdoss){numerodedossier = numdoss; }           cet ensemble de set  pour modifier un agent
	public static void setmontnet(String montnet){montantnet = montnet; }
	public static void setmonttotale(String monttot){montanttotal = monttot; }
	public static void setmontmensuel(String montmens){montantmesuel = montmens; }
	public static void setnbremois(String nbmois){nombremois = nbmois; }
	public static void setdatedebut(String datedeb){ datedebut = datedeb; }
	public static void setdatefin(String dtfin){datefin = dtfin; }
	*/
	
	@SuppressWarnings("static-access")
	public static Vector<String> getvectagent(Agent ag){
		Vector<String> res = new Vector<String>();
		res.add(ag.getposition());
		res.add(ag.getmatricule());
		res.add(ag.getnom());
		res.add(ag.getcin());
		res.add(ag.getchap());
		res.add(ag.getfichim());
		res.add(ag.getnumdoss());
		res.add(ag.getcdvendeur());
		res.add(ag.getmontnet());
		res.add(ag.getmonttotale());
		res.add(ag.getmontmensuel());
		res.add(ag.getnbremois());
		res.add(ag.getdatedebut());
		res.add(ag.getdatefin());
		res.add(ag.getidcession());
		res.add(ag.getcodemetteur());
		res.add(ag.getdatemission());
		res.add(ag.getnbredossier());
		return res;
	}
	public static void tostring(){
		System.out.println(position+"/"+nbredoss+"    "+nom +"       "+cin+"  "+matricule+"  "+numerodedossier+" "+fichierimage+"\n"
				+"\t \t"+codevendeur+""+numerodedossier+"\n"
				+"\t \t"+montantnet+"   \n"+montanttotal+"    \n    "+montantmesuel+"  \n \t   nombre de mois :  "+nombremois +"  \n   " +
						" date debut : "+datedebut+"   \t    date fin :    "+datefin+"\n" 
				+"infos cession :--->\t \t"+idcession +"      "+codemet+"            "+ datemis + "\n" );
		System.out.println("**************************************************************************************************************************************************************" +
				"*****************************************************************************************************************************************************************************");
	}
	
	public static void main(String[] args) {
		

	}

}
