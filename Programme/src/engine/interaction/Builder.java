/* ============================================================================
 * Filename   : Builder.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine.interaction;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Builder {
	
	private static Builder instance;
	
	private Builder(){}
	
	public static Builder getInstance() {
		if(instance == null)
			instance = new Builder();
		return instance;
	}
	
	/**
	 * For now, our builder can create only Tile.
	 * @return
	 */
	public GameObject createGameObject(float x, float y) {
		return new Tile(x,y);
	}

}
