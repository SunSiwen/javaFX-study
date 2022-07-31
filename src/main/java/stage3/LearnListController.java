package stage3;

import entities.Student;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnListController extends Application {

    ListView<Student> list = new ListView<>();
//    ObservableList<String> data = FXCollections.observableArrayList(
//            "chocolate", "salmon", "gold", "coral", "darkorchid",
//            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
//            "blueviolet", "brown");

    ObservableList<Student> data = FXCollections.observableArrayList(
            new Student("a", 46, "b@gmail.com"),
            new Student("c", 426, "asdb@gmail.com"),
            new Student("b", 26, "bdfaf@gmail.com"),
            new Student("d", 416, "asdfab@gmail.com"));
    final Label label = new Label("hahahahaha");

    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box, 200, 200);
        stage.setScene(scene);
        stage.setTitle("ListViewSample");
        box.getChildren().addAll(list, label);
        VBox.setVgrow(list, Priority.ALWAYS);

        label.setLayoutX(10);
        label.setLayoutY(115);
        label.setFont(Font.font("Verdana", 20));

        list.setItems(data);

        list.setCellFactory((ListView<Student> l) -> new ColorRectCell());

//        list.getSelectionModel().selectedItemProperty().addListener(
//                (ObservableValue<? extends String> ov, String old_val,
//                 String new_val) -> {
//                    label.setText(new_val);
//                    label.setTextFill(Color.web(new_val));
//                });
        stage.show();
    }

    static class ColorRectCell extends ListCell<Student> {
        @Override
        public void updateItem(Student item, boolean empty) {
            super.updateItem(item, empty);
//            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                this.setText(item.getName() + " --- " + item.getAge() + " --- " + item.getEmail());
//                setGraphic(rect);
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
