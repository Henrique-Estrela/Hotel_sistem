package Models;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Atendente {
    private int id;
    private String nome;
    private String senha;
    private boolean ativo;

    // Construtores
    
    public Atendente(int id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    public Atendente(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    
    public Atendente(int id, String nome, String senha, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.ativo = true;
    }
    
    public Atendente (ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
            this.nome = rs.getString("NOME");
            this.senha = rs.getString("SENHA");
            this.ativo = rs.getBoolean("ATIVO");
        } catch (SQLException ex) {
            Logger.getLogger(Atendente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Getters

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getSenha(){
        return this.senha;
    }

    // Setters

    public void setNome(String novo_nome){
        this.nome = novo_nome;
    }

    public void setSenha(String nova_senha){
        this.senha = nova_senha;
    }

    public void setStatusAtivo(boolean novo_status){
        this.ativo = novo_status;
    }

    // Métodos Adicionais

    public boolean estaAtivo(){
        return this.ativo;
    }
}
