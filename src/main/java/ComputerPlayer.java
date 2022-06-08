

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComputerPlayer extends Player {
    private Board board;
    private List<Square> myPieces = new ArrayList<>();
    private List<Square> enemyPieces = new ArrayList<>();
    private static int[][] pawnPositions;
    private static int[][] horsePositions;
    private static int[][] rookPositions;
    private static int[][] bishopPositions;
    private static int[][] queenPositions;
    private static int[][] kingPositions;
    private Square maximStart;
    private Square maximEnd;
    static {
        pawnPositions = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {5, 10, 10, -20, -20, 10, 10, 5},
                {5, -50, -50, 0, 0, -50, -50, 5},
                {0, -20, -20, 20, 20, -20, -20, 0},
                {5, 5, 10, 25, 25, 10, 5, 5},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        horsePositions = new int[][]{
                {-50, -40, -30, -30, -30, -30, -40, -50},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-30, 5, 100, 150, 150, 100, 5, -30},
                {-30, 0, 150, 200, 200, 150, 0, -30},
                {-30, 0, 150, 200, 200, 150, 0, -30},
                {-30, 5, 100, 150, 150, 100, 5, -30},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-50, -40, -30, -30, -30, -30, -40, -50},
        };
        rookPositions = new int[][]{
                {0, 0, 0, 5, 5, 0, 0, 0},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {5, 10, 10, 10, 10, 10, 10, 5},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        bishopPositions = new int[][]{
                {-20, -10, -10, -10, -10, -10, -10, -20},
                {-10, 5, 0, 0, 0, 0, 5, -10},
                {-10, 10, 10, 10, 10, 10, 10, -10},
                {-10, 0, 10, 10, 10, 10, 0, -10},
                {-10, 5, 5, 10, 10, 5, 5, -10},
                {-10, 0, 5, 10, 10, 5, 0, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-20, -10, -10, -10, -10, -10, -10, -20},
        };
        queenPositions = new int[][]{
                {-20, -10, -10, -5, -5, -10, -10, -20},
                {-10, 0, 0, 0, 0, 5, 0, -10},
                {-10, 0, 5, 5, 5, 5, 5, -10},
                {-5, 0, 5, 5, 5, 5, 0, 0},
                {-5, 0, 5, 5, 5, 5, 5, -5},
                {-10, 0, 5, 5, 5, 5, 0, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-20, -10, -10, -5, -5, -10, -10, -20},
        };
        kingPositions = new int[][]{
                {20, 30, 10, 0, 0, 10, 30, 20},
                {20, 20, 0, 0, 0, 0, 20, 20},
                {-10, -20, -20, -20, -20, -20, -20, -10},
                {-20, -30, -30, -40, -40, -30, -30, -20},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
        };
    }

    public ComputerPlayer(boolean whiteSide, Board board) {
        this.isWhite = whiteSide;
        this.isHuman = false;
        this.board = board;
        for(Square[] i : board.getBoard()){
            for(Square j : i){
                if (j.getPiece()!=null && j.getPiece().isWhite() == this.isWhite){
                    myPieces.add(j);
                }
                else if (j.getPiece()!=null && j.getPiece().isWhite() != this.isWhite){
                    enemyPieces.add(j);
                }
            }
        }
    }

    int maxi( int depth ) throws Exception {
        if ( depth == 0 ) {
            return evaluate();
        }
        int maxim = Integer.MIN_VALUE;
        for (Square i : myPieces) {
            if (i.getPiece()!=null) {
                for (Square j : i.getPiece().possibleMoves(board, i)) {
                    if (i.getPiece().validMove(board, i, j)) {
                        //se face mutarea
                        Piece temp = null;
                        if (j.getPiece() != null) {
                            temp = j.getPiece();
                        }
                        //daca e rege/rook trb marcat faptul ca a fost mutat
                        if (i.getPiece().isKing()) {
                            King temp2 = (King) i.getPiece();
                            temp2.setHasBeenMoved(true);
                        } else if (i.getPiece().getClass().getSimpleName().equals("Rook")) {
                            Rook temp2 = (Rook) i.getPiece();
                            temp2.setHasBeenMoved(true);
                        }
                        j.setPiece(i.getPiece());
                        i.setPiece(null);

                        int score = mini(depth - 1);
                        if (score > maxim) {
                            maxim = score;
                            if(depth==3){
                                maximStart = i;
                                maximEnd = j;
                            }
                        }
                        //dam revert mutarii
                        i.setPiece(j.getPiece());
                        if (i.getPiece().isKing()) {
                            King temp2 = (King) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        } else if (i.getPiece().getClass().getSimpleName().equals("Rook")) {
                            Rook temp2 = (Rook) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        }
                        j.setPiece(null);
                        if (temp != null) {
                            j.setPiece(temp);
                        }
                    }
                }
            }
        }
        if (depth == 3) {
            if (maximEnd!=null && maximStart!=null && maximStart.getPiece()!=null) {
                if (maximStart.getPiece().isKing()){
                    King temp2 = (King) maximStart.getPiece();
                    temp2.setHasBeenMoved(true);
                }
                else if(maximStart.getPiece().getClass().getSimpleName().equals("Rook")){
                    Rook temp2 = (Rook) maximStart.getPiece();
                    temp2.setHasBeenMoved(true);
                }
                maximEnd.setPiece(maximStart.getPiece());
                maximStart.setPiece(null);
                if(maximEnd.getPiece().getClass().getSimpleName().equals("Pawn") ){
                    Pawn temp = (Pawn) maximEnd.getPiece();
                    temp.setMovedTwoSpaces(maximEnd.getY() == maximStart.getY() + 2 && Objects.equals(maximEnd.getX(), maximStart.getX()));
                }
                if(maximEnd.getPiece().getClass().getSimpleName().equals("Pawn") && maximStart.getY()==4 && ((board.getBoard()[maximStart.getY()][maximStart.getX()-1].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[maximStart.getY()][maximStart.getX()-1].getPiece().isWhite()!=this.isWhite) || (board.getBoard()[maximStart.getY()][maximStart.getX()+1].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[maximStart.getY()][maximStart.getX()+1].getPiece().isWhite()!=this.isWhite)) ){
                    board.getBoard()[maximEnd.getY()-1][maximEnd.getX()].setPiece(null);
                }
            }
        }
        return maxim;
    }

    int mini( int depth ) throws Exception {
        if ( depth == 0 ) {
            return evaluate();
        }
        int minim = Integer.MAX_VALUE;
        for (Square i : enemyPieces) {
            if (i.getPiece()!=null) {
                for (Square j : i.getPiece().possibleMoves(board, i)) {
                    if (i.getPiece().validMove(board, i, j) ) {
                        //se face mutarea
                        Piece temp = null;
                        if (j.getPiece() != null) {
                            temp = j.getPiece();
                        }
                        if (i.getPiece().isKing()){
                            King temp2 = (King) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        }
                        else if(i.getPiece().getClass().getSimpleName().equals("Rook")){
                            Rook temp2 = (Rook) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        }
                        j.setPiece(i.getPiece());
                        i.setPiece(null);

                        int score = maxi(depth - 1);
                        if (score < minim)
                            minim = score;

                        //dam revert mutarii
                        i.setPiece(j.getPiece());
                        if (i.getPiece().isKing()){
                            King temp2 = (King) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        }
                        else if(i.getPiece().getClass().getSimpleName().equals("Rook")){
                            Rook temp2 = (Rook) i.getPiece();
                            temp2.setHasBeenMoved(false);
                        }
                        j.setPiece(null);
                        if (temp != null) {
                            j.setPiece(temp);
                        }
                    }
                }
            }
        }
        return minim;
    }

    public int evaluate(){
        return playerValue(myPieces, isWhite) + playerValue(enemyPieces, !isWhite);
    }

    public int playerValue(List<Square> pieces, boolean side){
        int totalValue = 0;
        for (Square i : pieces){
            if(i.getPiece()!=null){
                switch (i.getPiece().getClass().getSimpleName()){
                    case "Pawn":
                        if (side == this.isWhite) {
                            totalValue += pawnPositions[i.getY()][i.getX()];
                            totalValue += 100;
                        }
                        else {
                            totalValue += (-1) * pawnPositions[i.getY()][i.getX()];
                            totalValue -= 100;
                        }
                        break;
                    case "Knight":
                        if (side == this.isWhite) {
                            totalValue += horsePositions[i.getY()][i.getX()];
                            totalValue += 300;
                        }
                        else {
                            totalValue += (-1) * horsePositions[i.getY()][i.getX()];
                            totalValue -= 300;
                        }
                        break;
                    case "Bishop":
                        if (side == this.isWhite) {
                            totalValue += bishopPositions[i.getY()][i.getX()];
                            totalValue += 300;
                        }
                        else {
                            totalValue -= bishopPositions[i.getY()][i.getX()];
                            totalValue -= 300;
                        }
                        break;
                    case "Rook":
                        if (side == this.isWhite) {
                            totalValue += rookPositions[i.getY()][i.getX()];
                            totalValue += 500;
                        }
                        else {
                            totalValue += (-1) * rookPositions[i.getY()][i.getX()];
                            totalValue -= 500;
                        }
                        break;
                    case "Queen":
                        if (side == this.isWhite) {
                            totalValue += 900;
                            totalValue += queenPositions[i.getY()][i.getX()];
                        }
                        else {
                            totalValue -= 900;
                            totalValue -= queenPositions[i.getY()][i.getX()];
                        }
                        break;
                    case "King":
                        if (side == this.isWhite) {
                            totalValue += 9000;
                            totalValue += kingPositions[i.getY()][i.getX()];
                        }
                        else {
                            totalValue -= 9000;
                            totalValue -= kingPositions[i.getY()][i.getX()];
                        }
                        break;
                }
            }
        }
        return totalValue;
    }
}