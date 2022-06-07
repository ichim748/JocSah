import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer extends Player {
    private Board board;
    private List<Square> myPieces = new ArrayList<>();
    private List<Square> enemyPieces = new ArrayList<>();
    private static int[][] pawnPositions;
    private static int[][] horsePositions;
    private static int[][] rookPositions;
    private Square maximStart;
    private Square maximEnd;
    static {
        pawnPositions = new int[][]{
                {-1000, -1000, -1000, -1000, -1000, -1000, -1000, -1000},
                {-10, -10, -10, -10, -10, -10, -10, -10},
                {10, 10, 10, 10, 10, 10, 10, 10},
                {10, 10, 10, 10, 10, 10, 10, 10},
                {15, 15, 15, 15, 15, 15, 15, 15},
                {20, 20, 20, 20, 20, 20, 20, 20},
                {30, 30, 30, 30, 30, 30, 30, 30},
                {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
        };
        horsePositions = new int[][]{
                {-10, -10, -10, -10, -10, -10, -10, -10},
                {-10, -5, -5, -5, -5, -5, -5, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, 0, 10, 10, 10, 10, 0, -10},
                {-10, 0, 20, 30, 40, 30, 20, -10},
                {-10, 0, 10, 10, 10, 10, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, -10, -10, -10, -10, -10, -10, -10},
        };
        rookPositions = new int[][]{
                {-10, -10, -10, -10, -10, -10, -10, -10},
                {-10, -5, -5, -5, -5, -5, -5, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, 0, 10, 10, 10, 10, 0, -10},
                {-10, 0, 20, 30, 40, 30, 20, -10},
                {-10, 0, 10, 10, 10, 10, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, -10, -10, -10, -10, -10, -10, -10},
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
            for(Square j : i.getPiece().possibleMoves(board, i)){
                if (i.getPiece().validMove(board, i, j)){
                    //se face mutarea
                    Piece temp = null;
                    if (j.getPiece() != null){
                        temp = j.getPiece();
                    }
                    //daca e rege/rook trb marcat faptul ca a fost mutat
                    if (i.getPiece().isKing()){
                        King temp2 = (King) i.getPiece();
                        temp2.setHasBeenMoved(true);
                    }
                    else if(i.getPiece().getClass().getSimpleName().equals("Rook")){
                        Rook temp2 = (Rook) i.getPiece();
                        temp2.setHasBeenMoved(true);
                    }

                    j.setPiece(i.getPiece());
                    i.setPiece(null);

                    int score = mini( depth - 1 );
                    if( score > maxim ) {
                        maxim = score;
                        maximStart = i;
                        maximEnd = j;
                    }
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
                    if (temp != null){
                        j.setPiece(temp);
                    }
                }
            }
        }
        if (depth == 5) {
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
            }
        }
        return maxim;
    }

    int mini( int depth ) throws Exception {
        if ( depth == 0 ) {
            return (-1) * evaluate();
        }
        int minim = Integer.MAX_VALUE;
        for (Square i : enemyPieces) {
            if (i.getPiece()!=null) {
                for (Square j : i.getPiece().possibleMoves(board, i)) {
                    if (j.getPiece()!=null && i.getPiece().validMove(board, i, j)) {
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
                            totalValue += 10;
                        }
                        else {
                            //totalValue += (-1) * pawnPositions[i.getY()][i.getX()];
                            totalValue -= 10;
                        }
                        break;
                    case "Knight":
                        if (side == this.isWhite) {
                            totalValue += horsePositions[i.getY()][i.getX()];
                            totalValue += 30;
                        }
                        else {
                            //totalValue += (-1) * horsePositions[i.getY()][i.getX()];
                            totalValue -= 30;
                        }
                        break;
                    case "Bishop":
                        if (side == this.isWhite) {
                            totalValue += 30;
                        }
                        else {
                            totalValue -= 30;
                        }
                        break;
                    case "Rook":
                        if (side == this.isWhite) {
                            totalValue += rookPositions[i.getY()][i.getX()];
                            totalValue += 50;
                        }
                        else {
                            //totalValue += (-1) * rookPositions[i.getY()][i.getX()];
                            totalValue -= 50;
                        }
                        break;
                    case "Queen":
                        if (side == this.isWhite) {
                            totalValue += 90;
                        }
                        else {
                            totalValue -= 90;
                        }
                        break;
                    case "King":
                        if (side == this.isWhite) {
                            totalValue += 900;
                        }
                        else {
                            totalValue -= 900;
                        }
                        break;
                }
            }
        }
        return totalValue;
    }
}