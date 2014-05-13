package game.logic.actions;

import game.logic.Player;

public class MoveLeft extends PlayerAction {
	
	private Player player;
	
	public MoveLeft(int delta, Player player) {
		super(delta);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveLeft(getDelta() * .1f);
   }

	@Override
   public void cancel() {
		player.moveLeft(-getDelta() * .1f);
   }
}
