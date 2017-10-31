package ecrans;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import execution.ExecutionCessCrea;

public class SysTray {
	String[] arg;
	public static SystemTray tray =null;
	
	public SysTray(){
		if(SystemTray.isSupported()){
			tray = SystemTray.getSystemTray();
				}
			
		      Image  image =  Toolkit.getDefaultToolkit().getImage(System.getenv("ProgramFiles")+"\\"+"CessCrea2.1"+"\\images"+"\\icone.jpg");
		      
		
			PopupMenu popup = new PopupMenu();
			MenuItem openItem = new MenuItem("Ouvrir CessCrea");
			openItem.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			//Ouvre la fenêtre de cesscrea
				if(FenetreTraitement.fentemp == null && Traitement.fentemp == null){
					ExecutionCessCrea.executercesscrea(arg);  
					}else{
if(!(FenetreTraitement.fentemp == null)){
if(FenetreTraitement.fentemp.getExtendedState()== JFrame.HIDE_ON_CLOSE){FenetreTraitement.fentemp.show(true);}		
if(FenetreTraitement.fentemp.getExtendedState()== JFrame.ICONIFIED ){ FenetreTraitement.fentemp.setExtendedState(JFrame.MAXIMIZED_BOTH) ;  }	 
}
if(!(Traitement.fentemp == null)){
  if(Traitement.fentemp.getExtendedState()== JFrame.HIDE_ON_CLOSE){Traitement.fentemp.show(true);}		
  if(Traitement.fentemp.getExtendedState()== JFrame.ICONIFIED ){ Traitement.fentemp.setExtendedState(JFrame.NORMAL) ;  }	 
}
else{ }
			} }});
			popup.add(openItem);
			MenuItem closeItem = new MenuItem("Quitter CessCrea");
			closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.gc(); System.exit(0);//liberer la memoire et Fermer l'application
			
			}
			});
			popup.add(closeItem);
			TrayIcon trayIcon = new TrayIcon(image, " CEssCrea2.1", popup);

			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				
				e1.printStackTrace();
			}
		    
			trayIcon.addActionListener(new ActionListener() {
				
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
				//Clic droit
					System.out.println("clique droit");
					if(FenetreTraitement.fentemp == null && Traitement.fentemp == null){
						ExecutionCessCrea.executercesscrea(arg);  
						}else{
if(!(FenetreTraitement.fentemp == null)){
  if(FenetreTraitement.fentemp.getExtendedState()== JFrame.HIDE_ON_CLOSE){FenetreTraitement.fentemp.show(true);}		
  if(FenetreTraitement.fentemp.getExtendedState()== JFrame.ICONIFIED ){ FenetreTraitement.fentemp.setExtendedState(JFrame.MAXIMIZED_BOTH) ;  }	 
}
if(!(Traitement.fentemp == null)){
	  if(Traitement.fentemp.getExtendedState()== JFrame.HIDE_ON_CLOSE){Traitement.fentemp.show(true);}		
	  if(Traitement.fentemp.getExtendedState()== JFrame.ICONIFIED ){ Traitement.fentemp.setExtendedState(JFrame.NORMAL) ;  }	 
	}
else{ }
//if(FenetreTraitement.fentemp.getExtendedState()== JFrame.DISPOSE_ON_CLOSE && Traitement.fentemp==null){Traitement.main(null);}

				}
				
				}//fin action performed double clique
				});
			
			trayIcon.setImageAutoSize(true);
			trayIcon.displayMessage("Bienvenu sur CessCrea \n autheur: Belmamoun Adnane", "CessCrea", TrayIcon.MessageType.INFO);
		
	}
	
	 public static ImageIcon createImageIcon(String path, String description) {
	        
	        if (path != null) {
	            return new ImageIcon(path, description);
	        } else {
	            System.err.println("fichier introuvable: " + path);
	            return null; 
	        }
	 }
	 public static void main(String[] args) throws AWTException {

		new SysTray();
}

}