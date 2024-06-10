package Utils;

import Models.Cliente;

public class No {
     
    private Cliente valorInterno;
    private No proximaRef;
    
    // Construtores
    public No(Cliente valorInterno) {
        this.valorInterno = valorInterno;
        this.proximaRef = null;
    }
    
    public No(Cliente valorInterno, No proximaRef) {
        this.valorInterno = valorInterno;
        this.proximaRef = proximaRef;
    }

    // Getters
    public Cliente getValor() {
        return valorInterno;
    }

    public No getProximo() {
        return proximaRef;
    }

    // Setters
    public void setValor(Cliente valorInterno) {
        this.valorInterno = valorInterno;
    }

    public void setProximo(No proximaRef) {
        this.proximaRef = proximaRef;
    }
}
