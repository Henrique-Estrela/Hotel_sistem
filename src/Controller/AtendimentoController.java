package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.Cliente;
import Models.FilaAtendimento;
import Utils.Fila;
import java.util.LinkedList;
import java.util.Queue;

public class AtendimentoController {

    /*
    * Função: ver o próximo código que será atendido na fila
    * Requisito: -
    * Retorno: retornará o próximo código que será atendido na fila
    * Obs: em caso de erros, retornará código null
     */
    public FilaAtendimento verProxNaFila() {
        String templateComandoSql = "SELECT * FROM fila_de_atendimento ORDER BY id_fila LIMIT 1";
        try {
            Connection db = DB.getConexao();
            ResultSet rs = db.createStatement().executeQuery(templateComandoSql);
            //
            return new FilaAtendimento(rs);
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return null;
    }

    /*
    * Função: atende o próximo da fila
    * Requisito: -
    * Retorno: retornará as informações do cliente que será atendido
    * Obs: este método une funcionalidades de outros métodos para automaticamente obter as informações do próximo da fila e fazer a remoção do mesmo na ordem armazenada no banco de dados
     */
    public Boolean atenderDaFila() {        
        return removerDaFila();
    }

    /*
    * Função: adiciona um cliente ao final da fila
    * Requisito: informar o id do cliente que será adicionado à fila
     */
    public void adicionarCliente(int idCliente) {
        String templateComandoSql = "INSERT INTO fila_de_atendimento(id_fila, id_cliente) VALUES (null, ?)";
        try {
            Connection db = DB.getConexao();
            PreparedStatement ps = db.prepareStatement(templateComandoSql);
            ps.setInt(1, idCliente);
            ps.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }

    public Queue<FilaAtendimento> consultarFila() {
        String templateComandoSql = "SELECT *                   " +
                                    "  FROM fila_de_atendimento " +
                                    " inner join cliente        " +
                                    "    on cliente.id = fila_de_atendimento.id_cliente" +
                                    "  order by id_fila";
        
        Queue<FilaAtendimento> filaAtendimentos = new LinkedList<>();        
        try {
            Connection db = DB.getConexao();
            ResultSet rs = db.createStatement().executeQuery(templateComandoSql);
            while (rs.next()) {
                FilaAtendimento filaAtendimento = new FilaAtendimento(rs);
                filaAtendimentos.add(filaAtendimento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return filaAtendimentos;
    }
    
    /*
    * Função: consultar fila armazenada no banco de dados
    * Requisito: -
    * Retorno: retorna a fila de Clientes armazenada no banco de dados, onde cada Cliente possui suas informações que podem ser facilmente extraídas
     */
    public Fila consultarFilaNoBD() {
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
        }
        return filaDeClientes;
    }

    /*
    * Função: remove um cliente da fila
    * Requisito: informar o id do cliente que será removido da fila
    * Retorno: retornará as informações do próximo cliente que será atendido na fila
     */
    private boolean removerDaFila() {
        String templateComandoSql = "DELETE FROM fila_de_atendimento WHERE id_fila = (select min(fila_de_atendimento.id_fila) from fila_de_atendimento)";
        try {
            Connection db = DB.getConexao();
            PreparedStatement ps = db.prepareStatement(templateComandoSql);
            return ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return false;
    }
    
    
}
