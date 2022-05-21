import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends Piece{

    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean validMove(Board board, Square start, Square end) throws Exception {
        if (start.equals(end) || (!Objects.equals(start.getX(), end.getX()) && !Objects.equals(start.getY(), end.getY()))){
            return false;
        }
        Piece temp = null;
        if (end.getPiece() != null){
            temp = end.getPiece();
        }
        end.setPiece(start.getPiece());
        start.setPiece(null);

        Square regeleMeu = null;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.getBoard()[i][j].getPiece().isKing() && board.getBoard()[i][j].getPiece().isWhite() == this.isWhite()){
                    regeleMeu = board.getBox(i, j);
                    break;
                }
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.getBoard()[i][j].getPiece() != null && board.getBoard()[i][j].getPiece().isWhite() != this.isWhite()){
                    for (Square t : board.getBoard()[i][j].getPiece().possibleMoves(board, board.getBox(i, j))){
                        if (t.equals(regeleMeu))
                            return false;
                    }
                }
            }
        }
        start.setPiece(end.getPiece());
        end.setPiece(null);
        if (temp != null){
            end.setPiece(temp);
        }
        return true;
    }

    @Override
    public List<Square> possibleMoves(Board board, Square box) throws Exception {
        List<Square> deReturnat = new ArrayList<>();
        int tempX = box.getX();
        int tempY = box.getY();
        tempX--;
        while (tempX >= 0){
            if (board.getBox(tempX, tempY).getPiece() != null){
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempX--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempY--;
        while (tempY >= 0){
            if (board.getBox(tempX, tempY).getPiece() != null){
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempY--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        while (tempX <= 7){
            if (board.getBox(tempX, tempY).getPiece() != null){
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempX++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempY++;
        while (tempY <= 7){
            if (board.getBox(tempX, tempY).getPiece() != null){
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempY++;
        }

        return deReturnat;
    }
}
