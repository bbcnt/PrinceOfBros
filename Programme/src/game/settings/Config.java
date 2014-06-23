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
 * Configuration of the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class Config {
	
	/**
	 * The name of the game.
	 */
	private String gameName;
	
	/**
	 * The minimum delay between two consideration of key events.
	 */
	private int keyMinDelay;   
  
	private int resolutionWidth;
	private int resolutionHeight;
	
	public String getGameName() {
		return gameName;
	}
	
	/**
	 * Return the minimum delay between two use of key events in milliseconds.
	 * @return The minimum delay between two use of key events.
	 */
	public int getInputKeyMinDelay() {
		return keyMinDelay;
	}
	
	/**
	 * Returns the resolution width.
	 * @return the resolution width.
	 */
	public int getResolutionWidth() {
		return resolutionWidth;
	}
	
	/**
	 * Returns the resolution height.
	 * @return the resolution height.
	 */
	public int getResolutionHeight() {
		return resolutionHeight;
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	/**
	 * This private class allows the singleton to be thread safe.
	 * @author Brito Carvalho Bruno
	 * @author Decorvet Grégoire
	 * @author Ngo Quang Dung
	 * @author Schweizer Thomas
	 *
	 */
	private static class Instance {
		static final Config instance = new Config();
	}
	
	private Config() {
		gameName = "Prince of Bros";
		keyMinDelay = 250;
		resolutionWidth = 800;
		resolutionHeight = 500;
	}
	
	/**
	 * Returns the singleton.
	 * @return The instance.
	 */
	public static Config getInstance() {
		return Instance.instance;
	}

}
