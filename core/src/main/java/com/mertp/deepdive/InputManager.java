package com.mertp.deepdive;

import com.badlogic.gdx.Gdx;

public class InputManager {

    public boolean isScreenTapped() {
        return Gdx.input.justTouched();
    }

    public boolean isTouchingUpperHalf() {
        return Gdx.input.isTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() / 2;
    }

    public boolean isTouchingLowerHalf() {
        return Gdx.input.isTouched() && Gdx.input.getY() >= Gdx.graphics.getHeight() / 2;
    }

    public float getTouchY() {
        return Gdx.input.getY();
    }

    public int getTouchYFromTop() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }
}
