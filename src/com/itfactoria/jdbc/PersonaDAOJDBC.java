/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itfactoria.jdbc;

import com.itfactoria.dto.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jnino
 */
public class PersonaDAOJDBC implements PersonaDAO{
    
    private Connection userConnection;
    private final String SQL_INSERT ="INSERT INTO Persona(nombre, apellido) VALUES (?,?)";
    private final String SQL_UPDATE ="UPDATE persona SET nombre=?, apellido=? WHERE idPersona=?";
    private final String SQL_DELETE ="DELETE FROM persona WHERE idPersona=?";
    private final String SQL_SELECT ="SELECT FROM persona WHERE idPersona=?";

    public PersonaDAOJDBC() {
    }
    
    public PersonaDAOJDBC(Connection connection) {
    }
    
    
    @Override
    public int insert(PersonaDTO personaDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        int rows =0;
        try{
            connection = (this.userConnection!=null)?this.userConnection : ConexionDB.getConnection();
            stmt = connection.prepareStatement(SQL_INSERT);
            int index =1;
            stmt.setString(index++, personaDTO.getNombre());
            stmt.setString(index, personaDTO.getApellido());
            System.out.println("Ejecutando query" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: "+rows);
        }
        finally{
            ConexionDB.close(stmt);
            if (this.userConnection==null){
                ConexionDB.close(connection);
            }
        }
        
        return rows;
    }

    @Override
    public int update(PersonaDTO personaDTO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(PersonaDTO personaDTO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonaDTO> select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
