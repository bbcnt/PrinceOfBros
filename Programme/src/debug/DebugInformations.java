/* ============================================================================
 * Filename   : DebugInformations.java
 * ============================================================================
 * Created on : 13 mai 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */

package debug;

import game.logic.models.Player;
import game.logic.modstack.ModificationManager;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

/**
 * Displayed values on the top of the game panel.
 * 
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class DebugInformations {
	
	public final static boolean SHOW_DEBUG_INFORMATIONS = true;
	
	public final static boolean SHOW_MOUSE_POS = true;
	public final static boolean SHOW_UPDATE_DELTA = true;
	public final static boolean SHOW_PLAYER_POS = true;
	public final static boolean SHOW_STACK_COMPLETION = true;
	public final static boolean SHOW_STACK_COMPLETION_PERCENT = false;
	
	
	private int displayPosX, displayPosY;
	
	private int updateDelta;
	
	private Player player;
	private ModificationManager modStack;
	
	public void draw(Graphics g) {
		
		if (!SHOW_DEBUG_INFORMATIONS) return;
		
		String text = "";
		
		// Mouse position
		if (SHOW_MOUSE_POS)
			text += "Mouse X: " + Mouse.getX() + "\nMouse Y: " + Mouse.getY() 
					+ "\n";
		
		// Update delta
		if (SHOW_UPDATE_DELTA)
			text += "Update delta: " + updateDelta + "\n";
		
		// Player position
		if (SHOW_PLAYER_POS) {
			text += "Player pos: ";
			
			if (player == null) {
				text += " ?, ?";
			}
			else {
				text += String.format("%.4f, %4f", player.getPosX(),
				      player.getPosY());
			}
			text += "\n";
		}
		
		// Modification stack
		if (SHOW_STACK_COMPLETION) {
			int stackSize = 0;
			int stackCapacity = 0;
			if (modStack != null) {
				stackSize = modStack.getStackSize();
				stackCapacity = modStack.getCapacityLogical();
			}
			text += "Stack : " + stackSize + " / " + stackCapacity + "\n"; 
		}
		
		if (SHOW_STACK_COMPLETION_PERCENT) {
			int stackSize = 0;
			int stackCapacity = 0;
			if (modStack != null) {
				stackSize = modStack.getStackSize();
				stackCapacity = modStack.getCapacityLogical();
			}
			text += "Stack : "
					+ String.format("%.2f", 100.0*(double)stackSize /
							(double)(stackCapacity > 0 ? stackCapacity : 1))
					+ " %\n"; 
		}
		
		g.drawString(text, displayPosX, displayPosY);
	}
	
	public void setPosition(int x, int y) {
		displayPosX = x;
		displayPosY = y;
	}
	
	public void updateUpdateDelta(int delta) {
		updateDelta = delta;
	}
	
	public void registerPlayer(Player player) {
		this.player = player;
	}
	
	public void registerModificationStack(ModificationManager stack) {
		modStack = stack;
	}
	
	/*---Singleton part--------------------------------------------------------*/
	
	private static class Instance {
		static final DebugInformations instance = new DebugInformations();
	}
	
	private DebugInformations() {}
	
	public static DebugInformations getInstance() {
		return Instance.instance;
	}

}
