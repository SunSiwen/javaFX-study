package stage2;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 16:16
 */
public class LearnCustomizedController extends Application {

    TextField textField = new TextField();
    Button select = new Button("select file");
    Button upload = new Button("upload file");

    @Override
    public void start(Stage stage) throws Exception {

        HBox hBox = new HBox();
        MyPane root = new MyPane();

        ImageView imageView = new ImageView("img/k8s.png");

        root.getChildren().add(imageView);

        hBox.getChildren().add(root);
        Scene scene = new Scene(hBox, 400, 400);
        scene.getStylesheets().add("application.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    class MyPane extends Pane {

        @Override
        protected void layoutChildren() {
           if(getChildren().size() == 0) return;

            double width = getWidth();
            double height = getHeight();

            ImageView imageView = (ImageView)getChildren().get(0);
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            imageView.setPreserveRatio(true);


            double fitHeight = imageView.getFitHeight();
            double fitWidth = imageView.getFitWidth();

            imageView.resizeRelocate((width-fitWidth)/2,(height-fitHeight)/2,fitWidth,fitHeight);

        }
    }
}
