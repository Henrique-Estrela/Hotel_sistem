package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormaPagamento {
    private int id;
    private String nome;

    // Construtores
    
    public FormaPagamento(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    public FormaPagamento(ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
            this.nome = rs.getString("NOME");
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    // Getters

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    // Setters

    public void setNome(String novoNome){
        this.nome = novoNome;
    }
}
