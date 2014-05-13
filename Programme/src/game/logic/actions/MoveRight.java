package game.logic.actions;

import game.logic.Player;

public class MoveRight extends PlayerAction {
	
	private Player player;
	
	public MoveRight(int delta, Player player) {
		super(delta);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveRight(getDelta() * .1f);
   }

	@Override
   public void cancel() {
		player.moveRight(-getDelta() * .1f);
   }
}
