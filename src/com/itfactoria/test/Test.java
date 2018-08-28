/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itfactoria.test;

import com.itfactoria.dto.PersonaDTO;
import com.itfactoria.jdbc.PersonaDAO;
import com.itfactoria.jdbc.PersonaDAOJDBC;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JairoNino
 */
public class Test {
    
    public static void selectRecords(){
        
        PersonaDAO.
        
    
    }
    
    public static void main(String[] args) {
        
        PersonaDAO personaDAO = new PersonaDAOJDBC();
        
        try {
            List<PersonaDTO>personasDTO = personaDAO.select();
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        selectRecords();
    }
    
}
