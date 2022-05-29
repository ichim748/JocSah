import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DrawBoard {
    public DrawBoard(Board board) throws IOException {
        JFrame frame = new JFrame("Chess Board");
        frame.setSize(new Dimension(615,640));
        JDialog jd = new JDialog(frame);
        jd.setLayout(new FlowLayout());
        jd.setBounds(200,200,200,200);
        JLabel jLabel = new JLabel("Pawn promoted into: ");
        jd.add(jLabel);
        jd.setVisible(false);
        DrawPanel panel = new DrawPanel(board,jd);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
