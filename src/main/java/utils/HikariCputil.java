package utils;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCputil {
	
	private static HikariDataSource hikariDataSource;
	
	public static DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=movie1;encrypt=false");
		config.setUsername("ryan");
		config.setPassword("9426");
		hikariDataSource = new HikariDataSource(config);
			
		return hikariDataSource;
	}
	
	public static void closeDataSource() {
		if (hikariDataSource != null && !hikariDataSource.isClosed()) {
			hikariDataSource.close();
		}
	}
}
