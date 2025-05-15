package utils;

import java.util.Random;
import physics.Paddle;
import physics.Puck;

public class Randomizer {
    private static final Random rand = new Random();

    public static void randomizeDirectionForServer(Puck puck, Paddle serverPaddle) {
        Random random = new Random();
        
        int dx = random.nextBoolean() ?  6: -6;
        
        int dy = random.nextInt(5) + 2;
        if (random.nextBoolean()) {
            dy = -dy; 
        }
        
        puck.setDx(dx);
        puck.setDy(dy);
    }

}
