package utils;

import physics.Paddle;
import physics.Puck;
import java.util.Random;

public class Randomizer {
    private static final Random rand = new Random();
    private static final int PUCK_SPEED = 6;
    private static final int MAX_VERTICAL_SPEED = 2;
    private static final int SCREEN_CENTER_X = 250;

    public static void randomizePuckDirection(Puck puck) {
        puck.setDx(rand.nextBoolean() ? -PUCK_SPEED : PUCK_SPEED);
        puck.setDy(rand.nextInt(MAX_VERTICAL_SPEED * 2 + 1) - MAX_VERTICAL_SPEED);
    }

    public static void randomizeDirectionForServer(Puck puck, Paddle serverPaddle) {
        Random random = new Random();
        
        int dx = random.nextBoolean() ? 5 : -5;
       
        int dy = random.nextInt(5) + 2;
        if (random.nextBoolean()) {
            dy = -dy; 
        }
        puck.setDx(dx);
        puck.setDy(dy);
    }

}
