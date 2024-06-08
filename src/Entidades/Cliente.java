package Entidades;

import java.util.Date;

public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private Date data_nasc;
    private String telefone;

    // Construtores

    // Getters

    public int getId(){
        return this.id;
    }

    public String getCpf(){
        return this.cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public Date getDataNasc(){
        return this.data_nasc;
    }

    public String getTelefone(){
        return this.telefone;
    }

    // Setters

    public void setCpf(String novo_cpf){
        this.cpf = novo_cpf;
    }
    
    public void setNome(String novo_nome){
        this.nome = novo_nome;
    }

    public void setDataNasc(Date nova_data){
        this.data_nasc = nova_data;
    }

    public void setTelefone(String novo_telefone){
        this.telefone = novo_telefone;
    }

    // Métodos Adicionais
    
}
