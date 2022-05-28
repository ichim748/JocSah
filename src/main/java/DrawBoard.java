import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DrawBoard {
    public DrawBoard(Board board) throws IOException {
        JFrame frame = new JFrame("Chess Board");
        frame.setSize(new Dimension(615,640));
        DrawPanel panel = new DrawPanel(board);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
