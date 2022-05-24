import java.sql.SQLException;

public class Main {

    public static void main (String[] args) throws SQLException {
        PLSQL bd = new PLSQL();
        //bd.insertUser("Gigel", "Ceva");
        System.out.println(bd.selectieUser("Altceva"));
        System.out.println(bd.insertUser("UnNume", "OParola"));
    }
}
