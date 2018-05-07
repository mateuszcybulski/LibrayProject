
public class Tests {
	
	public static void main(String[] args) {
		//startAllTests();
		//System.out.println(testInsertSQL());
		//System.out.println(testQuerySQL());
		//System.out.println(testLoginStudent());
		//System.out.println(testSaveDataStudent());
		//System.out.println(testWholeUpdateStudent());
		
		//System.out.println(testSaveObject());
		//System.out.println(testReadObject());
		//System.out.println(testUpdateObject());
		
		System.out.println(testGiveBackAndBorrowObject());
    }
	
	public static String startAllTests() {
		System.out.println(testInsertSQL());
		System.out.println(testQuerySQL());
		
		
		System.out.println(testLoginStudent());
		System.out.println(testSaveDataStudent());
		
		
		System.out.println(testSaveObject());
		System.out.println(testReadObject());
		System.out.println(testUpdateObject());
		
		return null;
	}

	
	
	public static String testGiveBackAndBorrowObject() {
		LibraySystem lsystem = new LibraySystem();
		String giveBack = lsystem.giveBackObject(1, 1);
		
		if("book is give back".equals(giveBack)) {
			String boorowBack = lsystem.borrowObject(1, 1);
			if("book is borrow".equals(boorowBack)) {
				return "zakonczony powodzeniem testGiveBackAndBorrowObject";
			}
			else return "null testGiveBackAndBorrowObject: " + boorowBack ;
			
		}
		/*else if("book is inside".equals(giveBack)) {
			return "null testGiveBackAndBorrowObject: " + giveBack ;
		}*/
		else return "null testGiveBackAndBorrowObject " + giveBack ;
	}
	
	
	public static String testUpdateObject() {
		Object object = new Object("aaaa", "aaa", 0000, Type.Indefinite);
		object.setAbility(false);
		object.saveObject();
		
		object.setAuthor("asdd");
		
		if(object.updateObject().equals("Data are updated")) {

			if("data was readed".equals(object.readObject(object.getObjectId()))) {

				String authorAfterUpdate = object.getAuthor();
				
				
				if("asdd".equals(authorAfterUpdate)) {
					return "zakonczony powodzeniem testUpdateObject";
				}
				return "null testUpdateObject";
			}
			else return "null testUpdateObject";
		}

		
		return "null testUpdateObject";
		
	}

	
	public static String testReadObject() {
		Object object = new Object();
		
		String result = object.readObject(5);
		
		if("data was readed".equals(result)) {
			return "zakonczony powodzeniem testReadObject";
		}
		
		return "null testReadObject, Error: " + result;
	}
	
	public static String testInsertSQL(){
		JDBC baza = new JDBC("libray", "root");
		
		if("Data are updated" == baza.sendInsert("INSERT INTO `students` (`login`, `haslo`, `permissionId`, `index`) VALUES ('example', 'password', '12', '1000000'); ")) {
				return "zakonczony powodzeniem testDataSQL";
			
		}
		
		
		return "null testDataSQL";
	}

	public static String testQuerySQL(){
		JDBC baza = new JDBC();
		
		if("daniel".equals(baza.sendQuery("SELECT login FROM students WHERE studentId = 3"))) {
			return "zakonczony powodzeniem testQuerySQL";
		}
		
		
		
		
		return "null testQuerySQL";
	}
	
	public static String testLoginStudent() {
		Student student = new Student("daniel", "macielecki");
		String result = student.loginPerson();
		
		if("Access".equals(result)) {
			if("daniel".equals(student.getLogin())){
				if("macielecki".equals(student.getPassword())){
					if(3 == student.getPersonId()){
						if("2".equals(student.getPermissionId())){
							if("999999".equals(student.getIndex())){
								
								return "zakonczony powodzeniem testLoginStudent";
								
							}
							else result = result + " " + student.getIndex();
						}
						else result = result + " " + student.getPermissionId();
					}
					else result = result + " " + student.getPersonId();
				}
				else result = result + " " + student.getPassword();
			}
			else result = result + " " + student.getLogin();
		}
		
		
		
		return "null testLoginStudent, Error: " + result;
	}
	
	public static String testSaveDataStudent() {
		Student student = new Student("example", "student");
		student.setPermissionId("9999");
		student.setIndex("111111");
		String answer = student.saveDataPerson();
		
		if(answer.equals("Data are updated")) {
			return "zakonczony powodzeniem testSaveDataStudent";
		}
		
		
		
		return "null testSaveDataStudent, Error: " + answer;
	}
	
	public static String testWholeUpdateStudent() {
		String answer = "";
		Student studentToUpdate = new Student ("LstudentToUpdate", "HstudentToUpdate");
		studentToUpdate.setPermissionId("1");
		studentToUpdate.setIndex("99999");
		studentToUpdate.setNumberBooks(0);
		
		String saveStudent = studentToUpdate.saveDataPerson();
		
		if("Data are updated".equals(saveStudent)) {
			studentToUpdate.setNumberBooks(1);
			studentToUpdate.wholeUpdatePerson();
			
			Student theSameStudent = new Student("LstudentToUpdate", "HstudentToUpdate");
			String resultLoging = theSameStudent.loginPerson();
	
			if(theSameStudent.getNumberBooks() == studentToUpdate.getNumberBooks()) {
				return "zakonczony powodzeniem testWholeUpdateStudent";
			}
			else answer = answer + " " + resultLoging;
			
		}
		else answer = answer + saveStudent;
		
		return "null testWholeUpdateStudent, Error: " + answer;
	}

	public static String testSaveObject() {

		Object object = new Object("Adam Mickiewicz", "Pan Tadeusz", 1990, Type.Book);
		object.setAbility(false);
		String answer = object.saveObject();
		
		if(answer.equals("Data are updated")) {
			return "zakonczony powodzeniem testSaveObject";
		}
		
		
		
		return "null testSaveObject, Error: " + answer;
		
		
	}

}
