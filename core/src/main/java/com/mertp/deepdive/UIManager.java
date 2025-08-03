package com.mertp.deepdive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class UIManager {

    private BitmapFont font;
    private Texture heartTexture;

    public UIManager() {
        font = new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.WHITE);
        heartTexture = new Texture("heart.png");
    }

    public void drawGameInfo(SpriteBatch batch, GameManager gameManager, LevelManager levelManager, float screenHeight) {
        font.draw(batch, "Compass: " + gameManager.getCompassCount(), 50, screenHeight - 100);
        font.draw(batch, "Level: " + levelManager.getLevel(), 50, screenHeight - 150);
        drawHearts(batch, gameManager.getHealth());
    }

    public void drawHearts(SpriteBatch batch, int count) {
        float x = 50;
        float y = Gdx.graphics.getHeight() - 60;
        for (int i = 0; i < count; i++) {
            batch.draw(heartTexture, x + i * 50, y, 40, 40);
        }
    }

    public void drawCenteredMessage(SpriteBatch batch, String message, float screenWidth, float screenHeight) {
        GlyphLayout layout = new GlyphLayout(font, message);
        float x = (screenWidth - layout.width) / 2f;
        float y = screenHeight / 2f;
        font.draw(batch, layout, x, y);
    }

    public void drawTripleMessage(SpriteBatch batch, String top, String middle, String bottom, float screenWidth, float screenHeight) {
        GlyphLayout layoutTop = new GlyphLayout(font, top);
        GlyphLayout layoutMid = new GlyphLayout(font, middle);
        GlyphLayout layoutBot = new GlyphLayout(font, bottom);

        float centerX = screenWidth / 2f;
        float centerY = screenHeight / 2f;
        float spacing = 70;

        font.draw(batch, layoutTop, centerX - layoutTop.width / 2f, centerY + spacing);
        font.draw(batch, layoutMid, centerX - layoutMid.width / 2f, centerY);
        font.draw(batch, layoutBot, centerX - layoutBot.width / 2f, centerY - spacing);
    }

    public void drawMainMenu(SpriteBatch batch, float screenWidth, float screenHeight) {
        GlyphLayout newGameLayout = new GlyphLayout(font, "NEW GAME");
        GlyphLayout continueLayout = new GlyphLayout(font, "CONTINUE");

        float centerX = screenWidth / 2f;
        float y = screenHeight / 2f;

        font.draw(batch, newGameLayout, centerX - newGameLayout.width / 2f, y + 40);
        font.draw(batch, continueLayout, centerX - continueLayout.width / 2f, y - 40);
    }

    public void drawPauseMenu(SpriteBatch batch, float screenWidth, float screenHeight) {
        GlyphLayout resume = new GlyphLayout(font, "RESUME");
        GlyphLayout restart = new GlyphLayout(font, "RESTART");
        GlyphLayout main = new GlyphLayout(font, "MAIN MENU");

        float centerX = screenWidth / 2f;
        float y = screenHeight / 2f;

        font.draw(batch, resume, centerX - resume.width / 2f, y + 60);
        font.draw(batch, restart, centerX - restart.width / 2f, y);
        font.draw(batch, main, centerX - main.width / 2f, y - 60);
    }

    public void drawLevelSelection(SpriteBatch batch, int maxLevel, int unlockedLevel) {
        float startX = 100;
        float startY = Gdx.graphics.getHeight() - 200;
        float spacing = 120;
        int cols = 4;

        for (int i = 1; i <= maxLevel; i++) {
            boolean unlocked = i <= unlockedLevel;
            font.setColor(unlocked ? Color.WHITE : Color.GRAY);
            String label = "Level " + i;

            GlyphLayout layout = new GlyphLayout(font, label);
            float x = startX + ((i - 1) % cols) * spacing;
            float y = startY - ((i - 1) / cols) * spacing;

            font.draw(batch, layout, x, y);
        }

        font.setColor(Color.WHITE);
    }

    public void dispose() {
        font.dispose();
        heartTexture.dispose();
    }
}
