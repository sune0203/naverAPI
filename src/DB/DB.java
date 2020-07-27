package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	// 연결
	public static Connection conn() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DBconfig.DB_URL, DBconfig.DB_USER, DBconfig.DB_PW);
		System.out.println("");
		return conn;
	}
}
