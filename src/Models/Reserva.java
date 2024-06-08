package Models;

import java.time.LocalDateTime;

public class Reserva {
    private Integer num;
    private Cliente cliente;
    private Atendente atendente;
    private Quarto quarto;
    private FormaPagamento pagamento;
    private LocalDateTime dataCheckin;
    private LocalDateTime dataCheckout;
    private Integer numHospedes;
    private Double valorPagamento;
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
        return this.dataCheckin;
    }

    public LocalDateTime getDataCheckout(){
        return this.dataCheckout;
    }

    public Integer getNumHospedes(){
        return this.numHospedes;
    }

    public Double getValorPagamento(){
        return this.valorPagamento;
    }

    // Setters

    public void setNumHospedes(Integer novoNum){
        this.numHospedes = novoNum;
    }

    public void setValorPagamento(Double novoValor){
        this.valorPagamento = novoValor;
    }

    public void setStatusPago(boolean novoStatus){
        this.pago = novoStatus;
    }

    // Métodos Adicionais

    public boolean estaPago(){
        return this.pago;
    }

}
