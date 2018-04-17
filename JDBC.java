
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * Class to conect with data base - MySql
 * @author MakarenaCosablena
 *
 */
public class JDBC {
 
        static String daneZBazy;
    	String polaczenieURL = "";
    	String answer = "";
    	
    	
    	public JDBC () {
    		polaczenieURL = "jdbc:mysql://127.0.0.1/libray?user=root";
    	}
    	
    	public JDBC (String dataBase, String user, String password) {
    		
    		
    		polaczenieURL = "jdbc:mysql://127.0.0.1/" + dataBase + "?user=" + user + "&password=" + password;
    	}
    	
    	public JDBC (String dataBase, String user) {
    		

    		polaczenieURL = "jdbc:mysql://127.0.0.1/" + dataBase + "?user=" + user;
    		
    	}
        
        
/*
        public static void main(String[] args) {
        	sql();
        }*/
        
        /**
         * 
         * @param query
         * @return SELECT- only one tuple
         */
        public String sendQuery(String query) {
        	answer = "";
        	
           
            Connection conn = null;
           
            try {

                    //Ustawiamy dane dotycz¹ce pod³¹czenia
                    conn = DriverManager.getConnection(polaczenieURL);
                   
                    //Ustawiamy sterownik MySQL
                    Class.forName("com.mysql.jdbc.Driver");
                   
                    
                   // PreparedStatement ps = conn.prepareStatement(query);
                   // ps.executeUpdate();
                    
                    
                    //Uruchamiamy zapytanie do bazy danych
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
   
                    //while (rs.next()) {
                   //         wyswietlDaneZBazy(rs);
                   // }
                    
                    try{
                    	while(rs.next() ) {
                    		answer = answer + rs.getString(1);
                    	}
                        }catch(SQLException e) {
                                e.printStackTrace();
                        }
   
                    conn.close();
                    
                	///answer += "Data are updated";
            }
            //Wyrzuæ wyj¹tki jê¿eli nast¹pi¹ b³êdy z pod³¹czeniem do bazy danych lub blêdy zapytania o dane
            catch(ClassNotFoundException wyjatek) {
                    //System.out.println("Problem ze sterownikiem");
                    answer += "Problem ze sterownikiem ";
                	
            }

            catch(SQLException wyjatek) {
                //e.printStackTrace();
                //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwê u¿ytkownika, has³o, nazwê bazy danych lub adres IP serwera");
                //System.out.println("SQLException: " + wyjatek.getMessage());
                //System.out.println("SQLState: " + wyjatek.getSQLState());
                //System.out.println("VendorError: " + wyjatek.getErrorCode());
                
                answer += "SQLException: " + wyjatek.getMessage() + ", SQLState: " + wyjatek.getSQLState() + ", VendorError: " + wyjatek.getErrorCode();
            }

            
        	return answer;
        }
        
        
        
        /**
         * 
         * @param query
         * @return Data are updated OR Problem ze sterownikiem...
         */
        public String sendUpdate(String query) {
        	answer = "";
        	
           
            Connection conn = null;
           
            try {

                    //Ustawiamy dane dotycz¹ce pod³¹czenia
                    conn = DriverManager.getConnection(polaczenieURL);
                   
                    //Ustawiamy sterownik MySQL
                    Class.forName("com.mysql.jdbc.Driver");
                   
                    
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    
                    /*
                    //Uruchamiamy zapytanie do bazy danych
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
   
                    while (rs.next()) {
                            wyswietlDaneZBazy(rs);
                    }*/
   
                    conn.close();
                    
                	answer = "Data are updated";
            }
            //Wyrzuæ wyj¹tki jê¿eli nast¹pi¹ b³êdy z pod³¹czeniem do bazy danych lub blêdy zapytania o dane
            catch(ClassNotFoundException wyjatek) {
                    System.out.println("Problem ze sterownikiem");
                    answer += "Problem ze sterownikiem ";
                	
            }

            catch(SQLException wyjatek) {
                //e.printStackTrace();
                //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwê u¿ytkownika, has³o, nazwê bazy danych lub adres IP serwera");
                //System.out.println("SQLException: " + wyjatek.getMessage());
                //System.out.println("SQLState: " + wyjatek.getSQLState());
                //System.out.println("VendorError: " + wyjatek.getErrorCode());
                
                answer += "SQLException: " + wyjatek.getMessage() + ", SQLState: " + wyjatek.getSQLState() + ", VendorError: " + wyjatek.getErrorCode();
            }

            
        	return answer;
    }
        
        
        
        public static void sql() {
               
                String polaczenieURL = "jdbc:mysql://127.0.0.1/libray?user=root";
                //Tworzymy proste zapytanie doa bazy danych
                String query = "INSERT INTO `students` (`login`, `haslo`, `permissionId`, `index`) VALUES ('cmok', 'cmokaczek', '1', '1'); ";
               
                Connection conn = null;
               
                try {
 
                        //Ustawiamy dane dotycz¹ce pod³¹czenia
                        conn = DriverManager.getConnection(polaczenieURL);
                       
                        //Ustawiamy sterownik MySQL
                        Class.forName("com.mysql.jdbc.Driver");
                       
                        
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.executeUpdate();
                        
                        /*
                        //Uruchamiamy zapytanie do bazy danych
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
       
                        while (rs.next()) {
                                wyswietlDaneZBazy(rs);
                        }*/
       
                        conn.close();
                }
                //Wyrzuæ wyj¹tki jê¿eli nast¹pi¹ b³êdy z pod³¹czeniem do bazy danych lub blêdy zapytania o dane
                catch(ClassNotFoundException wyjatek) {
                        System.out.println("Problem ze sterownikiem");
                }
 
                catch(SQLException wyjatek) {
                        //e.printStackTrace();
                        //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwê u¿ytkownika, has³o, nazwê bazy danych lub adres IP serwera");
                        System.out.println("SQLException: " + wyjatek.getMessage());
                    System.out.println("SQLState: " + wyjatek.getSQLState());
                    System.out.println("VendorError: " + wyjatek.getErrorCode());
                }
 
        }
      
               
}