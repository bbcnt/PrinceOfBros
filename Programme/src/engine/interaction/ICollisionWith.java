/* ============================================================================
 * Filename   : ICollisionWith.java
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

import engine.interaction.tiles.Tile;
import engine.models.player.Player;

/**
 * Defines the action todo when a game objet collision with a certain object.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 */
public interface ICollisionWith {
	public void action(Player p);
	public void action(Enemy e);
	public void action(Tile t);
	public void action(Weapon w);
}
