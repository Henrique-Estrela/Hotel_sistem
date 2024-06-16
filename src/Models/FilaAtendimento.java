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
public class FilaAtendimento {
    private Integer idFila;
    private Cliente cliente;

    public FilaAtendimento(Integer idFila, Cliente cliente) {
        this.idFila = idFila;
        this.cliente = cliente;
    }    

    public FilaAtendimento(ResultSet rs) {
        try {
            this.idFila = rs.getInt("id_fila");
            this.cliente = new Cliente(rs);
        } catch (SQLException ex) {
            Logger.getLogger(FilaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getIdFila() {
        return idFila;
    }

    public void setIdFila(Integer idFila) {
        this.idFila = idFila;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
