import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main (String[] args) throws SQLException, IOException {
        Board board = new Board();
        DrawBoard drawBoard = new DrawBoard(board);
    }
}
