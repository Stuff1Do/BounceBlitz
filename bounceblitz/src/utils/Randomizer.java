package utils;

import java.util.Random;
import physics.Paddle;
import physics.Puck;
public class Randomizer {
    private static int puckSpeed;

    // static setter for the static field
    public static void setPuckSpeed(int speed) {
        puckSpeed = speed + 6;
    }

    public static void randomizeDirectionForServer(Puck puck, Paddle serverPaddle) {
        Random random = new Random();
        int dy = random.nextInt(5) - 2;
        
        int dx = (int) Math.sqrt(puckSpeed*puckSpeed - dy*dy);      
        dx = random.nextBoolean() ?  6: -6;

        puck.setDx(dx);
        puck.setDy(dy);
    }
}
