package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.FormaPagamento;
import Models.Pagamento;

public class PagamentoController {
    
    /*
    * Função: ver histórico de pagamentos
    * Requisito: -
    * Retorno: retornará as informações de todos os pagamentos registrados relacionados às reservas realizadas
    */
    public ArrayList<Pagamento> verPagamentos(){
        String templateComandoSql = "SELECT * FROM RESERVA";
        ArrayList<Pagamento> pagamentos = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Pagamento pagamento = new Pagamento(retornoSql, dbConectado);
                pagamentos.addFirst(pagamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return pagamentos;
    }
    
    /*
    * Função: ver pagamentos pendentes
    * Requisito: -
    * Retorno: retornará as informações de todos os pagamentos que constam como pendentes no banco de dados
    */
    public ArrayList<Pagamento> verPendencias(){
        String templateComandoSql = "SELECT * FROM RESERVA WHERE PAGO=0";
        ArrayList<Pagamento> pendencias = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Pagamento pagamento = new Pagamento(retornoSql, dbConectado);
                pendencias.addFirst(pagamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return pendencias;
    }

    /*
    * Função: ver formas de pagamento
    * Requisito: -
    * Retorno: retornará uma lista com todas as formas de pagamento disponíveis para o cliente
    */
    public ArrayList<FormaPagamento> verFormasDePagar(){
        String templateComandoSql = "SELECT * FROM forma_de_pagamento";
        ArrayList<FormaPagamento> formas = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                FormaPagamento forma = new FormaPagamento(retornoSql);
                formas.add(forma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return formas;
    }
    
    /*
    * Função: obter a forma de pagamento
    * Requisito: informar o código da forma de pagamento que deseja buscar
    * Retorno: retornará uma instância contendo todas as informações da forma de pagamento vinculada ao código informado
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
    
    /*
    * Função: obter a forma de pagamento
    * Requisito: informar o código da forma de pagamento que deseja buscar
    * Retorno: retornará uma instância contendo todas as informações da forma de pagamento vinculada ao código informado
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
    */
    public FormaPagamento acessarForma(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM forma_de_pagamento WHERE id=" + codigo;
        FormaPagamento pagamentoAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            pagamentoAcessado = new FormaPagamento(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return pagamentoAcessado;
    }
    
    /*
    * Função: marcar uma reserva como paga
    * Requisito: informar o núemro da reserva
    */
    public void marcarReservaComoPaga(int numReserva) {
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
             Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }
}