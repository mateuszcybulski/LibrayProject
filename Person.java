
public class Person {

	
	private String login = "";
	private String password = "";
	private String permissionId = "";
	private String nameDataTable = "";
	private String index = "";
	private JDBC base = new JDBC();
	private int personId = 0;
	private boolean isLogged = false;
	
	
	


	


	public String saveDataPerson() {
		
		
		
		return null;
	}
	
	/*public boolean readDataPerson() {
		return false;
	}*/
	
	public boolean serchObject() {
		return false;
	}
	public String updatePerson() {

		return null;
	}
	
	public boolean checkAccessObject() {
		
		
		
		return false;
	}

	public boolean giveObject() {
	
	
	
	return false;
	}

	public boolean rentObject() {
	
	
	
	return false;
	}
	

	
	public int getPersonId() {
		return personId;
	}


	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getNameDataTable() {
		return nameDataTable;
	}


	public void setNameDataTable(String nameDataTable) {
		this.nameDataTable = nameDataTable;
	}


	public String loginPerson() {
		
		
		return null;
		
	}
	

	public JDBC getBase() {
		return base;
	}


	public void setBase(JDBC base) {
		this.base = base;
	}


	public boolean getIsLogged() {
		return isLogged;
	}


	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	
}
