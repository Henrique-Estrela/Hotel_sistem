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

import Connection.DB;

import Utils.DateFormatterFactory;

public class ClienteController {
    
    /*
    * Função: registrar novo cliente
    * Requisitos:
    * - Nome deve ter 2 letras
    * - Não pode deixar um campo em branco
    */
    public void inserirCliente(Cliente cliente) {
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
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
        }
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
        String templateComandoSql = "SELECT * FROM cliente WHERE id=" + codigo;
        Cliente clienteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            clienteAcessado = new Cliente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } finally {
            DB.closeConexao();
        }
        return clienteAcessado;
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
            
    // ------------------------------------------------------------------- //
    
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

