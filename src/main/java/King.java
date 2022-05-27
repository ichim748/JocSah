import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private boolean isCastled = false;
    private boolean hasBeenMoved = false;

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
        if (start.getX() >= 1 && start.getY() >= 1)
            deReturnat.add(board.getBox(start.getX() - 1, start.getY() - 1));
        if (start.getX() >= 1)
            deReturnat.add(board.getBox(start.getX() - 1, start.getY()));
        if (start.getX() >= 1 && start.getY() <= 6)
            deReturnat.add(board.getBox(start.getX() - 1, start.getY() + 1));
        if (start.getY() >= 1)
            deReturnat.add(board.getBox(start.getX(), start.getY() - 1));
        if (start.getY() <= 6)
            deReturnat.add(board.getBox(start.getX(), start.getY() + 1));
        if (start.getX() <= 6 && start.getY() >= 1)
            deReturnat.add(board.getBox(start.getX() + 1, start.getY() - 1));
        if (start.getX() <= 6)
            deReturnat.add(board.getBox(start.getX() + 1, start.getY()));
        if (start.getX() <= 6 && start.getY() <= 6)
            deReturnat.add(board.getBox(start.getX() + 1, start.getY() + 1));
        if (!this.hasBeenMoved){
            //daca e regele alb
            if (this.isWhite()){
                if (board.getBox(0,7).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(0, 7).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(0,7).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(1,7).getPiece() == null && board.getBox(2,7).getPiece() == null && board.getBox(3,7).getPiece() == null){
                        deReturnat.add(board.getBox(start.getX()-3, start.getY()));
                    }
                }
                if (board.getBox(7,7).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(7, 7).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(7,7).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(6,7).getPiece() == null && board.getBox(5,7).getPiece() == null){
                        deReturnat.add(board.getBox(start.getX()+2, start.getY()));
                    }
                }
            }
            //daca e regele negru
            else{
                if (board.getBox(0,0).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(0, 0).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(0,0).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(1,0).getPiece() == null && board.getBox(2,0).getPiece() == null && board.getBox(3,0).getPiece() == null){
                        deReturnat.add(board.getBox(start.getX()-3, start.getY()));
                    }
                }
                if (board.getBox(7,0).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(7, 0).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(7,0).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(6,0).getPiece() == null && board.getBox(5,0).getPiece() == null){
                        deReturnat.add(board.getBox(start.getX()+2, start.getY()));
                    }
                }
            }
        }
        return deReturnat;
    }
    public boolean hasBeenMoved() {
        return hasBeenMoved;
    }
    public void setHasBeenMoved(boolean hasBeenMoved) {
        this.hasBeenMoved = hasBeenMoved;
    }
}
