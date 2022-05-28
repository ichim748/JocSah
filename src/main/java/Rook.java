import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends Piece{
    private boolean hasBeenMoved = false;

    public Rook(boolean white){
        super(white);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(800,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(800,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            super.setShape(shape);
        } catch (IOException e){
            System.out.println("Error");
        }
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
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
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
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
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
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
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
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempY++;
        }

        if (!hasBeenMoved){
            if (isWhite()){
                //partea de sus
                if (box.getY() == 0){
                    if(board.getBox(4, 0).getPiece().isKing() && board.getBox(4, 0).getPiece().isWhite()==isWhite()){
                        King temp = (King) board.getBox(4, 0).getPiece();
                        if (!temp.hasBeenMoved()){
                            if (box.getX() == 0 && board.getBox(1, 0).getPiece() == null && board.getBox(2, 0).getPiece() == null && board.getBox(3, 0).getPiece() == null){
                                deReturnat.add(board.getBox(box.getX()+3, box.getY()));
                            }
                            if (box.getX() == 7 && board.getBox(6, 0).getPiece() == null && board.getBox(5, 0).getPiece() == null){
                                deReturnat.add(board.getBox(box.getX()-2, box.getY()));
                            }
                        }
                    }
                }
            }
            else{
                //partea de jos
                if (box.getY() == 7){
                    if(board.getBox(4, 7).getPiece().isKing() && board.getBox(4, 7).getPiece().isWhite()==isWhite()){
                        King temp = (King) board.getBox(4, 7).getPiece();
                        if (!temp.hasBeenMoved()){
                            if (box.getX() == 0 && board.getBox(1, 7).getPiece() == null && board.getBox(2, 7).getPiece() == null && board.getBox(3, 7).getPiece() == null){
                                deReturnat.add(board.getBox(box.getX()+3, box.getY()));
                            }
                            if (box.getX() == 7 && board.getBox(6, 7).getPiece() == null && board.getBox(5, 7).getPiece() == null){
                                deReturnat.add(board.getBox(box.getX()-2, box.getY()));
                            }
                        }
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
