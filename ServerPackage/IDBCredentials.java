package ServerPackage;

/**
 * A class that contains the credentials of the database
 * @version 1.0
 * @since April 20, 2020
 */
public interface IDBCredentials {
	
	/**
	 * The JDBC driver name and database URL
	 */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/lib?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	/**
	 * The database credentials
	 */
	static final String USERNAME = "root";
	static final String PASSWORD = "baylee123";

}

