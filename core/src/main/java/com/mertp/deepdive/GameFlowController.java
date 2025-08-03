package com.mertp.deepdive;

public class GameFlowController {
    private GameState currentState = GameState.MAIN_MENU;

    public GameState getState() {
        return currentState;
    }

    public void goToMainMenu() {
        currentState = GameState.MAIN_MENU;
    }

    public void goToLevelSelect() {
        currentState = GameState.LEVEL_SELECT;
    }

    public void startGame() {
        currentState = GameState.PLAYING;
    }

    public void showLevelTransition() {
        currentState = GameState.LEVEL_TRANSITION;
    }

    public void confirmTransition() {
        currentState = GameState.PLAYING;
    }

    public void gameOver() {
        currentState = GameState.GAME_OVER;
    }

    public void pauseGame() {
        currentState = GameState.PAUSED;
    }

    public void resumeGame() {
        currentState = GameState.PLAYING;
    }

    public boolean is(GameState state) {
        return currentState == state;
    }
}
