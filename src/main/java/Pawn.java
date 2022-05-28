import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    public Pawn(boolean white){
        super(white, false);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(1000,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(1000,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            super.setShape(shape);
        } catch (IOException e){
            System.out.println("Error");
        }
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
        //Daca e pe casuta de start => poate merge 2 casute
        if (this.isWhite()){
            if (box.getY() == 6){
                deReturnat.add(board.getBox(box.getX(), box.getY()-2));
            }
            if (box.getY() >= 1){
                deReturnat.add(board.getBox(box.getX(), box.getY()-1));
                if (box.getX() >= 1 && (board.getBox(box.getX()-1, box.getY()-1).getPiece() != null || (board.getBox(box.getX()-1, box.getY()).getPiece().getClass().getSimpleName().equals("Pawn") && board.getBox(box.getX()-1, box.getY()).getPiece().isWhite() != this.isWhite() && box.getY() == 4))){
                    deReturnat.add(board.getBox(box.getX()-1, box.getY()+1));
                }
                if (box.getX() <= 6 && (board.getBox(box.getX()+1, box.getY()-1).getPiece() != null || (board.getBox(box.getX()+1, box.getY()).getPiece().getClass().getSimpleName().equals("Pawn") && board.getBox(box.getX()+1, box.getY()).getPiece().isWhite() != this.isWhite() && box.getY() == 4))){
                    deReturnat.add(board.getBox(box.getX()+1, box.getY()+1));
                }
            }
        }
        else{
            if (box.getY() == 1){
                deReturnat.add(board.getBox(box.getX(), box.getY()+2));
            }
            if (box.getY() <= 6){
                deReturnat.add(board.getBox(box.getX(), box.getY()+1));
                if (box.getX() >= 1 && (board.getBox(box.getX()-1, box.getY()+1).getPiece() != null || (board.getBox(box.getX()-1, box.getY()).getPiece().getClass().getSimpleName().equals("Pawn") && board.getBox(box.getX()-1, box.getY()).getPiece().isWhite() != this.isWhite() && box.getY() == 3))){
                    deReturnat.add(board.getBox(box.getX()-1, box.getY()+1));
                }
                if (box.getX() <= 6 && (board.getBox(box.getX()+1, box.getY()+1).getPiece() != null || (board.getBox(box.getX()+1, box.getY()).getPiece().getClass().getSimpleName().equals("Pawn") && board.getBox(box.getX()+1, box.getY()).getPiece().isWhite() != this.isWhite() && box.getY() == 3))){
                    deReturnat.add(board.getBox(box.getX()+1, box.getY()+1));
                }
            }
        }
        return deReturnat;
    }

}
