package game.logic.actions;

import game.logic.models.Player;

public class MoveUp extends PlayerAction {
	
	private Player player;
	
	public MoveUp(int delta, Player player) {
		super(delta);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveUp(getDelta() * .1f);
   }

	@Override
   public void cancel() {
		player.moveUp(-getDelta() * .1f);
   }
}
