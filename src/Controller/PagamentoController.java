package Controller;

import Connection.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.FormaPagamento;

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
}
