package game;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

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
                view.showDialog(BUTTON_NO_GAME, NO_GAME, Alert.AlertType.ERROR);
                break;
            case BUTTON_MANUAL:
                view.showDialog(BUTTON_MANUAL, MANUAL, Alert.AlertType.INFORMATION);
                break;
            case BUTTON_THANKS:
                view.showDialog(YOU_ARE_WELCOME, BYE, Alert.AlertType.INFORMATION);
                break;
        }
    }

    /* правая кнопка */
    public void actionRightButton(String buttonText, String inputText) {
        switch (buttonText) {
            case BUTTON_MOVE:
                parseAndMove(inputText);
                break;
            case BUTTON_I_KNEW:
                view.showDialog(NOT_FUNNY, YOU_DID_NOT_KNOW,
                        Alert.AlertType.WARNING);
                break;
            case BUTTON_LETS_GAME:
            case BUTTON_PLAY_MORE:
                view.initStartGame();
                break;
        }
    }

    public void parseAndMove(String inputText) {
        if (!inputText.isEmpty()) {
            nextMove(Integer.parseInt(inputText));
        } else {
            view.showDialog(ERROR, EMPTY_STRING, Alert.AlertType.ERROR);
        }
    }

    private void nextMove(int useNumber) {
        /* число уже было? */
        if (model.getUseNumbers().contains(useNumber)) {
            view.showDialog(WRONG, String.format(CHANGE_NUMBER, useNumber),
                    Alert.AlertType.CONFIRMATION);
            return;
        }

        /* увеличение количества попыток */
        model.incrementMoveNumber();

        /* проверка окончания игры */
        if (isGameOver(useNumber)) return;

        /* действия после хода */
        if (model.getSecretNumber() < useNumber) {
            view.showDialog(WRONG, NUMBER_LESS, Alert.AlertType.WARNING);
        }

        if (model.getSecretNumber() > useNumber) {
            view.showDialog(WRONG, NUMBER_GREATER, Alert.AlertType.WARNING);
        }

        view.getTextLabel().setText(String.format(ENTER_NUMBER, model.getMoveNumber()));
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

    public void gameOver() {
        view.showDialog(Messages.YOU_LOSE,
                String.format(CORRECT_ANSWER, model.getSecretNumber()),
                BUTTON_REBOOT, Alert.AlertType.ERROR);
        repeatGame();
    }

    public void victory() {
        view.showDialog(String.format(VICTORY, model.getSecretNumber()), HINT,
                Alert.AlertType.INFORMATION);
        view.getTextLabel().setText(HINT);
        view.getInputText().setVisible(false);
        view.getButtonLeft().setText(BUTTON_THANKS);
        view.getButtonRight().setText(BUTTON_I_KNEW);
    }

    public void repeatGame() {
        Model.notFirstGame();
        view.getPrimaryStage().close();
        MainClass.startWindow(new Stage());
    }
}
