package stage2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 16:16
 */
public class LearnBorderPane extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Button button = new Button("click me");

        button.setOnAction(actionEvent -> button.setText("good"));

        root.setTop(button);

        ImageView imageView = new ImageView(new Image("img/k8s.png"));
        root.setCenter(imageView);

        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add("application.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
