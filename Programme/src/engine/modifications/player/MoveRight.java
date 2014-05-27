package engine.modifications.player;

import engine.models.player.Player;

public class MoveRight extends PlayerAction {
	
	private Player player;
	
	public MoveRight(int delta, Player player) {
		super(delta, 0);
		this.player = player;
	}

	@Override
   public void apply() {
	   player.moveRight(getDelta() * player.getSpeed());
   }

	@Override
   public void cancel() {
		player.moveRight(-getDelta() * player.getSpeed());
   }
}
