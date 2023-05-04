package game;

import game.controller.Controller;
import game.model.Model;
import game.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) {
        startWindow();
    }

    public static void startWindow() {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setController(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
