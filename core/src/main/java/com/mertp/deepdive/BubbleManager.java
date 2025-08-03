package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BubbleManager {
    private Texture bubbleTexture;
    private List<Bubble> bubbles = new LinkedList<>();
    private float spawnChance = 0.15f;

    public BubbleManager(Texture bubbleTexture) {
        this.bubbleTexture = bubbleTexture;
    }

    public void setSpawnChance(float chance) {
        this.spawnChance = chance;
    }

    public void update(float delta) {
        Iterator<Bubble> iterator = bubbles.iterator();
        while (iterator.hasNext()) {
            Bubble b = iterator.next();
            b.y += b.speed;
            b.life -= delta;
            if (b.life <= 0) iterator.remove();
        }
    }

    public void draw(SpriteBatch batch) {
        for (Bubble b : bubbles) {
            batch.draw(bubbleTexture, b.x, b.y, b.size, b.size);
        }
    }

    public void spawn(float x, float y) {
        if (Math.random() < spawnChance) {
            bubbles.add(new Bubble(x, y, 2 + (float)Math.random() * 2, 48));
        }
    }

    private static class Bubble {
        float x, y;
        float speed;
        float size;
        float life = 1.5f;

        Bubble(float x, float y, float speed, float size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.size = size;
        }
    }
}
