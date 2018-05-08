import java.util.ArrayList;

public class LibraySystem {
	JDBC base = new JDBC();
	 ArrayList<Student> students = new ArrayList<Student>();
	 ArrayList<Object> objects = new ArrayList<Object>();
	 
	public LibraySystem() {
		
		//read Objects to ArrayList objects
		String queryObjects = "SELECT objectId FROM object";
		
		ArrayList<String> objectsData = new ArrayList<String>();
		objectsData = base.getArrayList(queryObjects, 1);
		
		for(int i=0 ; i < objectsData.size(); i++) {
			Object newObject = new Object();
			newObject.readObject(Integer.parseInt(objectsData.get(i)));
			
			objects.add(newObject);
		}
		
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
		
		String ability = base.sendQuery(query);

		//System.out.println(query);
		//System.out.println(ability);
		
		if(ability.equals("1")) {
			String update = "UPDATE object SET ability = 0 WHERE objectId = "  + objectId + ";";
			base.sendUpdate(update);
			
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
		
		String ability = base.sendQuery(query);

		//System.out.println(query);
		//System.out.println(ability);
		
		if(ability.equals("0")) {
			String update = "UPDATE object SET ability = 1 WHERE objectId = "  + objectId + ";";
			base.sendUpdate(update);
			
			return "book is give back";
		}
		else
			return  "book is inside";
		
	}
	
	
	
	
	
	
	

}
