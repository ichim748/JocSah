import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private boolean isCastled = false;
    private boolean hasBeenMoved = false;

    public King(boolean white) {
        super(white, true);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(0,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(0,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            super.setShape(shape);
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    public boolean isCastled() {
        return isCastled;
    }
    public void setCastled(boolean castled) {
        isCastled = castled;
    }

    /*@Override
    public boolean validMove(Board board, Square start, Square end) throws Exception {
        if (start.getPiece() != null && end.getPiece() != null && start.getPiece().isWhite() == end.getPiece().isWhite()){
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
    }*/

    @Override
    public List<Square> possibleMoves(Board board, Square start) throws Exception {
        List<Square> deReturnat = new ArrayList<>();
        if (start.getX() >= 1 && start.getY() >= 1 && (board.getBox(start.getY() - 1, start.getX() - 1).getPiece() == null || (board.getBox(start.getY() - 1, start.getX() - 1).getPiece() != null && board.getBox(start.getY() - 1, start.getX() - 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() - 1, start.getX() - 1));
        if (start.getX() >= 1 && (board.getBox(start.getY(), start.getX() - 1).getPiece() == null || (board.getBox(start.getY(), start.getX() - 1).getPiece() != null && board.getBox(start.getY(), start.getX() - 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY(), start.getX() - 1));
        if (start.getX() >= 1 && start.getY() <= 6 && (board.getBox(start.getY() + 1, start.getX() - 1).getPiece() == null || (board.getBox(start.getY() + 1, start.getX() - 1).getPiece() != null && board.getBox(start.getY() + 1, start.getX() - 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() + 1, start.getX() - 1));
        if (start.getY() >= 1 && (board.getBox(start.getY() - 1, start.getX()).getPiece() == null || (board.getBox(start.getY() - 1, start.getX()).getPiece() != null && board.getBox(start.getY() - 1, start.getX()).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() - 1, start.getX()));
        if (start.getY() <= 6  && (board.getBox(start.getY() + 1, start.getX()).getPiece() == null || (board.getBox(start.getY() + 1, start.getX()).getPiece() != null && board.getBox(start.getY() + 1, start.getX()).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() + 1, start.getX()));
        if (start.getX() <= 6 && start.getY() >= 1 && (board.getBox(start.getY() - 1, start.getX() + 1).getPiece() == null || (board.getBox(start.getY() - 1, start.getX() + 1).getPiece() != null && board.getBox(start.getY() - 1, start.getX() + 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() - 1, start.getX() + 1));
        if (start.getX() <= 6  && (board.getBox(start.getY(), start.getX() + 1).getPiece() == null || (board.getBox(start.getY(), start.getX() + 1).getPiece() != null && board.getBox(start.getY(), start.getX() + 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY(), start.getX() + 1));
        if (start.getX() <= 6 && start.getY() <= 6  && (board.getBox(start.getY() + 1, start.getX() + 1).getPiece() == null || (board.getBox(start.getY() + 1, start.getX() + 1).getPiece() != null && board.getBox(start.getY() + 1, start.getX() + 1).getPiece().isWhite()!=this.isWhite())))
            deReturnat.add(board.getBox(start.getY() + 1, start.getX() + 1));
        if (!this.hasBeenMoved){
            //daca e regele alb
            if (this.isWhite()){
                if (board.getBox(7, 0).getPiece()!=null && board.getBox(7,0).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(7, 0).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(7,0).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(7,1).getPiece() == null && board.getBox(7,2).getPiece() == null && board.getBox(7,3).getPiece() == null){
                        deReturnat.add(board.getBox(start.getY(), start.getX()-2));
                    }
                }
                if (board.getBox(7, 7).getPiece()!=null && board.getBox(7,7).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(7, 7).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(7,7).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(7,6).getPiece() == null && board.getBox(7,5).getPiece() == null){
                        deReturnat.add(board.getBox(start.getY(), start.getX()+2));
                    }
                }
            }
            //daca e regele negru
            else{
                if (board.getBox(0, 0).getPiece()!=null && board.getBox(0,0).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(0, 0).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(0,0).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(0,1).getPiece() == null && board.getBox(0,2).getPiece() == null && board.getBox(0,3).getPiece() == null){
                        deReturnat.add(board.getBox(start.getY(), start.getX()-2));
                    }
                }
                if (board.getBox(0, 7).getPiece()!=null &&board.getBox(0,7).getPiece().getClass().getSimpleName().equals("Rook") && board.getBox(0, 7).getPiece().isWhite()==this.isWhite()){
                    Rook temp = (Rook) board.getBox(0,7).getPiece();
                    if (!temp.hasBeenMoved() && board.getBox(0,6).getPiece() == null && board.getBox(0,5).getPiece() == null){
                        deReturnat.add(board.getBox(start.getY(), start.getX()+2));
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
