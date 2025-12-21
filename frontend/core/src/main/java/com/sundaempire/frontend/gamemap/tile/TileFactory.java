package com.sundaempire.frontend.gamemap.tile;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sundaempire.frontend.gamemap.tile.environments.TileEnvironmentGrass;
import com.sundaempire.frontend.gamemap.tile.environments.TileEnvironmentMountain;
import com.sundaempire.frontend.gamemap.tile.environments.TileEnvironmentWater;
import com.sundaempire.frontend.noise.NoisePerlin;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {
    private final NoisePerlin noise;

    public TileFactory(long seed, int gridSize) {
        noise = new NoisePerlin(seed, gridSize);
    }

    public Tile create(Vector2 tilePosition, Vector2 tileDimensions, TileEnvironment tileEnvironment, List<TileProp> tileProps) {
        Tile tile = new Tile();
        tile.setEnvironment(tileEnvironment);

        tile.setPosition(tilePosition);
        tile.setDimensions(tileDimensions);

        tileEnvironment.setPosition(tilePosition);
        tileEnvironment.setPosition(tileDimensions);

        for (TileProp tileProp : tileProps) {
            tileProp.setPosition(tilePosition);
            tileProp.setDimensions(tileDimensions);
            tile.addProp(tileProp);
        }

        return tile;
    }

    public Tile createRandom(float x, float y) {
        float random = noise.generate(x, y);

        if (random <= 0.25f) {
            return create(new Vector2(0f, 0f), new Vector2(0f, 0f), new TileEnvironmentWater(), new ArrayList<>());
        }
        else if (random <= 0.8f) {
            return create(new Vector2(0f, 0f), new Vector2(0f, 0f), new TileEnvironmentGrass(), new ArrayList<>());
        }
        else {
            return create(new Vector2(0f, 0f), new Vector2(0f, 0f), new TileEnvironmentMountain(), new ArrayList<>());
        }

    }

    public Tile createDefault() {
        return create(new Vector2(0f, 0f), new Vector2(0f, 0f), new TileEnvironmentGrass(), new ArrayList<>());
    }
}
