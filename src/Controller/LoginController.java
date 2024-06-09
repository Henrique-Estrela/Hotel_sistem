package Controller;

import Connection.DB;
import Models.Atendente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    public boolean auth(Atendente atendente) {
        try {
            // TODO add your handling code here:
            Connection conn = DB.getConexao();
            //
            PreparedStatement pst = conn.prepareStatement("SELECT ID,"
                                                        + "       NOME,"
                                                        + "       SENHA,"
                                                        + "       ATIVO"
                                                        + "  FROM ATENDENTE"
                                                        + " WHERE ATENDENTE.ID = ?"
                                                        + "   AND ATENDENTE.SENHA = ?"
                                                        + "   AND ATENDENTE.ATIVO = 1");
            pst.setInt(1, atendente.getId());
            pst.setString(2, atendente.getSenha());
            ResultSet rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }

        return false;
    }

}
