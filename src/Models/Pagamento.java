package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.AtendenteController;
import Controller.ClienteController;
import Controller.QuartosController;
import Controller.PagamentoController;

public class Pagamento{
    private int numReserva;
    private Timestamp dataPagamento;
    private String cliente;
    private String atendente;
    private int quarto;
    private String formaPagamento;
    private Double valor;

    // Construtores
    
    public Pagamento(int numReserva, Timestamp dataPagamento, String cliente, String atendente, int quarto, String formaPagamento, Double valor){
        this.numReserva = numReserva;
        this.dataPagamento = dataPagamento;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }
    
    public Pagamento(int numReserva, String cliente, String atendente, int quarto, String formaPagamento, Double valor){
        this.numReserva = numReserva;
        this.dataPagamento = null;
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }
    
    public Pagamento(ResultSet retornoSql) {
        // Obtido a partir da tabela reserva
        try {
            AtendenteController atendKit = new AtendenteController();
            ClienteController clienteKit = new ClienteController();
            QuartosController quartoKit = new QuartosController();
            PagamentoController pagKit = new PagamentoController();
            int idCliente = retornoSql.getInt("ID_CLIENTE");
            int idAtendente = retornoSql.getInt("ID_ATENDENTE");
            int idQuarto = retornoSql.getInt("ID_QUARTO");
            int idFormaPagamento = retornoSql.getInt("ID_PAGAMENTO");
            Timestamp dataCheckin = retornoSql.getTimestamp("DATA_CHECKIN");
            Timestamp dataCheckout = retornoSql.getTimestamp("DATA_CHECKOUT");
            boolean pago = retornoSql.getBoolean("PAGO");
            this.numReserva = retornoSql.getInt("NUM_RESERVA");
            if (pago){
                this.dataPagamento = ((dataCheckout == null) && (pago == true))? dataCheckin: dataCheckout;
            } else{
                this.dataPagamento = null;
            }
            this.cliente = clienteKit.acessarCliente(idCliente).getNome();
            this.atendente = atendKit.acessarAtendente(idAtendente).getNome();
            this.quarto = quartoKit.acessarQuarto(idQuarto).getNum();
            this.formaPagamento = pagKit.acessarForma(idFormaPagamento).getNome();
            this.valor = retornoSql.getDouble("VALOR_PAGAMENTO");
        } catch (SQLException ex) {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Pagamento(ResultSet retornoSql, Connection dbConectado) {
        // Obtido a partir da tabela reserva
        try {
            AtendenteController atendKit = new AtendenteController();
            ClienteController clienteKit = new ClienteController();
            QuartosController quartoKit = new QuartosController();
            PagamentoController pagKit = new PagamentoController();
            int idCliente = retornoSql.getInt("ID_CLIENTE");
            int idAtendente = retornoSql.getInt("ID_ATENDENTE");
            int idQuarto = retornoSql.getInt("ID_QUARTO");
            int idFormaPagamento = retornoSql.getInt("ID_PAGAMENTO");
            Timestamp dataCheckin = retornoSql.getTimestamp("DATA_CHECKIN");
            Timestamp dataCheckout = retornoSql.getTimestamp("DATA_CHECKOUT");
            boolean pago = retornoSql.getBoolean("PAGO");
            this.numReserva = retornoSql.getInt("NUM_RESERVA");
            if (pago){
                this.dataPagamento = ((dataCheckout == null) && (pago == true))? dataCheckin: dataCheckout;
            } else{
                this.dataPagamento = null;
            }
            this.cliente = clienteKit.acessarCliente(idCliente, dbConectado).getNome();
            this.atendente = atendKit.acessarAtendente(idAtendente, dbConectado).getNome();
            this.quarto = quartoKit.acessarQuarto(idQuarto, dbConectado).getNum();
            this.formaPagamento = pagKit.acessarForma(idFormaPagamento, dbConectado).getNome();
            this.valor = retornoSql.getDouble("VALOR_PAGAMENTO");
        } catch (SQLException ex) {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Getters
    
    public int getNumReserva(){
        return this.numReserva;
    }
    
    public Timestamp getDataPagamento(){
        return this.dataPagamento;
    }
    
    public String getCliente(){
        return this.cliente;
    }
    
    public String getAtendente(){
        return this.atendente;
    }
    
    public int getQuarto(){
        return this.quarto;
    }
    
    public String getFormaPagamento(){
        return this.formaPagamento;
    }
    
    public Double getValor(){
        return this.valor;
    }
    
    // Métodos adicionais
    
    public boolean estaPago(){
        return this.dataPagamento != null;
    }
    
}