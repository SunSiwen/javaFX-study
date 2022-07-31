package stage4;

import entities.Person;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnTableView extends Application {
    private final TableView<Person> table = new TableView<>();
    final HBox hb = new HBox();
    // 数据源
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new Person("Emma", "Jones", "emma.jones@example.com"),
                    new Person("Michael", "Brown", "michael.brown@example.com")
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(500);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        // 每个Table的列
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        // 设置宽度
        firstNameCol.setMinWidth(100);
        // 设置分箱的类下面的属性名
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        // 设置为可编辑的
        firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        firstNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Person, String> t) -> {
                    ((Person) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setFirstName(t.getNewValue());
                });


        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        lastNameCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> t) -> {
            ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
        });

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        emailCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> t) -> {
            ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
        });

        // 设置数据源
        table.setItems(data);
        // 一次添加列进TableView
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        // 创建3个文本编辑框
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        // 设置宽度
        addFirstName.setPrefWidth(100);

        final TextField addLastName = new TextField();
        addLastName.setPrefWidth(100);
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setPrefWidth(195);
        addEmail.setPromptText("Email");


        // 创建一个添加按钮
        final Button addButton = new Button("Add");
        // 设置按钮的事件响应
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()
            ));
            // 清空文本
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
