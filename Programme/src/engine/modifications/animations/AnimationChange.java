/* ============================================================================
 * Filename   : AnimationChange.java
 * ============================================================================
 * Created on : 26 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.modifications.animations;

import engine.animations.IAnimatedObject;
import engine.animations.IAnimatedState;
import engine.modifications.IModification;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class AnimationChange implements IModification {
	
	private int ellapsedTime;
	private IAnimatedState oldAnimation;
	private IAnimatedState animation;
	private IAnimatedObject object;
	
	public AnimationChange(IAnimatedObject object, IAnimatedState anim) {
		this.animation = anim;
		this.object = object;
	}

	@Override
   public void apply() {
		// Keep copy of old state
		oldAnimation = object.getState();
		ellapsedTime = oldAnimation.getAnimation().getCurrentTime();
		
		// Setup the new animation
		animation.getAnimation().restart();
	   object.setAnimation(animation);
   }

	@Override
   public void cancel() {
		// Restore old animation state
	   oldAnimation.getAnimation().setCurrentTime(ellapsedTime);
	   object.setAnimation(oldAnimation);
   }

}
