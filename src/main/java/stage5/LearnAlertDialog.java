package stage5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnAlertDialog extends Application {
    /**
     * 任务目标
     * 使用javaFX 创建一个表单登录页面
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            /**
             * 1.使用gridpanel(网格面板)
             * */
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER); //对齐方式(居中)

            //设置grippanel属性
            grid.setHgap(10); //水平距离
            grid.setVgap(10); //垂直距离
            grid.setPadding(new Insets(25, 25, 25, 25)); //设置内边距

            /*
             * 2.声明组件 并设置组件属性
             * */

            //页面标题
            Text screenTitle = new Text("Welcome");
            screenTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            //设置页面标题id
            screenTitle.setId("title");
            //grid.add(child, 列索引, 行索引, 跨列数, 跨行数);
            grid.add(screenTitle, 0, 0, 2, 1);

            //账号
            Label username = new Label("User Name:");
            grid.add(username, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            //密码
            Label pw = new Label("Password:");
            grid.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 2);

            //提交 按钮
            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT); //对齐方式(底部右侧)
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            //添加一个文本框(用于显示信息的控制)
            final Text actiontarget = new Text();
            actiontarget.setId("actiontarget");
            grid.add(actiontarget, 1, 6);


            /**
             * 3.声明按钮事件
             *      点击按钮文本显示信息
             * */
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    String username = userTextField.getText();
                    String password = pwBox.getText();

                    Alert warning;
                    if ("ssw".equals(username) && "123456".equals(password)) {
                        warning = new Alert(Alert.AlertType.INFORMATION);
                        warning.setHeaderText("Success");
                        warning.setContentText("Welcome to log in");
                    } else {
                        warning = new Alert(Alert.AlertType.WARNING);
                        warning.setHeaderText("Fail");
                        warning.setContentText("SOMETHING WRONG");
                    }
                    warning.showAndWait();

                }
            });

            /**
             * 4.把容器添加到场景中  并设置场景大小
             * ps:如果不设置场景大小，默认是最小
             * */
            Scene scene = new Scene(grid, 300, 275);

            //场景引入css文件
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/application.css")));

            primaryStage.setTitle("JavaFX Welcome");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
