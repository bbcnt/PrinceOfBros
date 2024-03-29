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

/**
 * Represent the current world of the game from a loaded TiledMap.
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Ngo Quang Dung
 * @author Schweizer Thomas
 *
 */
public class LogicWorld implements Iterable<GameObject>{
	
	private static String layerName = "Content";
	
	private GameObject[][] world;
	private TiledMap map;
	private int height;
	private int width;
	
	private int idLayerContent;
	
	/**
	 * Initialize the logicworld by reading be tiledmap and creating associated Tiles.
	 * @param map The TiledMap containing the description of the world.
	 */
	public LogicWorld(TiledMap map) {
		this.map = map;
		height = map.getHeight();
		width = map.getWidth();
		world = new GameObject[height][width];

		// We are looking for tiles in the 'Content' layer.
		idLayerContent = map.getLayerIndex(layerName);
		
		// Analysing every case from the TiledMap and associating it to a logical Tile.
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				int id = map.getTileId(j, i, idLayerContent);
				
				// Create logical tiles.
				if(checkTileProperty(map, j, i, idLayerContent, "obstacle")) {
					if(checkTileProperty(map, j, i, idLayerContent, "break")) {
						world[i][j] = Builder.getInstance().createGameObject(id,j+0.5f,height - i - 0.5f, Builder.TileType.Breakable);
					} else {
						world[i][j] = Builder.getInstance().createGameObject(id, j+0.5f,height - i - 0.5f, Builder.TileType.Solid);
					}
				} else if(checkTileProperty(map, j, i, idLayerContent, "spike")) {
					world[i][j] = Builder.getInstance().createGameObject(id, j+0.5f,height - i - 0.5f, Builder.TileType.Spike);
				} else {
					world[i][j] = null;
				}
			}
		}
	}
	
	/**
	 * Converts the vertical logical position to internal vertical position.
	 * @param i The vertical position
	 * @return The converted vertical position
	 */
	private int toTabHeight(int i) {
		return height - i - 1;
	}
	
	/**
	 * Moves the tile on the screen.
	 */
	public void moveTile(int x, int y, int newX, int newY) {
		// Getting the ids that are going to get swaped.
		int oldId = map.getTileId(x, toTabHeight(y), idLayerContent);
		int newId = map.getTileId(newX, toTabHeight(newY), idLayerContent);
		
		// Swapping the tiles on the map. (Graphic representation)
		map.setTileId(x, toTabHeight(y), idLayerContent, newId);
		map.setTileId(newX, toTabHeight(newY), idLayerContent, oldId);
		
		// Swapping the tiles on the map. (Logic representation)
		GameObject temp = world[toTabHeight(y)][x];
		world[toTabHeight(y)][x] = world[toTabHeight(newY)][newX];
		world[toTabHeight(newY)][newX] = temp;
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
		return world[toTabHeight(y)][x];
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
	 * Removes a tile from the game.
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
	 */
   public void removeTile(int x, int y) {
   	if(x >= width || y >= height || x < 0 || y < 0)
			throw new IllegalArgumentException("The x or y are out of the map bounds!");
		map.setTileId(x, toTabHeight(y), idLayerContent, 0);
		world[toTabHeight(y)][x] = null;
   }
   
	/**
	 * Removes a tile from the game.
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
	 */
   public void removeTile(float x, float y) {
   	removeTile((int)Math.floor(x), (int)Math.floor(y));
   }
   
   /**
    * Creates a tile in the game.
    * @param id The id of the tile (TiledMap id)
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
    * @return The new Tile
    */
   public GameObject createTile(int id, float x, float y) {
   	return createTile(id, (int)Math.floor(x), (int)Math.floor(y));
   }
   
   /**
    * Creates a tile in the game.
    * @param id The id of the tile (TiledMap id)
	 * @param x The horizontal position of the tile.
	 * @param y The vertical position of the tile.
    * @return The new Tile
    */
   public GameObject createTile(int id, int x, int y) {
   	GameObject g = Builder.getInstance().createGameObject(id, x+0.5f,toTabHeight(y), Builder.TileType.Breakable);
   	world[toTabHeight(y)][x] = g;
   	map.setTileId(x, toTabHeight(y), idLayerContent, id);
   	return g;
   }
	
   /**
    * Returns the width of the world.
    * @return The width of the world
    */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * Returns the height of the world.
	 * @return The height of the world
	 */
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
