package stage1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 0:01
 */
public class Album extends Application {

    private static int INDEX = 0;
    private static ImageView imageView;
    private static Image[] img = new Image[5];
    private static String[] paths = new String[]{
            "联系人.jpg",
            "2.进程模型简介.pcwl_视频截图_3638.jpg",
            "3.IO模型简介 截取视频.pcwl_视频截图_251.jpg",
            "3.IO模型简介 截取视频.pcwl_视频截图_507.jpg",
            "文件夹.png"
    };
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("next");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showNext();
            }
        });

        for (int i = 0; i < img.length; i++) {
            img[i] = new Image("img/" + paths[i]);
        }
        BorderPane root = new BorderPane();
        imageView = new ImageView();
        root.setTop(btn);
        root.setCenter(imageView);
        Scene scene = new Scene(root, 300, 400);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        showNext();
    }

    private void showNext() {
        INDEX %= paths.length;
        imageView.setImage(img[INDEX++]);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
