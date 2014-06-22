package engine.modifications.player;

import engine.models.player.Player;

public class Charge extends PlayerAction {
	
	private float oldSpeed;
	
	public Charge(int delta, int cooldown, Player player) {
		super(delta, cooldown, player);
	}

	@Override
   public void apply() {
		oldSpeed = getPlayer().getSpeed();
		getPlayer().setSpeed(oldSpeed * 3.0f);
   }

	@Override
   public void cancel() {
		getPlayer().setSpeed(oldSpeed);
   }
}
