package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.Atendente;

public class LoginController {

    public boolean auth(Atendente atendente) {
        String templateComandoSql = "SELECT ID,                 " +
                                    "   NOME,                   " +
                                    "   SENHA,                  " +
                                    "   ATIVO                   " + 
                                    "FROM                       " +
                                    "   ATENDENTE               " +
                                    "WHERE                      " +
                                    "   ATENDENTE.ID = ? AND    " +
                                    "   ATENDENTE.SENHA = ? AND " +
                                    "   ATENDENTE.ATIVO = 1     ";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, atendente.getId());
            comandoSql.setString(2, atendente.getSenha());
            ResultSet retornoSql = comandoSql.executeQuery();
            return retornoSql.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return false;
    }
}
