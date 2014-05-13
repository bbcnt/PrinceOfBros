package game.logic.actions;

import game.logic.Player;

public class MoveDown extends PlayerAction {
	
	private Player player;
	
	public MoveDown(int delta, Player player) {
		super(delta);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveDown(getDelta() * .1f);
   }

	@Override
   public void cancel() {
		player.moveDown(-getDelta() * .1f);
   }
}
