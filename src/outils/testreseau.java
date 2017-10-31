package outils;

import java.net.*; 
import java.io.*;

public class testreseau {

	public static void main(String[] args) throws IOException {
		
		URL yahoo = new URL("http://www.yahoo.com/");
		System.out.println(yahoo.getProtocol());
		  URLConnection test = yahoo.openConnection();
		//yahoo.getHost());
		BufferedReader in = new BufferedReader(
					new InputStreamReader(
					test.getInputStream()));//openStream()

		String inputLine;

		while ((inputLine = in.readLine()) != null)
		    System.out.println(inputLine);

		in.close();

	System.out.println(yahoo.getAuthority());
		System.out.println(yahoo.getPath());
		@SuppressWarnings("unused")
		URLConnection test2 = yahoo.openConnection();
		System.out.println(test.getPermission().getActions());
			BufferedReader intest = new BufferedReader(
				new InputStreamReader(
				test.getInputStream()));

	String inputLinetest;

	while ((inputLinetest = intest.readLine()) != null)
	    System.out.println(inputLinetest);

	intest.close();}

		/* try {
	         Socket skt = new Socket("localhost", 1234);
	         BufferedReader in = new BufferedReader(new
	            InputStreamReader(skt.getInputStream()));
	         System.out.print("Received string: '");

	         while (!in.ready()) {}
	         System.out.println(in.readLine()); // Read one line and output it

	         System.out.print("'\n");
	         in.close();
	      }
	      catch(Exception e) {
	         System.out.print("Whoops! It didn't work!\n");
	      }
*/
	
//	}
	/*Socket theSocket;
	DataInputStream theInputStream; DataInputStream userInput;
	PrintStream theOutputStream;
	String theLine;
	//int i =0;
	try {
	 theSocket = new Socket("test socket", 33957);//Integer.parseInt(args[i+2]));
	 theInputStream = new DataInputStream(theSocket.getInputStream());
	 theOutputStream = new PrintStream(theSocket.getOutputStream());
	 userInput = new DataInputStream(System.in);
	while (true) {
	theLine = userInput.readLine();
	if (theLine.equals(".")) break;
	theOutputStream.println(theLine);
	System.out.println(theInputStream.readLine());
	}
	} catch (UnknownHostException e) { System.err.println(e);
	} catch (IOException e) { System.err.println(e); }
	
	}
	
//}

 try
		{
			InetAddress localaddr = InetAddress.getLocalHost();
			InetSocketAddress res =InetSocketAddress.createUnresolved(yahoo.getHost(),38145);
			System.out.println ("Local IP Address : " + localaddr );
			System.out.println ("Local hostname   : " + localaddr.getHostName());
			System.out.println ("resultat   :"+  res);
			res.getAddress().getHostAddress();
		}
		catch (UnknownHostException e)
		{
			System.err.println ("Can't detect localhost : " + e);
		}
		
	}

	// Converts a byte_array of octets into a string 
	public static String byteToStr( byte[] byte_arr )
	{
		StringBuffer internal_buffer = new StringBuffer();

		// Keep looping, and adding octets to the IP Address
		for (int index = 0; index < byte_arr.length -1; index++)
		{
			internal_buffer.append ( String.valueOf(byte_arr[index]) + ".");
		}
		
		// Add the final octet, but no trailing '.'
		internal_buffer.append ( String.valueOf (byte_arr.length) );

		return internal_buffer.toString();*/
	}
//}//*/