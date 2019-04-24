//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {

    private List<Alien> aliens;

    public AlienHorde(int size) {
        aliens = new ArrayList<Alien>(size);
    }

    public void add(Alien al) {
        for (int i = 0; i < aliens.size() ; i++){
            aliens.add(al);
        }
    }

    public void drawEmAll(Graphics window) {
        for (Alien al : aliens){
            al.draw(window);
        }
    }

    public void moveEmAll() {
        for (Alien al : aliens){
            al.move("RIGHT");
        }
    }

    public void removeDeadOnes(List<Ammo> shots) {
        List <Alien> removeAl = new ArrayList<Alien>();
        for(Ammo a : shots){
            for (Alien al : aliens){
                if ((a.getX() > al.getX() && a.getX() < al.getX() + al.getWidth()) 
                        && (a.getY() < al.getY() + al.getHeight() && a.getY()> al.getY())){
                    removeAl.add(al);
                }
            }
        }
        aliens.removeAll(removeAl);
    }

    public String toString() {
        return "";
    }
}
