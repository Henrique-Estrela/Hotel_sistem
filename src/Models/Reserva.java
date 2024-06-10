package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.AtendenteController;
import Controller.ClienteController;
import Controller.QuartosController;
import Controller.PagamentoController;

public class Reserva{
    private Integer num;
    private Cliente cliente;
    private Atendente atendente;
    private Quarto quarto;
    private FormaPagamento pagamento;
    private Timestamp dataCheckin;
    private Timestamp dataCheckout;
    private Integer numHospedes;
    private Double valorPagamento;
    private boolean pago;
    
    // Construtores
    
    public Reserva (Integer num, Cliente cliente, Atendente atendente, Quarto quarto, FormaPagamento pagamento, LocalDateTime dataCheckin, LocalDateTime dataCheckout, Integer numHospedes, Double valorPagamento){
        this.num = num;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.pagamento = pagamento;
        this.dataCheckin = Timestamp.valueOf(dataCheckin);
        this.dataCheckout = Timestamp.valueOf(dataCheckout);
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
    }
    
    public Reserva (Integer num, Cliente cliente, Atendente atendente, Quarto quarto, FormaPagamento pagamento, LocalDateTime dataCheckin, LocalDateTime dataCheckout, Integer numHospedes, Double valorPagamento, boolean pago){
        this.num = num;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.pagamento = pagamento;
        this.dataCheckin = Timestamp.valueOf(dataCheckin);
        this.dataCheckout = Timestamp.valueOf(dataCheckout);
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
        this.pago = false;
    }
    
    public Reserva (Integer num, Cliente cliente, Atendente atendente, Quarto quarto, FormaPagamento pagamento, Timestamp dataCheckin, Timestamp dataCheckout, Integer numHospedes, Double valorPagamento){
        this.num = num;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.pagamento = pagamento;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
    }
    
    public Reserva (Integer num, Cliente cliente, Atendente atendente, Quarto quarto, FormaPagamento pagamento, Timestamp dataCheckin, Timestamp dataCheckout, Integer numHospedes, Double valorPagamento, boolean pago){
        this.num = num;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.pagamento = pagamento;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.numHospedes = numHospedes;
        this.valorPagamento = valorPagamento;
        this.pago = false;
    }
    
    public Reserva(ResultSet rs) {
        try {
            ClienteController clienteKit = new ClienteController();
            QuartosController quartoKit = new QuartosController();
            PagamentoController pagamentoKit = new PagamentoController();
            AtendenteController atendenteKit = new AtendenteController();
            this.num = rs.getInt("NUM_RESERVA");
            this.cliente = clienteKit.acessarCliente(rs.getInt("ID_CLIENTE"));
            this.atendente = atendenteKit.acessarAtendente(rs.getInt("ID_ATENDENTE"));
            this.quarto = quartoKit.acessarQuarto(rs.getInt("ID_QUARTO"));
            this.pagamento = pagamentoKit.acessarForma(rs.getInt("ID_PAGAMENTO"));
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