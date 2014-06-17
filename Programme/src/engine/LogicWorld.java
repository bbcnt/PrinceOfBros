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

import java.util.Iterator;

import org.newdawn.slick.tiled.TiledMap;

import engine.interaction.Builder;
import engine.interaction.GameObject;
import engine.interaction.tiles.Tile;

/**
 * Represent the current world of the game.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class LogicWorld implements Iterable<GameObject>{
	
	private GameObject[][] world;
	private TiledMap map;
	private int height;
	private int width;
	
	private int idLayerContent;
	
	public LogicWorld(TiledMap map) {
		this.map = map;
		height = map.getHeight();
		width = map.getWidth();
		world = new GameObject[height][width];

		idLayerContent = map.getLayerIndex("Content");
		
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {

				// Create world tiles.
				if(checkTileProperty(map, j, i, idLayerContent, "obstacle"))
					if(checkTileProperty(map, j, i, idLayerContent, "break"))
						world[i][j] = Builder.getInstance().createGameObject(j+0.5f,height - i - 0.5f, Builder.TileType.Breakable);
					else
						world[i][j] = Builder.getInstance().createGameObject(j+0.5f,height - i - 0.5f, Builder.TileType.Solid);
				else
					world[i][j] = null;
			}
		}
	}
	
	/**
	 * Moves the tile on the screen.
	 */
	public void moveTile(int x, int y, int newX, int newY) {
		int oldId = map.getTileId(x, height - y - 1, idLayerContent);
		
		int newId = map.getTileId(newX, height - newY - 1, idLayerContent);
		
		map.setTileId(x, height - y - 1, idLayerContent, newId);
		map.setTileId(newX, height - newY - 1, idLayerContent, oldId);
		
		GameObject temp = world[height - y - 1][x];
		world[height - y - 1][x] = world[height - newY - 1][newX];
		world[height - newY - 1][newX] = temp;
	}
	
	/**
	 * Check if the property of a given tile exists.
	 * @param map The TiledMap containing the tile
	 * @param x The horizontal position of the tile
	 * @param y The vertical position of the tile
	 * @param layer The layer of the map where the tile is on
	 * @param property The property we are looking for
	 * @return True if this tile contains the property, false otherwise.
	 */
	private boolean checkTileProperty(TiledMap map, int x, int y, int layer, String property) {
		return Boolean.valueOf(map.getTileProperty(map.getTileId(x, y, layer), property, "false"));
	}
	
	/**
	 * Returns the gameObject on the tile (x,y)
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
	 * @return The game object on this tile or null if there is not one on the given position.
	 */
	public GameObject getTile(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0)
			throw new IllegalArgumentException("The x or y are out of the map bounds!");
		return world[height - y - 1][x];
	}
	
	/**
	 * Returns the gameObject on the tile (x,y)
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
	 * @return The game object on this tile or null if there is not one on the given position.
	 */
   public GameObject getTile(float x, float y) {
	   return getTile((int)Math.floor(x), (int)Math.floor(y));
   }
	
	/**
	 * Simply prints a quick representation of the world. (Debug)
	 */
	public String toString() {
		String result = "Map: " + height + " rows X " + width + " columns\n";
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				result += (world[i][j] != null?(((Tile)world[i][j]).isBreakable()?"brea":"soli"): "null") + "|";
			}
			result += "\n";
		}
		return result;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
   public Iterator<GameObject> iterator() {
		Iterator<GameObject> result = new Iterator<GameObject>() {
			
			private int position = 0;
			
			@Override
			public void remove() {
				throw new RuntimeException("The remove feature is disabled");
			}
			
			@Override
			public GameObject next() {
				GameObject result = world[position / width][position % width];
				position++;
				return result;
			}
			
			@Override
			public boolean hasNext() {
				return position < height * width;
			}
		};
	   return result;
   }
}
