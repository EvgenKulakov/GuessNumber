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

    /* левая кнопка */
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

    /* правая кнопка */
    public void actionRightButton(String buttonText, String inputText) {
        switch (buttonText) {
            case BUTTON_FORTH:
                checkAndMove(inputText);
                break;
            case BUTTON_I_KNEW:
                view.showDialog(NOT_FUNNY, YOU_DID_NOT_KNOW,
                        JOptionPane.WARNING_MESSAGE);
                break;
            case BUTTON_LETS_GAME:
            case BUTTON_PLAY_MORE:
                view.initStartGame();
                break;
        }
    }

    public void checkAndMove(String inputText) {
        if (!inputText.isEmpty()) {
            nextMove(Integer.parseInt(inputText));
        } else {
            view.showDialog(ERROR, EMPTY_STRING, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nextMove(int useNumber) {
        /* число уже было? */
        if (model.getUseNumbers().contains(useNumber)) {
            view.showDialog(WRONG, String.format(CHANGE_NUMBER, useNumber),
                    JOptionPane.QUESTION_MESSAGE);
            return;
        }

        /* увеличение количества попыток */
        model.incrementMoveNumber();

        /* проверка окончания игры */
        if (isGameOver(useNumber)) return;

        /* действия после хода */
        if (model.getSecretNumber() < useNumber) {
            view.showDialog(WRONG, NUMBER_LESS, JOptionPane.WARNING_MESSAGE);
        }

        if (model.getSecretNumber() > useNumber) {
            view.showDialog(WRONG, NUMBER_GREATER, JOptionPane.WARNING_MESSAGE);
        }

        view.getLabelText().setText(String.format(ENTER_NUMBER, model.getMoveNumber()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean isGameOver(int useNumber) {
        boolean end = false;

        if (model.getMoveNumber() > 10 && model.getSecretNumber() != useNumber) {
            gameOver();
            end = true;
        }

        if (model.getSecretNumber() == useNumber) {
            victory();
            end = true;
        }

        return end;
    }

    private void gameOver() {
        view.showDialog(Messages.YOU_LOSE,
                String.format(CORRECT_ANSWER, model.getSecretNumber()),
                BUTTON_REBOOT, JOptionPane.ERROR_MESSAGE);
        repeatGame();
    }

    public void victory() {
        view.showDialog(String.format(VICTORY, model.getSecretNumber()), HINT,
                JOptionPane.INFORMATION_MESSAGE);
        view.getLabelText().setText(HINT);
        view.getInputText().setVisible(false);
        view.getButtonLeft().setText(BUTTON_THANKS);
        view.getButtonRight().setText(BUTTON_I_KNEW);
    }

    private void repeatGame() {
        view.dispose();
        Model.notFirstGame();
        MainClass.start();
    }
}
