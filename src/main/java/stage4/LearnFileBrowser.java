package stage4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnFileBrowser extends Application {

    public static File ROOT_FILE = FileSystemView.getFileSystemView().getRoots()[0];
    @Override
    public void start(Stage stage) {
        HBox hBox = new HBox();
        TreeView<String> treeView = new TreeView<>();

        FileTreeItem fileTreeItem = new FileTreeItem(ROOT_FILE, f -> {
            File[] allFiles = f.listFiles();
            File[] directorFiles = f.listFiles(File::isDirectory);
            List<File> list = new ArrayList<>(Arrays.asList(allFiles));
            list.removeAll(Arrays.asList(directorFiles));
            return list.toArray(new File[list.size()]);
        });
        treeView.setRoot(fileTreeItem);
        treeView.setShowRoot(false);
        treeView.setMinWidth(250);
        hBox.getChildren().add(treeView);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hBox);

        Scene scene = new Scene(stackPane, 900, 700);
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        launch(args);

    }


}
