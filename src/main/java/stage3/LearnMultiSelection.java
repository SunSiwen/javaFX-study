package stage3;

import entities.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Iterator;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnMultiSelection extends Application {

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
    VBox vbox;
    TextField textField = new TextField();
    Button insert_bnt = new Button("insert");
    Button delete_bnt = new Button("delete");
    Button update_bnt = new Button("update");
    Stage g_stage;
    @Override
    public void start(Stage stage) {
        g_stage = stage;
        vbox = new VBox();
        HBox hBox = new HBox() {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                double width = getWidth();
                double height = getHeight();

                double first = 300;
                double second = (width - first) / 3;
                double third = (width - first - second) / 2;
                double fourth = width - first - second - third;

                double start = 0;
                textField.resizeRelocate(start, 0, first, height);
                start += first;
                insert_bnt.resizeRelocate(start, 0, second, height);
                start += second;
                delete_bnt.resizeRelocate(start, 0, third, height);
                start += third;
                update_bnt.resizeRelocate(start, 0, fourth, height);
            }
        };
        hBox.getChildren().addAll(textField, insert_bnt, delete_bnt, update_bnt);
        Scene scene = new Scene(vbox, 500, 500);
        stage.setScene(scene);
        stage.setTitle("ListViewSample");
        vbox.getChildren().addAll(hBox, list, label);

        VBox.setVgrow(list, Priority.ALWAYS);

        insert_bnt.setOnAction(event -> data.add(new Student(textField.getText(), 0, "unknown")));
        delete_bnt.setOnAction(event -> onDelete());
        update_bnt.setOnAction(event -> onUpdate());

        label.setLayoutX(10);
        label.setLayoutY(115);
        label.setFont(Font.font("Verdana", 20));

        list.setItems(data);

        list.setCellFactory((ListView<Student> l) -> new ColorRectCell());
//        list.getSelectionModel().selectedItemProperty().addListener(
//                (ObservableValue<? extends Student> ov, Student old_val,
//                 Student new_val) -> {
//                    label.setText(new_val.getName());
//                });
        stage.show();
    }

    private void onUpdate() {
        if (0 <= list.getSelectionModel().getSelectedIndex()) {
            System.out.println(textField.getText());
            data.get(list.getSelectionModel().getSelectedIndex()).setName(textField.getText());
            System.out.println(data.size());
            list.setItems(null);
            list.setItems(data);
        }

    }

    private void onDelete() {
        Iterator<Student> iterator = data.iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            if(next.getChecked()){
                iterator.remove();
            }
        }

    }

    static class ColorRectCell extends ListCell<Student> {
        @Override
        public void updateItem(Student item, boolean empty) {
            super.updateItem(item, empty);
//            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText(item.getName() + " --- " + item.getAge() + " --- " + item.getEmail());
                checkBox.setSelected(item.getChecked());
//                setGraphic(rect);
                this.setGraphic(checkBox);
                checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> item.setChecked(!item.getChecked()));
            }else{
                this.setGraphic(null);
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
