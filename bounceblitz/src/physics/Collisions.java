package physics;

import ui.SoundEffects;

public class Collisions {
    public static void handlePaddleCollision(Puck puck, Paddle paddle) {
        if (puck.getBounds().intersects(paddle.getBounds())) {
            puck.dx = -puck.dx;
            SoundEffects.playSound("bounceblitz/resources/sound.wav");
        }
    }

    public static boolean checkGoal(Puck puck) {
        return puck.x <= 0 || puck.x >= 640 - puck.radius;
    }
}