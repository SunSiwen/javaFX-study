package stage1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 15:29
 */
public class LearnButton extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Button button = new Button("click me");

        button.setOnAction(actionEvent -> System.out.println("good"));

        root.setCenter(button);

        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toString());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
