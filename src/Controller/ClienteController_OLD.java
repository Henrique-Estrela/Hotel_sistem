package Controller;

import Models.Cliente;
// import Views.JPClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
// import java.util.logging.Level;
// import java.util.logging.Logger;

import Connection.DB;

public class ClienteController {

    // Função: Registrar novo cliente

    // Requisitos:
    // Nome deve ter 2 letras
    // Não pode deixar um campo em branco

    // Posteriormente pode fazer um código onde o cpf não é passado

    // public void registrarCliente(String cpf, String nome, String data_nasc, String telefone){
    //     String template_comando_sql = "INSERT INTO cliente(cpf, nome, data_nasc, telefone) VALUES (?, ?, ?, ?)";
    //     try {
    //         Connection db_conectado = DB.conectar();
    //         PreparedStatement comando_sql = db_conectado.prepareStatement(template_comando_sql);
    //         comando_sql.setString(0, cpf);
    //         comando_sql.setString(1, nome);
    //         comando_sql.setString(2, data_nasc);
    //         comando_sql.setString(3, telefone);
    //         comando_sql.execute();
    //     } catch (SQLException excecao) {
    //         Logger.getLogger(JPClientes.class.getName()).log(Level.SEVERE, null, excecao);
    //     } finally {
    //         DB.desconectar();
    //     }
    // }

    // Função: Acessar registro de um cliente

    public static void acessarCliente(int codigo) throws SQLException{
        String template_comando_sql = "SELECT * FROM cliente WHERE id = 1";
        Connection db_conectado = DB.conectar();
        ResultSet retorno_sql = db_conectado.createStatement().executeQuery(template_comando_sql);
        System.out.println(retorno_sql);
        DB.desconectar();
    }

    // public static void acessarCliente(int codigo){
    //     String template_comando_sql = "SELECT * FROM cliente WHERE id = 1";
    //     try {
    //         Connection db_conectado = DB.conectar();
    //         ResultSet retorno_sql = db_conectado.createStatement().executeQuery(template_comando_sql);
    //         System.out.println(retorno_sql);
    //     } catch (SQLException ex) {
    //         Logger.getLogger(JPClientes.class.getName()).log(Level.SEVERE, null, ex);
    //     } finally {
    //         DB.desconectar();
    //     }
    // }

        //     while (rs.next()) {
        //         Cliente cliente = new Cliente(rs);
        //         lista.add(cliente);
        //     }
    

    // ------------------------------------------------------------------------------------------ //

    // public ArrayList<Cliente> conultarCliente() {
    //     ArrayList<Cliente> lista = new ArrayList();
    //     try {
    //         // TODO add your handling code here:
    //         Connection conn = DB.getConexao();
    //         //
    //         Statement st = conn.createStatement();
    //         ResultSet rs = st.executeQuery("SELECT * FROM TB_CLIENTE");
    //         while (rs.next()) {
    //             Cliente cliente = new Cliente(rs);
    //             lista.add(cliente);
    //         }
    //     } catch (SQLException ex) {
    //         Logger.getLogger(JPClientes.class.getName()).log(Level.SEVERE, null, ex);
    //     } finally {
    //         DB.closeConexao();
    //     }
    //     return lista;
    // }

    // public void editarCliente(Cliente cliente) {
    //     try {
    //         // TODO add your handling code here:
    //         Connection conn = DB.getConexao();
    //         //
    //         PreparedStatement pst = conn.prepareStatement("UPDATE TB_CLIENTE  "+
    //                                                       "   SET NOME = ?,   "+
    //                                                       "       CPF  = ?,   "+
    //                                                       "       TELEFONE = ?"+
    //                                                       " WHERE ID = ?      ");
    //         pst.setString(1, cliente.getNome());
    //         pst.setString(2, cliente.getCpf());
    //         pst.setString(3, cliente.getTelefone());
    //         pst.setInt(4, cliente.getId());
    //         pst.execute();
    //     } catch (SQLException ex) {
    //         Logger.getLogger(JPClientes.class.getName()).log(Level.SEVERE, null, ex);
    //     } finally {
    //         DB.closeConexao();
    //     }        
    // }
}