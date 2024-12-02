import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;



public class FlappyBird extends JPanel implements ActionListener, KeyListener {
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
    int velocityY = 0;
    int gravity = 1;


    Timer gameLoop;



    FlappyBird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        //setBackground(Color.red);

        setFocusable(true);
        addKeyListener(this);

        //load image
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();

        toppipeImg = new ImageIcon (getClass().getResource("./toppipe.png")).getImage();

        bottompipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();


        //bird
        bird = new Bird(birdImg);




        // game timer

        gameLoop = new Timer(1000/60, this);

        gameLoop.start();
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


    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


    

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }


    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}