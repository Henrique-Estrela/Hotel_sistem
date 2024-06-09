package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DB;

public class ClienteController {

    /*
    * Função: Registrar novo cliente 
    * Requisitos:
    * - Nome deve ter 2 letras ou mais
    * - Não pode ter valores em branco
    *
    * Posteriormente fazer uma função aonde o cpf não é passado
    */

    public static Exception registrarCliente(String cpf, String nome, String data_nasc, String telefone){
        String template_comando_sql = "INSERT INTO cliente(cpf, nome, data_nasc, telefone) VALUES (?, ?, ?, ?)";
        Exception excecao_runtime = null;
        try {
            Connection db_conectado = DB.getConexao();
            PreparedStatement comando_sql = db_conectado.prepareStatement(template_comando_sql);
            comando_sql.setString(0, cpf);
            comando_sql.setString(1, nome);
            comando_sql.setString(2, data_nasc);
            comando_sql.setString(3, telefone);
            comando_sql.execute();
        } catch (SQLException excecao_sql) {
            excecao_runtime = new Exception(excecao_sql.getMessage());
        } finally {
            DB.closeConexao();
        }
        return excecao_runtime;
    }

    /*
    * Função: Acessar registro de um cliente
    * Requisitos:
    * - Nome deve ter 2 letras ou mais
    * - Não pode ter valores em branco
    *
    * Posteriormente fazer uma função aonde o cpf não é passado
    */

    public static Exception acessarCliente(int codigo){
        String template_comando_sql = "SELECT * FROM cliente WHERE id = 1";
        Exception excecao_runtime = null;
        try {
            Connection db_conectado = DB.getConexao();
            ResultSet retorno_sql = db_conectado.createStatement().executeQuery(template_comando_sql);
            System.out.println(retorno_sql);
        } catch (SQLException excecao_sql) {
            excecao_runtime = new Exception(excecao_sql.getMessage());
        } finally {
            DB.closeConexao();
        }
        return excecao_runtime; 
    }
    
}
