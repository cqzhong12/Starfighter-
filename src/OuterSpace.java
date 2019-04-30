//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    //private Alien alienOne;
    //private Alien alienTwo;
    private Boolean shot = true;
    private Boolean touch = false;
    private Boolean past = false;

    //uncomment once you are ready for this part
    private AlienHorde horde;
    private Bullets shots;

    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        //instantiate other instance variables
        //Ship, Alien
        ship = new Ship(300, 420, 80, 80, 5);
        //alienOne = new Alien(300, 50, 60, 60, 5);
        //alienTwo = new Alien(250, 50, 60, 60, 5);
        shots = new Bullets();
        horde = new AlienHorde(8);

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("StarFighter ", 25, 50);
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);

        if (keys[0] == true) {
            ship.move("LEFT");
        }
        if (keys[1] == true) {
            ship.move("RIGHT");
        }
        if (keys[2] == true) {
            ship.move("UP");
        }
        if (keys[3] == true) {
            ship.move("DOWN");
        }
        if (keys[4] == true && shot == true) {
            shots.add(new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 3));
            shot = false;
        }

        shots.drawEmAll(graphToBack);
        shots.moveEmAll();
        ship.draw(graphToBack);
        //alienOne.draw(graphToBack);
        //alienTwo.draw(graphToBack);
        horde.drawEmAll(graphToBack);

        //add code to move Ship, Alien, etc.
        horde.moveEmAll();
        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship

        horde.removeDeadOnes(shots.getList());
        ship.setDead(false);

        for (Alien al : horde.getList()) {
            if ((al.getX() > ship.getX()
                    && al.getX() < ship.getX() + ship.getWidth())
                    && (al.getY() < ship.getY() + ship.getHeight()
                    && al.getY() > ship.getY())) {
                touch = true;
                break;
            }
        }

        for (Alien al : horde.getList()) {
            if (al.getY() > 580) {
                horde.getList().clear();
                past = true;
                break;
            }
        }

        if (horde.getList().isEmpty()) {
            graphToBack.setColor(Color.BLACK);
            graphToBack.fillRect(0, 0, 800, 600);
            graphToBack.setColor(Color.RED);
            if (past == true) {
                graphToBack.drawString("YOU LOSE ", 350, 300);
                horde.getList().clear();
            } else {
                graphToBack.drawString("YOU WIN! ", 350, 300);
                ship.setDead(true);
            }
        }

        if (touch == true) {
            horde.getList().clear();
            graphToBack.setColor(Color.BLACK);
            graphToBack.fillRect(0, 0, 800, 600);
            graphToBack.setColor(Color.RED);
            graphToBack.drawString("YOU LOSE ", 350, 300);
            ship.setDead(true);

        }

        twoDGraph.drawImage(back, null, 0, 0);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
            shot = true;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
