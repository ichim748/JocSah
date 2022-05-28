import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(boolean white){
        super(white, false);
        Image shape;
        try {
            BufferedImage image = ImageIO.read(new File("ChessPieces.png"));
            if(white){
                shape = image.getSubimage(200,0,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
            }
            else {
                shape = image.getSubimage(200,200,200,200).getScaledInstance(70,70,BufferedImage.SCALE_SMOOTH);
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
        int tempX = box.getX();
        int tempY = box.getY();
        tempX--;
        tempY--;
        while (tempX >= 0 && tempY >= 0){
            if (board.getBox(tempX, tempY).getPiece() != null){
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempX--;
            tempY--;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        tempY--;
        while (tempY >= 0 && tempX <= 7){
            if (board.getBox(tempX, tempY).getPiece() != null){
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempY--;
            tempX++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX--;
        tempY++;
        while (tempX >= 0 && tempY <= 7){
            if (board.getBox(tempX, tempY).getPiece() != null){
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempX--;
            tempY++;
        }
        tempX = box.getX();
        tempY = box.getY();
        tempX++;
        tempY++;
        while (tempX <= 7 && tempY <= 7){
            if (board.getBox(tempX, tempY).getPiece() != null){
                if (board.getBox(tempX, tempY).getPiece().isWhite() != this.isWhite()){
                    deReturnat.add(board.getBox(tempX, tempY));
                }
                break;
            }
            deReturnat.add(board.getBox(tempX, tempY));
            tempX++;
            tempY++;
        }

        tempX = box.getX();
        tempY = box.getY();
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

        return deReturnat;
    }
}
