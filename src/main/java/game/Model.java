package game;

import java.util.Random;
import java.util.Set;

public class Model {
    private Controller controller;
    private boolean stopGame;
    private int secretNumber;
    private int count;
    private Set<Integer> useNumbers;

    public void incrementCount() {
        count++;
    }

    public int createSecretNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public Set<Integer> getUseNumbers() {
        return useNumbers;
    }
    public void setUseNumbers(Set<Integer> useNumbers) {
        this.useNumbers = useNumbers;
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
