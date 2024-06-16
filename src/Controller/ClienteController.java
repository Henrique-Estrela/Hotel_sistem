package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Exceptions.Exception;
import Models.Cliente;
import Utils.DateFormatterFactory;

public class ClienteController {

    /*
    * Função: verificar se há algum erro nas informações do cliente instanciado
    * Requisito: passar o cliente instanciado
    * Retorno: retornará uma Exception com o erro encontrado nas informações do cliente
    * Obs: se as informações estiverem ok, retornará null
     */
    public Exception avaliarCliente(Cliente cliente) {
        String nome = cliente.getNome();
        String telefone = cliente.getTelefone();
        String cpf = cliente.getCpf();
        if (nome.length() < 1) {
            return new Exception("Nome deve conter mais de 1 caractere");
        }
        if (telefone.length() != 11) {
            return new Exception("Telefone informado incorretamente");
        }
        if (cpf.length() != 11) {
            return new Exception("CPF informado incorretamente");
        }
        return null;
    }

    /*
    * Função: inserir cliente no banco de dados
    * Requisito: passar o cliente instanciado
    * Retorno: retornará uma Exception caso algum erro seja encontrado nas informações do novo cliente
    * Obs: se as informações estiverem ok, retornará null
     */
    public Exception inserirCliente(Cliente cliente) {

        String templateComandoSql = "INSERT INTO CLIENTE(ID, NOME, CPF, TELEFONE, DATA_NASC) VALUES (null, ?, ?, ?, ?)";

//        Exception erroNoCliente = avaliarCliente(cliente);
//        if (erroNoCliente != null){
//            return erroNoCliente;
//        };
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setString(1, cliente.getNome());
            comandoSql.setString(2, cliente.getCpf());
            comandoSql.setString(3, cliente.getTelefone());
            comandoSql.setString(4, DateFormatterFactory.dateFormatyyyyMMdd().format(cliente.getDataNasc()));
            comandoSql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return null;
    }

    /*
    * Função: editar informações de um cliente
    * Requisito: passar o cliente instanciado com novas informações
    * Obs: a modificaçõo ocorre no registro do cliente que contenha o id inserido no cliente instanciado
     */
    public void editarCliente(Cliente cliente) {
        String templateComandoSql = "UPDATE CLIENTE      "
                + "   SET NOME = ?,    "
                + "       CPF  = ?,    "
                + "       TELEFONE = ?,"
                + "       DATA_NASC = ?"
                + "   WHERE ID = ?  ";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setString(1, cliente.getNome());
            comandoSql.setString(2, cliente.getCpf());
            comandoSql.setString(3, cliente.getTelefone());
            comandoSql.setString(4, DateFormatterFactory.dateFormatyyyyMMdd().format(cliente.getDataNasc()));
            comandoSql.setInt(5, cliente.getId());
            comandoSql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }

    /*
    * Função: acessar cliente
    * Requisito: informar o código do cliente
    * Retorno: retornará as informações do cliente que possui o código informado cadastrado no seu registro
     */
    public Cliente acessarCliente(int codigo) {
        Cliente clienteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            clienteAcessado = acessarCliente(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        }
        return clienteAcessado;
    }

    /*
    * Função: acessar cliente
    * Requisito: informar o código do cliente / passar a conexão já pré-estabelecida com o banco de dados
    * Retorno: retornará as informações do cliente que possui o código informado cadastrado no seu registro
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
     */
    public Cliente acessarCliente(int codigo, Connection dbConectado) {
        String templateComandoSql = "SELECT * FROM cliente WHERE id=" + codigo;
        Cliente clienteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            clienteAcessado = new Cliente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        }
        return clienteAcessado;
    }

    /*
    * Função: acessar cliente
    * Requisito: informar o cpf do cliente
    * Retorno: retornará as informações do cliente que possui o cpf informado cadastrado no seu registro
     */
    public Cliente acessarCliente(String cpf) {
        Cliente clienteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            clienteAcessado = acessarCliente(cpf, dbConectado);
        } finally {
            DB.closeConexao();
        }
        return clienteAcessado;
    }

    /*
    * Função: acessar cliente
    * Requisito: informar o cpf do cliente
    * Retorno: retornará as informações do cliente que possui o cpf informado cadastrado no seu registro
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
     */
    public Cliente acessarCliente(String cpf, Connection dbConectado) {
        String templateComandoSql = "SELECT * FROM cliente WHERE CPF=" + cpf;
        Cliente clienteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            clienteAcessado = new Cliente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        }
        return clienteAcessado;
    }

    /*
    * Função: consultar todos os clientes registrados
    * Requisito: -
    * Retorno: retornará uma lista contendo todos os clientes registrados no banco de dados
     */
    public ArrayList<Cliente> consultarCliente() {
        String templateComandoSql = "SELECT * FROM CLIENTE";
        ArrayList<Cliente> clientes = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Cliente cliente = new Cliente(retornoSql);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return clientes;
    }

    public ArrayList<Cliente> consultarClienteForaFila() {
        String templateComandoSql = "SELECT *" +
                                    "  FROM CLIENTE" +
                                    " WHERE CLIENTE.id NOT IN (SELECT fila_de_atendimento.id_cliente " +
                                    "                            FROM fila_de_atendimento)";
        
        ArrayList<Cliente> clientes = new ArrayList();
        try {
            Connection db = DB.getConexao();
            ResultSet retornoSql = db.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Cliente cliente = new Cliente(retornoSql);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return clientes;
    }
}
