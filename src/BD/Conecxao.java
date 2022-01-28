package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conecxao {

	private static Connection conn;

	public static Connection getConnection() {

		try {
			if (conn == null) {
				Properties pro = LoadParameter();
				String url = pro.getProperty("dburl");
				conn = DriverManager.getConnection(url, pro);
			}

		} catch (SQLException e) {
			throw new DBexp(e.getMessage());
		}
		return conn;

	}

	public static Connection CloseConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DBexp(e.getMessage());
		}
		return conn;
	}

	private static Properties LoadParameter() {

		try (FileInputStream fis = new FileInputStream("DB.conect")) {

			Properties prop = new Properties();
			prop.load(fis);
			return prop;

		} catch (IOException e) {
			throw new DBexp(e.getMessage());

		}

	}

}
