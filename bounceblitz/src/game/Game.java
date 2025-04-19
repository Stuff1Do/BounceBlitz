package game;
import players.Player;
import players.PlayerManager;
import physics.*;
import utils.Randomizer;
public class Game {
	Puck puck;
	PlayerManager playerManager;
	int panelWidth;
	boolean puckFrozen = false;
	public Game(Puck puck, PlayerManager playerManager, int panelWidth) {
			this.puck = puck;
			this.playerManager = playerManager;
			this.panelWidth = panelWidth;
	}
	
	
	public boolean isPuckFrozen() {
		return puckFrozen;
	}
	
	public void freezePuck() {
		puckFrozen =true;
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
			playerManager.switchServer();
			puck.resetPosition(panelWidth / 2, 200);
			
		}else if (Collisions.checkGoalRight(puck, panelWidth) && puck.getX() > panelWidth) {
			
			puckFrozen = true;
            p1.addPoint();
            playerManager.switchServer();
            puck.resetPosition(panelWidth / 2, 200);
            
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

