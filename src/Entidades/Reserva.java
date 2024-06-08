package Entidades;

import java.time.LocalDateTime;

public class Reserva {
    private int num;
    private Cliente cliente;
    private Atendente atendente;
    private Quarto quarto;
    private FormaPagamento pagamento;
    private LocalDateTime data_checkin;
    private LocalDateTime data_checkout;
    private int num_hospedes;
    private float valor_pagamento;
    private boolean pago;

    // Construtores

    // Getters

    public int getNum(){
        return this.num;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public Atendente getAtendente(){
        return this.atendente;
    }

    public Quarto getQuarto(){
        return this.quarto;
    }

    public FormaPagamento getPagamento(){
        return this.pagamento;
    }

    public LocalDateTime getDataCheckin(){
        return this.data_checkin;
    }

    public LocalDateTime getDataCheckout(){
        return this.data_checkout;
    }

    public int getNumHospedes(){
        return this.num_hospedes;
    }

    public float getValorPagamento(){
        return this.valor_pagamento;
    }

    // Setters

    public void setNumHospedes(int novo_num){
        this.num_hospedes = novo_num;
    }

    public void setValorPagamento(float novo_valor){
        this.valor_pagamento = novo_valor;
    }

    public void setStatusPago(boolean novo_status){
        this.pago = novo_status;
    }

    // Métodos Adicionais

    public boolean esta_pago(){
        return this.pago;
    }

}
