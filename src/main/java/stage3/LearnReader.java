package stage3;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;


/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnReader extends Application {

    FileListView fileList = new FileListView();
    TabPane tabPane = new TabPane();

    @Override
    public void start(Stage primaryStage) {
        try {
            // 加载左侧文件列表
            initFileList();

            BorderPane root = new BorderPane();
            root.setLeft(fileList);
            root.setCenter(tabPane);

            Scene scene = new Scene(root,800,500);
            scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initFileList(){

        fileList.setPrefWidth(200);

        File dir = new File(String.valueOf(getClass().getResource("../img")).substring(6));
        System.out.println(String.valueOf(getClass().getResource("/img")));
        File[] files = dir.listFiles();
        for(File f : files) {
            FileItem fitem = new FileItem(f);

            // 添加到左侧列表中
            fileList.data().add(fitem);
        }

        // 列表是鼠标事件响应
        fileList.setOnMouseClicked((MouseEvent event)->{
            // 如果左键单击的话
            if(event.getClickCount() == 1 && event.getButton() == MouseButton.PRIMARY) {
                oneClicked();
            }
        });

    }


    // 单击处理
    public void oneClicked() {

        // 获取列表选中模块，获取索引
        int index = fileList.getSelectionModel().getSelectedIndex();
        FileItem fitem = fileList.data().get(index);

        try {
            openFile(fitem);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // 打开左侧文件
    public void openFile(FileItem fitem) throws Exception{

        // 查看选项卡是否打开
        Tab tab = findTab(fitem);

        if(tab != null) {

            // 设置为选中的选项卡
            // int pos = tabPane.getTabs().indexOf(tab);  // 获取id
            tabPane.getSelectionModel().select(tab);
            return;
        }

        // 打开一个新的选项卡并选中

        Node currentView = null;

        if(fitem.type == FileItem.TEXT) {
            // 文本文件处理
            String str = TextFileUtils.read(fitem.file, "UTF-8");
            TextArea t = new TextArea();
            t.setText(str);
            currentView = t;

        }else if(fitem.type == FileItem.IMAGE) {
            // 图片文件处理

            // 获取文件的本地路径
            Image image = new Image(fitem.file.toURI().toString());
            MyImagePane t = new MyImagePane();
            t.showImage(image);
            currentView = t;

        }else throw new Exception("不支持打开该格式");

        // 创建新的选项卡并选中
        tab = new Tab();
        tab.setText(fitem.firstName);
        tab.setContent(currentView);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }


    // 查看在右侧选项卡是否打开
    public Tab findTab(FileItem fitem) {

        ObservableList<Tab> tabs = tabPane.getTabs();
        for(Tab tab : tabs) {
            if(tab.getText().equals(fitem.firstName)) {
                return tab;
            }
        }

        return null;

    }



    public static void main(String[] args) {
        launch(args);
    }

}
