/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itfactoria.jdbc;

import com.itfactoria.dto.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jnino
 */
public class PersonaDAOJDBC implements PersonaDAO {

    private Connection userConnection;
    private final String SQL_INSERT = "INSERT INTO Persona(nombre, apellido) VALUES (?,?)";
    private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE idPersona=?";
    private final String SQL_DELETE = "DELETE FROM persona WHERE idPersona=?";
    private final String SQL_SELECT = "SELECT FROM persona WHERE idPersona=?";

    public PersonaDAOJDBC() {
    }

    public PersonaDAOJDBC(Connection connection) {
    }

    @Override
    public int insert(PersonaDTO personaDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            connection = (this.userConnection != null) ? this.userConnection : ConexionDB.getConnection();
            stmt = connection.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, personaDTO.getNombre());
            stmt.setString(index, personaDTO.getApellido());
            System.out.println("Ejecutando query" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            ConexionDB.close(stmt);
            if (this.userConnection == null) {
                ConexionDB.close(connection);
            }
        }

        return rows;
    }

    @Override
    public int update(PersonaDTO personaDTO) {
        int rows = 0;
        int index = 1;
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConexionDB.getConnection();
            System.out.println("Ejecutanto query:" + SQL_UPDATE);
            stmt = connection.prepareStatement(SQL_UPDATE);
            stmt.setString(index++, personaDTO.getNombre());
            stmt.setString(index++, personaDTO.getApellido());
            stmt.setInt(index, personaDTO.getIdPersona());
            rows = stmt.executeUpdate();
            System.out.println("Update ejecutado:" + SQL_UPDATE);

        } catch (SQLException sqle) {
            sqle.printStackTrace();

        } finally {
            ConexionDB.close(connection);
            ConexionDB.close(stmt);

        }

        return rows;
    }

    @Override
    public int delete(PersonaDTO personaDTO) {
        int index = 1;
        Connection connection = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            connection = ConexionDB.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = connection.prepareStatement(SQL_DELETE);
            stmt.setInt(index, personaDTO.getIdPersona());
            rows = stmt.executeUpdate(SQL_DELETE);
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            ConexionDB.close(connection);
            ConexionDB.close(stmt);
        }
        return rows;
    }

    @Override
    public List<PersonaDTO> select()  {
       Connection connection = null;
       PreparedStatement stmt = null;
        ResultSet rs = null;
       List<PersonaDTO> personasDTO = new ArrayList<PersonaDTO>();
       PersonaDTO personaDTO = null;
        try {
            connection = ConexionDB.getConnection();
            stmt = connection.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPersonaTemp = rs.getInt(1);
                String nombreTemp = rs.getString(2);
                String apellidotemp = rs.getString(3);
                personaDTO.setIdPersona(idPersonaTemp);
                personaDTO.setNombre(nombreTemp);
                personaDTO.setApellido(apellidotemp);
                personasDTO.add(personaDTO);
            
            }
            
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally{
            ConexionDB.close(rs);
            ConexionDB.close(stmt);
            ConexionDB.close(connection);
        
        }
       return personasDTO;
       
    }

}
