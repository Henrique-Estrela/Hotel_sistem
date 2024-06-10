package Controller;

import Connection.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Atendente;

public class AtendenteController {
    
    /*
    * Função: acessar atendente
    */
    public Atendente acessarAtendente(int codigo){
        String templateComandoSql = "SELECT * FROM atendente WHERE id=" + codigo;
        Atendente atendenteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            atendenteAcessado = new Atendente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(AtendenteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } finally {
            DB.closeConexao();
        }
        return atendenteAcessado;
    }
}