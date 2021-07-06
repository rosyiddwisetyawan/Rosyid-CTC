package org.acme.getting.started.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;




public class SqlUtils {
	
	public static final String resourceName = "application.properties"; // could also be a constant
	
	public static Connection connect() throws IOException {
		
		
//    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
//    	Properties props = new Properties();
//    	String driver = null;
//		String user = null;
//		String url = null;
//		String password = null;
//    	try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
//    	    props.load(resourceStream);
//    	    driver = props.getProperty("db_driver");
//    	    user = props.getProperty("db_user");
//    	    url = props.getProperty("db_url");
//    	    password = props.getProperty("db_password");
//    	}
    	
    	String driver = "org.postgresql.Driver";
		String user = "admin";
		String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
		String password = "admin";
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection( url, user, password);			

		} catch (SQLException ex) {
			
		} catch (Exception ex){
			
		}finally {
			if(conn != null) {
				
			}else {
				
			}
				
		}

		return conn;
    }
	
	

}
