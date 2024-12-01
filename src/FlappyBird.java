import java.awt.*;
import javax.swing.*;



public class FlappyBird extends JPanel {
    int boardwidth = 960;
    int boardHeight = 850;


    //image
    Image backgroundImg;
    Image birdImg;
    Image toppipeImg;
    Image bottompipeImg;


    //bird
    int birdX = boardwidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 55;
    int birdHeight = 50;


    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird (Image img) {
            this.img = img;
        }
    }


    //game logic
    Bird bird;



    FlappyBird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        //setBackground(Color.red);



        //load image
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();

        toppipeImg = new ImageIcon (getClass().getResource("./toppipe.png")).getImage();

        bottompipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();


        //bird
        bird = new Bird(birdImg);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw (g);
    }


    public void draw(Graphics g){

        //background

        g.drawImage (backgroundImg, 0, 0, boardwidth, boardHeight, null);

        //bird
        g.drawImage (bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }
}