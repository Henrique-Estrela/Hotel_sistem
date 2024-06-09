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

    public void editarQuarto(Quarto quarto) {
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            
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
