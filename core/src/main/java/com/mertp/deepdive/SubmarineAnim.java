package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class SubmarineAnim {
    private Animation<TextureRegion> idleAnim;
    private Animation<TextureRegion> hitAnim;
    private float stateTime = 0f;
    private float x, y;
    private boolean isHit = false;

    public SubmarineAnim(Texture[] idleFrames, Texture[] hitFrames, float frameDuration, float x, float y) {
        TextureRegion[] idleRegions = new TextureRegion[idleFrames.length];
        TextureRegion[] hitRegions = new TextureRegion[hitFrames.length];

        for (int i = 0; i < idleFrames.length; i++) idleRegions[i] = new TextureRegion(idleFrames[i]);
        for (int i = 0; i < hitFrames.length; i++) hitRegions[i] = new TextureRegion(hitFrames[i]);

        idleAnim = new Animation<>(frameDuration, idleRegions);
        hitAnim = new Animation<>(frameDuration, hitRegions);
        idleAnim.setPlayMode(Animation.PlayMode.LOOP);
        hitAnim.setPlayMode(Animation.PlayMode.NORMAL);

        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(SpriteBatch batch, float width, float height) {
        TextureRegion currentFrame = isHit ? hitAnim.getKeyFrame(stateTime) : idleAnim.getKeyFrame(stateTime);
        batch.draw(currentFrame, x, y, width, height);

        if (isHit && hitAnim.isAnimationFinished(stateTime)) {
            isHit = false;
            stateTime = 0;
        }
    }

    public void setHit() {
        isHit = true;
        stateTime = 0;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
