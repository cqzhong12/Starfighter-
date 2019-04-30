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
    public boolean past = false;

    public AlienHorde(int size) {
        int row = 1;
        int xPos = 35;
        aliens = new ArrayList<Alien>();
        for(int i = 1; i <= size; i++){
            add(new Alien(xPos,row * 50));
            xPos+=100;
            if(i%8==0&&i!=0){
                row++;
                xPos = 35;
            }
        }
    }

    public void add(Alien al) {
            aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (Alien al : aliens){
            al.draw(window);
        }
    }

    public void moveEmAll() {
        for (Alien al : aliens){
            List <Alien> removeAl1 = new ArrayList<Alien>();
                removeAl1.add(al);
            if (al.getY() < 600){
            al.move("DOWN");
        }
            else{
                aliens.removeAll(removeAl1);
                past = true;
                break;
            }
        }
    }
    
     public void remove(Alien a){
        aliens.remove(a);
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
    
    public List<Alien> getList() {
        return aliens;
    }

    public String toString() {
        return "";
    }
}
