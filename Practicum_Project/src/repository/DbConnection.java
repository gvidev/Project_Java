/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author videv
 *
 */
public class DbConnection {

	static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/c:\\Users\\videv\\Desktop\\Practicum_Db\\Students_Book","sa","1234");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch(SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
