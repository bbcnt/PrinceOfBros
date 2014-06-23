/* ============================================================================
 * Filename   : ActionExecutor.java
 * ============================================================================
 * Created on : 17 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.controls;

import java.util.LinkedList;
import java.util.List;

import game.controls.actions.IUserAction;

/**
 * Executes the commands when given the order.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class ActionExecutor {
	
	private List<IUserAction> actions = new LinkedList<>();
	
	/**
	 * Add an action to the list.
	 * @param a The action
	 */
	public void pushAction(IUserAction a) {
		if (a != null)
			actions.add(a);
	}
	
	/**
	 * Execute all the actions.
	 */
	public void executeActions() {
		for (IUserAction a : actions) {
			if (a.isAllowed()) a.execute();
		}
		actions.clear();
	}
}
