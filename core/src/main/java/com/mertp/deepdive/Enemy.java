
package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private float x, y;
    private Texture texture;

    public Enemy(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void update(float speed) {
        x -= speed;
    }

    public void reset(float screenWidth, float screenHeight) {
        x = screenWidth;
        y = (float) Math.random() * (screenHeight - screenHeight / 8);
    }

    public void draw(SpriteBatch batch, float width, float height) {
        batch.draw(texture, x, y, width, height);
    }

    public boolean checkCollision(float playerX, float playerY, float playerW, float playerH, float width, float height) {
        return playerX < x + width &&
               playerX + playerW > x &&
               playerY < y + height &&
               playerY + playerH > y;
    }

    public float getX() {
        return x;
    }
}
