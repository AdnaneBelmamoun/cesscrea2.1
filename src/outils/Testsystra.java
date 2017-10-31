package outils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Testsystra {
	public static SystemTray tray;
	
	 public static ImageIcon createImageIcon(String path, String description) {
	        
	        if (path != null) {
	            return new ImageIcon(path, description);
	        } else {
	            System.err.println("fichier introuvable: " + path);
	            return null; 
	        }
	    }
	
	public static void main(String[] args) throws AWTException {
	
		if(SystemTray.isSupported()){
		tray = SystemTray.getSystemTray();
			}
		ImageIcon icon = createImageIcon("C:/Documents and Settings/Maestro/Bureau/vlcsnap-367581.png", "icone");
	      Image  image = icon.getImage();
	      
	//	Image image = ImageIO.read(getClass().getClassLoader().getResource("icone"));
		PopupMenu popup = new PopupMenu();
		MenuItem openItem = new MenuItem("Ouvrir");
		openItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//Ouvre la fenêtre
		} });
		popup.add(openItem);
		MenuItem closeItem = new MenuItem("Fermer");
		closeItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//Ferme l'application
		}
		});
		popup.add(closeItem);
		TrayIcon trayIcon = new TrayIcon(image, "test sys tray", popup);

		tray.add(trayIcon);
	    
		trayIcon.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			//Clic droit
			}
			});
		
		trayIcon.setImageAutoSize(true);
		trayIcon.displayMessage("Erreur", "Problème survenu", TrayIcon.MessageType.ERROR);
		
		
		
		
	}

}
