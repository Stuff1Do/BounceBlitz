package utils;

import java.util.Random;
import physics.Paddle;
import physics.Puck;
public class Randomizer {
    private static int puckSpeed = 6;

    
    public static void setPuckSpeed(int speed) {
        puckSpeed = speed + 4;
    }

    public static void randomizeDirectionForServer(Puck puck, Paddle serverPaddle) {
        Random random = new Random();
        
        
        int dx = random.nextBoolean() ? puckSpeed : -puckSpeed;     
        int dy = random.nextInt(puckSpeed) - 2;
        if(random.nextBoolean()){
            dy = -dy;
        }
        puck.setDx(dx);
        puck.setDy(dy);
    }
}
