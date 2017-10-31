package ecrans;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

	
	public class Fc extends JPanel implements ActionListener {
	    public static final long serialVersionUID = 1L;


	    public Fc() {


	    }
	    public void actionPerformed(ActionEvent e) {

                  }
	    
	   	    /** Returns an ImageIcon, or null if the path was invalid. */
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = Fc.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }

	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event dispatch thread.
	     */
	    @SuppressWarnings("unused")
		private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("FileChooserDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Add content to the window.
	        frame.add(new Fc());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
        
	    	System.out.println(System.getenv("WScesscrea"));
	    	
	   /* 	File file = null;
	    	//JFrame fentemp1 = FenetreTraitement.creerfenetre2(System.getenv("WScesscrea"),100);
	    	Wizardinstall testfcwizard = new Wizardinstall();
	    	JFileChooser fc = new JFileChooser();
	        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	int returnVal = fc.showOpenDialog(testfcwizard);
	    	 if (returnVal == JFileChooser.APPROVE_OPTION) {
	                 file = fc.getSelectedFile();	    	 
	    	System.out.println("ouverture: " + file.getName() + "---->    "+ file.getAbsolutePath());
	    	
	    	 }
	    	else{System.out.println("ouverture annulée par l'utilisateur ");}
	    	       
	    	/*  SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                //Turn off metal's use of bold fonts
	                UIManager.put("swing.boldMetal", Boolean.FALSE); 
	                createAndShowGUI();
	            }
	        });*/
	    	
	    	
	    }
	}
