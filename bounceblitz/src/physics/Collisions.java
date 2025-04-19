package physics;

import ui.SoundEffects;

public class Collisions {
    public static void handlePaddleCollision(Puck puck, Paddle paddle) {
        // Handle paddle collision
        if (puck.getBounds().intersects(paddle.getBounds())) {
            puck.dx = -puck.dx;
            SoundEffects.playSound("bounceblitz/resources/paddlehit.wav"); // Play collision sound
        }

        // Handle top and bottom edge collisions
        if (puck.y <= 0 || puck.y >= 400 - puck.radius) { // Assuming game height is 400
            puck.dy = -puck.dy; // Reverse vertical direction
            SoundEffects.playSound("bounceblitz/resources/wall.wav"); // Play collision sound
        }
    }

    public static boolean checkGoalRight(Puck puck) {
        // Check if the puck hits the right wall (goal for the left player)
        if (puck.x >= 500) { // Game width is 500
            SoundEffects.playSound("bounceblitz/resources/goal.wav"); // Play goal sound for right wall
            return true;
        }
        return false;
    }

    public static boolean checkGoalLeft(Puck puck) {
        // Check if the puck hits the left wall (goal for the right player)
        if (puck.x <= 0) {
            SoundEffects.playSound("bounceblitz/resources/goal.wav"); // Play goal sound for left wall
            return true;
        }
        return false;
    }
}