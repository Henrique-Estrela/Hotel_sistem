package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.Atendente;

public class AtendenteController {

    /*
    * Função: acessar atendente
    * Requisito: -
    * Retorno: ao não passar nenhum parâmetro, retornará o registro de todos os atendentes
    */
    public ArrayList<Atendente> acessarAtendente() {
        String templateComandoSql = "SELECT * FROM ATENDENTE";
        ArrayList<Atendente> atendentes = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Atendente atendente = new Atendente(retornoSql);
                atendentes.add(atendente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return atendentes;
    }
    
    /*
    * Função: acessar atendente
    * Requisito: informar o código do atendente
    * Retorno: retornará as informações do atendente que possui o código informado cadastrado no seu registro
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
    
    /*
    * Função: acessar atendente
    * Requisito: informar o código do atendente / passar a conexão já pré-estabelecida com o banco de dados
    * Retorno: retornará as informações do atendente que possui o código informado cadastrado no seu registro
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
    */
    public Atendente acessarAtendente(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM atendente WHERE id=" + codigo;
        Atendente atendenteAcessado = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            atendenteAcessado = new Atendente(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(AtendenteController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return atendenteAcessado;
    }
}