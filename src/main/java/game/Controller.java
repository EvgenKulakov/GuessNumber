package game;

import java.util.Random;

public class Controller {
    private final View view;
    private boolean stopGame = false;
    private int secretNumber;
    private int count;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
    }

    public void startGame() {
        setStopGame(false);
        secretNumber = createSecretNumber();
        count = 1;
    }

    public void nextMove(int useNumber) {
        if (isStopGame()) {
            view.showDialog("Игра уже сыграна", "Четвёртая цифра ПЯТЬ!");
            return;
        }

        if (useNumber > 999 || useNumber < 0) {
            view.errorMessage("Ошибка", "Нужно ввести число от 0 до 999\n" +
                    "(если хочешь победить)");
            return;
        }

        count++;

        if (count > 10 && secretNumber != useNumber) {
            gameOver();
            return;
        }

        if (secretNumber < useNumber) {
            view.wrongMessage("Загаданное число меньше", count);
        } else if (secretNumber > useNumber) {
            view.wrongMessage("Загаданное число больше", count);
        } else {
            setStopGame(true);
            view.showDialog("Ты угадал! Это число " + secretNumber, "<html>Подсказка:<br>Четвёртая цифра ПЯТЬ!<html>");
        }
    }

    int createSecretNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    void gameOver() {
        view.errorMessage("Ты проиграл!", "Правильный ответ был " + secretNumber);
        view.repeatGame();
    }

    public View getView() {
        return view;
    }

    public boolean isStopGame() {
        return stopGame;
    }

    public void setStopGame(boolean stopGame) {
        this.stopGame = stopGame;
    }
}
