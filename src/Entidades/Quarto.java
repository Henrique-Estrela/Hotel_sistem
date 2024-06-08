package Entidades;

public class Quarto {
    private int id;
    private int num;
    private boolean reservado;
    private char tamanho;

    // Construtores

    // Getters

    public int getId(){
        return this.id;
    }

    public int getNum(){
        return this.num;
    }

    public char getTamanho(){
        return this.tamanho;
    }

    // Setters

    public void setNum(int novo_num){
        this.num = novo_num;
    }

    public void setStatusReservado(boolean novo_status){
        this.reservado = novo_status;
    }

    public void setTamanho(char novo_tamanho){
        this.tamanho = novo_tamanho;
    }

    // Métodos Adicionais

    public boolean esta_reservado(){
        return this.reservado;
    }
    
}
