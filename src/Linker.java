import Controller.ClienteController;
import java.sql.SQLException;

public class Linker {
    public static void main(String[] args) throws SQLException{
        ClienteController.acessarCliente(1);
    }
}