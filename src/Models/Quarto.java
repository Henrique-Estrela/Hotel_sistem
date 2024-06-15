package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Quarto {
    private int id;
    private int num;
    private boolean reservado;
    private char tamanho;
    
    // Construtores

    public Quarto(int num, char tamanho) {
        this.num = num;
        this.tamanho = tamanho;
    }

    public Quarto(int id, int num, char tamanho) {
        this.id = id;
        this.num = num;
        this.tamanho = tamanho;
    }

    public Quarto(int id, int num, boolean reservado, char tamanho) {
        this.id = id;
        this.num = num;
        this.reservado = reservado;
        this.tamanho = tamanho;
    }

    public Quarto(ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
            this.num = rs.getInt("NUM");
            this.reservado = rs.getBoolean("RESERVADO");
            this.tamanho = rs.getString("TAMANHO").toCharArray()[0];
        } catch (SQLException ex) {
            Logger.getLogger(Quarto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Getters

    public int getId(){
        return this.id;
    }

    public Integer getNum(){
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

    public boolean isReservado(){
        return this.reservado;
    }
}
