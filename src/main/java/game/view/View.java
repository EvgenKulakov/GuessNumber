package game.view;

import game.controller.Controller;
import game.model.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Stage {
    private Controller controller;
    private final BorderPane root = new BorderPane();

    private final HBox imgBox = new HBox();
    private final Image windowIcon = new Image(getClass().getResourceAsStream(Icons.MAIN_IMAGINE));
    private final ImageView mainImg = new ImageView(windowIcon);

    private final StackPane centralPane = new StackPane();
    private final HBox textAndInputBox = new HBox();
    private final Label textLabel = new Label(Messages.START_TEXT);
    private final TextField inputText = new TextField();

    private final HBox mainBottomBox = new HBox();
    private final Button buttonLeft = new Button(Buttons.NO_GAME);
    private final Button buttonCenter = new Button(Buttons.REBOOT);
    private final Button buttonRight = new Button();


    public View() {
        initView();
    }

    private void initView() {
        /* Инициализация главного окна */
        Scene scene = new Scene(root);
        setScene(scene);
        setWidth(600);
        setHeight(600);
        setTitle(Titles.MAIN_WINDOW);
        setResizable(false);
        getIcons().add(windowIcon);

        /* Панель с картинкой */
        Rectangle rectangle = new Rectangle(windowIcon.getWidth(), windowIcon.getHeight());
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        mainImg.setClip(rectangle);
        imgBox.getChildren().add(mainImg);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setTranslateY(5);
        root.setTop(imgBox);

        /* Панель с текстом */
        textLabel.setStyle("-fx-font-size: 13.5;");
        centralPane.setStyle("-fx-background-color: #DCDCDC;" +
                "-fx-background-radius: 5px; -fx-font-weight: bold;");
        centralPane.setMaxHeight(80);
        centralPane.setMaxWidth(480);
        centralPane.setPadding(new Insets(10));
        centralPane.getChildren().add(textLabel);
        root.setCenter(centralPane);

        /* Кнопки */
        String buttonRightText = Model.isNotFirstGame()
                ? Buttons.PLAY_MORE
                : Buttons.LETS_GAME;
        buttonRight.setText(buttonRightText);
        buttonLeft.setPrefSize(235, 50);
        buttonRight.setPrefSize(235, 50);

        /* Панель с кнопками */
        mainBottomBox.getChildren().addAll(buttonLeft, buttonRight);
        mainBottomBox.setPadding(new Insets(10));
        mainBottomBox.setSpacing(10);
        mainBottomBox.setAlignment(Pos.CENTER);
        mainBottomBox.setTranslateY(-15);
        root.setBottom(mainBottomBox);

        /* слушатели для кнопок */
        buttonLeft.setOnAction(e -> controller.actionLeftButton(buttonLeft.getText()));

        buttonRight.setOnAction(e -> {
            controller.actionRightButton(buttonRight.getText(), inputText.getText());
        });

        show();
    }

    public void initStartGame() {
        textLabel.setText(String.format(Messages.ENTER, 1));
        inputText.setStyle("-fx-font-size: 13.25;");
        inputText.setMaxWidth(50);

        textAndInputBox.getChildren().addAll(textLabel, inputText);
        textAndInputBox.setAlignment(Pos.CENTER);
        textAndInputBox.setSpacing(30);

        centralPane.getChildren().add(textAndInputBox);
        textLabel.setAlignment(Pos.CENTER_LEFT);
        inputText.setAlignment(Pos.CENTER_RIGHT);
        inputText.setAlignment(Pos.CENTER);

        mainBottomBox.getChildren().add(1, buttonCenter);
        mainBottomBox.setSpacing(10);
        buttonLeft.setText(Buttons.MANUAL);
        buttonRight.setText(Buttons.MOVE);
        buttonLeft.setPrefSize(115, 50);
        buttonCenter.setPrefSize(115, 50);
        buttonRight.setPrefSize(230, 50);

        ImageView leftIcon = new ImageView(new Image(Icons.MANUAL));
        leftIcon.setFitWidth(20);
        leftIcon.setFitHeight(20);
        buttonLeft.setGraphic(leftIcon);

        ImageView centerIcon = new ImageView(new Image(Icons.REBOOT));
        centerIcon.setFitWidth(20);
        centerIcon.setFitHeight(20);
        buttonCenter.setGraphic(centerIcon);

        buttonCenter.setOnAction(event -> controller.actionCenterButton());
        inputText.setOnAction(event -> controller.parseAndMove(inputText.getText()));

        /* фильтр ввода цифр: можно вводить только цифры и не более трех */
        inputText.textProperty().addListener(this::inputFilter);
    }

    private void inputFilter(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
            inputText.setText(newValue.replaceAll("[^\\d]", ""));
        }
        if (newValue.length() > 3) {
            inputText.setText(oldValue);
        }
    }

    public void victoryRendering() {
        textLabel.setText(Messages.HINT_FINAL);
        textAndInputBox.getChildren().remove(inputText);
        mainBottomBox.getChildren().remove(buttonCenter);
        buttonLeft.setGraphic(null);
        buttonLeft.setPrefWidth(235);
        buttonRight.setPrefWidth(235);
        buttonLeft.setText(Buttons.THANKS);
        buttonRight.setText(Buttons.KNEW);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public Image getWindowIcon() {
        return windowIcon;
    }
    public Label getTextLabel() {
        return textLabel;
    }
}
