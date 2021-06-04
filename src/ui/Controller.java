package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class Controller implements Initializable{
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private Canvas canvas;
	
	GraphicsContext brushTool;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		brushTool = canvas.getGraphicsContext2D();
		canvas.setOnMouseDragged(e->{
			double x =e.getX() - 20/2;
			double y = e.getY()-20/2;
			brushTool.setFill(colorPicker.getValue());
			brushTool.fillRoundRect(x, y, 20, 20, 20, 20);
		});
	}
}
