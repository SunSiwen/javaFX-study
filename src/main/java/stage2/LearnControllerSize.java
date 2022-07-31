package stage2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 16:16
 */
public class LearnControllerSize extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HBox root = new HBox();

//        TextField textField = new TextField();
        Button select = new Button("select");
        select.setMinWidth(10);
        select.setMaxWidth(400);
        select.setPrefWidth(200);

        //width -> pref
        //height -> max
        Button upload = new Button("upload file");
        Button delete = new Button("delete file and discard");

        root.getChildren().addAll(select, upload,delete);
//        HBox.setHgrow(textField, Priority.ALWAYS);
        Scene scene = new Scene(root,400,40);
        scene.getStylesheets().add("application.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
