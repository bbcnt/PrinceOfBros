/* ============================================================================
 * Filename   : Commands.java
 * ============================================================================
 * Created on : 15 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.controls;

import game.settings.Config;

import org.newdawn.slick.Input;

/**
 * Commands available.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public enum Commands {
	MoveRight(Input.KEY_RIGHT, true),
	MoveLeft(Input.KEY_LEFT, true),
	
	Macro(Input.KEY_Q),
	
	Attack(Input.KEY_A),
	Jump(Input.KEY_S),
	BackInTime(Input.KEY_D, true);
	
	private int code;
	private boolean lastKeyDown = false;
	private boolean fastRepeat = true;
	private boolean forceRelease = false; // Block action until the key is released then pressed 
	private boolean toggleModeActivated = false;
	private boolean toggleOn = false;
	private int delay = 0; // millis
	
	private Commands(int code) {
		this(code, false, false, false);
	}
	private Commands(int code, boolean fastRepeat) {
		this(code, fastRepeat, false, false);
	}
	
	private Commands(int code, boolean fastRepeat, boolean toggleModeActivated,
			boolean forceRelease) {
		this.code = code;
		this.fastRepeat = fastRepeat;
		this.toggleModeActivated = toggleModeActivated;
		this.forceRelease = forceRelease;
		delay = 0;
		lastKeyDown = false;
	}
	
	/**
	 * Check whether a given input is triggered.
	 * @param input The input triggered
	 * @param delta Time between 2 frames
	 * @return True if the input is triggered
	 */
	public boolean isTriggered(Input input, int delta) {
		boolean keyDown = input.isKeyDown(code);
		boolean result = false;
		
		delay += delta;
		
		// Toggle mode don't care of any setting, the key has to be released then
		// pressed again to deactivate the action.
		if (toggleModeActivated) {
			// Swap state
			if (!lastKeyDown && keyDown) {
				toggleOn = !toggleOn;
				result = toggleOn;
			}
			else {
				result = toggleOn;
			}
		}
		// Standard mode, check if the key has to be released
		else {
			if (forceRelease) {
				// fastRepeat take over forceRelease
				if (lastKeyDown) {
					result = fastRepeat;
				}
				else {
					result = keyDown;
				}
			}
			else {
				result = keyDown && (fastRepeat || delay > Config.getInstance().getInputKeyMinDelay());
				
				// Reset counter
				if (result) delay = 0;
			}
		}
		
		// Remember last state
		lastKeyDown = keyDown;
		
		return result;
	}
	
	/**
	 * The key code used for this command.
	 * @return The key code used for this command.
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Sets the key code used for this command.
	 * @param code The key code.
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
