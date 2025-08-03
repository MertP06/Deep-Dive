package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CompassManager {
    private List<Compass> compassList = new LinkedList<>();
    private Texture texture;
    private float screenWidth, screenHeight;
    private float spawnTimer = 0;
    private float spawnInterval = 2f;
    private Random random = new Random();
    private GameManager gameManager;

    public CompassManager(Texture texture, float screenWidth, float screenHeight, GameManager gameManager) {
        this.texture = texture;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameManager = gameManager;
    }

    public void update(float delta, SubmarineAnim submarine, float subW, float subH) {
        spawnTimer += delta;
        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0;
            float x = screenWidth + random.nextInt(300);
            float y = random.nextFloat() * (screenHeight - 96);
            compassList.add(new Compass(texture, x, y, 3f));
        }

        Iterator<Compass> iterator = compassList.iterator();
        while (iterator.hasNext()) {
            Compass c = iterator.next();
            c.update();

            if (c.isCollected(submarine.getX(), submarine.getY(), subW, subH)) {
                gameManager.collectCompass();
                iterator.remove();
            } else if (c.getX() + c.getWidth() < 0) {
                iterator.remove();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (Compass c : compassList) {
            c.draw(batch);
        }
    }
}
