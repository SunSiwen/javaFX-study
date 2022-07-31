package stage2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 16:12
 */
public class LearnCallback extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Button button = new Button("click me");

        button.setOnAction(actionEvent -> button.setText("good"));

        root.setCenter(button);

        Scene scene = new Scene(root,400,400);
//        Class<? extends LearnCallback> aClass = getClass();
//        URL resource = aClass.getResource("../application.css");
//        URL resource1 = aClass.getResource("./application.css");
//        URL resource2 = aClass.getResource("/application.css");
//        URL resource3 = aClass.getResource("../");
//        URL resource4 = aClass.getResource("/");
//        URL resource5 = aClass.getResource("../../");
        scene.getStylesheets().add(getClass().getResource("/application.css").toString());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
