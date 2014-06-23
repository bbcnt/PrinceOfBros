/* ============================================================================
 * Filename   : IAnimatedState.java
 * ============================================================================
 * Created on : 26 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.animations;

/**
 * Represent a step in an animation.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IAnimatedState {

	/**
	 * Return the id of the animated state.
	 * @return The id of the animated state.
	 */
	public int getId();
	
	/**
	 * Initialize the animated state.
	 * @param anim The animation
	 * @param stoppable If the animation can be stopped.
	 */
	public void init(TimedAnimation anim, boolean stoppable);
	
	/**
	 * Return the animation.
	 * @return The animation.
	 */
	public TimedAnimation getAnimation();
	
	/**
	 * Return whether the state can be stopped or not.
	 * @return
	 */
	public boolean isStoppable();
	
	/**
	 * Return whether the state have an animation.
	 * @return
	 */
	public boolean hasAnimation();	
}
