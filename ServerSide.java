import java.net.*;
import java.util.*;
import java.io.*;

public class ServerSide {
	
    
    private static void storage(ArrayList<ForNotes> xyz){
			
		try 
		{
			FileOutputStream out = new FileOutputStream("note.ser");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(xyz);
			objOut.close();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		int port = 10000;
		ServerSocket listener = null;
		
		ArrayList<ForNotes> stored = new ArrayList<ForNotes>();
	
		stored.add(new ForNotes("Danyyal A. Khalid","MUST DO THE LAB ON MY OWN"));
		
		try {
			listener = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
                
		try{
			while(true){
				Socket soaking = listener.accept();
				
				InputStream clientoutbound = soaking.getInputStream();
				DataInputStream strng = new DataInputStream(soaking.getInputStream());
				DataOutputStream out = new DataOutputStream(soaking.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(soaking.getInputStream());
			
				int selection = clientoutbound.read();
				
				if(selection==0){
					
					String uname = strng.readUTF();
					System.out.println("Data obtained for " + uname);
					String Datatosend = "Data";
                                        
					for(int i=0; i<stored.size(); i++){
						if(stored.get(i).user.equals(uname)){
							Datatosend.concat(stored.get(i).notedata+"\n");
						}
					}
					System.out.println("Datatosend: " +Datatosend);
					
					out.writeUTF(Datatosend);
					
					
				}else if(selection==1){
					ForNotes tempNote = (ForNotes)ois.readObject();
					if(tempNote != null){
						
						stored.add(tempNote);
						System.out.println("Done!");
					}
					else{
						System.out.println("Problem");
					}
					
					System.out.println("Current stored: ");
					for(int i=0; i<stored.size(); i++){
						System.out.println(stored.get(i).user + " | " +stored.get(i).notedata);
					}
				}	
				
				out.writeUTF("Connection success");
				
				soaking.close();
				}			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		listener.close();
	}
}
