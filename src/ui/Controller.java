package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import shapes.Circle;
import shapes.LineSegment;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;

public class Controller{
	public static List<Node> current=new ArrayList<Node>();
	public static List<Node> prev =new ArrayList<Node>();
	@FXML
	private Button move,color,resize,draw,circle,line,rectangle;
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
	public static int selectedWidth;
	public static int selectedHeight;
	public static int state=0;
	public static Color selectedColor;
	@FXML
	public void draw(MouseEvent e) {
		boolean success=true;
		int f = 0,m=0;
		try {
			f=Integer.parseInt(width.getText());
			m=Integer.parseInt(height.getText());
		}
		catch(Exception e1) {
			error.setVisible(true);
			success=false;
		}
		if(success&&e.getX()>f&&e.getX()<1000-f&&e.getY()>f&&e.getY()<700-f) {
			if(state==4) {
				drawCircle(e.getX(),e.getY());
			}
			else if(state==5) {
				
			}
			else if(state==6) {
				drawLine(e.getX(),e.getY());
			}
		}
		prev.clear();
		prev.addAll(current);
		current.clear();
		current.addAll(board.getChildren());
	}
	public void drawCircle(double x,double y) {
		Circle circle = new Circle(x,y,Integer.parseInt(width.getText()),colorPicker.getValue());
		circle.draw(board);
	}
	public void drawLine(double x,double y) {
		LineSegment line = new LineSegment(x,y,Integer.parseInt(width.getText()),colorPicker.getValue());
		line.draw(board);
	}
	public void selectButton(int x) {
		move.setDisable(false);
		resize.setDisable(false);
		color.setDisable(false);
		draw.setDisable(false);
		circle.setDisable(false);
		rectangle.setDisable(false);
		line.setDisable(false);
		switch(x) {
		case 0:
			move.setDisable(true);
			break;
		case 1:
			resize.setDisable(true);
			break;
		case 2:
			color.setDisable(true);
			break;
		case 3:
			draw.setDisable(true);
			break;
		case 4:
			circle.setDisable(true);
			break;
		case 5:
			rectangle.setDisable(true);
			break;
		case 6:
			line.setDisable(true);
			break;
		}
		prev.clear();
		prev.addAll(current);
		current.clear();
		current.addAll(board.getChildren());
	}
	@FXML
	public void selectMove(ActionEvent event) {
		state=0;
		selectButton(state);
	}
	public void selectColor(ActionEvent event) {
		state=2;
		selectButton(state);
	}
	public void selectResize(ActionEvent event) {
		state=1;
		updateWidth();
		updateHeight();
		selectButton(state);
	}
	public void selectDraw(ActionEvent event) {
		state=3;
		selectButton(state);
	}
	public void selectCircle(ActionEvent event) {
		state=4;
		selectButton(state);
	}
	public void selectLine(ActionEvent event) {
		state=6;
		selectButton(state);
	}
	public void selectRectangle(ActionEvent event) {
		state=5;
		selectButton(state);
	}
	public void newColor(ActionEvent event) {
		selectedColor=colorPicker.getValue();
	}
	@FXML
	public void updateWidth() {
		boolean success=true;
		try {
			Integer.parseInt(width.getText());
		}
		catch(Exception e1) {
			error.setVisible(true);
			success=false;
		}
		if(success) {
			selectedWidth=Integer.parseInt(width.getText());
		}
	}
	@FXML
	public void updateHeight() {
		boolean success=true;
		try {
			Integer.parseInt(width.getText());
		}
		catch(Exception e1) {
			error.setVisible(true);
			success=false;
		}
		if(success) {
			selectedHeight=Integer.parseInt(height.getText());
		}
	}
	public void undo(ActionEvent event) {
		System.out.println(prev);
		System.out.println(current);
		board.getChildren().clear();
		board.getChildren().addAll(prev);
	}
}
