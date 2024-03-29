package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Labeled;
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
	//The array lists that hold the information about the objects on the screen to facilitate undo and redo
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
	public static int selectedWidth;//The width that the user selected in the width text box
	public static int selectedHeight;//The height that the user selected in the width text box
	public static int state=0;//The state variable that is used to define what action the user wants to perform
	public static Color selectedColor=Color.WHITE;//The color that the user selected in the color picker
	public static Object[] oldAction=new Object[]{"","","","","",""}; //The array that is used to save the previous state of object before actions are performed
	public static Object[] newAction=new Object[] {"","","","","",""};//The array that is used to save the current state of object before actions are undone
	public static double oldX,oldY;//The variables used to save the previous state of X and Y parameters of objects
	public static double newX,newY;//The variables used to save the current state of X and Y parameters of objects
	//The function that processes a mouse action by the user into drawing a shape
	//Uses Abstract Factory design pattern
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
		if(state==4) {
			if(success&&e.getX()>f&&e.getX()<1000-f&&e.getY()>f&&e.getY()<700-f)
				drawCircle(e.getX(),e.getY());
		}
		else if(state==5) {
			if(e.getX()>f/2&&e.getX()<1000-m/2&&e.getY()<700-f/2&&e.getY()>m/2)
				drawRectangle(e.getX(),e.getY());
		}
		else if(state==6) {
			if(e.getX()>f/2&&e.getX()<1000-f/2&&e.getY()<700&&e.getY()>0)
				drawLine(e.getX(),e.getY());
		}
		else if(state==7) {
			if(e.getX()>f/2&&e.getX()<1000-m/2&&e.getY()<700-f/2&&e.getY()>m/2)
				drawTriangle(e.getX(),e.getY());
		}
		else if(state==8) {
			if(e.getX()>f/2&&e.getX()<1000-f/2&&e.getY()<700-f/2&&e.getY()>f/2)
				drawSquare(e.getX(),e.getY());
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
	//The function that is invoked when a user presses a button to change the state of the program to perform the selected action
	public void selectButton(ActionEvent event) {
		String x=((Button) event.getSource()).getText();
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
		case "Move":
			state=0;
			move.setDisable(true);
			break;
		case "Resize":
			state=1;
			updateWidth();
			updateHeight();
			resize.setDisable(true);
			break;
		case "Color":
			state=2;
			color.setDisable(true);
			break;
		case "Draw":
			state=3;
			draw.setDisable(true);
			break;
		case "Circle":
			state=4;
			circle.setDisable(true);
			break;
		case "Rectangle":
			state=5;
			rectangle.setDisable(true);
			break;
		case "Line":
			state=6;
			line.setDisable(true);
			break;
		case "Triangle":
			state=7;
			triangle.setDisable(true);
			break;
		case "Square":
			state=8;
			square.setDisable(true);
		}
	}
	//The method that is used to update the selected color property
	public void newColor(ActionEvent event) {
		selectedColor=colorPicker.getValue();
	}
	//The method that is used to update the selected width property
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
	//The method that is used to update the selected height property
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
	//The undo function that is used to undo the last action that the user performed
	public void undo(ActionEvent event) {
		if(oldAction[1].equals("draw")&&!prev.isEmpty()) {
			board.getChildren().setAll(prev);
			ArrayList <Node> temp = new ArrayList<Node>();
			temp.addAll(prev);
			prev.clear();
			prev.addAll(current);
			current.clear();
			current.addAll(temp);
			newAction[1]="draw";
		}
		else if(oldAction[1].equals("draw")){
			board.getChildren().clear();
			newAction[1]="draw";
		}
		for(Object item : board.getChildren()) {
			if(item.equals(oldAction[0])) {
				newAction[0]=item;
				if(item instanceof javafx.scene.shape.Circle) {
					if(oldAction[1].equals("color")) {
						newAction[1]="color";
						newAction[2]=((javafx.scene.shape.Circle) item).getFill();
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						newAction[1]="resize";
						newX=((javafx.scene.shape.Circle) item).getRadius();
						((javafx.scene.shape.Circle) item).setRadius((Double)oldX);
					}
					else if(oldAction[1].equals("move")) {
						newAction[1]="move";
						newX=((javafx.scene.shape.Circle) item).getCenterX();
						newY=((javafx.scene.shape.Circle) item).getCenterY();
						((javafx.scene.shape.Circle) item).setCenterX((Double)oldX);
						((javafx.scene.shape.Circle) item).setCenterY((Double)oldY);
					}
				}
				else if(item instanceof javafx.scene.shape.Rectangle) {
					if(oldAction[1].equals("color")) {
						newAction[1]="color";
						newAction[2]=((javafx.scene.shape.Rectangle) item).getFill();
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						newAction[1]="resize";
						newX=((javafx.scene.shape.Rectangle) item).getWidth();
						newY=((javafx.scene.shape.Rectangle) item).getHeight();
						((javafx.scene.shape.Rectangle) item).setWidth((Double)oldX);
						((javafx.scene.shape.Rectangle) item).setHeight((Double)oldY);
					}
					else if(oldAction[1].equals("move")) {
						newAction[1]="move";
						newX=((javafx.scene.shape.Rectangle) item).getX();
						newY=((javafx.scene.shape.Rectangle) item).getY();
						((javafx.scene.shape.Rectangle) item).setX((Double)oldX);
						((javafx.scene.shape.Rectangle) item).setY((Double)oldY);
					}
				}
				else if(item instanceof javafx.scene.shape.Line) {
					if(oldAction[1].equals("color")) {
						newAction[1]="color";
						newAction[2]=((javafx.scene.shape.Line) item).getStroke();
						((Shape) item).setStroke((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						newAction[1]="resize";
						newX=((javafx.scene.shape.Line) item).getStartX();
						newY=((javafx.scene.shape.Line) item).getEndX();
						((javafx.scene.shape.Line) item).setStartX(((Line) item).getStartX()+oldX/2);
						((javafx.scene.shape.Line) item).setEndX(((Line) item).getEndX()-oldX/2);
					}
					else if(oldAction[1].equals("move")) {
						newAction[1]="move";newAction[2]=((javafx.scene.shape.Line) item).getStartX();
						newAction[3]=((javafx.scene.shape.Line) item).getStartY();newAction[4]=((javafx.scene.shape.Line) item).getEndX();
						newAction[5]=((javafx.scene.shape.Line) item).getEndY();
						((javafx.scene.shape.Line) item).setStartX((Double)oldAction[2]);
						((javafx.scene.shape.Line) item).setStartY((Double)oldAction[3]);
						((javafx.scene.shape.Line) item).setEndX((Double)oldAction[4]);
						((javafx.scene.shape.Line) item).setEndY((Double)oldAction[5]);
					}
				}
				else if(item instanceof javafx.scene.shape.Polygon) {
					if(oldAction[1].equals("color")) {
						newAction[1]="color";
						newAction[2]=((javafx.scene.shape.Polygon) item).getFill();
						((Shape) item).setFill((Color) oldAction[2]);
					}
					else if(oldAction[1].equals("resize")) {
						newAction[1]="resize";
						newAction[2]=new Double[] {
								((javafx.scene.shape.Polygon) item).getPoints().get(0),((javafx.scene.shape.Polygon) item).getPoints().get(1),
								((javafx.scene.shape.Polygon) item).getPoints().get(2),((javafx.scene.shape.Polygon) item).getPoints().get(3),
								((javafx.scene.shape.Polygon) item).getPoints().get(4),((javafx.scene.shape.Polygon) item).getPoints().get(5)};
						((javafx.scene.shape.Polygon) item).getPoints().clear();
						((javafx.scene.shape.Polygon) item).getPoints().setAll((Double [])oldAction[2]);
					}
					else if(oldAction[1].equals("move")) {
						newAction[1]="move";
						newAction[2]=new Double[] {
								((javafx.scene.shape.Polygon) item).getPoints().get(0),((javafx.scene.shape.Polygon) item).getPoints().get(1),
								((javafx.scene.shape.Polygon) item).getPoints().get(2),((javafx.scene.shape.Polygon) item).getPoints().get(3),
								((javafx.scene.shape.Polygon) item).getPoints().get(4),((javafx.scene.shape.Polygon) item).getPoints().get(5)};
						((javafx.scene.shape.Polygon) item).getPoints().clear();
						((javafx.scene.shape.Polygon) item).getPoints().setAll((Double [])oldAction[2]);
					}
				}
			}
		}
	}
	//The redo function that is used to undo the last action that the user undid
	public void redo(ActionEvent event) {
		if(newAction[1].equals("draw")&&!prev.isEmpty()) {
			board.getChildren().setAll(prev);
			ArrayList <Node> temp = new ArrayList<Node>();
			temp.addAll(prev);
			prev.clear();
			prev.addAll(current);
			current.clear();
			current.addAll(temp);
			newAction[1]="draw";
		}
		else if(newAction[1].equals("draw")){
			board.getChildren().setAll(current);
		}
		for(Object item : board.getChildren()) {
			if(item.equals(newAction[0])) {
				oldAction[0]=item;
				if(item instanceof javafx.scene.shape.Circle) {
					if(oldAction[1].equals("color")) {
						oldAction[1]="color";
						oldAction[2]=((javafx.scene.shape.Circle) item).getFill();
						((Shape) item).setFill((Color) newAction[2]);
					}
					else if(newAction[1].equals("resize")) {
						oldAction[1]="resize";
						oldX=((javafx.scene.shape.Circle) item).getRadius();
						((javafx.scene.shape.Circle) item).setRadius((Double)newX);
					}
					else if(newAction[1].equals("move")) {
						oldX=((javafx.scene.shape.Circle) item).getCenterX();
						oldY=((javafx.scene.shape.Circle) item).getCenterY();
						((javafx.scene.shape.Circle) item).setCenterX((Double)newX);
						((javafx.scene.shape.Circle) item).setCenterY((Double)newY);
					}
				}
				else if(item instanceof javafx.scene.shape.Rectangle) {
					if(newAction[1].equals("color")) {
						oldAction[1]="color";
						oldAction[2]=((javafx.scene.shape.Rectangle) item).getFill();
						((Shape) item).setFill((Color) newAction[2]);
					}
					else if(newAction[1].equals("resize")) {
						oldAction[1]="resize";
						oldX=((javafx.scene.shape.Rectangle) item).getWidth();
						oldY=((javafx.scene.shape.Rectangle) item).getHeight();
						((javafx.scene.shape.Rectangle) item).setWidth((Double)newX);
						((javafx.scene.shape.Rectangle) item).setHeight((Double)newY);
					}
					else if(newAction[1].equals("move")) {
						oldAction[1]="move";
						oldX=((javafx.scene.shape.Rectangle) item).getX();
						oldY=((javafx.scene.shape.Rectangle) item).getY();
						((javafx.scene.shape.Rectangle) item).setX((Double)newX);
						((javafx.scene.shape.Rectangle) item).setY((Double)newY);
					}
				}
				else if(item instanceof javafx.scene.shape.Line) {
					if(newAction[1].equals("color")) {
						oldAction[1]="color";
						oldAction[2]=((javafx.scene.shape.Line) item).getStroke();
						((Shape) item).setStroke((Color) newAction[2]);
					}
					else if(newAction[1].equals("resize")) {
						oldAction[1]="resize";
						oldX=((javafx.scene.shape.Line) item).getStartX();
						oldY=((javafx.scene.shape.Line) item).getEndX();
						((javafx.scene.shape.Line) item).setStartX(((Line) item).getStartX()+newX/2);
						((javafx.scene.shape.Line) item).setEndX(((Line) item).getEndX()-newX/2);
					}
					else if(newAction[1].equals("move")) {
						oldAction[1]="move";oldAction[2]=((javafx.scene.shape.Line) item).getStartX();
						oldAction[3]=((javafx.scene.shape.Line) item).getStartY();oldAction[4]=((javafx.scene.shape.Line) item).getEndX();
						oldAction[5]=((javafx.scene.shape.Line) item).getEndY();
						((javafx.scene.shape.Line) item).setStartX((Double)newAction[2]);
						((javafx.scene.shape.Line) item).setStartY((Double)newAction[3]);
						((javafx.scene.shape.Line) item).setEndX((Double)newAction[4]);
						((javafx.scene.shape.Line) item).setEndY((Double)newAction[5]);
					}
				}
				else if(item instanceof javafx.scene.shape.Polygon) {
					if(newAction[1].equals("color")) {
						oldAction[1]="color";
						oldAction[2]=((javafx.scene.shape.Polygon) item).getFill();
						((Shape) item).setFill((Color) newAction[2]);
					}
					else if(newAction[1].equals("resize")) {
						oldAction[1]="resize";
						oldAction[2]=new Double[] {
								((javafx.scene.shape.Polygon) item).getPoints().get(0),((javafx.scene.shape.Polygon) item).getPoints().get(1),
								((javafx.scene.shape.Polygon) item).getPoints().get(2),((javafx.scene.shape.Polygon) item).getPoints().get(3),
								((javafx.scene.shape.Polygon) item).getPoints().get(4),((javafx.scene.shape.Polygon) item).getPoints().get(5)};
						((javafx.scene.shape.Polygon) item).getPoints().clear();
						((javafx.scene.shape.Polygon) item).getPoints().setAll((Double [])newAction[2]);
					}
					else if(oldAction[1].equals("move")) {
						oldAction[1]="move";
						oldAction[2]=new Double[] {
								((javafx.scene.shape.Polygon) item).getPoints().get(0),((javafx.scene.shape.Polygon) item).getPoints().get(1),
								((javafx.scene.shape.Polygon) item).getPoints().get(2),((javafx.scene.shape.Polygon) item).getPoints().get(3),
								((javafx.scene.shape.Polygon) item).getPoints().get(4),((javafx.scene.shape.Polygon) item).getPoints().get(5)};
						((javafx.scene.shape.Polygon) item).getPoints().clear();
						((javafx.scene.shape.Polygon) item).getPoints().setAll((Double [])newAction[2]);
					}
				}
			}
		}
	}
}
