package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.tile.environments.TileEnvironmentGrass;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {
    public Tile create(Vector2 tilePosition, Vector2 tileDimensions, TileEnvironment tileEnvironment, List<TileProp> tileProps) {
        Tile tile = new Tile();
        tile.setPosition(tilePosition);
        tile.setDimensions(tileDimensions);

        tile.setEnvironment(tileEnvironment);

        for (TileProp tileProp : tileProps) {
            tile.addProp(tileProp);
        }

        return tile;
    }

    public Tile createRandom(Vector2 tilePosition, Vector2 tileDimensions) {
        TileEnvironment tileEnvironment = new TileEnvironmentGrass();
        return create(tilePosition, tileDimensions, tileEnvironment, new ArrayList<TileProp>());
    }
}
