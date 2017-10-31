package composants;

import java.io.*;

import javax.swing.JOptionPane;

public class Copie {
	
		  
		 public static void copier(final InputStream inStream, final OutputStream outStream, final int bufferSize) throws IOException {
		  final byte[] buffer = new byte[bufferSize];
		  int nbRead;
		  while ((nbRead = inStream.read(buffer)) != -1) {
		   outStream.write(buffer, 0, nbRead);
		  }
		 }
		    
		 public static void copiedossier(File from, File to) throws IOException {
		  if (! to.exists()) {
		   to.mkdir();
		  
		  final File[] inDir = from.listFiles();
		  for (int i = 0; i < inDir.length; i++) {
		    File file = inDir[i];
		   copier(file, new File(to, file.getName()));
		  }
		  }else{JOptionPane.showMessageDialog(null, " Cet Emission Evcc a deja été chargée ");}
		 }
		
		 public static void copierFichier(File src,  File dest) throws IOException {
		  InputStream inStream = new FileInputStream(src);
		  OutputStream outStream = new FileOutputStream(dest);
		  copier(inStream, outStream, (int) Math.min(src.length(), 4*1024));
		  inStream.close();
		  outStream.close();
		 }
		
		 public static void copier( File src,  File dest) throws IOException {
		  if (src.isFile()) {
		   copierFichier(src, dest);
		  } else if (src.isDirectory()){
		   copiedossier(src, dest);
		  } else {
		   throw new FileNotFoundException(src.toString() + " Source Introuvable" );
		  }
		 } 
		 static public boolean supprimerdossier(File path) { 
		        boolean resultat = true; 
		        
		        if( path.exists() ) { 
		                File[] files = path.listFiles(); 
		                for(int i=0; i<files.length; i++) { 
		                        if(files[i].isDirectory()) { 
		                                resultat &= supprimerdossier(files[i]); 
		                        } 
		                        else { 
		                        resultat &= files[i].delete(); 
		                        } 
		                } 
		        } 
		        resultat &= path.delete(); 
		        return( resultat ); 
		} 

	
	

		 
	public static void main(String[] args) {
/*		File source = new File("C:\\Documents and Settings\\Maestro\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\");//EVCC492920090900.xml	
		File destination  = new File("C:\\Documents and Settings\\Maestro\\Bureau\\Espace de Travail CessCrea\\4929\\200909");
	try {
			copier(source,destination);
		} catch (IOException e) {
			e.printStackTrace();
		
		}*/
		
		File [] racines = File.listRoots ();
		for(int i=0;i<racines.length;i++) 
		    System.out.println("racines["+i+"]:" + racines[i]); 
		
		
	}
}
