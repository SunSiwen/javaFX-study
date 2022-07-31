package stage3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * @author Siwen Sun
 * @date 2022/07/31/ 1:04
 */
public class FileListView extends ListView<FileItem> {
    private ObservableList<FileItem> listData = FXCollections.observableArrayList();

    // 构造函数
    public FileListView() {
        setItems(listData);

        // 设置单元格生成器 （工厂）
        setCellFactory(param -> new MyListCell());

    }

    // 获取数据源
    public ObservableList<FileItem> data() {
        return listData;
    }
    // 设置单元格显示
    static class MyListCell extends ListCell<FileItem> {

        @Override
        protected void updateItem(FileItem item, boolean empty) {
            // FX框架要求必须先调用 super.updateItem()
            super.updateItem(item, empty);

            // 自己的代码
            if (item == null) {
                this.setText("");
            } else {
                this.setText(item.firstName);
            }
        }
    }

}