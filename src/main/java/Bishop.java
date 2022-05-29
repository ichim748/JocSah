import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(boolean white){
        super(white, false);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(400,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(400,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
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
        tempY--;
        while (tempX >= 0 && tempY >= 0){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempX--;
            tempY--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        tempY--;
        while (tempY >= 0 && tempX <= 7){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempY--;
            tempX++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX--;
        tempY++;
        while (tempX >= 0 && tempY <= 7){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempX--;
            tempY++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        tempY++;
        while (tempX <= 7 && tempY <= 7){
            if (board.getBox(tempY, tempX).getPiece() != null){
                if (board.getBox(tempY, tempX).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempY, tempX));
                }
                break;
            }
            deReturnat.add(board.getBox(tempY, tempX));
            tempX++;
            tempY++;
        }
        return deReturnat;
    }
}
