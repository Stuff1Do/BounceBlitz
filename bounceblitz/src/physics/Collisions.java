package physics;

import ui.SoundEffects;
import physics.Paddle;
import physics.Puck;
public class Collisions {
    public static void handlePaddleCollision(Puck puck, Paddle paddle) {
        if (puck.getBounds().intersects(paddle.getBounds())) {
            puck.dx = -puck.dx;
            SoundEffects.playSound("bounceblitz/resources/sound.wav");
        }
    }

    public static boolean checkGoalLeft(Puck puck) {
    	  return puck.x <= 0;
    }
    
    public static boolean checkGoalRight(Puck puck, int panelWidth) {
    	return  puck.x + puck.radius >= panelWidth;
    }
}