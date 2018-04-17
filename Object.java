
public class Object {

	private String nameDataTable = "object";
	private int yearOfIssue;
	private String author;
	private String title;
	private JDBC base = new JDBC();
	private Type type;
	private boolean ability;
	



	public Object (String author, String title, int yearOfIssue, Type type) {
		setAbility(true);
		this.author = author;
		this.title = title;
		this.yearOfIssue = yearOfIssue;
		this.type = type;
		
		
	}
	
	
	
	
	public String saveObject() {
		String query = "INSERT INTO `" + nameDataTable + "` (`type`, `author`, `tytul`, `yearOfIssue`, `ability`) " + 
											"VALUES "+ "('" + getType() + "', '" + getAuthor() + "', '" + getTitle() + "', '" + getYearOfIssue() + "', '" + getAbility() + "'); ";


		String answer = getBase().sendUpdate(query);
		
		
		
		
		
		return answer;
	}






	public String getNameDataTable() {
		return nameDataTable;
	}




	public void setNameDataTable(String nameDataTable) {
		this.nameDataTable = nameDataTable;
	}




	public Type getType() {
		return type;
	}
	






	public void setType(Type type) {
		this.type = type;
	}






	public String getAuthor() {
		return author;
	}






	public void setAuthor(String author) {
		this.author = author;
	}






	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYearOfIssue() {
		return yearOfIssue;
	}

	public void setYearOfIssue(int yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}

	public int getAbility() {
		if(ability == false) {
			return 0;
		}else return 1;
	}


	public void setAbility(boolean ability) {
		this.ability = ability;
	}

	public JDBC getBase() {
		return base;
	}

	public void setBase(JDBC base) {
		this.base = base;
	}


}
enum Type{
	
	Book, 
	Film,
	Indefinite;
	
	
	
}