package com.sundaempire.frontend.noise;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class NoisePerlin {
    private final Random random = new Random();
    private final int gridSize;

    public NoisePerlin(long seed, int gridSize) {
        random.setSeed(seed);
        this.gridSize = gridSize;
    }

    public float generate(float x, float y) {
        int x0 = MathUtils.floor(x / gridSize);
        int y0 = MathUtils.floor(y / gridSize);

        float dx = x / gridSize - (float)x0;
        float dy = y / gridSize - (float)y0;

        Vector2 gradient00 = gradient(random.nextFloat() * 2f * MathUtils.PI);
        Vector2 gradient01 = gradient(random.nextFloat() * 2f * MathUtils.PI);
        Vector2 gradient10 = gradient(random.nextFloat() * 2f * MathUtils.PI);
        Vector2 gradient11 = gradient(random.nextFloat() * 2f * MathUtils.PI);

        float dot00 = gradient00.x * dx + gradient00.y * dy;
        float dot01 = gradient01.x * dx + gradient01.y * (dy - 1);
        float dot10 = gradient10.x * (dx - 1) + gradient10.y * dy;
        float dot11 = gradient11.x * (dx - 1) + gradient11.y * (dy - 1);

        float dxSmooth = smoothstep(dx);
        float dySmooth = smoothstep(dy);

        float noise = lerp(dySmooth, lerp(dxSmooth, dot00, dot10), lerp(dxSmooth, dot01, dot11));
        noise *= (float)Math.sqrt(2f);

        return noise;
    }

    private Vector2 gradient(float angle) {
        return new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));
    }

    private float lerp(float value, float lower, float upper) {
        return lower + value * (upper - lower);
    }

    private float smoothstep(float value) {
        return value * value * value * (value * (value * 6 - 15) + 10);
    }
}
