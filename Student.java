public class Student extends Person{

	
	
	public Student(String login, String password) {
		super();
		setLogin(login);
		setPassword(password);
		setNameDataTable("students");
		
		
	}
	
	

	/**
	 * Metoda zwraca kolejno lancuch znakow String:
	 * -Access jesli dane login i haslo sa poprawne
	 * -NotExist- jesli nie ma osoby o danum loginie lub haslo do tej osoby jest zle
	 */
	
	public String loginPerson() {

		String answer  = "";
		String query   = "SELECT haslo FROM students WHERE login = " + "'" + getLogin() + "'";
		
		String basePassToThisLogin = getBase().sendQuery(query);
		
		
		if(getPassword().equals(basePassToThisLogin)) {
			answer = "Access";
			
			query = "SELECT `studentId` 	FROM `students` WHERE login = " + "'" + getLogin() + "'";
			setPersonId(Integer.parseInt(getBase().sendQuery(query)));
			
			query = "SELECT `permissionId` 	FROM `students` WHERE login = " + "'" + getLogin() + "'";
			setPermissionId(getBase().sendQuery(query));
			
			query = "SELECT `index` 		FROM `students` WHERE login = " + "'" + getLogin() + "'";
			setIndex(getBase().sendQuery(query));
			
			
			
			setIsLogged(true);
		}
		else answer = "NotExist";
		
		return answer;
	}
	
	public String saveDataPerson() {

			String query = "INSERT INTO `" + getNameDataTable() + "` (`login`, `haslo`, `permissionId`, `index`) " + 
							"VALUES "+ "('" + getLogin() + "', '" + getPassword() + "', '" + getPermissionId() + "', '" + getIndex() + "'); ";
			
			
			String answer = getBase().sendUpdate(query);

		return answer;
	}
	
	
	

}
