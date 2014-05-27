/* ============================================================================
 * Filename   : UpdateDrawableObject.java
 * ============================================================================
 * Created on : 26 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.modifications.graphics;

import engine.graphics.IDrawable;
import engine.modifications.SynchronizedModification;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class UpdateDrawableObject extends SynchronizedModification {
	
	private IDrawable object;
	
	public UpdateDrawableObject(int delta, IDrawable object) {
		super(delta);
		this.object = object;
	}
	
	public IDrawable getObject() {
		return object;
	}

	@Override
   public void apply() {
	   object.update(getDelta());
   }

	@Override
   public void cancel() {
	   object.updateBackward(getDelta());
   }

}
