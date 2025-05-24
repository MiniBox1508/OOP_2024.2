```java
package hust.soict.itep.javafx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;
    @FXML
    private RadioButton penButton;
    @FXML
    private RadioButton eraserButton;
    @FXML
    private ToggleGroup toolGroup;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color color = penButton.isSelected() ? Color.BLACK : Color.WHITE;
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, color);
        drawingAreaPane.getChildren().add(newCircle);
    }
}
```