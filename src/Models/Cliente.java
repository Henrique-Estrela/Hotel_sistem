/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class Cliente {
    
    private Integer id;
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
            this.nome = rs.getString("NOME");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    
}
