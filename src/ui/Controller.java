package ui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controller {
	@FXML
	Circle circle;
	@FXML
    private void printHelloWorld(MouseEvent event) {
		circle=(Circle)event.getSource();
        circle.setFill(javafx.scene.paint.Color.RED);
    }
}
