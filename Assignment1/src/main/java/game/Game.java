package game;

import map.Map;
import map.CityMap;
import map.WildernessMap;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();

        // Change "city" to "wilderness" to test the other map
        Map map = game.createMap("city", 10, 6);
        map.display();
    }

    public Map createMap(String type, int width, int height) {
        if (type.equalsIgnoreCase("city")) {
            return new CityMap(width, height);
        } else {
            return new WildernessMap(width, height);
        }
    }
}
