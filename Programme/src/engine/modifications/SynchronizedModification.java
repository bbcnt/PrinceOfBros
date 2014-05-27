/* ============================================================================
 * Filename   : SynchronizedModification.java
 * ============================================================================
 * Created on : 26 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.modifications;



/**
 * Represent a modification linked to the delta time between updates.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public abstract class SynchronizedModification implements IModification {
	
	private int delta;
	
	/**
	 * Create the modification, for the specified delta. This value is given by
	 * the Slick2d library.
	 * @param delta - the value given by Slick2d.
	 */
	public SynchronizedModification(int delta) {
		this.delta = delta;
	}
	
	/**
	 * Return the delta when the action was called.
	 * @return The delta when the action was called. 
	 */
	public int getDelta() {
		return delta;
	}

}
