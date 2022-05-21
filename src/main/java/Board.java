import javax.xml.crypto.dsig.keyinfo.KeyName;

public class Board {
    Square[][] board;

    public Board(){
        this.resetBoard();
    }

    public Square getBox(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }
        return board[x][y];
    }
    public void resetBoard(){
        board[0][0] = new Square(0, 0, new Rook(true));
        board[0][1] = new Square(0, 1, new Knight(true));
        board[0][2] = new Square(0, 2, new Bishop(true));
        board[0][3] = new Square(0, 3, new Queen(true));
        board[0][4] = new Square(0, 4, new King(true));
        board[0][5] = new Square(0, 5, new Bishop(true));
        board[0][6] = new Square(0, 6, new Knight(true));
        board[0][7] = new Square(0, 7, new Rook(true));

        board[7][0] = new Square(7, 0, new Rook(false));
        board[7][1] = new Square(7, 1, new Knight(false));
        board[7][2] = new Square(7, 2, new Bishop(false));
        board[7][3] = new Square(7, 3, new Queen(false));
        board[7][4] = new Square(7, 4, new King(false));
        board[7][5] = new Square(7, 5, new Bishop(false));
        board[7][6] = new Square(7, 6, new Knight(false));
        board[7][7] = new Square(7, 7, new Rook(false));

        for (int i = 0; i < 8; i++){
            board[1][i] = new Square(1, i, new Pawn(true));
        }
        for (int i = 0; i < 8; i++){
            board[6][i] = new Square(6, i, new Pawn(false));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j, null);
            }
        }
    }
    public Square[][] getBoard(){
        return this.board;
    }
}
