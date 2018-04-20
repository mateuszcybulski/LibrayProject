import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Object {

	private String nameDataTable = "object";
	private int yearOfIssue;
	private String author;
	private String title;
	private JDBC base = new JDBC();
	private Type type;
	private boolean ability;
	
	
	public Object() {
		
	}
	


	public Object (String author, String title, int yearOfIssue, Type type) {
		this.author = author;
		this.title = title;
		this.yearOfIssue = yearOfIssue;
		this.type = type;
		setAbility(true);
		
	}
	
	
	
	
	public String saveObject() {
		String query = "INSERT INTO `" + nameDataTable + "` (`type`, `author`, `tytul`, `yearOfIssue`, `ability`) " + 
											"VALUES "+ "('" + getType() + "', '" + getAuthor() + "', '" + getTitle() + "', '" + getYearOfIssue() + "', '" + getAbility() + "'); ";

		String answer = getBase().sendUpdate(query);
		
		return answer;
	}

	public String readObject(int idObject) {
		ArrayList<String> dataFromBase = null;
		
		String query = "SELECT type, author, tytul, yearOfIssue, ability FROM " + getNameDataTable() + " WHERE objectId = " + Integer.toString(idObject);
		

		dataFromBase = getBase().getRsQuery(query, 5);
		

		if(dataFromBase.size() > 0) {
		setType(dataFromBase.get(0));
		setAuthor(dataFromBase.get(1));
		setTitle(dataFromBase.get(2));
		setYearOfIssue(Integer.parseInt(dataFromBase.get(3)));
		setAbility(Integer.parseInt(dataFromBase.get(4)));
		}
		if(dataFromBase.size() < 1) {
			return null;
		}
		else return "data was readed";
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
	
	public void setType(String tpe) {
		
		if(tpe.equals("Book")) {
			this.type = Type.Book;
		}
		else if(tpe.equals("Film")) {
			this.type = Type.Film;
		}
		else if(tpe.equals("Indefinite")) {
			this.type = Type.Indefinite;
		}
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
	public void setAbility(int abilit) {
		if(abilit == 1) {
			this.ability = true;
		}
		else if(abilit == 0) {
			this.ability = false;
		}
		
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