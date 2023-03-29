package game;

import javax.swing.*;
import static game.Messages.*;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void actionLeftButton(String buttonText) {
        switch (buttonText) {
            case BUTTON_NO_GAME:
                view.showDialog(BUTTON_NO_GAME, Messages.NO_GAME, JOptionPane.ERROR_MESSAGE);
                break;
            case BUTTON_MANUAL:
                view.showDialog(BUTTON_MANUAL, Messages.MANUAL, JOptionPane.INFORMATION_MESSAGE);
                break;
            case BUTTON_THANKS:
                view.showDialog(YOU_ARE_WELCOME, BYE, JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    public void actionRightButton(String buttonText) {
        switch (buttonText) {
            case BUTTON_FORTH:
                readAndMove();
                break;
            case BUTTON_CLUE:
                view.showDialog(BUTTON_CLUE, SECOND_HINT, JOptionPane.INFORMATION_MESSAGE);
                break;
            case BUTTON_LETS_GAME:
            case BUTTON_PLAY_MORE:
                view.initStartGame();
                break;
        }
    }

    private void readAndMove() {
        try {
            String text = view.getInputText().getText().trim();
            nextMove(Integer.parseInt(text));
        } catch (NumberFormatException ignored) {
            view.showDialog(ERROR, INCORRECT_CHAR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nextMove(int useNumber) {
        // проверка корректности ввода
        if (!checkNumber(useNumber)) return;

        model.incrementMoveNumber();

        // проверка окончания игры
        if (isGameOver(useNumber)) return;

        if (model.getSecretNumber() < useNumber) {
            view.showDialog(WRONG, NUMBER_LESS, JOptionPane.WARNING_MESSAGE);
        }

        if (model.getSecretNumber() > useNumber) {
            view.showDialog(WRONG, NUMBER_GREATER, JOptionPane.WARNING_MESSAGE);
        }

        view.getLabelText().setText(String.format(ENTER_NUMBER, model.getMoveNumber()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean checkNumber(int useNumber) {
        if (useNumber > 999 || useNumber < 0) {
            view.showDialog(ERROR, ERROR_NUMBER, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (model.getUseNumbers().contains(useNumber)) {
            view.showDialog(WRONG, String.format(CHANGE_NUMBER, useNumber),
                    JOptionPane.QUESTION_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isGameOver(int useNumber) {
        if (model.getMoveNumber() > 10 && model.getSecretNumber() != useNumber) {
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
                String.format(CORRECT_ANSWER, model.getSecretNumber()),
                JOptionPane.ERROR_MESSAGE);
        repeatGame();
    }

    private void victory() {
        view.showDialog(String.format(VICTORY, model.getSecretNumber()), HINT,
                JOptionPane.INFORMATION_MESSAGE);
        view.getButtonLeft().setText(BUTTON_THANKS);
        view.getButtonRight().setText(BUTTON_CLUE);
    }

    private void repeatGame() {
        view.dispose();
        View newView = new View();
        Model newModel = new Model();
        Controller newController = new Controller(newView, newModel);
        newView.setController(newController);
        newView.getButtonRight().setText(BUTTON_PLAY_MORE);
    }
}
