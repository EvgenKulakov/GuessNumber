package game;

import javax.swing.*;
import java.util.HashSet;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void startGame() {
        view.initStartGame();
        model.createSecretNumber();
        model.setCount(1);
        model.setUseNumbers(new HashSet<>(9));
    }

    public void nextMove(int useNumber) {
        // проверка корректности ввода
        if (!inputValidation(useNumber)) return;

        model.incrementCount();

        // проверка окончания игры
        if (isGameOver(useNumber)) return;

        if (model.getSecretNumber() < useNumber) {
            view.showDialog(Messages.WRONG, Messages.NUMBER_LESS,
                    JOptionPane.WARNING_MESSAGE);
        }

        if (model.getSecretNumber() > useNumber) {
            view.showDialog(Messages.WRONG, Messages.NUMBER_GREATER,
                    JOptionPane.WARNING_MESSAGE);
        }

        view.getLabelText().setText(String.format(Messages.ENTER_NUMBER, model.getCount()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean inputValidation(int useNumber) {
        if (useNumber > 999 || useNumber < 0) {
            view.showDialog(Messages.ERROR, Messages.ERROR_NUMBER,
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (model.getUseNumbers().contains(useNumber)) {
            view.showDialog(Messages.WRONG,
                    String.format(Messages.CHANGE_NUMBER, useNumber),
                    JOptionPane.QUESTION_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isGameOver(int useNumber) {
        if (model.getCount() > 10 && model.getSecretNumber() != useNumber) {
            gameOver();
            return true;
        }

        if (model.getSecretNumber() == useNumber) {
            victory();
            return true;
        }

        return false;
    }

    private void gameOver() {
        view.showDialog(Messages.YOU_LOSE,
                String.format(Messages.CORRECT_ANSWER, model.getSecretNumber()),
                JOptionPane.ERROR_MESSAGE);
        repeatGame();
    }

    private void victory() {
        view.showDialog(String.format(Messages.VICTORY, model.getSecretNumber()),
                Messages.HINT,
                JOptionPane.INFORMATION_MESSAGE);
        view.getButtonRight().setText(Messages.BUTTON_CLUE);
    }

    private void repeatGame() {
        View newView = new View();
        Model newModel = new Model();
        Controller newController = new Controller(newView, newModel);
        newView.setController(newController);
        newView.getButtonRight().setText(Messages.BUTTON_PLAY_MORE);
        view.dispose();
    }
}
