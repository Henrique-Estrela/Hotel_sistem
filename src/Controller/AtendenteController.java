package Controller;

import Connection.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import Models.Atendente;
import java.util.ArrayList;

public class AtendenteController {
    
    /*
    * Função: acessar atendente
    */
    public Atendente acessarAtendente(int codigo){
        Atendente atendenteAcessado = null;
        try {
            Connection dbConectado = DB.getConexao();
            atendenteAcessado = acessarAtendente(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        } return atendenteAcessado;
    }
    
    private Atendente acessarAtendente(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM atendente WHERE id=" + codigo;
        Atendente atendenteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            atendenteAcessado = new Atendente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(AtendenteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return atendenteAcessado;
    }
    
    public ArrayList<Atendente> acessarAtendente() {
         ArrayList<Atendente> lista = new ArrayList();
         try {
             // TODO add your handling code here:
             Connection conn = DB.getConexao();
             //
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Atendente");
             while (rs.next()) {
                 Atendente atendente = new Atendente(rs);
                 lista.add(atendente);
             }
         } catch (SQLException ex) {
             Logger.getLogger(AtendenteController.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             DB.closeConexao();
         }
         return lista;
    }
    
}