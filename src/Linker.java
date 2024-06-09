import Controller.ClienteController;
import java.sql.SQLException;
import Models.Cliente;
import Utils.DateFormatterFactory;
import java.text.ParseException;
import java.util.Date;

public class Linker {
    public static void main(String[] args) throws SQLException, ParseException{
        Date dataNasc = DateFormatterFactory.dateFormatyyyyMMdd().parse("2000-10-02");
        Cliente cliente = new Cliente(2, "Rafael Mendonça", "75960010002", "93801812000", dataNasc);
        ClienteController.alterarCliente(cliente);
    }
}