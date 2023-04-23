package game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View {
    private Stage primaryStage;
    private Controller controller;
    private final BorderPane root = new BorderPane();

    private final HBox imgBox = new HBox();
    private final Image img = new Image(getClass().getResourceAsStream(Messages.IMAGINE));
    private final ImageView imageView = new ImageView(img);

    private final StackPane centralPane = new StackPane();
    private final HBox textAndInputBox = new HBox();
    private final Label textLabel = new Label(Messages.START_TEXT);
    private final TextField inputText = new TextField();

    private final HBox bottomBox = new HBox();
    private final Button buttonLeft = new Button(Messages.BUTTON_NO_GAME);
    private final Button buttonCenter = new Button(Messages.BUTTON_REBOOT);
    private final Button buttonRight = new Button();


    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initView();
    }

    public void initView() {
        /* Инициализация главного окна */
        Scene scene = new Scene(root, 600, 610);
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(610);
        primaryStage.show();
        primaryStage.setTitle(Messages.MAIN_WINDOW);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(img);

        /* Панель с картинкой */
        Rectangle rectangle = new Rectangle(img.getWidth(), img.getHeight());
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        imageView.setClip(rectangle);
        imgBox.getChildren().add(imageView);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setTranslateY(5);
        root.setTop(imgBox);

        /* Панель с текстом */
        textLabel.setStyle("-fx-font-family: Calibri; -fx-font-size: 15.5; -fx-font-weight: bold;");
        BackgroundFill fill = new BackgroundFill(Color.web("#DCDCDC"), new CornerRadii(5), Insets.EMPTY);
        centralPane.setBackground(new Background(fill));
        centralPane.setPadding(new Insets(10));
        centralPane.setMaxHeight(80);
        centralPane.setMaxWidth(480);
        centralPane.getChildren().add(textLabel);
        root.setCenter(centralPane);

        /* Кнопки */
        String buttonRightText = Model.isNotFirstGame()
                ? Messages.BUTTON_PLAY_MORE
                : Messages.BUTTON_LETS_GAME;
        buttonRight.setText(buttonRightText);
        buttonLeft.setPrefSize(234, 50);
        buttonRight.setPrefSize(234, 50);

        /* Панель с кнопками */
        bottomBox.getChildren().addAll(buttonLeft, buttonRight);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setSpacing(10);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setTranslateY(-15);
        root.setBottom(bottomBox);

        /* слушатели для кнопок */
        buttonLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.actionLeftButton(buttonLeft.getText());
            }
        });

        buttonRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.actionRightButton(buttonRight.getText(), inputText.getText());
            }
        });
    }

    public void initStartGame() {
        textLabel.setText(String.format(Messages.ENTER_NUMBER, 1));
        inputText.setMaxWidth(50);

        textAndInputBox.getChildren().addAll(textLabel, inputText);
        textAndInputBox.setAlignment(Pos.CENTER);
        textAndInputBox.setSpacing(30);

        centralPane.getChildren().add(textAndInputBox);
        textLabel.setAlignment(Pos.CENTER_LEFT);
        inputText.setAlignment(Pos.CENTER_RIGHT);
        inputText.setAlignment(javafx.geometry.Pos.CENTER);

        bottomBox.getChildren().add(1, buttonCenter);
        buttonLeft.setText(Messages.BUTTON_MANUAL);
        buttonRight.setText(Messages.BUTTON_MOVE);
        buttonLeft.setPrefSize(100, 50);
        buttonCenter.setPrefSize(100, 50);
        buttonRight.setPrefSize(259, 50);

        /* фильтр ввода цифр: можно вводить только цифры и не более трех */
        inputText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputText.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (newValue.length() > 3) {
                    inputText.setText(oldValue);
                }
            }
        });

        inputText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.parseAndMove(inputText.getText());
            }
        });
    }

    public void showDialog(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setX(primaryStage.getX() + 85);
        alert.setY(primaryStage.getY() + 274);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        alert.showAndWait();
    }

    public void showDialog(String title, String message, String buttonText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setX(primaryStage.getX() + 85);
        alert.setY(primaryStage.getY() + 274);

        ButtonType buttonType = new ButtonType(buttonText);
        alert.getButtonTypes().setAll(buttonType);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        alert.showAndWait();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public Button getButtonRight() {
        return buttonRight;
    }
    public Button getButtonLeft() {
        return buttonLeft;
    }
    public Label getTextLabel() {
        return textLabel;
    }
    public TextField getInputText() {
        return inputText;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
