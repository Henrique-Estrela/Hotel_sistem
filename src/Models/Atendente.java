package Models;

public class Atendente {
    private int id;
    private String nome;
    private String senha;
    private boolean ativo;

    // Construtores

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
