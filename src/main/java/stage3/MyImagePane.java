package stage3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Siwen Sun
 * @date 2022/07/31/ 1:05
 */
public class MyImagePane extends Pane {
    ImageView imageView = new ImageView();
    Image image;

    public MyImagePane() {
        // 添加图片
        getChildren().add(imageView);
    }

    public void showImage(Image image) {
        this.image = image;
        imageView.setImage(image);
        layout();
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();

        // 对ImageView进行摆放，使其适应父窗口
        imageView.resizeRelocate(0, 0, w, h);
        imageView.setFitWidth(w);
        imageView.setFitHeight(h);
        imageView.setPreserveRatio(true);
    }
}
