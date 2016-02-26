import java.io.*;


public class ForNotes implements Serializable{
    
        public String user, notedata;
	
	ForNotes(){
		user = "Danyyal Ahmed Khalid";
		notedata = "WaafarData";		
	}
	
	ForNotes(String argU, String argD){
		user = argU;
		notedata = argD;		
	}
}
