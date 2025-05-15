package physics;

import java.awt.*;

public class Paddle {
   public int x, y, width  = 5, height = 60;
   public int dy;
   
   
   public Paddle(int x, int y) {
	   this.x  = x;
	   this.y = y;
   }
   
   public void draw(Graphics g) {
	   g.setColor(Color.WHITE);
	   g.fillRect(x, y, width, height);
   }
   
   public void move() {
       y += dy;
       if (y < 0) y = 0;
       if (y +height  > 400) y = 400-height;
   }

   public void setDirection(int dy){
	   this.dy = dy;
   }
   public Rectangle getBounds() {
       return new Rectangle(x, y, width, height);
   }
   
   public int getX() {
       return x;
   }

   
}
