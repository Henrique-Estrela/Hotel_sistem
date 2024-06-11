package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Utils.Fila;
import Models.Cliente;

public class AtendimentoController {
    
    public Integer verProxCodigoNaFila(){
        String templateComandoSql = "SELECT * FROM fila_de_atendimento LIMIT 1";
        Integer idCliente = null;
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            idCliente = retornoSql.getInt("ID_CLIENTE");
         } catch (SQLException ex) {
             Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             DB.closeConexao();
         } return idCliente;
    }
    
    public Cliente verProxClienteNaFila(){
        ClienteController funcKit = new ClienteController();
        int idCliente = verProxCodigoNaFila();
        return funcKit.acessarCliente(idCliente);
    }
    
    public void removerProxDaFila(int idCliente){
        String templateComandoSql = "DELETE FROM fila_de_atendimento WHERE ID_CLIENTE=" + idCliente;
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
    
    public Cliente atenderDaFila(){
        Cliente proxCliente = verProxClienteNaFila();
        removerProxDaFila(proxCliente.getId());
        return proxCliente;
    }
    
    public void enfileirarNoBD(int idCliente){
        String templateComandoSql = "INSERT INTO fila_de_atendimento(id_cliente) VALUES (?)";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idCliente);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
    
    public Fila consultarFilaNoBD(){
        String templateComandoSql = "SELECT * FROM fila_de_atendimento";
        Fila filaDeClientes = new Fila();
        ClienteController funcKit = new ClienteController();
        try {
             Connection dbConectado = DB.getConexao();
             ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
             while (retornoSql.next()) {
                 int idCliente = retornoSql.getInt("ID_CLIENTE");
                 Cliente cliente = funcKit.acessarCliente(idCliente, dbConectado);
                 filaDeClientes.enfileirar(cliente);
             }
         } catch (SQLException ex) {
             Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             DB.closeConexao();
         } return filaDeClientes;
    }
}
