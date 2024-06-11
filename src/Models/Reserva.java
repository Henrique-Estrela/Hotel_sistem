package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reserva{
    private Integer num;
    private Integer idCliente;
    private Integer idAtendente;
    private Integer idQuarto;
    private Integer idFormaPagamento;
    private Timestamp dataCheckin;
    private Timestamp dataCheckout;
    private Integer numHospedes;
    private Double valorPagamento;
    private boolean pago;
    
    // Construtores
    
    public Reserva (Integer num, Integer idCliente, Integer idAtendente, Integer idQuarto, Integer idFormaPagamento, Timestamp dataCheckin, Timestamp dataCheckout, Integer numHospedes, Double valorPagamento, boolean pago){
        this.num = num;
        this.idCliente = idCliente;
        this.idAtendente = idAtendente;
        this.idQuarto = idQuarto;
        this.idFormaPagamento = idFormaPagamento;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
        this.pago = pago;
    }
    
    public Reserva (Integer num, Integer idCliente, Integer idAtendente, Integer idQuarto, Integer idFormaPagamento, Timestamp dataCheckin, Timestamp dataCheckout, Integer numHospedes, Double valorPagamento){
        this.num = num;
        this.idCliente = idCliente;
        this.idAtendente = idAtendente;
        this.idQuarto = idQuarto;
        this.idFormaPagamento = idFormaPagamento;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
        this.pago = false;
    }
     public Reserva (Integer num, boolean pago){
        this.num = num;
        this.pago = pago;
    }
    
    public Reserva(ResultSet rs) {
        try {
            this.num = rs.getInt("NUM_RESERVA");
            this.idCliente = rs.getInt("ID_CLIENTE");
            this.idAtendente = rs.getInt("ID_ATENDENTE");
            this.idQuarto = rs.getInt("ID_QUARTO");
            this.idFormaPagamento = rs.getInt("ID_PAGAMENTO");
            this.dataCheckin = rs.getTimestamp("data_checkin");
            this.dataCheckout = rs.getTimestamp("data_checkout");
            this.numHospedes = rs.getInt("NUM_RESERVA");
            this.valorPagamento = rs.getDouble("VALOR_PAGAMENTO");
            this.pago = rs.getBoolean("PAGO");
        } catch (SQLException ex) {
            Logger.getLogger(Quarto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Getters
    
    public int getNum(){
        return this.num;
    }

    public Integer getIdCliente(){
        return this.idCliente;
    }

    public Integer getIdAtendente(){
        return this.idAtendente;
    }

    public Integer getIdQuarto(){
        return this.idQuarto;
    }

    public Integer getIdFormaPagamento(){
        return this.idFormaPagamento;
    }

    public Timestamp getDataCheckin(){
        return this.dataCheckin;
    }

    public Timestamp getDataCheckout(){
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