/* ============================================================================
 * Filename   : IUserAction.java
 * ============================================================================
 * Created on : 17 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package game.controls.actions;

/**
 * Represent a user action.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public interface IUserAction {
	
	/**
	 * Whether the action is allowed.
	 * @return True if the action is allowed.
	 */
	public boolean isAllowed();
	
	/**
	 * Executes the action.
	 */
	public void execute();

}
