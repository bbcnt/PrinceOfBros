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

import org.newdawn.slick.Graphics;

import engine.CoordsConverter;
import engine.animations.IAnimatedState;
import engine.animations.TimedAnimation;
import engine.graphics.IDrawable;
import engine.models.player.Player;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class GPlayer implements IDrawable {
	
	private static float x = 3f;
	private static float y = 4.5f;

	public enum AnimationState implements IAnimatedState {
		IdleRight,
		IdleLeft,
		MovingRight,
		MovingLeft,
		AttackingRight,
		AttackingLeft;

		private TimedAnimation animation;
		private boolean stoppable;

		private AnimationState() {
			this(null);
		}

		private AnimationState(TimedAnimation anim) {
			animation = anim;
			stoppable = true;
		}

		@Override
		public int getId() {
			return ordinal();
		}

		@Override
		public void init(TimedAnimation anim, boolean stoppable) {
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
		public TimedAnimation getAnimation() {
			return animation;
		}
	}
	
	private Player player;
	
	private IAnimatedState current;
	
	public GPlayer(Player player) {
		this.player = player;
		player.setAnimation(AnimationState.IdleRight);
	}
	
	@Override
	public void draw(Graphics g) {
		if (current != null) {
			current.getAnimation().draw(getX(),getY());
		}
	}

	@Override
   public float getX() {
	   return CoordsConverter.getInstance().toGraphic(x);
   }

	@Override
   public float getY() {
	   return CoordsConverter.getInstance().toGraphic(y);
   }

	@Override
   public float getWidth() {
	   return player.getState().getAnimation().getWidth();
   }

	@Override
   public float getHeight() {
	   return player.getState().getAnimation().getHeight();
   }
	
	@Override
	public void update(int delta) {
		current = player.getState();
		
		if (current != null)
			current.getAnimation().update(delta);
	}

}
