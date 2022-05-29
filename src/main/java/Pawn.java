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
    public List<Square> possibleMoves(Board board, Square box) throws Exception {
        List<Square> deReturnat = new ArrayList<>();
        //Daca e pe casuta de start => poate merge 2 casute
        int tempX = box.getX();
        int tempY = box.getY();
        System.out.println(tempX + " " + tempY);
        if(this.isWhite()){
            if(tempY==6){
                if(board.getBox(tempY-2,tempX).getPiece()==null && board.getBox(tempY-1,tempX).getPiece()==null)
                    deReturnat.add(board.getBox(tempY-2,tempX));
            }
            if(tempY>=1){
                if(board.getBox(tempY-1,tempX).getPiece()==null){
                    deReturnat.add(board.getBox(tempY-1,tempX));
                }
                if(tempX!=0 && board.getBox(tempY-1,tempX-1).getPiece()!=null && board.getBox(tempY-1,tempX-1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-1,tempX-1));
                }
                if(tempX!=7 && board.getBox(tempY-1,tempX+1).getPiece()!=null && board.getBox(tempY-1,tempX+1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY-1,tempX+1));
                }
            }
        }
        else{
            if(tempY==1){
                if(board.getBox(tempY+2,tempX).getPiece()==null && board.getBox(tempY+1,tempX).getPiece()==null)
                    deReturnat.add(board.getBox(tempY+2,tempX));
            }
            if(tempY<=6){
                if(board.getBox(tempY+1,tempX).getPiece()==null){
                    deReturnat.add(board.getBox(tempY+1,tempX));
                }
                if(tempX!=0 && board.getBox(tempY+1,tempX-1).getPiece()!=null && board.getBox(tempY+1,tempX-1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+1,tempX-1));
                }
                if(tempX!=7 && board.getBox(tempY+1,tempX+1).getPiece()!=null && board.getBox(tempY+1,tempX+1).getPiece().isWhite()!=this.isWhite()){
                    deReturnat.add(board.getBox(tempY+1,tempX+1));
                }
            }
        }
        return deReturnat;
    }

}
