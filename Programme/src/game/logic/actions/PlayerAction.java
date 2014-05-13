package game.logic.actions;

import game.logic.modstack.IModification;

public abstract class PlayerAction implements IModification {
	
	private int delta;
	
	public PlayerAction(int delta) {
		this.delta = delta;
	}
	
	public int getDelta() {
		return delta;
	}

}
