package map;

import tile.Tile;
import java.util.Random;

public abstract class Map {

    protected int width;
    protected int height;
    protected Tile[][] tiles;
    protected Random random = new Random();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
        generateMap();
    }

    // Factory Method
    protected abstract Tile createTile();

    private void generateMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = createTile();
            }
        }
    }

    // Completely agnostic of concrete tile types
    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(tiles[y][x].getCharacter() + " ");
            }
            System.out.println();
        }
    }
}
