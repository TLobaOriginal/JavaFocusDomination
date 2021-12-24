import engine.*;

import javax.swing.*;
import java.awt.*;

public class JFocus {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Focus Java Engine");
        jFrame.setSize(new Dimension(1600, 1500));
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        Board board = new Board();
        board.prettyPrint();
    }
}
