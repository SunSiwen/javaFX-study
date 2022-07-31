package stage5;

import entities.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import stage3.LearnListControllerChange;

import java.util.Optional;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnCustomizedDialog extends Application {
    ListView<Student> list = new ListView<>();

    ObservableList<Student> data = FXCollections.observableArrayList(
            new Student("a", 46, "b@gmail.com"),
            new Student("c", 426, "asdb@gmail.com"),
            new Student("b", 26, "bdfaf@gmail.com"),
            new Student("d", 416, "asdfab@gmail.com"));
    final Label label = new Label("hahahahaha");
    VBox vbox;
    Button add_bnt = new Button("add");

    @Override
    public void start(Stage stage) {
        vbox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(add_bnt);
        Scene scene = new Scene(vbox, 500, 500);
        stage.setScene(scene);
        stage.setTitle("ListViewSample");
        vbox.getChildren().addAll(hBox, list, label);

        VBox.setVgrow(list, Priority.ALWAYS);

        add_bnt.setOnAction(a -> onAdd());

        label.setLayoutX(10);
        label.setLayoutY(115);
        label.setFont(Font.font("Verdana", 20));

        list.setItems(data);

        list.setCellFactory((ListView<Student> l) -> new LearnListControllerChange.StudentRectCell());

        stage.show();
    }

    private void onAdd() {
        VBox content  = new VBox();
        TextField s_id = new TextField();
        s_id.setPromptText("student id");
        TextField s_age = new TextField();
        s_age.setPromptText("student age");
        TextField s_email = new TextField();
        s_email.setPromptText("student email");

        content.setSpacing(10);
        content.getChildren().addAll(s_id,s_age,s_email);

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().add(ok);
        dialogPane.getButtonTypes().add(no);


        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);
        dlg.setTitle("add a new student");

        Optional<ButtonType> result = dlg.showAndWait();

        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            data.add(new Student(s_id.getText(), Integer.parseInt(s_age.getText()), s_email.getText()));
        }
    }


    static class StudentRectCell extends ListCell<Student> {
        @Override
        public void updateItem(Student item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                this.setText(item.getName() + " --- " + item.getAge() + " --- " + item.getEmail());
            }else{
                this.setText("");
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
