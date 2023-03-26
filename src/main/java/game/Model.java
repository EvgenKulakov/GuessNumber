package game;

import java.util.Random;

public class Model {
    private Controller controller;
    private boolean stopGame = false;
    private int secretNumber;
    private int count;

    public void incrementCount() {
        count++;
    }

    public int createSecretNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public boolean isStopGame() {
        return stopGame;
    }
    public void setStopGame(boolean stopGame) {
        this.stopGame = stopGame;
    }
    public int getSecretNumber() {
        return secretNumber;
    }
    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
