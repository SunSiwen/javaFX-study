package stage6;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnMenu extends Application {
    private Button btnOpen, btnSave;
    private TextArea textArea = new TextArea();
    private BorderPane borderPane = new BorderPane();

    //创建单行面板，面板中各组件的间距为30像素
    private HBox hBox = new HBox(30);


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #FFFF00");

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("menu1");
        Menu menu2 = new Menu("menu2");
        Menu menu3 = new Menu("menu3");
        menuBar.getMenus().addAll(menu1, menu2, menu3);

        MenuItem item1 = new MenuItem("item1");
        MenuItem item2 = new MenuItem("item2");

        MenuItem item3 = new MenuItem("item3");

        // 分割符（一条线）
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        // 子菜单
        Menu menu4 = new Menu("menu4");
        MenuItem item5 = new MenuItem("MenuItem5");
        MenuItem item6 = new MenuItem("MenuItem6");
        menu4.getItems().addAll(item5, item6);

        menu1.getItems().addAll(item1, item2, separatorMenuItem, menu4, item3);

//        menu3.setDisable(true);  // 设置禁用
//        item2.setDisable(true);

//        item2.setVisible(false);  // 设置不可见

        // 菜单栏单选
        ToggleGroup tg = new ToggleGroup();  // 组
        RadioMenuItem radioMenuItem1 = new RadioMenuItem("RadioMenuItem1");
        RadioMenuItem radioMenuItem2 = new RadioMenuItem("RadioMenuItem2");
        RadioMenuItem radioMenuItem3 = new RadioMenuItem("RadioMenuItem3");
        radioMenuItem1.setToggleGroup(tg);
        radioMenuItem2.setToggleGroup(tg);
        radioMenuItem3.setToggleGroup(tg);
        menu2.getItems().addAll(radioMenuItem1, radioMenuItem2, radioMenuItem3);

        radioMenuItem3.setSelected(true);  // 设置默认为选中状态

        // 绑定单击事件
        radioMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RadioMenuItem source = (RadioMenuItem) event.getSource();// 获得事件源
                System.out.println(source.isSelected());  // 判断是否被选中
                // 或者直接点
                System.out.println(radioMenuItem1.isSelected());

            }
        });

        // 菜单栏多选
        CheckMenuItem checkMenuItem1 = new CheckMenuItem("checkMenuItem1");
        CheckMenuItem checkMenuItem2 = new CheckMenuItem("checkMenuItem2");
        CheckMenuItem checkMenuItem3 = new CheckMenuItem("checkMenuItem3");
        menu3.getItems().addAll(checkMenuItem1, checkMenuItem2, checkMenuItem3);

        checkMenuItem2.setSelected(true);  // 设置默认选中
        checkMenuItem3.setSelected(true);

        // 绑定单击事件
        checkMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CheckMenuItem source = (CheckMenuItem) event.getSource();// 获得事件源
                System.out.println(source.isSelected());  // 判断是否被选中
                // 或者直接点
                System.out.println(checkMenuItem1.isSelected());
            }
        });

        anchorPane.getChildren().add(menuBar);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.show();

        // 设置菜单条长度
        menuBar.setPrefWidth(primaryStage.getWidth());
        // 宽度监听设置菜单条长度
        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                menuBar.setPrefWidth(newValue.doubleValue());
            }
        });

    }
}
