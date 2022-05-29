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
                if (board.getBoard()[i][j].getPiece()!=null && board.getBoard()[i][j].getPiece().isKing() && board.getBoard()[i][j].getPiece().isWhite() == this.isWhite()){
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
        int tempX = box.getX();
        int tempY = box.getY();
        System.out.println(tempX + " " + tempY);
        if(this.isWhite()){
            if(tempY==6){
                if(board.getBox(tempY-2,tempX).getPiece()==null)
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
                if(board.getBox(tempY+2,tempX).getPiece()==null)
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
        System.out.println("Possible pawn moves: ");
        for(Square i : deReturnat){
            System.out.print(i.getX()+" ");
            System.out.println(i.getY());
        }
        return deReturnat;
    }

}
