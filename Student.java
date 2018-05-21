import java.util.ArrayList;

public class Student extends Person{
	
	private int numberBooks = 0;
	private ArrayList<Object> myObjects = null; 
	
	

	public Student(String login, String password) {
		super();
		setLogin(login);
		setPassword(password);
		setNameDataTable("students");
		
		
	}
	
	public Student(int studentId) {
		super();
		readStudent(studentId);
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
			ArrayList<String> data = getBase().getArrayList(query, 4);
			
			if(data.size() > 0) {
				setPersonId(Integer.parseInt(data.get(0)));
				setPermissionId(data.get(1));
				setIndex(data.get(2));
				setNumberBooks(Integer.parseInt(data.get(3)));
				setIsLogged(true);
				answer = "Access";
				
			}
			else answer = "NotExist";
			
		
		}
		else answer = answer + "Bad pass";

		return answer;
	}
	
	/**
	 * Metoda wczytuje studenta o podanym studentId, zwraca kolejno:
	 * -Access jesli taki student istnieje w bazie
	 * -NotExist- jesli taki student nie istnieje w bazie
	 */
	public String readStudent(int studentId) {
		String answer  = "";
		String query  =  "SELECT  `permissionId`, `index`, `numberBooks`, `login`, `haslo` FROM `students` " +  "WHERE studentId = " + studentId ;

		ArrayList<String> data = getBase().getArrayList(query, 5);
			
		if(data.size() > 0) {
			setPersonId(studentId);
			setPermissionId(data.get(0));
			setIndex(data.get(1));
			setNumberBooks(Integer.parseInt(data.get(2)));
			setLogin(data.get(3));
			setPassword(data.get(4));
			setIsLogged(true);
			answer = "Access";
			
			
				
		}
		else answer = "NotExist";
			

		return answer;
	}
	
	/**
	 * metoda wczytuje obiekty ktore dany student ma wypozyczone
	 */
	public void storyBorrows() {
		String query = "SELECT objectId, author, tytul "
				+ "FROM borrows "
				+ "NATURAL JOIN students "
				+ "NATURAL JOIN object "
				+ "WHERE studentId = " + getPersonId() 
				+ " AND ability = 0 "
				+ "GROUP BY tytul, author";

		ArrayList<String> myBorrowObjectId = null; 
		myBorrowObjectId = getBase().getArrayList(query, 1);

		myObjects = new ArrayList<Object>();
		
		for(String s : myBorrowObjectId) {
			Object myBorrow = new Object();
			myBorrow.readObject(Integer.parseInt(s));
			myObjects.add(myBorrow);
		}
		
	}
	public ArrayList<String> getStoryBorrows() {
		ArrayList<String> myStory = null;
		
		for(Object obj :myObjects) {
			myStory.add(obj.toString());
		}
		
		
		return myStory;
	}
	
	public ArrayList<Object> getMyObjets(){
		storyBorrows();
		return myObjects;
	}
	public ArrayList<String> getMyObjetsString(){
		storyBorrows();
		String answer = "";
		
		ArrayList<String> objectss = new ArrayList<String>();
		for(Object obj : myObjects) {
			objectss.add(obj.toString());
		}
		
		return objectss;
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
	
	public String toString() {
		String answer = (getPersonId() + ", " + getLogin() + ", " + getPassword() + ", " + getPermissionId() + ", " + getIndex() +  ", " + getNumberBooks());
				
		return answer;
	}

	public int getNumberBooks() {
		return numberBooks;
	}



	public void setNumberBooks(int numberBooks) {
		this.numberBooks = numberBooks;
	}


	

}
