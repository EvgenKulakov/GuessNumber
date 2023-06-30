package game.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Model {
    private int secretNumber;
    private int moveNumber;
    private int useNumber;
    private final Set<Integer> useNumbers;
    private boolean notFirstGame;

    public Model() {
        createSecretNumber();
        moveNumber = 1;
        useNumbers = new HashSet<>();
        notFirstGame = false;
    }

    private void createSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(1000);
    }

    public void resetModel() {
        createSecretNumber();
        moveNumber = 1;
        useNumbers.clear();
        notFirstGame = true;
    }

    public void incrementMoveNumber() {
        moveNumber++;
    }

    public int getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(int useNumber) {
        this.useNumber = useNumber;
    }

    public boolean isNotFirstGame() {
        return notFirstGame;
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
