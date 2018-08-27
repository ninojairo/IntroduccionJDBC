/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introduccionjdbc;

import java.sql.*;
/**
 *
 * @author JairoNino
 */
public class IntroduccionJDBC {
    private static final String JDBC_DRIVER ="com.mysql.jdbc.Driver"; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Cadena de conexion de mySQL
        String url = "jdbc:mysql://localhost:3306/sga?usesSSL=false";
        
        try{
            //Carga del driver de mySQL
            Class.forName(JDBC_DRIVER);
            
            //Creación de objeto de conexion
            Connection connection = (com.mysql.jdbc.Connection)DriverManager.getConnection(url,"root", "admin");
            
            //Ceacion de statement
            Statement statement = connection.createStatement();
            
            //Creacion query
            String query = "select idpersona, nombre, apellido from persona";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()){
                System.out.println("Id= "  +resultSet.getInt(1));
                System.out.println("Name= "  +resultSet.getString(2));
                System.out.println("Last Name= "  +resultSet.getString(3));
            
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
