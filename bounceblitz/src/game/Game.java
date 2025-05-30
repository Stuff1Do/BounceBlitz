package game;
import players.Player;
import players.PlayerManager;
import physics.*;
import utils.Randomizer;
public class Game {
	Puck puck;
	PlayerManager playerManager;
	boolean puckFrozen = false;
	public Game(Puck puck, PlayerManager playerManager) {
			this.puck = puck;
			this.playerManager = playerManager;
	
	}
	
	
	public boolean isPuckFrozen() {
		return puckFrozen;
	}
	
	public void freezePuck() {
		puckFrozen = true;
	}
	public void engine() {
		if(!puckFrozen) {
		
		puck.move();
		
		Player p1 = playerManager.getPlayers().get(0);
		Player p2 = playerManager.getPlayers().get(1);
		
		Collisions.handlePaddleCollision(puck, p1.getPaddle());
		Collisions.handlePaddleCollision(puck, p2.getPaddle());
		
		if(Collisions.checkGoalLeft(puck) && puck.getX() < 0) {
			
			puckFrozen = true;
			p2.addPoint();
			puck.resetPosition();
			
		}else if (Collisions.checkGoalRight(puck) && puck.getX() > 500) {
			
			puckFrozen = true;
            p1.addPoint();
            puck.resetPosition();
            
        	}
		}
	
    }
	
	public void unfreezePuckOnServe() {
		if(puckFrozen) {
			puckFrozen = false;
			
			Randomizer.randomizeDirectionForServer(puck, playerManager.getServer().getPaddle());
		}
	}
	
}

