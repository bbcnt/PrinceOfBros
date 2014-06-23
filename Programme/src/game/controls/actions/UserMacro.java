/* ============================================================================
 * Filename   : UserMacro.java
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
 * Macro user action. It's encapsulating several actions.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class UserMacro implements IUserAction {
	
	private IUserAction[] actions;
	
	public UserMacro(IUserAction ... actions) {
		this.actions = actions;
	}

	@Override
   public boolean isAllowed() {
		for (IUserAction a : actions) {
			if (!a.isAllowed()) return false;
		}
	   return true;
   }

	@Override
   public void execute() {
		for (IUserAction a : actions) a.execute();
   }
}
