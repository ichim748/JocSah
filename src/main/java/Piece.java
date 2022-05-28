import java.awt.*;
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

    public abstract boolean validMove(Board board, Square start, Square end) throws Exception;
    public abstract List<Square> possibleMoves(Board board, Square box) throws Exception;
}
