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
    public List<Square> possibleMoves(Board board, Square box) throws Exception {
        List<Square> deReturnat = new ArrayList<>();
        int tempX = box.getX();
        int tempY = box.getY();
        tempX--;
        while (tempX >= 0){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempX--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempY--;
        while (tempY >= 0){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempY--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        while (tempX <= 7){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempX++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempY++;
        while (tempY <= 7){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempY++;
        }

        if (!hasBeenMoved){
            if (isWhite()){
                //partea de sus
                if (box.getY() == 0){
                    if(board.getBox(0, 4).getPiece()!=null && board.getBox(0, 4).getPiece().isKing() && board.getBox(0, 4).getPiece().isWhite()==isWhite()){
                        King temp = (King) board.getBox(0, 4).getPiece();
                        if (!temp.hasBeenMoved()){
                            if (box.getX() == 0 && board.getBox(0, 1).getPiece() == null && board.getBox(0, 2).getPiece() == null && board.getBox(0, 3).getPiece() == null){
                                deReturnat.add(board.getBox(box.getY(), box.getX()+3));
                            }
                            if (box.getX() == 7 && board.getBox(0, 6).getPiece() == null && board.getBox(0, 5).getPiece() == null){
                                deReturnat.add(board.getBox(box.getY(), box.getX()-2));
                            }
                        }
                    }
                }
            }
            else{
                //partea de jos
                if (box.getY() == 7){
                    if(board.getBox(7, 4).getPiece()!=null && board.getBox(7, 4).getPiece().isKing() && board.getBox(7, 4).getPiece().isWhite()==isWhite()){
                        King temp = (King) board.getBox(7, 4).getPiece();
                        if (!temp.hasBeenMoved()){
                            if (box.getX() == 0 && board.getBox(7, 1).getPiece() == null && board.getBox(7, 2).getPiece() == null && board.getBox(7, 3).getPiece() == null){
                                deReturnat.add(board.getBox(box.getY(), box.getX()+3));
                            }
                            if (box.getX() == 7 && board.getBox(7, 6).getPiece() == null && board.getBox(7, 5).getPiece() == null){
                                deReturnat.add(board.getBox(box.getY(), box.getX()-2));
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
