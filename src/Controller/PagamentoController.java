package Controller;

import Connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.FormaPagamento;
import Models.Reserva;
import Utils.DateFormatterFactory;

public class PagamentoController {
    
    /*
    * Função: acessar pagamento
    */
    
    public FormaPagamento acessarForma(int codigo){
        FormaPagamento pagamentoAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            pagamentoAcessado = acessarForma(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        } return pagamentoAcessado;
    }
    
    public FormaPagamento acessarForma(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM forma_de_pagamento WHERE id=" + codigo;
        FormaPagamento pagamentoAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            pagamentoAcessado = new FormaPagamento(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(FormaPagamento.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return pagamentoAcessado;
    }
    
    /*
    * Função: ver histórico de pagamentos
    */
    
    public ArrayList<String> verPagamentos() {
        String templateComandoSql = "SELECT " +
            "RESERVA.DATA_CHECKOUT as 'checkout', " +
            "CLIENTE.NOME as 'cliente', " +
            "RESERVA.VALOR_PAGAMENTO as 'valor', " +
            "FORMA_DE_PAGAMENTO.NOME as 'pagamento' " +
            "FROM RESERVA " +
            "INNER JOIN CLIENTE ON CLIENTE.ID = RESERVA.ID_CLIENTE " +
            "INNER JOIN FORMA_DE_PAGAMENTO ON FORMA_DE_PAGAMENTO.ID = RESERVA.ID_PAGAMENTO " +
            "WHERE PAGO = 1 " +
            "ORDER BY checkout DESC";
        ArrayList<String> pagamentos = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                String horario = retornoSql.getTimestamp("checkout").toLocalDateTime().format(DateFormatterFactory.dateTime());
                String cliente = retornoSql.getString("cliente");
                Double valor = retornoSql.getDouble("valor");
                String forma = retornoSql.getString("pagamento");
                String pagamento = "[ " + horario + " ] " + cliente + " pagou R$" + valor + " via " + forma + ".";
                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return pagamentos;
    }
    
    /*
    * Função: esvaziar quarto buscando-o pelo número da reserva
    */

    public void esvaziarQuarto(int numReserva){
        String templateComandoSql = "UPDATE QUARTO SET " +
                                    "RESERVADO = 0 " + 
                                    "WHERE ID = ?";
        ReservasController funcKit = new ReservasController();
        Reserva reserva = funcKit.acessarReserva(numReserva);
        int idQuarto = reserva.getIdQuarto();
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idQuarto);
            comandoSql.execute();
        } catch (SQLException ex) {
             Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }  
    }
    
    /*
    * Função: marcar reserva como paga
    */
    
    public void marcarReservaComoPaga(int numReserva, boolean quartoVago) {
        String templateComandoSql = "UPDATE RESERVA SET "+
                                    "DATA_CHECKOUT = CURRENT_TIMESTAMP, "+
                                    "PAGO = 1 " +
                                    "WHERE NUM_RESERVA = ?";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, numReserva);
            comandoSql.execute();
        } catch (SQLException ex) {
             Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        if (quartoVago){
            esvaziarQuarto(numReserva);
        }
    }
    
    /*
    * Função: ver pagamentos pendentes
    */
    
    public ArrayList<Reserva> verPendencias(){
        String templateComandoSql = "SELECT * FROM RESERVA WHERE PAGO=0";
        ArrayList<Reserva> reservas = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Reserva reserva = new Reserva(retornoSql);
                reservas.addFirst(reserva);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return reservas;
    }
}
