import java.awt.*;
import javax.swing.*;



public class FlappyBird extends JPanel {
    int boardwidth = 960;
    int boardHeight = 640;


    //image
    Image backgroundImg;
    Image birdImg;
    Image toppipeImg;
    Image bottompipeImg;


    FlappyBird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        //setBackground(Color.red);



        //load image
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();

        toppipeImg = new ImageIcon (getClass().getResource("./toppipe.png")).getImage();

        bottompipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw (g);
    }


    public void draw(Graphics g){
        
    }
}