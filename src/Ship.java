//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {

    private int speed;
    private Image image;
    private boolean dead;

    public Ship() {
        this(10, 10, 10, 10, 10);
    }

    public Ship(int x, int y) {
        //add code here
        super (x, y);
    }

    public Ship(int x, int y, int s) {
        //add code here
        super (x, y);
        speed = s;
    }

    public Ship(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
        try {
            URL url = getClass().getResource("ship.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public void setSpeed(int s) {
        //add more code
        this.speed = s;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setDead(boolean b) {
        dead = b;
    }

    public boolean getDead() {
        return dead;
    }

    public void move(String direction) {
        //add code here
        if (direction == "LEFT" && getX() > 0){
            setX(getX() - getSpeed());
        }
        if (direction == "RIGHT" && getX() < 800 - getWidth()){
            setX(getX() + getSpeed());
        }
        if (direction == "DOWN" && getY() < 550 - getHeight()){
            setY(getY() + getSpeed());
        }
        if (direction == "UP"){
            setY(getY() - getSpeed());
        }
    }

    public void draw(Graphics window) {
        if (!dead){
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    public String toString() {
        return super.toString() + getSpeed();
    }
}
