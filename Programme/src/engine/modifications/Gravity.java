/* ============================================================================
 * Filename   : Gravity.java
 * ============================================================================
 * Created on : 12 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.modifications;

import engine.interaction.GameObject;

/**
 * Command for the gravity force.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Gravity implements IModification {
	
	float force;
	GameObject g;
	
	public Gravity(float force, GameObject g) {
		this.force = force;
		this.g = g;
	}

	@Override
	public void apply() {
		g.moveY(force);
	}

	@Override
	public void cancel() {
		g.moveY(-force);
	}
}
