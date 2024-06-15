package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;
import Models.Reserva;
import Utils.DateFormatterFactory;
import java.text.DateFormat;

public class ReservasController {

    /*
    * Função: registrar reserva
    * Atribui automaticamente os seguintes valores...
    * - num_reserva: é a chave primária autoincrementável
    * - data_checkin: registra o horário atual em que o registro é feito
    * - data_checkout: iniciará como nulo, já que não faz sentido fazer checkin e checkout no hotel ao mesmo tempo
    * - pago: iniciará como false, indicando que o pagamento pode ser feito em momento posterior ao checkin
    */
    public void registrarReserva(int idCliente, int idAtendente, int idQuarto, int idPagamento, int numHospedes, Double valorPagamento){
        String templateComandoSql = "INSERT INTO reserva(id_cliente, id_atendente, id_quarto, id_pagamento, num_hospedes, valor_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idCliente);
            comandoSql.setInt(2, idAtendente);
            comandoSql.setInt(3, idQuarto);
            comandoSql.setInt(4, idPagamento);
            comandoSql.setInt(5, numHospedes);
            comandoSql.setDouble(6, valorPagamento);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
    
    /*
    * Função: registrar reserva
    * Atribui automaticamente os seguintes valores...
    * - num_reserva: é a chave primária autoincrementável
    * - data_checkout: iniciará como nulo, já que não faz sentido fazer checkin e checkout no hotel ao mesmo tempo
    * - pago: iniciará como false, indicando que o pagamento pode ser feito em momento posterior ao checkin
    */
    public void registrarReserva(int idCliente, int idAtendente, int idQuarto, int idPagamento, LocalDateTime dataCheckin, int numHospedes, float valorPagamento){
        String templateComandoSql = "INSERT INTO reserva(id_cliente, id_atendente, id_quarto, id_pagamento, data_checkin, num_hospedes, valor_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Timestamp dataCheckinSql = Timestamp.valueOf(dataCheckin);
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idCliente);
            comandoSql.setInt(2, idAtendente);
            comandoSql.setInt(3, idQuarto);
            comandoSql.setInt(4, idPagamento);
            comandoSql.setTimestamp(5, dataCheckinSql);
            comandoSql.setInt(6, numHospedes);
            comandoSql.setFloat(7, valorPagamento);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }

    /*
    * Função: acessar todas as reservas registradas no banco de dados
    * Requisito: -
    * Retorno: retornará uma lista com todos os registros de reservas armazenados no banco de dados
    */
    public ArrayList<Reserva> acessarReservas() {
        String templateComandoSql = "SELECT * FROM RESERVA";
        ArrayList<Reserva> reservas = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Reserva reserva = new Reserva(retornoSql);
                reservas.addFirst(reserva);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return reservas;
    }
    
      public ArrayList<Reserva> verPendencias() {
        String templateComandoSql = "SELECT * FROM RESERVA WHERE PAGO = 0";
        ArrayList<Reserva> reservas = new ArrayList();
        try {
            Connection dbConectado = DB.getConexao();
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            while (retornoSql.next()) {
                Reserva reserva = new Reserva(retornoSql);
                reservas.addFirst(reserva);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        } return reservas;
    }
          
    
    /*
    * Função: acessar reserva pelo código
    * Requisito: informar o código da reserva
    * Retorno: retornará as informações da reserva que possui o código informado cadastrado no seu registro
    */
    public Reserva acessarReserva(int codigo){
        Reserva reservaAcessada = null;
        try {
            Connection dbConectado = DB.getConexao();
            reservaAcessada = acessarReserva(codigo, dbConectado);
        } finally {
            DB.closeConexao();
        } return reservaAcessada;
    }
    
    /*
    * Função: acessar reserva pelo código
    * Requisito: informar o código da reserva / passar a conexão já pré-estabelecida com o banco de dados
    * Retorno: retornará as informações da reserva que possui o código informado cadastrado no seu registro
    * Obs: este método evita que a conexão com o banco de dados seja finalizada antes da execução completa do método
    */
    public Reserva acessarReserva(int codigo, Connection dbConectado){
        String templateComandoSql = "SELECT * FROM reserva WHERE num_reserva=" + codigo;
        Reserva reservaAcessada = null;
        try {
            ResultSet retornoSql = dbConectado.createStatement().executeQuery(templateComandoSql);
            reservaAcessada = new Reserva(retornoSql);
        } catch (SQLException excecaoSql) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, excecaoSql);
        } return reservaAcessada;
    }
    
    /*
    * Função: alterar informações de uma reserva
    * Requisito: passar a reserva instanciada com novas informações
    * Obs: a modificaçõo ocorre no registro da reserva que contenha o id inserido na reserva instanciada
    */
    public void alterarReserva(Reserva reserva) {
        String templateComandoSql = "UPDATE reserva             " +
                                    "   SET ID_CLIENTE  = ?,    " +
                                    "       ID_ATENDENTE = ?,   " +
                                    "       ID_QUARTO = ?,      " +
                                    "       ID_PAGAMENTO = ?,   " +
                                    "       DATA_CHECKIN = ?,   " +
                                    "       DATA_CHECKOUT = ?,  " +
                                    "       NUM_HOSPEDES = ?,   " +
                                    "       VALOR_PAGAMENTO = ?," +
                                    "       PAGO = ?            " +
                                    " WHERE NUM_RESERVA = ?     ";
        try {
            DateFormat df = DateFormatterFactory.dateFormatyyyyMMdd();
            
            
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, reserva.getIdCliente());
            comandoSql.setInt(2, reserva.getIdAtendente());
            comandoSql.setInt(3, reserva.getIdQuarto());
            comandoSql.setInt(4, reserva.getIdFormaPagamento());
            comandoSql.setString(5, Timestamp.valueOf(reserva.getDataCheckin()).toString());
            comandoSql.setString(6, Timestamp.valueOf(reserva.getDataCheckout()).toString());
            comandoSql.setInt(7, reserva.getNumHospedes());
            comandoSql.setDouble(8, reserva.getValorPagamento());
            comandoSql.setBoolean(9, reserva.estaPago());
            comandoSql.setInt(10, reserva.getNum());
            comandoSql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeConexao();
        }        
    }
}

