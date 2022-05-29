import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private boolean isKilled = false;
    private boolean isWhite = false;
    private boolean isKing = false;
    private Image shape;

    public Piece(boolean isWhite){
        this.isWhite = isWhite;
    }
    public Piece(boolean isWhite, boolean isKing){
        this.isWhite = isWhite;
        this.isKing = isKing;
    }

    public boolean isWhite() {
        return isWhite;
    }
    public void setWhite(boolean white) {
        isWhite = white;
    }
    public boolean isKing() {
        return isKing;
    }
    public void setKing(boolean king) {
        isKing = king;
    }
    public Image getShape() {return shape; }
    public void setShape(Image shape) {this.shape = shape; }

    public abstract List<Square> possibleMoves(Board board, Square box) throws Exception;

    public boolean validMove(Board board, Square start, Square end) throws Exception {
        if (start.equals(end) || !this.possibleMoves(board, start).contains(end))
            return false;
        Piece temp = null;
        if (end.getPiece() != null){
            temp = end.getPiece();
        }
        end.setPiece(start.getPiece());
        start.setPiece(null);

        Square regeleMeu = null;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.getBoard()[i][j].getPiece()!=null && board.getBoard()[i][j].getPiece().isKing() && board.getBoard()[i][j].getPiece().isWhite() == this.isWhite()){
                    regeleMeu = board.getBox(i, j);
                    break;
                }
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.getBoard()[i][j].getPiece() != null && board.getBoard()[i][j].getPiece().isWhite() != this.isWhite()){
                    for (Square t : board.getBoard()[i][j].getPiece().possibleMoves(board, board.getBox(i, j))){
                        if (t.equals(regeleMeu)) {
                            start.setPiece(end.getPiece());
                            end.setPiece(null);
                            if (temp != null){
                                end.setPiece(temp);
                            }
                            return false;
                        }
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
}
