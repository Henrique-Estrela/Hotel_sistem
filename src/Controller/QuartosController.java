package Controller;

import Connection.DB;
import Models.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuartosController {
    
    /*
    * Função: acessar quarto pelo código
    * Requisito: informar o código do quarto
    * Retorno: retornará as informações do quarto que possui o código informado cadastrado no seu registro
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
    
    /*
    * Função: acessar quarto pelo código
    * Requisito: informar o código do quarto / passar a conexão já pré-estabelecida com o banco de dados
    * Retorno: retornará as informações do quarto que possui o código informado cadastrado no seu registro
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
    */
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
    
    /*
    * Função: inserir novo quarto no banco de dados
    * Requisito: passar o quarto instanciado com as novas informações
    */
    public void inserirQuarto(Quarto quarto) {
        String templateComandoSql = "INSERT INTO QUARTO(ID, NUM, TAMANHO) VALUES (null, ?, ?)";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, quarto.getNum());           
            comandoSql.setString(2, String.valueOf(quarto.getTamanho()));            
            comandoSql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }

    /*
    * Função: consultar todos os quartos registrados
    * Retorno: retornará uma lista contendo todos os quartos registrados no banco de dados
    */ 
    public ArrayList<Quarto> conultarQuartos() {
        String templateComandoSql = "SELECT * FROM QUARTO";
        ArrayList<Quarto> quartos = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Quarto quarto = new Quarto(retornoSql);
                quartos.add(quarto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return quartos;
    }

    /*
    * Função: consultar todos os quartos registrados que estão vazios atualmente
    * Retorno: retornará uma lista contendo todos os quartos registrados no banco de dados que estão vazios atualmente
    */ 
    public ArrayList<Quarto> conultarQuartosVazios() {
        String templateComandoSql = "SELECT * FROM QUARTO WHERE RESERVADO = 0";
        ArrayList<Quarto> quartosVazios = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Quarto quarto = new Quarto(retornoSql);
                quartosVazios.add(quarto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return quartosVazios;
    }

    /*
    * Função: editar informações de um quarto
    * Requisito: passar o quarto instanciado com novas informações
    * Obs: a modificaçõo ocorre no registro do quarto que contenha o id inserido no quarto instanciado
    */
    public void editarQuarto(Quarto quarto) {
        String templateComandoSql = "UPDATE QUARTO SET  " +
                                    "   NUM      = ?,   " +
                                    "   TAMANHO  = ?    " +                                                       
                                    "WHERE ID = ?";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, quarto.getNum());
            comandoSql.setString(2, String.valueOf(quarto.getTamanho()));
            comandoSql.setInt(3, quarto.getId());
            comandoSql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(QuartosController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }
    }
}
