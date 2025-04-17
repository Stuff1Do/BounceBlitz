package physics;

import java.awt.*;
import java.util.Random;

public class Puck {
    public int x, y, radius = 15;
    public int dx, dy;
    private Random random = new Random();

    public Puck(int startX, int startY) {
        resetPosition(startX, startY); // 
    }

    public void move() {
        x += dx;
        y += dy;

        // Bounce off top and bottom
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

       
        dx = random.nextBoolean() ? 5 : -5;
        dy = random.nextBoolean() ? 5 : -5;
    }
}
