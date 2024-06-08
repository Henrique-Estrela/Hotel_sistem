package Models;

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

    public void setNum(int novoNum){
        this.num = novoNum;
    }

    public void setStatusReservado(boolean novoStatus){
        this.reservado = novoStatus;
    }

    public void setTamanho(char novoTamanho){
        this.tamanho = novoTamanho;
    }

    // Métodos Adicionais

    public boolean estaReservado(){
        return this.reservado;
    }
    
}
