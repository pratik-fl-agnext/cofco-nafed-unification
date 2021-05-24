package com.agnext.unification.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DbConnection {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(DbConnection.class);
    
    private static String url = "jdbc:mysql://mysql-server-in.mysql.database.azure.com:3306/nafed_pilot?serverTimezone=UTC&autoReconnect=true";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "agnextadmin@mysql-server-in";
    private static String password = "Agnext@2019#";
    private static Connection con;
    
//    private static String url;
//    private static String driverName;
//    private static String username;
//    private static String password;
//    
//    
//    @Value("${spring.datasource.url}")
//    public void setUrl(String urlProp) {
//        DbConnection.url = urlProp;
//    }
//
//    @Value("${spring.datasource.driverClassName}")
//    public void setDriverName(String driverNameProp) {
//        DbConnection.driverName = driverNameProp;
//    }
//
//    @Value("${spring.datasource.username}")
//    public void setUsername(String usernameProp) {
//        DbConnection.username = usernameProp;
//    }
//
//    @Value("${spring.datasource.password}")
//    public void setPassword(String passwordProp) {
//        DbConnection.password = passwordProp;
//    }

    private DbConnection() {
    }

    public static Connection getConnection() {
	logger.info("connecting database: " + url);
	try {
	    Class.forName(driverName);
	    makeConnection();
	} catch (ClassNotFoundException ex) {
	    logger.error("Driver not found." + ex);
	}
	return con;
    }

    private static void makeConnection() {
	try {
	    con = DriverManager.getConnection(url, username, password);
	    logger.info("connection success");
	} catch (SQLException ex) {
	    logger.error("Failed to create the database connection: " + ex);
	}
    }
}
