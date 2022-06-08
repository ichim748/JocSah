import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class DrawAIPanel extends JPanel implements MouseListener {
    private Board board;
    private boolean isPressed;
    private boolean isMoved;
    private boolean choose;
    private int pressedX;
    private int pressedY;
    private int destX;
    private int destY;
    private JDialog jd;
    private Square pawnToGetPromoted;
    public DrawAIPanel(Board board, JDialog jd) throws IOException {
        this.board=board;
        this.isPressed=false;
        this.isMoved=false;
        this.choose=true;
        this.pressedX=0;
        this.pressedY=0;
        this.jd=jd;
        JButton queen = new JButton("Queen");
        JButton rook = new JButton("Rook");
        JButton bishop = new JButton("Bishop");
        JButton knight = new JButton("Knight");
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.getBoard()[pawnToGetPromoted.getY()][pawnToGetPromoted.getX()].setPiece(new Queen(pawnToGetPromoted.getPiece().isWhite()));
                repaint();
                jd.setVisible(false);
            }
        });
        rook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.getBoard()[pawnToGetPromoted.getY()][pawnToGetPromoted.getX()].setPiece(new Rook(pawnToGetPromoted.getPiece().isWhite()));
                repaint();
                jd.setVisible(false);
            }
        });
        bishop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.getBoard()[pawnToGetPromoted.getY()][pawnToGetPromoted.getX()].setPiece(new Bishop(pawnToGetPromoted.getPiece().isWhite()));
                repaint();
                jd.setVisible(false);
            }
        });
        knight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.getBoard()[pawnToGetPromoted.getY()][pawnToGetPromoted.getX()].setPiece(new Knight(pawnToGetPromoted.getPiece().isWhite()));
                repaint();
                jd.setVisible(false);
            }
        });
        jd.add(queen);
        jd.add(bishop);
        jd.add(rook);
        jd.add(knight);
        jd.setVisible(false);
        addMouseListener(this);
    }
    @Override
    public void paint(Graphics graphics){
        setPreferredSize(new Dimension(600,600));
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,600,600);
        try {
            drawBoard(graphics);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void drawBoard(Graphics graphics) throws Exception {
        boolean color = true;
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++){
                if(color){
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.LIGHT_GRAY);
                }
                graphics.fillRect(20+i*70,20+j*70,70,70);
                color=!color;
            }
            color=!color;
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board.getBoard()[i][j].getPiece()!=null){
                    graphics.drawImage(board.getBoard()[i][j].getPiece().getShape(),20+j*70,20+i*70,this);
                }
            }
        }
        if(isPressed){
            if(!isMoved){
                if(board.getBoard()[pressedY][pressedX].getPiece()!=null && board.getBoard()[pressedY][pressedX].getPiece().isWhite()==this.choose){
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(20+70*pressedX,20+70*pressedY,70,70);
                    graphics.drawImage(board.getBoard()[pressedY][pressedX].getPiece().getShape(),20+70*pressedX,20+70*pressedY,this);
                    for(Square square : board.getBoard()[pressedY][pressedX].getPiece().possibleMoves(board,board.getBoard()[pressedY][pressedX])){
                        if (board.getBoard()[pressedY][pressedX].getPiece().validMove(board, board.getBox(pressedY, pressedX), square))
                            graphics.fillRect(20+70*square.getX(),20+70*square.getY(),70,70);
                    }
                }
                else
                {
                    this.isPressed=false;
                }
            }
            else{
                if(board.getBoard()[pressedY][pressedX].getPiece() != null && board.getBoard()[pressedY][pressedX].getPiece().validMove(board,board.getBox(pressedY,pressedX),board.getBox(destY,destX)) && board.getBox(pressedY,pressedX).getPiece().possibleMoves(board,board.getBox(pressedY,pressedX)).contains(board.getBox(destY,destX))){
                    board.getBoard()[destY][destX].setPiece(board.getBoard()[pressedY][pressedX].getPiece());
                    board.getBoard()[pressedY][pressedX].setPiece(null);
                    graphics.drawImage(board.getBoard()[destY][destX].getPiece().getShape(),destX,destY,this);
                    if((pressedX+pressedY)%2==0)
                        graphics.setColor(Color.white);
                    else
                        graphics.setColor(Color.LIGHT_GRAY);
                    graphics.drawRect(pressedX,pressedY,70,70);
                    if(destY!=7 && board.getBoard()[destY][destX].getPiece()!=null && board.getBoard()[destY][destX].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[destY+1][destX].getPiece()!=null && board.getBoard()[destY+1][destX].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[destY+1][destX].getPiece().isWhite()!=board.getBoard()[destY][destX].getPiece().isWhite()){
                        if(destY == 2 && pressedX == destX + 1){
                            board.getBoard()[destY+1][destX].setPiece(null);
                        }
                        if(destY == 2 && pressedX == destX - 1){
                            board.getBoard()[destY+1][destX].setPiece(null);
                        }
                    }
                    if(destY!=0 && board.getBoard()[destY][destX].getPiece()!=null && board.getBoard()[destY][destX].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[destY-1][destX].getPiece()!=null && board.getBoard()[destY-1][destX].getPiece().getClass().getSimpleName().equals("Pawn") && board.getBoard()[destY-1][destX].getPiece().isWhite()!=board.getBoard()[destY][destX].getPiece().isWhite()){
                        if(destY == 5 && pressedX == destX + 1){
                            board.getBoard()[destY-1][destX].setPiece(null);
                        }
                        if(destY == 5 && pressedX == destX - 1){
                            board.getBoard()[destY-1][destX].setPiece(null);
                        }
                    }
                    if(board.getBoard()[destY][destX].getPiece().getClass().getSimpleName().equals("Rook")){
                        ((Rook) board.getBoard()[destY][destX].getPiece()).setHasBeenMoved(true);
                    }
                    if(board.getBoard()[destY][destX].getPiece().isKing()){
                        if(destY==pressedY){
                            if(destX==pressedX+2){
                                board.getBoard()[destY][destX-1].setPiece(new Rook(board.getBoard()[destY][destX].getPiece().isWhite()));
                                ((Rook) board.getBoard()[destY][destX-1].getPiece()).setHasBeenMoved(true);
                                board.getBoard()[destY][7].setPiece(null);
                            }
                            if(destX==pressedX-2){
                                board.getBoard()[destY][destX+1].setPiece(new Rook(board.getBoard()[destY][destX].getPiece().isWhite()));
                                ((Rook) board.getBoard()[destY][destX+1].getPiece()).setHasBeenMoved(true);
                                board.getBoard()[destY][0].setPiece(null);
                            }
                        }
                        ((King)board.getBoard()[destY][destX].getPiece()).setHasBeenMoved(true);
                    }
                    //Aici trebuie clasa pentru AI
                    //choose=!choose;
                    ComputerPlayer AI = new ComputerPlayer(!choose, board);
                    AI.maxi(3);
                    repaint();
                }
                isMoved = false;
                isPressed = false;
            }
            try {
                if (board.isGameEnded() == 1){
                    System.out.println("Alb a castigat!");
                }
                else if (board.isGameEnded() == 2){
                    System.out.println("Negru a castigat!");
                }
                else if(promotion()){
                    jd.setVisible(true);
                    ComputerPlayer AI = new ComputerPlayer(!choose, board);
                    AI.maxi(3);
                    repaint();
                }
                else if(pressedX == destX && destY == pressedY + 2 && destY == 3 && board.getBoard()[destY][destX].getPiece()!=null && board.getBoard()[destY][destX].getPiece().getClass().getSimpleName().equals("Pawn")){
                    Pawn temp = (Pawn) board.getBoard()[destY][destX].getPiece();
                    temp.setMovedTwoSpaces(true);
                }
                else if(pressedX == destX && destY == pressedY - 2 && destY == 4 && board.getBoard()[destY][destX].getPiece()!=null && board.getBoard()[destY][destX].getPiece().getClass().getSimpleName().equals("Pawn")){
                    Pawn temp = (Pawn) board.getBoard()[destY][destX].getPiece();
                    temp.setMovedTwoSpaces(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean promotion() throws Exception {
        for(int i=0;i<8;i++)
            if(board.getBox(0,i).getPiece()!=null && board.getBox(0,i).getPiece().getClass().getSimpleName().equals("Pawn")){
                pawnToGetPromoted = board.getBox(0,i);
                return true;
            }
        for(int i=0;i<8;i++)
            if(board.getBox(7,i).getPiece()!=null && board.getBox(7,i).getPiece().getClass().getSimpleName().equals("Pawn")){
                pawnToGetPromoted = board.getBox(7,i);
                return true;
            }
        return false;
    }
    @Override
    public void mousePressed(MouseEvent me){
        if(me.getX()>20 && me.getY()>20 && me.getX()<580 && me.getY()<580){
            if(!isPressed) {
                isPressed = true;
                pressedX = (me.getX() - 20) / 70;
                pressedY = (me.getY() - 20) / 70;
                repaint();
            }
            else {
                int x = (me.getX() - 20) / 70;
                int y = (me.getY() - 20) / 70;
                if(board.getBoard()[y][x].getPiece()!=null){
                    if(board.getBoard()[y][x].getPiece()!=null && board.getBoard()[pressedY][pressedX].getPiece()!=null && board.getBoard()[y][x].getPiece().isWhite()==board.getBoard()[pressedY][pressedX].getPiece().isWhite()){
                        isMoved=false;
                        pressedX=x;
                        pressedY=y;
                        repaint();
                    }
                    else{
                        isMoved = true;
                        destX = x;
                        destY = y;
                        repaint();
                        Robot robot = null;
                        try {
                            robot = new Robot();
                        } catch (Exception e){
                            System.out.println("error");
                        }
                        int mask = InputEvent.BUTTON1_DOWN_MASK;
                        Point mouseOnScreen = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove((int)mouseOnScreen.getX(),(int)mouseOnScreen.getY());
                        robot.mousePress(mask);
                        robot.mouseRelease(mask);
                        robot.mouseMove((int)mouseOnScreen.getX(),(int)mouseOnScreen.getY());
                    }
                }
                else{
                    isMoved = true;
                    destX = x;
                    destY = y;
                    repaint();
                    Robot robot = null;
                    try {
                        robot = new Robot();
                    } catch (Exception e){
                        System.out.println("error");
                    }
                    int mask = InputEvent.BUTTON1_DOWN_MASK;
                    Point mouseOnScreen = MouseInfo.getPointerInfo().getLocation();
                    robot.mouseMove((int)mouseOnScreen.getX(),(int)mouseOnScreen.getY());
                    robot.mousePress(mask);
                    robot.mouseRelease(mask);
                    robot.mouseMove((int)mouseOnScreen.getX(),(int)mouseOnScreen.getY());
                }
            }
        }
        else{
            isPressed=false;
            repaint();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

}
