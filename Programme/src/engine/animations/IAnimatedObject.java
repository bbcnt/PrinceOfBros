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
 * Represent an animated object.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IAnimatedObject {
	
	/**
	 * Set the object animation to the given state if possible. Return true if
	 * the state has been changed, false otherwise.
	 * @param state - the new animation state for the object.
	 * @return True if the state has been changed, false otherwise.
	 */
	public boolean setAnimation(IAnimatedState state);
	
	/**
	 * Get the state of the animation.
	 * @return The state of the animation.
	 */
	public IAnimatedState getState();

}
