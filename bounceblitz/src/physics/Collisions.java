package physics;

import ui.SoundEffects;

public class Collisions {
    public static void handlePaddleCollision(Puck puck, Paddle paddle) {
        
        if (puck.getBounds().intersects(paddle.getBounds())) {
            puck.dx = -puck.dx;
            SoundEffects.playSound("bounceblitz/resources/paddlehit.wav"); 
        }

        if (puck.y <= 0 || puck.y >= 400 - puck.radius) { 
            puck.dy = -puck.dy; 
            SoundEffects.playSound("bounceblitz/resources/wall.wav"); 
        }
    }

    public static boolean checkGoalRight(Puck puck) {
      
        if (puck.x >= 500) { 
            SoundEffects.playSound("bounceblitz/resources/goal.wav"); 
            return true;
        }
        return false;
    }

    public static boolean checkGoalLeft(Puck puck) {
       
        if (puck.x <= 0) {
            SoundEffects.playSound("bounceblitz/resources/goal.wav"); 
            return true;
        }
        return false;
    }
}