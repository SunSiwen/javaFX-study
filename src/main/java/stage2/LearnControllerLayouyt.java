package stage2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class LearnControllerLayouyt extends Application {

    TextField textField = new TextField();
    Button select = new Button("select file");
    Button upload = new Button("upload file");

    @Override
    public void start(Stage stage) throws Exception {
//        HBox root = new MyBox();
        HBox root = new HBox(){
            @Override
            protected void layoutChildren() {
                super.layoutChildren();

                double width = getWidth();
                double height = getHeight();

                double first = 300;
                double second = select.getPrefWidth();
                double third = width - first - second;

                double start = 0;
                textField.resizeRelocate(start, 0, first, height);
                start += first;
                select.resizeRelocate(start, 0, second, height);
                start += second;
                upload.resizeRelocate(start, 0, third, height);

            }
        };

        select.setPrefWidth(125);
//        root.setPadding(new Insets(10,10,20,30));//up right bottom left
//        root.setSpacing(10);
//        root.setAlignment(Pos.BOTTOM_LEFT);
//        HBox.setHgrow(textField, Priority.ALWAYS);


        root.getChildren().addAll(textField, select, upload);
        Scene scene = new Scene(root, 400, 50);
        scene.getStylesheets().add("application.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


//    class MyBox extends HBox {
//
//        @Override
//        protected void layoutChildren() {
//            super.layoutChildren();
//            double width = getWidth();
//            double height = getHeight();
//
//            double first = 300;
//            double second = (width - first) / 2;
//            double third = width - first - second;
//
//            double start = 0;
//            textField.resizeRelocate(start, 0, first, height);
//            start += first;
//            select.resizeRelocate(start, 0, second, height);
//            start += second;
//            upload.resizeRelocate(start, 0, third, height);
//        }
//    }
}
