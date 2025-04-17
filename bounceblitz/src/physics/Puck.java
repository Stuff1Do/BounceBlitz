package physics;

import java.awt.*;

public class Puck {
    public int x, y, radius = 15;
    public int dx = 5, dy = 5;

    public Puck(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move() {
        x += dx;
        y += dy;

        // Bounce off top and bottom
        if (y <= 0 || y >= 480 - radius) {
            dy = -dy;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, radius, radius);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radius, radius);
    }

    public void resetPosition(int startX, int startY) {
        x = startX;
        y = startY;
        dx = 5;
        dy = 5;
    }
}
