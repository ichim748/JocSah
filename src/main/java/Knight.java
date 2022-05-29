import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(boolean white){
        super(white, false);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(600,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(600,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
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
        System.out.println(tempX +" "+ tempY);
        if(tempY>=2){
            if(tempX>=2){
                if(board.getBox(tempY-1,tempX-2).getPiece()==null || board.getBox(tempY-1,tempX-2).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-1,tempX-2));
                }
                if(board.getBox(tempY-2,tempX-1).getPiece()==null || board.getBox(tempY-2,tempX-1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-2,tempX-1));
                }
            }
            else {
                if(tempX==1){
                    if(board.getBox(tempY-2,tempX-1).getPiece()==null || board.getBox(tempY-2,tempX-1).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY-2,tempX-1));
                    }
                }
            }
            if(tempX<=5){
                if(board.getBox(tempY-1,tempX+2).getPiece()==null || board.getBox(tempY-1,tempX+2).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-1,tempX+2));
                }
                if(board.getBox(tempY-2,tempX+1).getPiece()==null || board.getBox(tempY-2,tempX+1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-2,tempX+1));
                }
            }
            else{
                if(tempX==6){
                    if(board.getBox(tempY-2,tempX+1).getPiece()==null || board.getBox(tempY-2,tempX+1).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY-2,tempX+1));
                    }
                }
            }
        }
        else{
            if(tempY==1){
                if(tempX>=2){
                    if(board.getBox(tempY-1,tempX-2).getPiece()==null || board.getBox(tempY-1,tempX-2).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY-1,tempX-2));
                    }
                }
                if(tempX<=5){
                    System.out.println("ceva");
                    if(board.getBox(tempY-1,tempX+2).getPiece()==null || board.getBox(tempY-1,tempX+2).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY-1,tempX+2));
                        System.out.println("altceva");
                    }
                }
            }
        }
        if(tempY<=5){
            if(tempX>=2){
                if(board.getBox(tempY+1,tempX-2).getPiece()==null || board.getBox(tempY+1,tempX-2).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+1,tempX-2));
                }
                if(board.getBox(tempY+2,tempX-1).getPiece()==null || board.getBox(tempY+2,tempX-1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+2,tempX-1));
                }
            }
            else {
                if(tempX==1){
                    if(board.getBox(tempY+2,tempX-1).getPiece()==null || board.getBox(tempY+2,tempX-1).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY+2,tempX-1));
                    }
                }
            }
            if(tempX<=5){
                if(board.getBox(tempY+1,tempX+2).getPiece()==null || board.getBox(tempY+1,tempX+2).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+1,tempX+2));
                }
                if(board.getBox(tempY+2,tempX+1).getPiece()==null || board.getBox(tempY+2,tempX+1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+2,tempX+1));
                }
            }
            else{
                if(tempX==6){
                    if(board.getBox(tempY+2,tempX+1).getPiece()==null || board.getBox(tempY+2,tempX+1).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY+2,tempX+1));
                    }
                }
            }
        }
        else{
            if(tempY==6){
                if(tempX>=2){
                    if(board.getBox(tempY+1,tempX-2).getPiece()==null || board.getBox(tempY+1,tempX-2).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY+1,tempX-2));
                    }
                }
                if(tempX<=5){
                    if(board.getBox(tempY+1,tempX+2).getPiece()==null || board.getBox(tempY+1,tempX+2).getPiece().isWhite()!=this.isWhite()){
                        deReturnat.add(board.getBox(tempY+1,tempX+2));
                    }
                }
            }
        }
        return deReturnat;
    }
}
