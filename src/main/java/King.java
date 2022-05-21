import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private boolean isCastled = false;

    public King(boolean white)
    {
        super(white, true);
    }

    public boolean isCastled() {
        return isCastled;
    }
    public void setCastled(boolean castled) {
        isCastled = castled;
    }

    @Override
    public boolean validMove(Board board, Square start, Square end) throws Exception {
        if (start.getPiece().isWhite() == end.getPiece().isWhite()){
            return false;
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.getBoard()[i][j].getPiece() != null && board.getBoard()[i][j].getPiece().isWhite() != this.isWhite()){
                    for (Square t : board.getBoard()[i][j].getPiece().possibleMoves(board, board.getBox(i, j))){
                        if (t.equals(end))
                            return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<Square> possibleMoves(Board board, Square start) throws Exception {
        List<Square> deReturnat = new ArrayList<>();
        deReturnat.add(board.getBox(start.getX() - 1, start.getY() - 1));
        deReturnat.add(board.getBox(start.getX() - 1, start.getY()));
        deReturnat.add(board.getBox(start.getX() - 1, start.getY() + 1));
        deReturnat.add(board.getBox(start.getX(), start.getY() - 1));
        deReturnat.add(board.getBox(start.getX(), start.getY()));
        deReturnat.add(board.getBox(start.getX(), start.getY() + 1));
        deReturnat.add(board.getBox(start.getX() + 1, start.getY() - 1));
        deReturnat.add(board.getBox(start.getX() + 1, start.getY()));
        deReturnat.add(board.getBox(start.getX() + 1, start.getY() + 1));
        return deReturnat;
    }
}
