package physics;

import java.awt.*;
import java.util.Random;

public class Puck {
    public int x, y, radius = 15;
    public int dx, dy;
    private Random random = new Random();

    public Puck(int startX, int startY) {
        resetPosition(startX, startY);  
    }
    public void setDx(int dx) {
    	this.dx = dx;
    }
    public void setDy(int dy) {
    	this.dy = dy;
    }
    public void move() {
    	if (dx != 0 || dy != 0) {  
            x += dx;
            y += dy;
        }

        
        if (y <= 0 || y >= 400 - radius) {
            dy = -dy;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, radius, radius);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radius, radius);
    }
    
    public void resetPosition(int startX, int startY) {
        x = startX;
        y = startY;

       
        dx = 0;
        dy = 0;
    }
    public int getX() {
    	return x;
    }
   
}
