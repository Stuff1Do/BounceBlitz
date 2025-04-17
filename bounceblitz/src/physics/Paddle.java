package physics;

import java.awt.*;

public class Paddle {
    public int x, y, width = 10, height = 60;
    public int dy = 0;
    private final int speed = 8;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += dy;
        if (y < 0) y = 0;
        if (y > 480 - height) y = 480 - height;
    }

    public void setDirection(int dy) {
        this.dy = dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
