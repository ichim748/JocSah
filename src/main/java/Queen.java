import java.util.List;

public class Queen extends Piece{

    public Queen(boolean white){
        super(white, false);
    }

    @Override
    public boolean validMove(Board board, Square start, Square end) throws Exception {
        return false;
    }

    @Override
    public List<Square> possibleMoves(Board board, Square box) throws Exception {
        return null;
    }
}
