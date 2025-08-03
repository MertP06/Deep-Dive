package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EnemyManager {
    private ArrayList<AnimatedEntity> enemies = new ArrayList<>();
    private Texture[] monsterFrames;
    private int numberOfEnemies;
    private float screenWidth, screenHeight;
    private float enemyWidth, enemyHeight;
    private Random random = new Random();

    public EnemyManager(Texture[] monsterFrames, int numberOfEnemies, float screenWidth, float screenHeight) {
        this.monsterFrames = monsterFrames;
        this.numberOfEnemies = numberOfEnemies;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.enemyWidth = (screenWidth / 10f) * 0.8f;
        this.enemyHeight = (screenHeight / 8f) * 0.8f;

        spawnEnemies();
    }

    private void spawnEnemies() {
        enemies.clear();
        for (int i = 0; i < numberOfEnemies; i++) {
            float x = screenWidth + i * 400;
            float y = random.nextFloat() * (screenHeight - enemyHeight);
            AnimatedEntity monster = new AnimatedEntity(monsterFrames, 0.08f, x, y);
            enemies.add(monster);
        }
    }

    public void update(float delta, float speed) {
        for (AnimatedEntity enemy : enemies) {
            enemy.update(delta);
            float newX = enemy.getX() - speed;
            enemy.setPosition(newX, enemy.getY());
        }

        for (AnimatedEntity enemy : enemies) {
            if (enemy.getX() + enemyWidth < 0) {
                float resetX = screenWidth + random.nextInt(300);
                float resetY = random.nextFloat() * (screenHeight - enemyHeight);
                enemy.setPosition(resetX, resetY);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (AnimatedEntity enemy : enemies) {
            enemy.draw(batch, enemyWidth, enemyHeight);
        }
    }

    public boolean checkCollision(SubmarineAnim submarine, float subWidth, float subHeight) {
        Iterator<AnimatedEntity> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            AnimatedEntity enemy = iterator.next();
            if (submarine.getX() < enemy.getX() + enemyWidth &&
                submarine.getX() + subWidth > enemy.getX() &&
                submarine.getY() < enemy.getY() + enemyHeight &&
                submarine.getY() + subHeight > enemy.getY()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void reset() {
        spawnEnemies();
    }
}
