package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Compass {
    private float x, y;
    private float speed;
    private Texture texture;
    private float width = 96;
    private float height = 96;

    public Compass(Texture texture, float x, float y, float speed) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update() {
        x -= speed;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public boolean isCollected(float playerX, float playerY, float playerW, float playerH) {
        return playerX < x + width &&
            playerX + playerW > x &&
            playerY < y + height &&
            playerY + playerH > y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
