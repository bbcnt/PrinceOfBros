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

import engine.animations.IAnimatedState;
import engine.animations.IAnimationType;
import engine.animations.TimedAnimation;
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
	
	public enum AnimationPart implements IAnimationType {
		Legs, Body;

		@Override
      public int getTypeId() {
	      return ordinal();
      }
	}

	public enum LegsState implements IAnimatedState {
		IdleRight,
		IdleLeft,
		MovingRight,
		MovingLeft;

		private TimedAnimation animation;
		private boolean stoppable;

		private LegsState() {
			this(null);
		}

		private LegsState(TimedAnimation anim) {
			animation = anim;
			stoppable = true;
		}

		@Override
		public int getId() {
			return ordinal();
		}

		@Override
		public IAnimationType getType() {
			return AnimationPart.Legs;
		}

		@Override
		public void init(TimedAnimation anim) {
			if (!hasAnimation())
				animation = anim;
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
	
	public enum BodyState implements IAnimatedState {
		Idle,
		AttackingRight,
		AttackingLeft;

		private TimedAnimation animation;
		private boolean stoppable;

		private BodyState() {
			this(null);
		}

		private BodyState(TimedAnimation anim) {
			animation = anim;
			stoppable = true;
		}

		@Override
		public int getId() {
			return ordinal();
		}

		@Override
		public IAnimationType getType() {
			return AnimationPart.Body;
		}

		@Override
		public void init(TimedAnimation anim) {
			if (!hasAnimation())
				animation = anim;
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
	
	private float shiftX = 0;
	private float shiftY = 0;
	
	private IAnimatedState[] current;
	
	public GPlayer(Player player) {
		this.player = player;
		
		current = new IAnimatedState[AnimationPart.values().length];
	}
	
	@Override
	public void draw(Graphics g) {
		for (IAnimatedState s : current) {
			if (s != null)
				s.getAnimation().draw(player.getPosX() * 64, (10 - player.getPosY()) * 64);
		}
	}
	
	@Override
	public void update(int delta) {
		current[AnimationPart.Legs.getTypeId()] = player.getState(AnimationPart.Legs);
		current[AnimationPart.Body.getTypeId()] = player.getState(AnimationPart.Legs);
		
		for (IAnimatedState s : current) {
			if (s != null)
				s.getAnimation().update(delta);
		}
	}
	
	@Override
	public void updateBackward(int delta) {
		current[AnimationPart.Legs.getTypeId()] = player.getState(AnimationPart.Legs);
		current[AnimationPart.Body.getTypeId()] = player.getState(AnimationPart.Legs);
		
		for (IAnimatedState s : current) {
			if (s != null)
				s.getAnimation().updateBackward(delta);
		}
	}

}
