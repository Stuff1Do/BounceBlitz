package physics;

public class Collisions {
    public static void handlePaddleCollision(Puck puck, Paddle paddle) {
        if (puck.getBounds().intersects(paddle.getBounds())) {
            puck.dx = -puck.dx;
        }
    }

    public static boolean checkGoal(Puck puck) {
        return puck.x <= 0 || puck.x >= 640 - puck.radius;
    }
}
