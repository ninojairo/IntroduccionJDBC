/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itfactoria.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JairoNino
 */
public class ConexionDB {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost/sga?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin";
    private static Driver driver = null;

    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);

            } catch (Exception e) {
                System.out.println("Fallo al cargar driver JDBC");
                e.printStackTrace();
            }

        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
