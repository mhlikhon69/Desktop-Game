import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.channels.Pipe;

import javax.swing.*;
import java.util.ArrayList;



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

    //pipe class
    int pipeX = boardwidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;


    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;


        Pipe(Image img){
            this.img = img;
        }
    }

    
 
    //game logic
    Bird bird;  
    int velocityX = -4;
    int velocityY = 0;   
    int gravity = 1;

    ArrayList<Pipe> Pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver = false;
    double score = 0;


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
        Pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipeTimer = new Timer (1500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });
        placePipeTimer.start();

    
     // game timer

        gameLoop = new Timer(1000/60, this);

        gameLoop.start();
    }
void placePipes(){
        
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;
    
        Pipe topPipe = new Pipe(toppipeImg);
        topPipe.y = randomPipeY;
        Pipes.add(topPipe);
    
        Pipe bottomPipe = new Pipe(bottompipeImg);
        bottomPipe.y = topPipe.y  + pipeHeight + openingSpace;
        
        Pipes.add(bottomPipe);
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

        //pipes
        for (int i = 0; i< Pipes.size(); i++){
            Pipe pipe = Pipes.get(i);

            g.drawImage (pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null );

        }

 //score
        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }
        else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }


    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);


        //pipes
        for (int i = 0; i < Pipes.size(); i++){
            Pipe pipe = Pipes.get(i);
            pipe.x += velocityX;

             if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5; 
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

            if (bird.y > boardHeight) {
                gameOver = true;
            } 
            

    }
    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&   
               a.x + a.width > b.x &&   
               a.y < b.y + b.height &&  
               a.y + a.height > b.y;
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
