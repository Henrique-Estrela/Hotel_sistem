package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.AtendenteController;
import Controller.ClienteController;
import Controller.QuartosController;
import Controller.PagamentoController;
import java.sql.Connection;

public class Pagamento{
    private String cliente;
    private String atendente;
    private int quarto;
    private String formaPagamento;
    private Double valor;
    private boolean finalizado;

    // Construtores

    public Pagamento(String cliente, String atendente, int quarto, String formaPagamento, Double valor, boolean finalizado){
        this.cliente = cliente;
        this.atendente = atendente;
        this.quarto = quarto;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.finalizado = finalizado;
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
            this.cliente = clienteKit.acessarCliente(idCliente).getNome();
            this.atendente = atendKit.acessarAtendente(idAtendente).getNome();
            this.quarto = quartoKit.acessarQuarto(idQuarto).getNum();
            this.formaPagamento = pagKit.acessarForma(idFormaPagamento).getNome();
            this.valor = retornoSql.getDouble("VALOR_PAGAMENTO");
            this.finalizado = retornoSql.getBoolean("PAGO");
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
            this.cliente = clienteKit.acessarCliente(idCliente, dbConectado).getNome();
            this.atendente = atendKit.acessarAtendente(idAtendente, dbConectado).getNome();
            this.quarto = quartoKit.acessarQuarto(idQuarto, dbConectado).getNum();
            this.formaPagamento = pagKit.acessarForma(idFormaPagamento, dbConectado).getNome();
            this.valor = retornoSql.getDouble("VALOR_PAGAMENTO");
            this.finalizado = retornoSql.getBoolean("PAGO");
        } catch (SQLException ex) {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Getters
    
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
        return this.finalizado;
    }
    
}