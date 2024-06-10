package Models;

import Utils.DateFormatterFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private Integer id;
    private String nome;
    private String telefone;
    private String cpf;
    private Date dataNasc;
    
    // Construtores

    public Cliente(Integer id, String nome, String telefone, String cpf, Date dataNasc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }
    
    public Cliente(Integer id, String nome, String telefone, Date dataNasc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = null;
        this.dataNasc = dataNasc;
    }

    public Cliente(ResultSet rs) {
        try {            
            this.id = rs.getInt("ID");
            this.nome = rs.getString("NOME");
            this.telefone = rs.getString("TELEFONE");
            this.cpf = rs.getString("CPF");
            this.dataNasc =  DateFormatterFactory.dateFormatyyyyMMdd().parse(rs.getString("DATA_NASC"));
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Getters

    public Integer getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
        
    public String getTelefone() {
        return telefone;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public Date getDataNasc() {
        return dataNasc;
    }
    
    // Setters
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

}
