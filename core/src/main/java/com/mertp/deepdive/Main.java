package com.mertp.deepdive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class Main extends ApplicationAdapter {
    private Music bgmMusic;
    private Sound collisionSound;
    private SpriteBatch batch;
    private Texture background, background2;
    private Texture[] monsterFrames, subIdleFrames, subHitFrames;
    private Texture compassTexture, bubbleTexture;
    private Texture pauseButton;

    private float bgX1 = 0, bgX2;
    private float submarineWidth, submarineHeight;
    private float moveSpeed = 10f;
    private float monsterSpeed = 4f;
    private float scrollSpeed = 2f;

    private SubmarineAnim submarine;
    private CompassManager compassManager;
    private EnemyManager enemyManager;
    private BubbleManager bubbleManager;
    private GameManager gameManager;
    private LevelManager levelManager;
    private UIManager uiManager;
    private InputManager inputManager;
    private GameFlowController flow;

    private Preferences prefs;
    private int maxLevelUnlocked = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        background2 = new Texture("background2.png");
        bubbleTexture = new Texture("bubble.png");
        compassTexture = new Texture("compass.png");
        pauseButton = new Texture("pause.png");
        bgmMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm.ogg"));
        bgmMusic.setLooping(true);
        bgmMusic.setVolume(0.5f);
        bgmMusic.play(); // Arka plan müziği başlasın

        collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.ogg"));


        monsterFrames = new Texture[] {
            new Texture("layer1.png"), new Texture("layer2.png"),
            new Texture("layer3.png"), new Texture("layer4.png"),
            new Texture("layer5.png"), new Texture("layer6.png"),
            new Texture("layer7.png"), new Texture("layer8.png")
        };

        subIdleFrames = new Texture[] {
            new Texture("idle1.png"), new Texture("idle2.png"),
            new Texture("idle3.png"), new Texture("idle4.png")
        };

        subHitFrames = new Texture[] {
            new Texture("hit.png"), new Texture("hit2.png")
        };

        float screenW = Gdx.graphics.getWidth();
        float screenH = Gdx.graphics.getHeight();
        submarineWidth = screenW / 10;
        submarineHeight = screenH / 8;

        submarine = new SubmarineAnim(subIdleFrames, subHitFrames, 0.1f, screenW / 4, screenH / 2);
        gameManager = new GameManager();
        levelManager = new LevelManager();
        bubbleManager = new BubbleManager(bubbleTexture);
        enemyManager = new EnemyManager(monsterFrames, 5, screenW, screenH);
        compassManager = new CompassManager(compassTexture, screenW, screenH, gameManager);
        uiManager = new UIManager();
        inputManager = new InputManager();
        flow = new GameFlowController();

        prefs = Gdx.app.getPreferences("DeepDiveProgress");
        maxLevelUnlocked = prefs.getInteger("maxLevel", 1);
        bgX2 = screenW;
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        drawBackground();

        if (flow.is(GameState.MAIN_MENU)) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            uiManager.drawCenteredMessage(batch, "NEW GAME", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            if (inputManager.isScreenTapped()) {
                flow.goToLevelSelect();
            }
            return;
        }

        if (flow.is(GameState.LEVEL_SELECT)) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            uiManager.drawLevelSelection(batch, 4, maxLevelUnlocked);
            batch.end();

            if (inputManager.isScreenTapped()) {
                int x = Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();
                int selectedLevel = getTappedLevel(x, y);
                if (selectedLevel > 0 && selectedLevel <= maxLevelUnlocked) {
                    startNewGame();
                    levelManager.setLevelDirectly(selectedLevel);
                    for (int i = 1; i < selectedLevel; i++) gameManager.heal();
                }
            }
            return;
        }

        if (flow.is(GameState.PAUSED)) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            uiManager.drawPauseMenu(batch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();

            if (inputManager.isScreenTapped()) {
                int tapY = Gdx.graphics.getHeight() - Gdx.input.getY();
                float centerY = Gdx.graphics.getHeight() / 2f;

                if (tapY > centerY + 40 && tapY < centerY + 80) {
                    // Resume
                    flow.resumeGame();
                } else if (tapY > centerY - 20 && tapY < centerY + 20) {
                    // Restart
                    startNewGame();
                } else if (tapY > centerY - 80 && tapY < centerY - 40) {
                    // Main Menu
                    resetGame();
                    flow.goToMainMenu();
                }
            }
            return;
        }

        if (flow.is(GameState.LEVEL_TRANSITION)) {
            drawBackground();
            batch.begin();
            uiManager.drawTripleMessage(batch, "CONGRATULATIONS!", "LEVEL " + levelManager.getLevel(), "TAP TO CONTINUE", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            if (inputManager.isScreenTapped()) {
                flow.confirmTransition();
                levelManager.confirmTransition();
            }
            return;
        }

        if (flow.is(GameState.GAME_OVER)) {
            drawBackground();
            batch.begin();
            if (levelManager.isGameFinished()) {
                uiManager.drawTripleMessage(batch, "CONGRATULATIONS!", "YOU COMPLETED ALL LEVELS!", "TAP TO RESTART", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            } else {
                uiManager.drawTripleMessage(batch, "GAME OVER", "", "TAP TO RESTART", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
            batch.end();
            if (inputManager.isScreenTapped()) {
                resetGame();
                flow.goToMainMenu();
            }
            return;
        }

        if (levelManager.checkLevelUp(gameManager.getCompassCount())) {
            levelManager.levelUp();
            gameManager.setHealth(levelManager.getLevel());
            gameManager.resetCompass();
            enemyManager.reset();
            compassManager = new CompassManager(compassTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameManager);
            submarine.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
            bubbleManager.setSpawnChance(0.03f);
            updateDifficulty();
            maxLevelUnlocked = Math.max(maxLevelUnlocked, levelManager.getLevel());
            prefs.putInteger("maxLevel", maxLevelUnlocked);
            prefs.flush();
            flow.showLevelTransition();
            return;
        }

        updateGame(delta);
        drawGame();
    }

    private void updateGame(float delta) {
        bgX1 -= scrollSpeed;
        bgX2 -= scrollSpeed;
        if (bgX1 + Gdx.graphics.getWidth() <= 0) bgX1 = Gdx.graphics.getWidth();
        if (bgX2 + Gdx.graphics.getWidth() <= 0) bgX2 = Gdx.graphics.getWidth();

        if (Gdx.input.justTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() - 100 && Gdx.input.getY() < 100) {
            flow.pauseGame(); return;
        }

        submarine.update(delta);
        if (inputManager.isTouchingUpperHalf()) {
            submarine.setPosition(submarine.getX(), Math.min(submarine.getY() + moveSpeed, Gdx.graphics.getHeight() - submarineHeight));
        } else if (inputManager.isTouchingLowerHalf()) {
            submarine.setPosition(submarine.getX(), Math.max(submarine.getY() - moveSpeed, 0));
        }

        if (submarine.getY() != 0) bubbleManager.spawn(submarine.getX(), submarine.getY());
        bubbleManager.update(delta);
        compassManager.update(delta, submarine, submarineWidth, submarineHeight);
        enemyManager.update(delta, monsterSpeed);

        if (enemyManager.checkCollision(submarine, submarineWidth, submarineHeight)) {
            if (!submarine.isHit()) {
                submarine.setHit();
                collisionSound.play();
                gameManager.damage();
                if (gameManager.getHealth() <= 0) flow.gameOver();
            }
        }

        if (levelManager.isGameFinished()) {
            flow.gameOver();
        }
    }

    private void drawGame() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, bgX1, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background, bgX2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        submarine.draw(batch, submarineWidth, submarineHeight);
        bubbleManager.draw(batch);
        enemyManager.draw(batch);
        compassManager.draw(batch);
        uiManager.drawGameInfo(batch, gameManager, levelManager, Gdx.graphics.getHeight());
        batch.draw(pauseButton, Gdx.graphics.getWidth() - 96, Gdx.graphics.getHeight() - 96, 64, 64);
        batch.end();
    }

    private void drawBackground() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    private void startNewGame() {
        levelManager = new LevelManager();
        gameManager = new GameManager();
        enemyManager.reset();
        compassManager = new CompassManager(compassTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameManager);
        submarine.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        monsterSpeed = 4f;
        moveSpeed = 10f;
        bubbleManager.setSpawnChance(0.03f);
        flow.startGame();
    }

    private void resetGame() {
        gameManager.reset();
        levelManager.reset();
        enemyManager.reset();
        compassManager = new CompassManager(compassTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameManager);
        submarine.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        monsterSpeed = 4f;
        moveSpeed = 10f;
        bubbleManager.setSpawnChance(0.03f);
    }

    private int getTappedLevel(int x, int y) {
        float startX = 100;
        float startY = Gdx.graphics.getHeight() - 200;
        float spacing = 120;
        int cols = 4;
        for (int i = 1; i <= 4; i++) {
            float buttonX = startX + ((i - 1) % cols) * spacing;
            float buttonY = startY - ((i - 1) / cols) * spacing;
            if (x > buttonX && x < buttonX + 100 && y < buttonY && y > buttonY - 60) {
                return i;
            }
        }
        return -1;
    }

    private void updateDifficulty() {
        int currentLevel = levelManager.getLevel();
        monsterSpeed = 4f + (currentLevel - 1);
        moveSpeed = 10f + (currentLevel - 1) * 2;
        bubbleManager.setSpawnChance(0.03f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        background2.dispose();
        compassTexture.dispose();
        bubbleTexture.dispose();
        pauseButton.dispose();
        uiManager.dispose();
        bgmMusic.dispose();
        collisionSound.dispose();
        for (Texture t : monsterFrames) t.dispose();
        for (Texture t : subIdleFrames) t.dispose();
        for (Texture t : subHitFrames) t.dispose();
    }
}
