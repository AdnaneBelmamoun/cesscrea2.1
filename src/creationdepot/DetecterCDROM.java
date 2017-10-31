package creationdepot;


	import java.io.File; 
	import java.util.Arrays;
	import java.util.List;
	import javax.swing.filechooser.FileSystemView; 
	
	public class DetecterCDROM {
		
		public static File fichsys;
		public static String s1;
        public static List <File> files = Arrays.asList(File.listRoots());
        
   public static boolean iscdrom(File f){
			boolean res = false ;
		int i =	(FileSystemView.getFileSystemView().getSystemTypeDescription(f)).compareToIgnoreCase("lecteur cd");
			if(i==0){  res = true;    }
			return res;
		}
   public static void detectercd(){
				
		for (File f : files) { 
			
			 s1 = null;
			if(iscdrom(f)){ 
			
				s1	= FileSystemView.getFileSystemView().getSystemDisplayName (f);
			      // System.out.println("le lecteur cd est : " + s1);
			               }
		                      }
   }
   public static String detecterlettrelecteurcd(){
	   String [] x = {"C:","D:","E:","F:","G:","H:","I:","J:","K:","L:","M:","N:"};
	   String res = null ;
	   detectercd();
	   for(String y: x){
	  		   if(s1.contains(y)){
			   //System.out.println("le nom racine du lecteur cd est : " + y);
			   res = y ;
			   }
	  		  
	   }
	
	   
	   return res;
   }
		
   public static void main(String args[]){ 
	      
		 String lettrelecteur =detecterlettrelecteurcd();
		 if(lettrelecteur == null){System.out.println("CD Emission introuvable");}
		 else{System.out.println("le nom racine du lecteur cd est : " + lettrelecteur);}
		} 
		
	}
		