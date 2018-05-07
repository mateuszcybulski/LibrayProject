
public class Student extends Person{
	
	private int numberBooks = 0;

	
	

	public Student(String login, String password) {
		super();
		setLogin(login);
		setPassword(password);
		setNameDataTable("students");
		
		
	}
	
	

	/**
	 * Metoda zwraca kolejno lancuch znakow String:
	 * -Access jesli dane login i haslo sa poprawne
	 * -NotExist- jesli nie ma osoby o danym loginie lub haslo tej osoby jest niepoprawne
	 * -Bad pass
	 */
	
	public String loginPerson() {
		String answer  = "";
		String query   = "SELECT haslo FROM students WHERE login = " + "'" + getLogin() + "' LIMIT 1";
		
		String basePassToThisLogin = getBase().sendQuery(query);
		
		
		if(getPassword().equals(basePassToThisLogin)) {
			
			query = "SELECT `studentId`, `permissionId`, `index`, `numberBooks` FROM `students` " +  "WHERE login = " + "'" + getLogin() + "'";
			ArrayList<String> data = getBase().getRsQuery(query, 4);
			
			if(data.size() > 0) {
				setPersonId(Integer.parseInt(data.get(0)));
				setPermissionId(data.get(1));
				setIndex(data.get(2));
				setNumberBooks(Integer.parseInt(data.get(3)));
				setIsLogged(true);
				answer = "Access";
				
				/*System.out.println(getPersonId());
				System.out.println(getPermissionId());
				System.out.println(getIndex());
				System.out.println(getNumberBooks());*/
			}
			else answer = "NotExist";
			
		
		}
		else answer = answer + "Bad pass";

		return answer;
	}
	
	
	
	/**
	 * Metoda zapisuje dane nowej osoby do tabeli:
	 * @return-"Data are updated" jesli dane zostaly poprawnie wstawione do tabeli
	 * @return- Error from base
	 */
	public String saveDataPerson() {

			String query = "INSERT INTO `" + getNameDataTable() + "` (`login`, `haslo`, `permissionId`, `index`) " + 
							"VALUES "+ "('" + getLogin() + "', '" + getPassword() + "', '" + getPermissionId() + "', '" + getIndex() + "'); ";
			
			String queryToUpdateId = "SELECT studentId FROM " + getNameDataTable() + 
					" WHERE login = '" + getLogin() + "' AND haslo = '" + getPassword()  + 
					"' ORDER BY studentId DESC LIMIT 1" + ";";

			

			
			String answer = getBase().sendInsert(query);
			String id = getBase().sendQuery(queryToUpdateId);
			setPersonId(Integer.parseInt(id));
			
			//System.out.println(getPersonId());
			

		return answer;
	}
	
	
	/**
	 * Metoda aktualizuje wszystkie dane o studencie w bazie oprocz kolumn index oraz studentId
	 * @return
	 */
	public String wholeUpdatePerson() {
		
		String query = "UPDATE " + getNameDataTable() + 
				" SET login = '" + getLogin() + "', haslo = '" + getPassword() + "', permissionId = '" + getPermissionId() + "', numberBooks = " + getNumberBooks()
				+ " WHERE studentId = " + getPersonId() + ";";
		
		//System.out.println(query);
		
		String answer ="";
		answer = answer + getBase().sendUpdate(query);
		
		
		
		return answer;
		
		
	}
	

	public int getNumberBooks() {
		return numberBooks;
	}



	public void setNumberBooks(int numberBooks) {
		this.numberBooks = numberBooks;
	}


	

}
