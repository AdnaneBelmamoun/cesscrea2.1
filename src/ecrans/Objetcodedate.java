package ecrans;

import java.util.Vector;

public class Objetcodedate extends Vector<String>{
public static final long serialVersionUID = 1L;
	public static String code;
	public static String date;
	public static Vector<String> entreecodedate= new Vector<String>();
	@SuppressWarnings("static-access")
	public Objetcodedate(String code,String date){
		super.add(code);
		super.add(date);
		this.code = code;
		this.date =date;
		this.entreecodedate.add(code);
		this.entreecodedate.add(date);
	                                              }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
