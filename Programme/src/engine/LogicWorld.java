/* ============================================================================
 * Filename   : LogicWorld.java
 * ============================================================================
 * Created on : 3 juin 2014
 * ============================================================================
 * Authors    : Brito Carvalho Bruno
 *              Decorvet Grégoire
 *              Ngo Quang Dung
 *              Schweizer Thomas
 * ============================================================================
 */
package engine;

import org.newdawn.slick.tiled.TiledMap;

import engine.interaction.GameObject;

/**
 * Represent the current world of the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class LogicWorld {
	
	private GameObject[][] world;
	private int height;
	private int width;
	
	public LogicWorld(TiledMap map) {
		height = map.getHeight();
		width = map.getWidth();
		world = new GameObject[height][width];

		int idLayerContent = map.getLayerIndex("Content");
		
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				// Debug les tiles considerées comme des obstacles.
				System.out.print(map.getTileProperty(map.getTileId(j, i, idLayerContent), "obstacle", "false") + "|");
				
				//world[i][j] = Builder.createGameObject(...stuff...)
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns the gameObject on the tile (x,y)
	 * @param x The column of the tile.
	 * @param y The line of the tile.
	 * @return
	 */
	public GameObject getTile(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0)
			throw new IllegalArgumentException();
		return world[y][x];
	}
}
