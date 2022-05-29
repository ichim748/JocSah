import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.util.ArrayList;
import java.util.List;

public class Board {
    Square[][] board;

    public Board(){
        board = new Square[8][8];
        this.resetBoard();
    }

    public Square getBox(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }
        return board[x][y];
    }
    public void resetBoard(){
        board[0][0] = new Square(0, 0, new Rook(false));
        board[0][1] = new Square(1, 0, new Knight(false));
        board[0][2] = new Square(2, 0, new Bishop(false));
        board[0][3] = new Square(3, 0, new Queen(false));
        board[0][4] = new Square(4, 0, new King(false));
        board[0][5] = new Square(5, 0, new Bishop(false));
        board[0][6] = new Square(6, 0, new Knight(false));
        board[0][7] = new Square(7, 0, new Rook(false));

        board[7][0] = new Square(0, 7, new Rook(true));
        board[7][1] = new Square(1, 7, new Knight(true));
        board[7][2] = new Square(2, 7, new Bishop(true));
        board[7][3] = new Square(3, 7, new Queen(true));
        board[7][4] = new Square(4, 7, new King(true));
        board[7][5] = new Square(5, 7, new Bishop(true));
        board[7][6] = new Square(6, 7, new Knight(true));
        board[7][7] = new Square(7, 7, new Rook(true));

        for (int i = 0; i < 8; i++){
            board[1][i] = new Square(i, 1, new Pawn(false));
        }
        for (int i = 0; i < 8; i++){
            board[6][i] = new Square(i, 6, new Pawn(true));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(j, i, null);
            }
        }
    }
    // 0 - nu e terminat
    // 1 - alb a castigat
    // 2 - negru a castigat
    public Integer isGameEnded() throws Exception {
        Square regeleAlb = null;
        Square regeleNegru = null;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (this.board[i][j].getPiece() != null && this.board[i][j].getPiece().isKing() && this.board[i][j].getPiece().isWhite()){
                    regeleAlb = this.board[i][j];
                }
                if (this.board[i][j].getPiece() != null && this.board[i][j].getPiece().isKing() && !this.board[i][j].getPiece().isWhite()){
                    regeleNegru = this.board[i][j];
                }
                if (regeleAlb!=null && regeleNegru!=null)
                    break;
            }
        }
        List<Square> listaPosibile = new ArrayList<>();
        List<Square> listaValide = new ArrayList<>();
        listaPosibile = regeleAlb.getPiece().possibleMoves(this, regeleAlb);
        if (!listaPosibile.isEmpty()){
            for(Square i : listaPosibile){
                if (regeleAlb.getPiece().validMove(this, regeleAlb, i)){
                    listaValide.add(i);
                }
            }
        }
        System.out.println("Mutari rege alb: ");
        System.out.println(listaPosibile.toString());
        System.out.println(listaValide.toString());
        if (listaValide.isEmpty() && !listaPosibile.isEmpty()) {
            return 2;
        }
        List<Square> listaPosibile1 = new ArrayList<>();
        List<Square> listaValide1 = new ArrayList<>();
        listaPosibile1 = regeleNegru.getPiece().possibleMoves(this, regeleNegru);
        if (!listaPosibile1.isEmpty()){
            for(Square i : listaPosibile1){
                if (regeleNegru.getPiece().validMove(this, regeleNegru, i)){
                    listaValide1.add(i);
                }
            }
        }
        System.out.println("Mutari rege negru: ");
        System.out.println(listaPosibile1.toString());
        System.out.println(listaValide1.toString());
        if (listaValide1.isEmpty() && !listaPosibile1.isEmpty())
            return 1;
        return 0;
    }
    public Square[][] getBoard(){
        return this.board;
    }
}
