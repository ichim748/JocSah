import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DrawPanel extends JPanel implements MouseListener {
    private Board board;
    private boolean isPressed;
    private boolean isMoved;
    private int pressedX;
    private int pressedY;
    private int destX;
    private int destY;
    public DrawPanel(Board board) throws IOException {
        this.board=board;
        this.isPressed=false;
        this.isMoved=false;
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
                if(board.getBoard()[pressedY][pressedX].getPiece()!=null){
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(20+70*pressedX,20+70*pressedY,70,70);
                    graphics.drawImage(board.getBoard()[pressedY][pressedX].getPiece().getShape(),20+70*pressedX,20+70*pressedY,this);
                    for(Square square : board.getBoard()[pressedY][pressedX].getPiece().possibleMoves(board,board.getBox(pressedY,pressedX))){
                        graphics.fillRect(20+70*square.getY(),20+70*square.getX(),70,70);
                    }
                }
                else
                {
                    this.isPressed=false;
                }
            }
            else{
                if(board.getBoard()[pressedY][pressedX].getPiece().validMove(board,board.getBox(pressedY,pressedX),board.getBox(destY,destX)) && board.getBox(pressedY,pressedX).getPiece().possibleMoves(board,board.getBox(pressedY,pressedX)).contains(board.getBox(destY,destX))){
                    System.out.println("mesaj");
                    board.getBoard()[destY][destX].setPiece(board.getBoard()[pressedY][pressedX].getPiece());
                    board.getBoard()[pressedY][pressedX].setPiece(null);
                    graphics.drawImage(board.getBoard()[destY][destX].getPiece().getShape(),destX,destY,this);
                    if((pressedX+pressedY)%2==0)
                        graphics.setColor(Color.white);
                    else
                        graphics.setColor(Color.LIGHT_GRAY);
                    graphics.drawRect(pressedX,pressedY,70,70);
                }
                System.out.println("ajung");
                isMoved = false;
                isPressed = false;
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent me){
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
                    System.out.println("pas1");
                    isMoved=false;
                    pressedX=x;
                    pressedY=y;
                    repaint();
                }
                else{
                    System.out.println("pas2");
                    isMoved = true;
                    destX = x;
                    destY = y;
                    repaint();
                }
            }
            else{
                System.out.println("pas3");
                isMoved = true;
                destX = x;
                destY = y;
                repaint();
            }
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
