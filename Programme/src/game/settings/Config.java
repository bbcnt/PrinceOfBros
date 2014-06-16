/* ============================================================================
 * Filename   : Config.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.settings;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Config {
	
	/**
	 * The minimum delay between two consideration of key events.
	 */
	private int keyMinDelay;
	
	/**
	 * Return the minimum delay between two use of key events in milliseconds.
	 * @return The minimum delay between two use of key events.
	 */
	public int getInputKeyMinDelay() {
		return keyMinDelay;
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	private static class Instance {
		static final Config instance = new Config();
	}
	
	private Config() {
		keyMinDelay = 250;
	}
	
	public static Config getInstance() {
		return Instance.instance;
	}

}
