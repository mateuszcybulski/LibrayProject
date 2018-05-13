import java.util.ArrayList;

public class Admin extends Person{


	public Admin (String login, String password) {
		super();
		setLogin(login);
		setPassword(password);
		setNameDataTable("admins");
		
		
	}
	
	/**
	 * Metoda zwraca kolejno lancuch znakow String:
	 * -Access jesli dane login i haslo sa poprawne
	 * -NotExist- jesli nie ma administratora o danym loginie lub haslo tej osoby jest niepoprawne
	 * -Bad pass
	 */
	
	public String loginPerson() {
		String answer  = "";
		String query   = "SELECT password FROM admins WHERE login = " + "'" + getLogin() + "' LIMIT 1";
		
		String basePassToThisLogin = getBase().sendQuery(query);
		
		if(getPassword().equals(basePassToThisLogin)) {
			
			query = "SELECT `adminId`, `permissionId` FROM `admins` " +  "WHERE login = " + "'" + getLogin() + "'";
			ArrayList<String> data = getBase().getArrayList(query, 2);
			
			
			if(data.size() > 0) {
				setPersonId(Integer.parseInt(data.get(0)));
				setPermissionId(data.get(1));
				setIsLogged(true);
				answer = "Access";
				
			}
			else answer = "NotExist";
			
		
		}
		else answer = answer + "Bad pass";

		return answer;
	}
	
	
	
	
	
	
}
