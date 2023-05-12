package game.view;

import game.controller.Controller;
import game.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Stage {
    private Controller controller;
    private final BorderPane root = new BorderPane();

    private final HBox imgBox = new HBox();
    private final Image icon = new Image(getClass().getResourceAsStream(Icons.MAIN_IMAGINE));
    private final ImageView img = new ImageView(icon);

    private final StackPane centralPane = new StackPane();
    private final HBox centralBox = new HBox();
    private final Label text = new Label(Messages.START_TEXT);
    private final TextField input = new TextField();

    private final HBox buttonBox = new HBox();
    private final Button buttonLeft = new Button(Buttons.NO_GAME);
    private final Button buttonCenter = new Button(Buttons.REBOOT);
    private final Button buttonRight = new Button();


    public View() {
        initView();
        addListeners();
    }

    public View(double x, double y) {
        setX(x);
        setY(y);
        initView();
        addListeners();
    }

    private void initView() {
        /* Инициализация всего окна */
        Scene scene = new Scene(root);
        setScene(scene);
        setWidth(600);
        setHeight(600);
        show();
        setTitle(Titles.MAIN_WINDOW);
        setResizable(false);
        getIcons().add(icon);

        /* Панель с картинкой */
        Rectangle rectangle = new Rectangle(icon.getWidth(), icon.getHeight());
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        img.setClip(rectangle);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setTranslateY(5);
        imgBox.getChildren().add(img);
        root.setTop(imgBox);

        /* Панель с текстом */
        text.setStyle(Styles.SIZE_13_50);
        centralPane.setStyle(Styles.BACKGROUND_GREY + Styles.RADIUS_5PX + Styles.FONT_BOLD);
        centralPane.setMaxHeight(80);
        centralPane.setMaxWidth(480);
        centralPane.setPadding(new Insets(10));
        centralPane.getChildren().add(text);
        root.setCenter(centralPane);

        /* Панель с кнопками */
        String buttonRightText = Model.isNotFirstGame()
                ? Buttons.PLAY_MORE
                : Buttons.LETS_GAME;
        buttonRight.setText(buttonRightText);
        buttonLeft.setPrefSize(235, 50);
        buttonRight.setPrefSize(235, 50);
        buttonBox.getChildren().addAll(buttonLeft, buttonRight);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(-15);
        root.setBottom(buttonBox);
    }

    private void addListeners() {
        final String MOUSE_LEFT = "PRIMARY";

        buttonLeft.setOnMouseClicked(event -> {
            if (event.getButton().name().equals(MOUSE_LEFT)) {
                controller.actionLeftButton(buttonLeft.getText());
            }
        });

        buttonLeft.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                controller.actionLeftButton(buttonLeft.getText());
            }
        });

        buttonCenter.setOnMouseClicked(event -> {
            if (event.getButton().name().equals(MOUSE_LEFT)) {
                controller.actionCenterButton();
            }
        });

        buttonCenter.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                controller.actionCenterButton();
            }
        });

        buttonRight.setOnMouseClicked(event -> {
            if (event.getButton().name().equals(MOUSE_LEFT)) {
                controller.actionRightButton(buttonRight.getText(), input.getText());
            }
        });

        buttonRight.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                controller.actionRightButton(buttonRight.getText(), input.getText());
            }
        });

        input.setOnAction(event -> controller.parseAndMove(input.getText()));
    }

    public void renderStartGame() {
        /* центральная панель */
        text.setText(String.format(Messages.ENTER, controller.getModel().getMoveNumber()));
        text.setAlignment(Pos.CENTER_LEFT);
        input.setStyle(Styles.SIZE_13_25);
        input.setMaxWidth(50);
        input.setAlignment(Pos.CENTER);
        centralBox.setAlignment(Pos.CENTER);
        centralBox.setSpacing(30);
        centralBox.getChildren().addAll(text, input);
        centralPane.getChildren().add(centralBox);

        /* иконки кнопок */
        ImageView leftIcon = new ImageView(new Image(Icons.MANUAL));
        leftIcon.setFitWidth(20);
        leftIcon.setFitHeight(20);
        buttonLeft.setGraphic(leftIcon);

        ImageView centerIcon = new ImageView(new Image(Icons.REBOOT));
        centerIcon.setFitWidth(20);
        centerIcon.setFitHeight(20);
        buttonCenter.setGraphic(centerIcon);

        /* кнопки */
        buttonLeft.setText(Buttons.MANUAL);
        buttonRight.setText(Buttons.MOVE);
        buttonLeft.setPrefSize(115, 50);
        buttonCenter.setPrefSize(115, 50);
        buttonRight.setPrefSize(230, 50);
        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(1, buttonCenter);

        /* фильтр: можно вводить только 3 цифры */
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                input.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 3) {
                input.setText(oldValue);
            }
        });
    }

    public void victoryRendering() {
        text.setText(Messages.HINT_FINAL);
        centralBox.getChildren().remove(input);
        buttonBox.getChildren().remove(buttonCenter);
        buttonLeft.setGraphic(null);
        buttonLeft.setPrefWidth(235);
        buttonRight.setPrefWidth(235);
        buttonLeft.setText(Buttons.THANKS);
        buttonRight.setText(Buttons.KNEW);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public Image getIcon() {
        return icon;
    }
    public Label getText() {
        return text;
    }
}
