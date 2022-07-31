package stage2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 16:16
 */
public class LearnNestedController extends Application {

    TextField textField = new TextField();
    Button select = new Button("select file");
    Button add = new Button("add");
    Button upload = new Button("upload file");
    TextArea textArea = new TextArea();

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(textField, add);
        root.setTop(hBox);
        root.setCenter(textArea);
        HBox.setHgrow(textField, Priority.ALWAYS);

        add.setOnAction(actionEvent -> onAdd());
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("application.css");
        stage.setScene(scene);
        stage.show();
    }

    private void onAdd() {
        String text = textField.getText();
        textArea.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch();
    }


}
