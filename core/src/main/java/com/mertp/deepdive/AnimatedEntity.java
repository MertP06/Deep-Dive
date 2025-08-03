package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedEntity {

    private Animation<TextureRegion> animation;
    private float stateTime = 0f;
    private float x, y;

    public AnimatedEntity(Texture[] frames, float frameDuration, float x, float y) {
        TextureRegion[] regions = new TextureRegion[frames.length];
        for (int i = 0; i < frames.length; i++) {
            regions[i] = new TextureRegion(frames[i]);
        }
        animation = new Animation<>(frameDuration, regions);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(SpriteBatch batch, float width, float height) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        batch.draw(currentFrame, x, y, width, height);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
