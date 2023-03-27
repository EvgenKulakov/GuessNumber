package game;

import java.util.Random;
import java.util.Set;

public class Model {
    private boolean stopGame;
    private int secretNumber;
    private int count;
    private Set<Integer> useNumbers;

    public void incrementCount() {
        count++;
    }

    public void createSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(1000);
    }

    public Set<Integer> getUseNumbers() {
        return useNumbers;
    }
    public void setUseNumbers(Set<Integer> useNumbers) {
        this.useNumbers = useNumbers;
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
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
