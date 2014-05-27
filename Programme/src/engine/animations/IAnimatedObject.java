/* ============================================================================
 * Filename   : IAnimatedObject.java
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
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IAnimatedObject {
	
	/**
	 * Init the management of all the animation states.
	 * @param states - all the possible states for this object.
	 */
	public void initAnimationStates(IAnimatedState[] states);
	
	/**
	 * Set the object animation to the given state if possible. Return true if
	 * the state has been changed, false otherwise.
	 * @param state - the new animation state for the object.
	 * @return True if the state has been changed, false otherwise.
	 */
	public boolean setAnimation(IAnimatedState state);
	
	public IAnimatedState getState(IAnimationType type);

}
