/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itfactoria.jdbc;

import com.itfactoria.dto.PersonaDTO;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jnino
 */
public interface PersonaDAO {
    public int insert(PersonaDTO personaDTO) throws SQLException;
    public int update(PersonaDTO personaDTO) throws SQLException;
    public int delete(PersonaDTO personaDTO) throws SQLException;
    public List<PersonaDTO> select() throws SQLException;
    
    
}
