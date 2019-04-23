//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

    private int speed;
    private Image image;

    public Alien() {
        this(0, 0, 30, 30, 0);
    }

    public Alien(int x, int y) {
        //add code here
        super(x,y);
    }

    public Alien(int x, int y, int s) {
        //add code here
        super(x, y);
        speed = s;  
    }

    public Alien(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
        try {
            URL url = getClass().getResource("alien.JPG");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public void setSpeed(int s) {
        //add code
        this.speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        //add code here
        if (direction == "LEFT"){
            setX(getX() - getSpeed());
        }
        if (direction == "RIGHT"){
            setX(getX() + getSpeed());
        }
        if (direction == "DOWN"){
            setY(getY() + getSpeed());
        }
        if (direction == "UP"){
            setY(getY() - getSpeed());
        }
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return "";
    }
}
