import javax.swing.*;


public class App {
    public static void main(String[] args) throws Exception {

        int boardWidth = 900;
        int boardHeight = 880;


        JFrame frame = new JFrame("Flappy Bird");
        frame.setVisible(true);
        frame.setSize(boardHeight, boardWidth);
        frame.setLocationRelativeTo (null);
        frame.setResizable (false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);


    }
}
