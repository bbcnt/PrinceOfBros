/* ============================================================================
 * Filename   : Player.java
 * ============================================================================
 * Created on : 16 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.graphics.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import engine.animations.IAnimatedState;
import engine.animations.IAnimationType;
import engine.animations.NTimedAnimation;
import engine.graphics.IDrawable;
import engine.models.player.Player;
import game.states.Game;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class GPlayer implements IDrawable {

	public enum AnimationState implements IAnimatedState {
		IdleRight,
		IdleLeft,
		MovingRight,
		MovingLeft,
		AttackingRight,
		AttackingLeft;

		private NTimedAnimation animation;
		private boolean stoppable;

		private AnimationState() {
			this(null);
		}

		private AnimationState(NTimedAnimation anim) {
			animation = anim;
			stoppable = true;
		}

		@Override
		public int getId() {
			return ordinal();
		}

		@Override
		public void init(NTimedAnimation anim, boolean stoppable) {
			if (!hasAnimation()) {
				animation = anim;
				this.stoppable = stoppable;
			}
		}

		@Override
		public boolean isStoppable() {
			return stoppable;
		}

		@Override
		public boolean hasAnimation() {
			return animation != null;
		}

		@Override
		public NTimedAnimation getAnimation() {
			return animation;
		}
	}
	
	private Player player;
	
	private float shiftX = 0;
	private float shiftY = 0;
	
	private IAnimatedState current;
	
	public GPlayer(Player player) {
		this.player = player;
		player.setAnimation(AnimationState.IdleRight);
	}
	
	@Override
	public void draw(Graphics g) {
		if (current != null) {
			current.getAnimation().draw(player.getPosX() * 64, (10 - player.getPosY()) * 64);
			
			// DEBUG - bounds
			float x = shiftX - current.getAnimation().getWidth() / 2;
			float y = shiftY - current.getAnimation().getHeight() / 2;
			
			g.setColor(Color.green);
			g.drawRect(x, y, current.getAnimation().getWidth(), current.getAnimation().getHeight());
			
			// Center
			g.fillRect(shiftX - 6, shiftY, 13, 2);
			g.fillRect(shiftX, shiftY - 6, 2, 13);
		}
	}
	
	@Override
	public void update(int delta) {
		current = player.getState();
		
		if (current != null)
			current.getAnimation().update(delta);
	}
	
	@Override
	public void updateBackward(int delta) {
		current = player.getState();
		
		if (current != null)
			current.getAnimation().update(-delta);
	}

}
