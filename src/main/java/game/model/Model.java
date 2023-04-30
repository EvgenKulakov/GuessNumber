package game.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Model {
    private int secretNumber;
    private int moveNumber;
    private Set<Integer> useNumbers;
    private static boolean notFirstGame;

    public Model() {
        createSecretNumber();
        initializeMoveNumber();
        resetUseNumbers();
    }

    private void createSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(1000);
    }

    private void initializeMoveNumber() {
        moveNumber = 1;
    }

    private void resetUseNumbers() {
        useNumbers = new HashSet<>(9);
    }

    public void incrementMoveNumber() {
        moveNumber++;
    }

    public static void notFirstGame() {
        notFirstGame = true;
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
    public static boolean isNotFirstGame() {
        return notFirstGame;
    }
}
