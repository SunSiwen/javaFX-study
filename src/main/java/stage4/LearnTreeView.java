package stage4;

import entities.Employee;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/30/ 17:25
 */
public class LearnTreeView extends Application {

    private final Node rootIcon =
            new ImageView(new Image(String.valueOf(getClass().getResource("/文件夹.png"))));
    private final Image depIcon =
            new Image(String.valueOf(getClass().getResource("/联系人.jpg")));
    List<Employee> employees = Arrays.<Employee>asList(
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabella Johnson", "Accounts Department"),
            new Employee("Ethan Williams", "Sales Department"),
            new Employee("Emma Jones", "Sales Department"),
            new Employee("Michael Brown", "Sales Department"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susan Collins", "Sales Department"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregory Smith", "IT Support"));
    TreeItem<String> rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public LearnTreeView() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources", rootIcon);
    }

    @Override
    public void start(Stage stage) {
        rootNode.setExpanded(true);
        for (Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(employee.getDepartment())) {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<>(
                        employee.getDepartment(),
                        new ImageView(depIcon)
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        stage.setTitle("Tree View Sample");
        VBox box = new VBox();
        final Scene scene = new Scene(box, 1100, 1100);
        scene.setFill(Color.LIGHTGRAY);

        TreeView<String> treeView = new TreeView<>(rootNode);
        // 设置为可编辑的
        treeView.setEditable(true);
        // 单元设置，TreeView下的每个子控件都支持,包扩子子控件,所以添加菜单栏那里只对有儿子有父亲的进行设置
        treeView.setCellFactory((TreeView<String> p) ->
                new TextFieldTreeCellImpl());

        box.getChildren().add(treeView);
        stage.setScene(scene);
        stage.show();
    }

    // 对TreeView下的每个单元格进行处理
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        // 文本编辑
        private TextField textField;

        // 添加菜单
        private final ContextMenu addMenu = new ContextMenu();

        // 构造方法,如果对儿子单元有特殊的需求可以在构造方法里实现
        public TextFieldTreeCellImpl() {

            // 新增菜单栏目
            MenuItem addMenuItem = new MenuItem("Add Employee");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem<String> newEmployee =
                        new TreeItem<>("New Employee");
                getTreeItem().getChildren().add(newEmployee);
            });
        }


        // 所有的TreeView的儿子控件都是支持Edit的,所有每次选中编辑的时候都会执行该方法
        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                // 每个单元会拥有一个文本框
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            // 清空编辑
            setText(getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            // 为空不处理
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                // 如果正在编辑
                if (isEditing()) {
                    if (textField != null) {
                        // 设置为原来Item的内容
                        textField.setText(getString());
                    }
                    setText(null);
                    // 设置显示为文本内容
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    // 如果有儿子  并且 有父亲，则拥有添加顾客菜单栏
                    if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                        setContextMenu(addMenu);
                    } else {
                        // 其他情况没有菜单栏
                        setContextMenu(null);
                    }
                }
            }
        }

        // 创建文本区，名字为该item名
        private void createTextField() {
            textField = new TextField(getString());

            // 键盘事件处理，松开键盘时
            textField.setOnKeyReleased((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    // 输入回车时，单元编辑为文本框内容
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    // 输入退出键时,清空编辑
                    cancelEdit();
                }
            });
        }

        // 获取该Item的名字
        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

}
