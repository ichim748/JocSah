import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DrawBoard {
    public DrawBoard(Board board) throws IOException {
        JFrame frame = new JFrame("Chess Board");
        frame.setSize(new Dimension(615,640));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLayout(null);
        createMenu(frame,board);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void createMenu(JFrame frame,Board board){
        frame.getContentPane().setBackground(Color.pink);
        JLabel title = new JLabel("Chess",JLabel.CENTER);
        title.setFont(new Font("Ceva",Font.BOLD,60));
        title.setBounds(200,20,200,150);
        frame.add(title);
        JButton playLocal = new JButton("Play Local");
        JButton playVsAI = new JButton("Play Vs AI");
        JButton exit = new JButton("Exit");
        playLocal.setLayout(null);
        playVsAI.setLayout(null);
        exit.setLayout(null);
        playLocal.setForeground(Color.black);
        playVsAI.setForeground(Color.black);
        exit.setForeground(Color.black);
        playLocal.setBackground(Color.orange);
        playVsAI.setBackground(Color.orange);
        exit.setBackground(Color.orange);
        playLocal.setFont(new Font("Ceva",Font.BOLD,24));
        playVsAI.setFont(new Font("Ceva",Font.BOLD,24));
        exit.setFont(new Font("Ceva",Font.BOLD,24));
        playLocal.setBounds(210,220,180,60);
        playVsAI.setBounds(210,320,180,60);
        exit.setBounds(210,420,180,60);
        playLocal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog(frame);
                jd.setLayout(new FlowLayout());
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                jd.setBounds(dim.width/2,dim.height/2,200,200);
                JLabel jLabel = new JLabel("Pawn promoted into: ");
                jd.add(jLabel);
                jd.setVisible(false);
                JDialog finishGame = new JDialog(frame);
                finishGame.setLayout(new FlowLayout());
                finishGame.setBounds(dim.width/2,dim.height/2,150,150);
                finishGame.setVisible(false);
                DrawPanel panel = null;
                try {
                    panel = new DrawPanel(board,jd,finishGame,frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.remove(playLocal);
                frame.remove(playVsAI);
                frame.remove(exit);
                frame.remove(title);
                frame.setLayout(new BorderLayout());
                frame.add(panel,BorderLayout.CENTER);
                frame.validate();
                frame.repaint();
            }
        });
        playVsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog(frame);
                jd.setLayout(new FlowLayout());
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                jd.setBounds(dim.width/2,dim.height/2,200,200);
                JLabel jLabel = new JLabel("Pawn promoted into: ");
                jd.add(jLabel);
                jd.setVisible(false);
                JDialog finishGame = new JDialog(frame);
                finishGame.setLayout(new FlowLayout());
                finishGame.setBounds(dim.width/2,dim.height/2,150,150);
                finishGame.setVisible(false);
                DrawAIPanel panel = null;
                try {
                    panel = new DrawAIPanel(board,jd,finishGame,frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.remove(playLocal);
                frame.remove(playVsAI);
                frame.remove(exit);
                frame.remove(title);
                frame.setLayout(new BorderLayout());
                frame.add(panel,BorderLayout.CENTER);
                frame.validate();
                frame.repaint();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(playLocal);
        frame.add(playVsAI);
        frame.add(exit);
        frame.revalidate();
        frame.repaint();
    }
}
