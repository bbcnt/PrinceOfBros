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
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IAnimatedState {

	public int getId();
	public IAnimationType getType();
	
	public void init(TimedAnimation anim);
	public TimedAnimation getAnimation();
	
	public boolean isStoppable();
	public boolean hasAnimation();
	
	
}
