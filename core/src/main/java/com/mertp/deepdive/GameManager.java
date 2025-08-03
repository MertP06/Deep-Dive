package com.mertp.deepdive;

public class GameManager {
    private int compassCount = 0;
    private int health = 1;

    public void collectCompass() {
        compassCount++;
    }

    public void resetCompass() {
        compassCount = 0;
    }

    public void damage() {
        if (health > 0) {
            health--;
        }
    }

    public void heal() {
        health++;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getCompassCount() {
        return compassCount;
    }

    public int getHealth() {
        return health;
    }

    public void reset() {
        compassCount = 0;
        health = 1;
    }
}
