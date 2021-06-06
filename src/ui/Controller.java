package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import shapes.Circle;
import shapes.LineSegment;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;

public class Controller{
	public static List<Node> current=new ArrayList<Node>();
	public static List<Node> prev =new ArrayList<Node>();
	@FXML
	private Button move,color,resize,draw,circle,line,rectangle,triangle,square;
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
	public static Object[] oldAction=new Object[4];
	public static double oldX;
	public static double oldY;
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
				drawRectangle(e.getX(),e.getY());
			}
			else if(state==6) {
				drawLine(e.getX(),e.getY());
			}
			else if(state==7) {
				drawTriangle(e.getX(),e.getY());
			}
			else if(state==8) {
				drawSquare(e.getX(),e.getY());
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
	public void drawRectangle(double x,double y) {
		Rectangle rectangle= new Rectangle(x,y,Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),colorPicker.getValue());
		rectangle.draw(board);
	}
	public void drawSquare(double x,double y) {
		Square square= new Square(x,y,Integer.parseInt(width.getText()),colorPicker.getValue());
		square.draw(board);
	}
	public void drawTriangle(double x,double y) {
		Triangle triangle= new Triangle(x,y,Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),colorPicker.getValue());
		triangle.draw(board);
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
		square.setDisable(false);
		triangle.setDisable(false);
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
		case 7:
			triangle.setDisable(true);
			break;
		case 8:
			square.setDisable(true);
		}
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
	public void selectTriangle(ActionEvent event) {
		state = 7;
		selectButton(state);
	}
	public void selectSquare(ActionEvent event) {
		state=8;
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
		if(oldAction[1].equals("draw")&&!prev.isEmpty()) {
			board.getChildren().setAll(prev);
		}
		else if(oldAction[1].equals("draw")){
			board.getChildren().clear();
		}
		for(Object item : board.getChildren()) {
			if(item.equals(oldAction[0])) {
				if(item instanceof javafx.scene.shape.Circle) {
					if(oldAction[1].equals("color")) {
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						((javafx.scene.shape.Circle) item).setRadius((Double)oldX);
					}
					else if(oldAction[1].equals("move")) {
						((javafx.scene.shape.Circle) item).setCenterX((Double)oldX);
						((javafx.scene.shape.Circle) item).setCenterY((Double)oldY);
					}
				}
				else if(item instanceof javafx.scene.shape.Rectangle) {
					if(oldAction[1].equals("color")) {
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						((javafx.scene.shape.Rectangle) item).setWidth((Double)oldX);
						((javafx.scene.shape.Rectangle) item).setHeight((Double)oldY);
					}
					else if(oldAction[1].equals("move")) {
						((javafx.scene.shape.Rectangle) item).setX((Double)oldX);
						((javafx.scene.shape.Rectangle) item).setY((Double)oldY);
					}
				}
				else if(item instanceof javafx.scene.shape.Line) {
					if(oldAction[1].equals("color")) {
						((Shape) item).setStroke((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						((javafx.scene.shape.Line) item).setStartX(((Line) item).getStartX()+oldX/2);
						((javafx.scene.shape.Line) item).setEndX(((Line) item).getEndX()-oldX/2);
					}
					else if(oldAction[1].equals("move")) {
						//((javafx.scene.shape.Line) item).setX((Double)oldAction[2]);
						//((javafx.scene.shape.Line) item).setY((Double)oldAction[3]);
					}
				}
				else if(item instanceof javafx.scene.shape.Polygon) {
					if(oldAction[1].equals("color")) {
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						((javafx.scene.shape.Polygon) item).getPoints().setAll(
								((javafx.scene.shape.Polygon) item).getPoints().get(0),((javafx.scene.shape.Polygon) item).getPoints().get(1)+oldY/2,
								((javafx.scene.shape.Polygon) item).getPoints().get(2)+oldX/2,((javafx.scene.shape.Polygon) item).getPoints().get(3)-oldY/2,
								((javafx.scene.shape.Polygon) item).getPoints().get(4)-oldX/2,((javafx.scene.shape.Polygon) item).getPoints().get(5)-oldY/2);
					}
					else if(oldAction[1].equals("move")) {
						//((javafx.scene.shape.Polygon) item).setX((Double)oldAction[2]);
						//((javafx.scene.shape.Polygon) item).setY((Double)oldAction[3]);
					}
				}
			}
		}
	}
	public void redo(ActionEvent event) {
		board.getChildren().setAll(current);
	}
}
