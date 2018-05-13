import java.util.ArrayList;

public class LibraySystem {
	JDBC base = new JDBC();
	 ArrayList<Student> students = new ArrayList<Student>();
	 ArrayList<Object> objects = null;
	 ArrayList<Borrow> borrows = null;
	 

	 
	 
	public LibraySystem() {
		
		//read Objects to ArrayList objects
		refresh();
	}
	
	public void refresh() {

		readObjects();
		readBorrows();
	}
	
	public void readObjects() {
		String queryObjects = "SELECT objectId FROM object";
		 objects = new ArrayList<Object>();
		
		ArrayList<String> objectsData = new ArrayList<String>();
		objectsData = base.getArrayList(queryObjects, 1);
		objects = new ArrayList<Object>();
		
		for(int i=0 ; i < objectsData.size(); i++) {
			Object newObject = new Object();
			newObject.readObject(Integer.parseInt(objectsData.get(i)));
			
			objects.add(newObject);
		}
	}
	
	public void readBorrows() {
		String queryObjects = "SELECT borrowId FROM borrows";
		 borrows = new ArrayList<Borrow>();
		
		ArrayList<String> borrowData = new ArrayList<String>();
		borrowData = base.getArrayList(queryObjects, 1); 
		borrows = new ArrayList<Borrow>();
		
		for(int i=0 ; i < borrowData.size(); i++) {
			Borrow newObject = new Borrow();
			newObject.readBorrow(Integer.parseInt(borrowData.get(i)));
			
			borrows.add(newObject);
		}
	}
	
	/**
	 * metoda zmienia dostepnosc wybranego obiektu
	 * @param obiectId
	 * @return Succes, Dont exist the object, Error in object
	 */
	public String changeAbility(int obiectId) {
		
		for(Object obj : objects) {
			if(obj.getObjectId() == obiectId) {
				if(obj.getAbility() == 0) {
					obj.setAbility(true);
				}else if(obj.getAbility() == 1) {
					obj.setAbility(false);
				}else {
					return "Error in object";
				}
				
				obj.updateObject();
				refresh();
				return "Succes";
			}
		}
		
		return "Dont exist the object";
	}
	
	
	/**
	 * 
	 * @param studentId
	 * @param objectId
	 * @return  "book is borrow" , "book is loan"
	 */
	public String borrowObject(int studentId, int objectId) {
		String ansewr = "Error";
		String query = "SELECT ability FROM object WHERE objectId = " + objectId + ";";
		Student student = new Student(studentId);
		
		String ability = base.sendQuery(query);

		//System.out.println(query);
		//System.out.println(ability);
		
		if(ability.equals("1")) {
			String update = "UPDATE object SET ability = 0 WHERE objectId = "  + objectId + ";";
			base.sendUpdate(update);
			
			student.setNumberBooks(student.getNumberBooks() + 1);
			
			String updateStudent = "UPDATE students SET numberBooks = " + student.getNumberBooks() + " WHERE studentId = "  + studentId + ";";
			base.sendUpdate(updateStudent);
		
			
			ansewr = "book is borrow";
		}
		else
			ansewr = "book is loan";
		
		String borrow = "INSERT INTO borrows (studentId, objectId) "
					+ "VALUES (" + studentId + ", " + objectId + ")";
		
		if("Data are updated".equals(base.sendInsert(borrow)));
		else ansewr = "error witch table borrow";
		
		return ansewr;
	}
	
	/**
	 * 
	 * @param studentId
	 * @param objectId
	 * @return  book is give back , "book is inside"
	 */
	public String giveBackObject(int studentId, int objectId) {
		String query = "SELECT ability FROM object WHERE objectId = " + objectId + ";";
		Student student = new Student(studentId);
		
		String ability = base.sendQuery(query);

		//System.out.println(query);
		//System.out.println(ability);
		
		if(ability.equals("0")) {
			String update = "UPDATE object SET ability = 1 WHERE objectId = "  + objectId + ";";
			base.sendUpdate(update);
			
			student.setNumberBooks(student.getNumberBooks() -1);
			
			String updateStudent = "UPDATE students SET numberBooks = " + student.getNumberBooks() + " WHERE studentId = "  + studentId + ";";
			base.sendUpdate(updateStudent);
			
			return "book is give back";
		}
		else
			return  "book is inside";
		
	}
	
	/*public ArrayList<Object> checkObjects(boolean loanObjects){
		ArrayList<Object> answer = new ArrayList<Object>();
		
		
		return objects;
	}*/
	
	public 	ArrayList<String> getObjectsToString(){
		ArrayList<String> objectss = new ArrayList<String>();
		for(Object obj : objects) {
			objectss.add(obj.toString());
		}
		
		return objectss;
	}
	
	public 	ArrayList<String> getStudentsToString(){
		ArrayList<String> studentss = new ArrayList<String>();
		for(Student std : students) {
			studentss.add(std.toString());
		}
		
		return studentss;
	}
	
	
	

}
