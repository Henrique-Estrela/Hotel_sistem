package Utils;

import Models.Cliente;

public class Fila {
    private No elemento1;
    private int tamanho;

    // Construtor
    public Fila() {
        this.elemento1 = null;
        this.tamanho = 0;
    }

    // Getters
    public No getElemento1() {
        return this.elemento1;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    // Setters
    public void setElemento1(No elemento1) {
        this.elemento1 = elemento1;
    }

    // Métodos
    public boolean estaVazia() {
        return (this.elemento1 == null);
    }

    public Cliente cabeca() {
        if (this.estaVazia()) {
            return null;
        } else {
            return this.elemento1.getValor();
        }
    }

    public String enfileirar(Cliente novoCliente) {
        No novoNo = new No(novoCliente);
        if (this.estaVazia()) {
            this.elemento1 = novoNo;
        } else {
            No ultimo = this.elemento1;
            while (ultimo.getProximo() != null) {
                ultimo = ultimo.getProximo();
            }
            ultimo.setProximo(novoNo);
        }
        tamanho++;
        return "Cliente enfileirado com sucesso!";
    }

    public String desenfileirar() {
        if (this.estaVazia()) {
            return "A fila está vazia, não há cliente para desenfileirar.";
        } else { 
            Cliente clienteDesenfileirado = this.elemento1.getValor();
            this.elemento1 = this.elemento1.getProximo();
            tamanho--;
            return "Cliente desenfileirado: " + clienteDesenfileirado.getNome();
        }
    }

    public String listar() {
        if (this.estaVazia()) {
            return "A fila está vazia.";
        } else {
            StringBuilder str = new StringBuilder();
            for (No ref = this.elemento1; ref != null; ref = ref.getProximo()) {
                Cliente cliente = ref.getValor();
                str.append("Cliente: ").append(cliente.getNome()).append(", ID: ").append(cliente.getId()).append("\n");
            }
            return str.toString();
        }
    }
}
