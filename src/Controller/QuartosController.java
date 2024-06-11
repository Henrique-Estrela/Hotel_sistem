package Controller;

import Connection.DB;
import Models.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuartosController {
    
    /*
    * Função: acessar quarto
    */
    public Quarto acessarQuarto(int codigo){
        Quarto quartoAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            quartoAcessado = acessarQuarto(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        } return quartoAcessado;
    }
    
    public Quarto acessarQuarto(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM quarto WHERE id=" + codigo;
        Quarto quartoAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            quartoAcessado = new Quarto(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return quartoAcessado;
    }
    
        public void inserirQuarto(Quarto quarto) {
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            PreparedStatement pst = conn.prepareStatement("INSERT INTO QUARTO(ID, NUM, TAMANHO) VALUES (null, ?, ?)");
            pst.setInt(1, quarto.getNum());           
            pst.setString(2, String.valueOf(quarto.getTamanho()));            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }

    public ArrayList<Quarto> conultarQuartos() {
        ArrayList<Quarto> lista = new ArrayList();
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUARTO");
            while (rs.next()) {
                Quarto quarto = new Quarto(rs);
                lista.add(quarto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return lista;
    }
    public ArrayList<Quarto> conultarQuartosVazios() {
        ArrayList<Quarto> lista = new ArrayList();
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUARTO WHERE RESERVADO = 0");
            while (rs.next()) {
                Quarto quarto = new Quarto(rs);
                lista.add(quarto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
        return lista;
    }

    public void editarQuarto(Quarto quarto) {
        try {
            Connection conn = DB.getConexao();
            PreparedStatement pst = conn.prepareStatement("UPDATE QUARTO       "
                                                        + "   SET NUM      = ?,"
                                                        + "       TAMANHO  = ? "                                                        
                                                        + " WHERE ID = ?       ");
            pst.setInt(1, quarto.getNum());
            pst.setString(2, String.valueOf(quarto.getTamanho()));
            pst.setInt(3, quarto.getId());
            //            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }
}
