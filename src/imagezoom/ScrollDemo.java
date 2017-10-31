package imagezoom;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import outils.Test;


public class ScrollDemo extends JPanel implements ItemListener {
	public static final long serialVersionUID = 1L;
	public static Rule columnView;
    public static Rule rowView;
    public static JToggleButton isMetric;
    public static ScrollablePicture picture;
   public static String nomimage;// ="49290759781A905382400.jpg"; 
   public static String pathimage ;//= FileChooser.repath("C:\\Documents and Settings\\Maestro\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\EVCC_Images\\");

    public ScrollDemo(String pathim, String nomim) {
    	nomimage = nomim ;
    	pathimage = pathim ;
    	editerimage(pathimage,nomimage);} 
    public  void editerimage(String path, String nom){ 
    	String chemincomplet = path.concat(nom);
    	editerimage(chemincomplet);}
    public void editerimage(String pathcomplet){
    	//this.setName(Testparsing.codeimage);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
       
        //Get the image to use.
        ImageIcon Adnane = createImageIcon(pathcomplet);
        ImageIcon Adnaneok = new ImageIcon(Adnane.getImage().getScaledInstance(700, -1,Image.SCALE_DEFAULT));
            
        //Create the row and column headers.
        columnView = new Rule(Rule.HORIZONTAL, true);
        rowView = new Rule(Rule.VERTICAL, true);

        if (Adnaneok != null) {
            columnView.setPreferredWidth((Adnaneok.getIconWidth()));
            rowView.setPreferredHeight((Adnaneok.getIconHeight()));
        } else {
            columnView.setPreferredWidth(320);
            rowView.setPreferredHeight(480);
        }

        //Create the corners.
        JPanel buttonCorner = new JPanel(); //use FlowLayout
        isMetric = new JToggleButton("cm", true);
        isMetric.setFont(new Font("SansSerif", Font.PLAIN, 11));
        isMetric.setMargin(new Insets(2,2,2,2));
        isMetric.addItemListener(this);
        buttonCorner.add(isMetric); 

        //Set up the scroll pane.
        picture = new ScrollablePicture(Adnaneok, columnView.getIncrement());
    
        JScrollPane pictureScrollPane = new JScrollPane(picture);
       // pictureScrollPane.setLocation(500, 20);
        pictureScrollPane.setPreferredSize(new Dimension(500, 650));
        pictureScrollPane.setViewportBorder( BorderFactory.createLineBorder(Color.black));

        pictureScrollPane.setColumnHeaderView(columnView);
        pictureScrollPane.setRowHeaderView(rowView);

	    pictureScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                                    buttonCorner);
        pictureScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER,
                                    new Corner());
        pictureScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER,
                                    new Corner());

        //Put it in this panel.
        add(pictureScrollPane);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            //Turn it to metric.
            rowView.setIsMetric(true);
            columnView.setIsMetric(true);
        } else {
            //Turn it to inches.
            rowView.setIsMetric(false);
            columnView.setIsMetric(false);
        }
        picture.setMaxUnitIncrement(rowView.getIncrement());
    }

    
    protected static ImageIcon createImageIcon(String path) {
       // java.net.URL imgURL = ScrollDemo.class.getResource(path);
        if (path != null) {
            return new ImageIcon(path);
        } else {
            System.err.println("fichier introuvable: " + path);
            return null;
        }
    }

    
    public static void createAndShowGUI(String path,String nom) {
        //Create and set up the window.
    	nomimage = nom;//"49290759781A905382400.jpg";
        JFrame frame = new JFrame(nomimage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
JComponent newContentPane = new ScrollDemo(Test.repath(path), nom);// );
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
public static void runner (){
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	nomimage = "49290759781A905382400.jpg";
            	pathimage = "C:\\Documents and Settings\\Maestro\\Bureau\\boulot tgr\\4282EVCC_ Echéance 08-09\\EVCC_Images\\";
                createAndShowGUI(pathimage,nomimage);
            }
        });
}
    public static void main(String[] args) {
         runner();
    	
    }
}
