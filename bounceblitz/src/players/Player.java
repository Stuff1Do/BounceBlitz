package players;

import physics.Paddle;

public class Player {
	String name;
	Paddle paddle;
	int score;
	public Player(String name, Paddle paddle) {
		this.name = name;
		this.paddle = paddle;
		this.score = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addPoint() {
		 score++;
	}
	
	public void resetScore() {
		score = 0;
	}
}
