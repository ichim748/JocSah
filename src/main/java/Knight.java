import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(boolean white){
        super(white, false);
    }

    @Override
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
        if (box.getY() >= 2){
            if (box.getX() >= 1 && (board.getBox(box.getX() - 1, box.getY()-2).getPiece() == null || board.getBox(box.getX() - 1, box.getY()-2).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() - 1, box.getY()-2));
            }
            if (box.getX() <= 6 && (board.getBox(box.getX() + 1, box.getY()-2).getPiece() == null || board.getBox(box.getX() + 1, box.getY()-2).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() + 1, box.getY()-2));
            }
        }
        if (box.getY() >= 1){
            if (box.getX() >= 2 && (board.getBox(box.getX() - 2, box.getY()-1).getPiece() == null || board.getBox(box.getX() - 2, box.getY()-1).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() - 2, box.getY()-1));
            }
            if (box.getX() <= 5 && (board.getBox(box.getX() + 1, box.getY()-1).getPiece() == null || board.getBox(box.getX() + 2, box.getY()-1).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() + 2, box.getY()-1));
            }
        }
        if (box.getY() <= 5){
            if (box.getX() >= 1 && (board.getBox(box.getX() - 1, box.getY()+2).getPiece() == null || board.getBox(box.getX() - 1, box.getY()+2).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() - 1, box.getY()+2));
            }
            if (box.getX() <= 6 && (board.getBox(box.getX() + 1, box.getY()+2).getPiece() == null || board.getBox(box.getX() + 1, box.getY()+2).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() + 1, box.getY()+2));
            }
        }
        if (box.getY() <= 6){
            if (box.getX() >= 2 && (board.getBox(box.getX() - 2, box.getY()+1).getPiece() == null || board.getBox(box.getX() - 2, box.getY()+1).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() - 2, box.getY()+1));
            }
            if (box.getX() <= 5 && (board.getBox(box.getX() + 2, box.getY()+1).getPiece() == null || board.getBox(box.getX() +2, box.getY() + 1).getPiece().isWhite()!= this.isWhite())){
                deReturnat.add(board.getBox(box.getX() + 2, box.getY()+1));
            }
        }
        return deReturnat;
    }
}
