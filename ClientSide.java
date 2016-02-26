import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSide {
	public static void main(String[] args){
                  
		String serverName = "localhost";
		int port = 10000;
		
		try{
			Socket sock = new Socket(serverName, port);
			DataOutputStream serverinbound_string = new DataOutputStream(sock.getOutputStream());
			
			InputStream serveroutbound = sock.getInputStream();
			OutputStream serverinbound = sock.getOutputStream();
			DataInputStream in = new DataInputStream(serveroutbound);
			ObjectOutputStream oos = new ObjectOutputStream(serverinbound);
			
			Scanner input = new Scanner(System.in);
			System.out.print("Please enter 0 to download a Note, or 1 to archive to the server");
                              
			String selection = input.nextLine();
			
			if(selection.equals("0")){
                            
                            
				System.out.println("Please enter your username: ");
				String Nameinput = input.nextLine();
				
				serverinbound.write(0); 
				serverinbound_string.writeUTF(Nameinput);
				
				System.out.println("Not received: ");
				System.out.println(in.readUTF());
				
			}else if(selection.equals("1")){
				System.out.println("Please enter your username: ");
				String uname = input.nextLine();
				
				System.out.println("Please enter Note Data: ");
				String noteData = input.nextLine();
				serverinbound.write(1); 
				ForNotes temp= new ForNotes(uname,noteData);
				oos.writeObject(temp);
				
			}
	
			sock.close();
		}catch(IOException e){
			e.printStackTrace();
            }
    }
}
