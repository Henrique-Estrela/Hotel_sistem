package Controller;

import Models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.ParseException;

import Connection.DB;
import Exceptions.Exception;
import Utils.DateFormatterFactory;

public class ClienteController {
    
    public Exception avaliarNovoCliente(Cliente novoCliente){
        String nome = novoCliente.getNome();
        String telefone = novoCliente.getTelefone();
        String cpf = novoCliente.getCpf();
        if (nome.length() < 1){
            return new Exception("Nome deve conter mais de 1 caractere");
        }
        if (telefone.length() != 11){
            return new Exception("Telefone informado incorretamente");
        }
        if (cpf.length() != 11){
            return new Exception("CPF informado incorretamente");
        }
        return null;
    }
    
    /*
    * Função: registrar novo cliente
    * Requisitos:
    * - Nome deve ter 2 letras
    * - Não pode deixar um campo em branco
    */
    
    public Exception inserirCliente(Cliente cliente) {
        Exception erroNoCliente = avaliarNovoCliente(cliente);
        if (erroNoCliente != null){
            return erroNoCliente;
        }
        try {
            Connection conn = DB.getConexao();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO CLIENTE(ID, NOME, CPF, TELEFONE, DATA_NASC) VALUES (null, ?, ?, ?, ?)");
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, DateFormatterFactory.dateFormatyyyyMMdd().format(cliente.getDataNasc()));
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return null;
    }
    
    public void editarCliente(Cliente cliente) {
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            
            PreparedStatement pst = conn.prepareStatement("UPDATE CLIENTE      "
                                                        + "   SET NOME = ?,    "
                                                        + "       CPF  = ?,    "
                                                        + "       TELEFONE = ?,"
                                                        + "       DATA_NASC = ?"
                                                        + " WHERE ID = ?      ");
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, DateFormatterFactory.dateFormatyyyyMMdd().format(cliente.getDataNasc()));
            pst.setInt(5, cliente.getId());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }    
    
    public void registrarCliente(String cpf, String nome, String data_nasc, String telefone){
        String templateComandoSql = "INSERT INTO cliente(cpf, nome, data_nasc, telefone) VALUES (?, ?, ?, ?)";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setString(1, cpf);
            comandoSql.setString(2, nome);
            comandoSql.setString(3, data_nasc);
            comandoSql.setString(4, telefone);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
        
    /*
    * Função: acessar cliente
    */
    public Cliente acessarCliente(int codigo){
        Cliente clienteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            clienteAcessado = acessarCliente(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        } return clienteAcessado;
    }
    
    public Cliente acessarCliente(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM cliente WHERE id=" + codigo;
        Cliente clienteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            clienteAcessado = new Cliente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return clienteAcessado;
    }
    
    public Cliente acessarCliente(String cpf){
        Cliente clienteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            clienteAcessado = acessarCliente(cpf, dbConectado);
        } finally {
            DB.closeConexao();
        } return clienteAcessado;
    }
    
    public Cliente acessarCliente(String cpf, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM cliente WHERE CPF=" + cpf;
        Cliente clienteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            clienteAcessado = new Cliente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return clienteAcessado;
    }
    
    /*
    * Função: alterar cliente
    */
    
    public void alterarCliente(Cliente cliente) {
        String templateComandoSql = "UPDATE cliente  "+
                                    "   SET NOME = ?,   "+
                                    "       CPF  = ?,   "+
                                    "       TELEFONE = ?"+
                                    " WHERE ID = ?      ";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setString(1, cliente.getNome());
            comandoSql.setString(2, cliente.getCpf());
            comandoSql.setString(3, cliente.getTelefone());
            comandoSql.setInt(4, cliente.getId());
            comandoSql.execute();
        } catch (SQLException ex) {
             Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }        
    }
            
     public ArrayList<Cliente> consultarCliente() {
         ArrayList<Cliente> lista = new ArrayList();
         try {
             // TODO add your handling code here:
             Connection conn = DB.getConexao();
             //
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CLIENTE");
             while (rs.next()) {
                 Cliente cliente = new Cliente(rs);
                 lista.add(cliente);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             DB.closeConexao();
         }
         return lista;
    }
}

