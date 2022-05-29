import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DrawPanel extends JPanel implements MouseListener {
    private Board board;
    private boolean isPressed;
    private boolean isMoved;
    private boolean choose;
    private int pressedX;
    private int pressedY;
    private int destX;
    private int destY;
    public DrawPanel(Board board) throws IOException {
        this.board=board;
        this.isPressed=false;
        this.isMoved=false;
        this.choose=true;
        this.pressedX=0;
        this.pressedY=0;
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
                if(board.getBoard()[pressedY][pressedX].getPiece().validMove(board,board.getBox(pressedY,pressedX),board.getBox(destY,destX)) && board.getBox(pressedY,pressedX).getPiece().possibleMoves(board,board.getBox(pressedY,pressedX)).contains(board.getBox(destY,destX))){
                    board.getBoard()[destY][destX].setPiece(board.getBoard()[pressedY][pressedX].getPiece());
                    board.getBoard()[pressedY][pressedX].setPiece(null);
                    graphics.drawImage(board.getBoard()[destY][destX].getPiece().getShape(),destX,destY,this);
                    if((pressedX+pressedY)%2==0)
                        graphics.setColor(Color.white);
                    else
                        graphics.setColor(Color.LIGHT_GRAY);
                    graphics.drawRect(pressedX,pressedY,70,70);
                    choose=!choose;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                    if(board.getBoard()[y][x].getPiece().isWhite()==board.getBoard()[pressedY][pressedX].getPiece().isWhite()){
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
                        robot.mouseMove(21,300);
                        robot.mousePress(mask);
                        robot.mouseRelease(mask);
                        robot.mouseMove(me.getX(), me.getY());
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
                    robot.mouseMove(21,300);
                    robot.mousePress(mask);
                    robot.mouseRelease(mask);
                    robot.mouseMove(me.getX(), me.getY());
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
