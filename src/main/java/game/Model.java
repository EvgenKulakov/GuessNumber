package game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Model {
    private int secretNumber;
    private int moveNumber;
    private Set<Integer> useNumbers;

    public void createSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(1000);
    }

    public void initializeMoveNumber() {
        moveNumber = 1;
    }
    public void incrementMoveNumber() {
        moveNumber++;
    }

    public void resetUseNumbers() {
        useNumbers = new HashSet<>(9);
    }

    public Set<Integer> getUseNumbers() {
        return useNumbers;
    }
    public int getSecretNumber() {
        return secretNumber;
    }
    public int getMoveNumber() {
        return moveNumber;
    }
}
