package ecrans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import parseurevcc.Agent;

public class FenetreDemarage extends JFrame implements ActionListener{

public static final long serialVersionUID = 1L;

public final static String cheminworkspace =System.getenv("WScesscrea");//System.getenv("USERPROFILE")+"\\Bureau"+"\\"+nonworkspace;
public static String  codeemm  ="4929";      //4929"; 
public static String   dateemm = "200909";    //200909";  //System.getenv("USERPROFILE")+"\\Bureau\\";
public static String pathdepot = (System.getenv("WScesscrea").trim()+"\\"+codeemm+"\\"+dateemm).trim();//cheminworkspace+"/"+codeemm+"/"+dateemm+"/" ;//"C:\\Documents and Settings\\Maestro\\Bureau\\test00\\4929\\200909\\";


public static parseurevcc.Agent agenttemp = new Agent();
public static JFrame fentemp;
	

	public void actionPerformed(ActionEvent e) {
	
		
	
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
