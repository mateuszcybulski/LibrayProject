import java.util.ArrayList;

public class Borrow {
	 //borrows
	 private int borrowId;
	 private int studentId;
	 private int objectId;
	 private String date;
	 private Student student = null;
	 private JDBC base = new JDBC();
	 
	 
	 
		public String readBorrow(int idBorrow) {
			ArrayList<String> dataFromBase = null;
			
			String query = "SELECT studentId, objectId, date FROM borrows WHERE borrowId = " + Integer.toString(idBorrow);
			

			dataFromBase = getBase().getArrayList(query, 3);
			

			if(dataFromBase.size() > 0) {
				setBorrowId(idBorrow);
				setStudentId(Integer.parseInt(dataFromBase.get(0)));
				setObjectId(Integer.parseInt(dataFromBase.get(1)));
				setDate(dataFromBase.get(2));
				
				student = new Student (getStudentId());
				
			}
			if(dataFromBase.size() < 1) {
				return null;
			}
			else return "data was readed";
		}

		public String getBorrow() {
			String answer = getBorrowId() + " " + getStudentId() + " " + getObjectId() + " " + getDate();
			return answer;
		}

		public int getBorrowId() {
			return borrowId;
		}



		public void setBorrowId(int borrowId) {
			this.borrowId = borrowId;
		}



		public int getStudentId() {
			return studentId;
		}



		public void setStudentId(int studentId) {
			this.studentId = studentId;
		}



		public int getObjectId() {
			return objectId;
		}



		public void setObjectId(int objectId) {
			this.objectId = objectId;
		}



		public String getDate() {
			return date;
		}



		public void setDate(String date) {
			this.date = date;
		}



		public Student getStudent() {
			return student;
		}



		public void setStudent(Student student) {
			this.student = student;
		}



		public JDBC getBase() {
			return base;
		}



		public void setBase(JDBC base) {
			this.base = base;
		}


		

}
