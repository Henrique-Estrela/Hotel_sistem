package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.DB;

public class ReservasController {
    
    /*
    * Função: registrar reserva
    * Atribui automaticamente os seguintes valores...
    * - num_reserva: é a chave primária autoincrementável
    * - data_checkin: registra o horário atual em que o registro é feito
    * - data_checkout: iniciará como nulo, já que não faz sentido fazer checkin e checkout no hotel ao mesmo tempo
    * - pago: iniciará como false, indicando que o pagamento pode ser feito em momento posterior ao checkin
    */
    
    public void registrarReserva(int idCliente, int idAtendente, int idQuarto, int idPagamento, int numHospedes, float valorPagamento){
        
    }
    
    
    public void registrarReserva(
            
            
            
            
            LocalDateTime dataCheckout,
            
            
            boolean pago){
        String templateComandoSql = "INSERT INTO reserva(id_cliente, id_atendente, id_quarto, id_pagamento, data_checkin, data_checkout, num_hospedes, valor_pagamento, pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Timestamp dataCheckinSql = Timestamp.valueOf(dataCheckin);
        Timestamp dataCheckoutSql = Timestamp.valueOf(dataCheckout);
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idCliente);
            comandoSql.setInt(2, idAtendente);
            comandoSql.setInt(3, idQuarto);
            comandoSql.setInt(4, idPagamento);
            comandoSql.setTimestamp(5, dataCheckinSql);
            comandoSql.setTimestamp(6, dataCheckoutSql);
            comandoSql.setInt(7, numHospedes);
            comandoSql.setFloat(8, valorPagamento);
            comandoSql.setBoolean(9, pago);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
    
    
    
    

    /*
    * Função: registrar reserva
    */
    
    public void registrarReserva(
            int idCliente,
            int idAtendente,
            int idQuarto,
            int idPagamento,
            LocalDateTime dataCheckin,
            LocalDateTime dataCheckout,
            int numHospedes,
            float valorPagamento,
            boolean pago){
        String templateComandoSql = "INSERT INTO reserva(id_cliente, id_atendente, id_quarto, id_pagamento, data_checkin, data_checkout, num_hospedes, valor_pagamento, pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Timestamp dataCheckinSql = Timestamp.valueOf(dataCheckin);
        Timestamp dataCheckoutSql = Timestamp.valueOf(dataCheckout);
        try {
            Connection dbConectado = DB.getConexao();
            PreparedStatement comandoSql = dbConectado.prepareStatement(templateComandoSql);
            comandoSql.setInt(1, idCliente);
            comandoSql.setInt(2, idAtendente);
            comandoSql.setInt(3, idQuarto);
            comandoSql.setInt(4, idPagamento);
            comandoSql.setTimestamp(5, dataCheckinSql);
            comandoSql.setTimestamp(6, dataCheckoutSql);
            comandoSql.setInt(7, numHospedes);
            comandoSql.setFloat(8, valorPagamento);
            comandoSql.setBoolean(9, pago);
            comandoSql.execute();
        } catch (SQLException excecao) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, excecao);
        } finally {
            DB.closeConexao();
        }
    }
    
    public static void main(String args[]){
        ReservasController kit = new ReservasController();
        kit.registrarReserva(2, 1, 0, 0, 0, LocalDateTime.MIN, LocalDateTime.MAX, 0, 0, true);
    }
}

