import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person{
	
	int numberOfIndex = 0;
	
	public Student(String login, String password) {
		super();
		setLogin(login);
		setPassword(password);
		setPermission("student");
		
		
	}
	
	/*
	 * Metoda zwraca kolejno lancuch znakow String:
	 * -Access jesli dane login i haslo sa poprawne
	 * -BadPassword- jesli w bazie znajdzie sie osoba z takim loginem ale haslo do niej jest nieprawidlowe
	 * -NotExist- jesli nie ma osoby o tym loginie
	 *
	/*public String loginPerson() {
		
		File file= new File("students.txt");
		try {

			String [] data;
			String [] data2 = {"", ""};
			String [] dataS = {"", ""};
			Scanner in = new Scanner(file);
			
			String ofFile = null;
			
			
			for(int i = 0 ; in.hasNext(); i++) {
				ofFile = in.nextLine();
				data = ofFile.split("#");
				
				
				data2[0] = data[0];
				data2[1] = data[1];
				
				dataS[0] = getLogin();
				dataS[1] = getPassword();
				

				System.out.println(data2[0] + ", " + data2[1] +  " vs. " + dataS[0] +  ", "+ dataS[1]);
				
				
				if(data2[0] == dataS[0]) {
					if(data2[1] == dataS[1]) {

						setLogin(data[0]);
						setPassword(data[1]);
						setPermission(data[3]);
						//setNumberOfIndex(Integer.parseInt(data[4]));
						
						return "Access";
					}
					else {
						return "BadPassword";
					}
					
				}
				
				
				
				//if(getLogin() == "asd") {
				
				
				//}	
				

			}
			
			
			
			
			
			return "NotExist";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		return null;
		
	}
	*/
	/*
	public boolean saveDataPerson() {
		try {
			
			FileWriter save = new FileWriter ("students.txt", true);
			save.append(getLogin() + "#" + getPassword() + "#" + getPermission() + "#" + getNumberOfIndex() + "#\r\n" );
			
			save.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean readDataPerson() {
		  File file = new File("students.txt");
	     
		  
		  try{
			  
			  Scanner in = new Scanner(file);

			  String zdanie = in.nextLine();
			  System.out.println(zdanie);
			  
			  
			  return true;
		  } catch(FileNotFoundException e) {}
		  
	     
	 
	      
	      
		return false;
	}
	*/

	
	
	
	
	
	public int getNumberOfIndex() {
		return numberOfIndex;
	}

	public void setNumberOfIndex(int numberOfIndex) {
		this.numberOfIndex = numberOfIndex;
	}

}
