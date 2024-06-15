package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.Cliente;
import Utils.Fila;

public class AtendimentoController {

    /*
    * Função: ver o próximo código que será atendido na fila
    * Requisito: -
    * Retorno: retornará o próximo código que será atendido na fila
    * Obs: em caso de erros, retornará código null
    */
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
    
    /*
    * Função: ver o próximo cliente que será atendido na fila
    * Requisito: -
    * Retorno: retornará as informações do próximo cliente que será atendido na fila
    */
    public Cliente verProxClienteNaFila(){
        ClienteController funcKit = new ClienteController();
        int idCliente = verProxCodigoNaFila();
        return funcKit.acessarCliente(idCliente);
    }
    
    /*
    * Função: remove um cliente da fila
    * Requisito: informar o id do cliente que será removido da fila
    * Retorno: retornará as informações do próximo cliente que será atendido na fila
    */
    public void removerDaFila(int idCliente){
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
    
    /*
    * Função: atende o próximo da fila
    * Requisito: -
    * Retorno: retornará as informações do cliente que será atendido
    * Obs: este método une funcionalidades de outros métodos para automaticamente obter as informações do próximo da fila e fazer a remoção do mesmo na ordem armazenada no banco de dados
    */
    public Cliente atenderDaFila(){
        Cliente proxCliente = verProxClienteNaFila();
        removerDaFila(proxCliente.getId());
        return proxCliente;
    }
    
    /*
    * Função: adiciona um cliente ao final da fila
    * Requisito: informar o id do cliente que será adicionado à fila
    */
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
    
    /*
    * Função: consultar fila armazenada no banco de dados
    * Requisito: -
    * Retorno: retorna a fila de Clientes armazenada no banco de dados, onde cada Cliente possui suas informações que podem ser facilmente extraídas
    */
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
