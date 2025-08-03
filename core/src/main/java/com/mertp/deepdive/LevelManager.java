package com.mertp.deepdive;

public class LevelManager {
    private int level = 1;
    private final int[] thresholds = {5, 10, 15, 20};
    private boolean isInTransition = false;
    private boolean waitingForTap = false;

    public boolean checkLevelUp(int compassCount) {
        if (!isInTransition && level <= thresholds.length && compassCount >= thresholds[level - 1]) {
            isInTransition = true;
            waitingForTap = true;
            return true;
        }
        return false;
    }

    public void levelUp() {
        level++;
        isInTransition = true;
        waitingForTap = true;
    }

    public boolean isTransitioning() {
        return isInTransition;
    }

    public boolean isWaitingForTap() {
        return waitingForTap;
    }

    public void confirmTransition() {
        waitingForTap = false;
        isInTransition = false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameFinished() {
        return level > thresholds.length;
    }

    public int getThreshold() {
        return level <= thresholds.length ? thresholds[level - 1] : -1;
    }

    public void reset() {
        level = 1;
        isInTransition = false;
        waitingForTap = false;
    }

    public void setLevelDirectly(int targetLevel) {
        this.level = targetLevel;
        this.isInTransition = false;
        this.waitingForTap = false;
    }
}
