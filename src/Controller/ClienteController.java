package Controller;

import Connection.DB;
import Models.Cliente;
import Utils.DateFormatterFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteController {

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

    public ArrayList<Cliente> conultarCliente() {
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
}
