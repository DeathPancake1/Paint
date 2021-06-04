package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import shapes.LineSegment;
import javafx.event.*;

public class Controller{
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private AnchorPane board;
	@FXML
	private Text error;
	@FXML
	private TextField width;
	@FXML
	private TextField height;
	@FXML
	public void drawLine(ActionEvent e) {
		boolean success=true;
		try {
			Integer.parseInt(width.getText());
		}
		catch(Exception e1) {
			error.setVisible(true);
			success=false;
		}
		if(success) {
			LineSegment line = new LineSegment(Integer.parseInt(width.getText()),colorPicker.getValue());
			line.draw(board);
		}
	}
}
