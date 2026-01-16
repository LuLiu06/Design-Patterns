package tile;

public class ForestTile extends Tile {

    @Override
    public char getCharacter() {
        return 'F';
    }

    @Override
    public String getType() {
        return "forest";
    }

    @Override
    public void action() {
        // Not used
    }
}
