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
package game.controlls;

/**
 * TODO
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public enum Commands {
	MoveRight,
	MoveLeft,
	Jump(false),
	Attack,
	BackInTime;
	
	
	private boolean lastKeyDown = false;
	private boolean fastRepeat = true;
	private boolean toggleModeActivated = false;
	private boolean toggleOn = false;
	
	private Commands() {
		this(true, false);
	}
	private Commands(boolean fastRepeat) {
		this(fastRepeat, false);
	}
	private Commands(boolean fastRepeat, boolean toggleModeActivated) {
		this.fastRepeat = fastRepeat;
		this.toggleModeActivated = toggleModeActivated;
	}
	public boolean isTriggered(boolean keyDown) {
		boolean result = false;
		
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
		else {
			if (lastKeyDown) {
				result = fastRepeat;
			}
			else {
				result = keyDown;
			}
		}
		
		// Remember last state
		lastKeyDown = keyDown;
		
		return result;
	}
}
