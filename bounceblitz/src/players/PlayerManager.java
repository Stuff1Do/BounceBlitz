package players;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlayerManager {
	
	List<Player> players = new ArrayList<>();
	Player player1;
	Player player2;
	Player server;
	Random random;
	int ctr;
	public PlayerManager(List<Player> players) {
		this.players = players;
		this.ctr = new Random().nextInt(2);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getServer() {
		return server;
	}
	public void setInitialServer(Player player) {
	    this.server = player;
	}
	
	
	
	public Player getWinningPlayer() {
		if(player1.getScore() > player2.getScore()) {
			return player1;
		}else {
			return player2;
		}
	}
	
	
}
