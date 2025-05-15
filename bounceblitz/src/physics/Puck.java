package physics;

import java.awt.*;
import java.util.Random;

public class Puck {
    public int x, y, radius = 15;
    public int dx, dy;
    private Random random = new Random();

    public Puck(int x, int y) {
        this.x = x;
        this.y = y;  
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
    
    public void resetPosition()  {
       this.x = 242;
       this.y = 193;
        this.dx = 0;
        this.dy = 0;
    }
    public int getX() {
    	return x;
    }
   
}
