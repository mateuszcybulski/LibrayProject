import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
 
/**
 * Class to conect with data base - MySql
 * @author Mateusz Cybulski
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
       
        
        /**
         * 
         * @param query
         * @return SELECT- only one tuple
         */
        public String sendQuery(String query) {
        	answer = "";
        	
           
            Connection conn = null;
           
            try {
                    conn = DriverManager.getConnection(polaczenieURL);
                   
                    Class.forName("com.mysql.jdbc.Driver");
                   
                    
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
   
                    
                    try{
                    	while(rs.next() ) {
                    		answer = answer + rs.getString(1);
                    	}
                        }catch(SQLException e) {
                                e.printStackTrace();
                        }
   
                    conn.close();

            }
            catch(ClassNotFoundException wyjatek) {

                    answer += "Problem ze sterownikiem ";
                	
            }

            catch(SQLException wyjatek) {
                //e.printStackTrace();
                //System.out.println("SQLException: " + wyjatek.getMessage());
                //System.out.println("SQLState: " + wyjatek.getSQLState());
                //System.out.println("VendorError: " + wyjatek.getErrorCode());
                
                answer += "SQLException: " + wyjatek.getMessage() + ", SQLState: " + wyjatek.getSQLState() + ", VendorError: " + wyjatek.getErrorCode();
            }

            
        	return answer;
        }
        
      
        /**
         * 
         * @param query, howMuchTuple
         * @return SELECT- data is update to ArrayList
         */
        public ArrayList<String> getRsQuery(String query, int howMuchTuple) {
        	ArrayList<String> odp = new ArrayList<String>();
        	
        	
        	answer = "";
        	ResultSet rs = null;
            
            Connection conn = null;
           
            try {

                    conn = DriverManager.getConnection(polaczenieURL);
                   
                    Class.forName("com.mysql.jdbc.Driver");
                   
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);
   
                    try{
                    	while(rs.next() ) {
                    			for(int i=1; i < howMuchTuple+1 ; i++) {
                            		odp.add(rs.getString(i));
                            		
                    			}
                    	}
                        }catch(SQLException e) {
                                e.printStackTrace();
                        }
   
                    conn.close();
            }

            catch(ClassNotFoundException wyjatek) {

                    answer += "Problem ze sterownikiem ";
                	
            }

            catch(SQLException e) {
                //e.printStackTrace();
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                
                answer += "SQLException: " + e.getMessage() + ", SQLState: " + e.getSQLState() + ", VendorError: " + e.getErrorCode();
            }

            
        	return odp;
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

                    conn = DriverManager.getConnection(polaczenieURL);
                   
                    Class.forName("com.mysql.jdbc.Driver");
                   
                    
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    
                    conn.close();
                    
                	answer = "Data are updated";
            }
            catch(ClassNotFoundException wyjatek) {
                    System.out.println("Problem ze sterownikiem");
                    answer += "Problem ze sterownikiem ";
                	
            }

            catch(SQLException e) {
                e.printStackTrace();
                //System.out.println("SQLException: " + wyjatek.getMessage());
                //System.out.println("SQLState: " + wyjatek.getSQLState());
                //System.out.println("VendorError: " + wyjatek.getErrorCode());
                
                answer += "SQLException: " + e.getMessage() + ", SQLState: " + e.getSQLState() + ", VendorError: " + e.getErrorCode();
            }

            
        	return answer;
    }
        
        
}