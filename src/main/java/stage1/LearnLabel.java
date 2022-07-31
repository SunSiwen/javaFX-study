package stage1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 15:29
 */
public class LearnLabel extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Label label = new Label("hello");


        root.setCenter(label);

        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toString());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
