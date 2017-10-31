package outils;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import composants.ImageFileView;
import composants.ImagePreview;

public class Test {
	public static String nomim;
	public static String pathim;
	public static String profilutilisateur = System.getenv("USERPROFILE");
	public static String initpath = profilutilisateur+"\\Bureau\\";//+pathdepot.getpathdepot();
	public static String profilcesscerea = null;
	
	public Test(){ fctest();}
	
	public static String repath(String init){
		 String clone = init;
		 String res  = clone.replace('\\', '/');
			      return res ;
	 }
	public static Component fctest(){
		JFileChooser fileopen = new JFileChooser(initpath);
             fileopen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    // Add custom icons for file types.
     fileopen.setFileView(new ImageFileView());
      // Add the preview pane.
     fileopen.setAccessory(new ImagePreview(fileopen));

    int ret = fileopen.showDialog(null, "parcoure de evcc images");
switch (ret) {
    case JFileChooser.APPROVE_OPTION:
    	      //System.out.println("click sur Approuve (Ouvrir  ou enregistrer) ");
                 File file = fileopen.getSelectedFile();
	     // System.out.println(file.getName());
	     // System.out.println(file.getPath());
	     // System.out.println(file.getParentFile().getPath());
	             nomim =file.getName();
	          imagezoom.ScrollDemo.nomimage = nomim;
	          imagezoom.ScrollDemo.pathimage = repath(file.getParent()+"\\");
	     // runner(nomim,file.getParentFile().getPath());
	          System.out.println(repath(file.getParentFile().getPath()+"\\"));
	          imagezoom.ScrollDemo.createAndShowGUI(imagezoom.ScrollDemo.nomimage, imagezoom.ScrollDemo.pathimage);
	          initpath = repath(file.getParentFile().getPath()+"\\");
     break;
    case JFileChooser.CANCEL_OPTION:
      //System.out.println("click sur annuler ou icone de fermeture ");
      
      break;
    case JFileChooser.ERROR_OPTION:
      System.out.println("Erreure");
      
      break;
}
return fileopen ;
	}
	public static void main(String[] args) {
 
       fctest();	
	}
	    
	

}
