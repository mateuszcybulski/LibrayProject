
public class Tests {
	
	public static void main(String[] args) {
		//System.out.println(testUpdateSQL());
		//System.out.println(testQuerySQL());
		//System.out.println(testLoginStudent());
		//System.out.println(testSaveDataStudent());
		//System.out.println(testSaveObject());
		//System.out.println(testReadObject());
		
		
    }
	
	public static String startEvryTests() {
		System.out.println(testUpdateSQL());
		System.out.println(testQuerySQL());
		
		
		System.out.println(testLoginStudent());
		System.out.println(testSaveDataStudent());
		
		
		System.out.println(testSaveObject());
		System.out.println(testReadObject());
		
		return null;
	}
	
	
	
	
	
	
	public static String testReadObject() {
		Object object = new Object();
		
		String result = object.readObject(5);
		
		if("data was readed".equals(result)) {
			return "zakonczony powodzeniem testReadObject";
		}
		
		return "null testReadObject, Error: " + result;
	}
	
	
	public static String testUpdateSQL(){
		//libray?user=root
		JDBC baza = new JDBC("libray", "root");
		
		//baza.sendQuery();
		if("Data are updated" == baza.sendUpdate("INSERT INTO `students` (`login`, `haslo`, `permissionId`, `index`) VALUES ('example', 'password', '12', '1000000'); ")) {
			//if() {
				return "zakonczony powodzeniem testDataSQL";
			//}
			
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
