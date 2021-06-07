package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Rectangle extends Polygon{
	javafx.scene.shape.Rectangle rectangle;
	//constor for new rectangle taking neccesarry data
	public Rectangle(double x,double y,double width,double height,Color color) {
		positionX=x;
		positionY=y;
		this.numberOfEdges=4;
		this.width=width;
		this.height=height;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		//adds the data to a new javafx rectangle and adds it to the drawing board
		rectangle = new javafx.scene.shape.Rectangle(positionX-width/2,positionY-height/2,width,height);
		rectangle.setFill(color);
		board.getChildren().add(rectangle);
		rectangle.setOnMouseClicked(event->processAction(event));
		rectangle.setOnMouseReleased(event->processAction(event));
		//sets the action for undo to draw
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		//gets the mouse location and sets the location of the rectangle to it
		positionX=rectangle.getX();positionY=rectangle.getY();
		double posX,posY;
		posX=positionX;posY=positionY;
		//stores the old data to the oldAction array to facilitate undo
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		//checks if the shape is out of bounds for the drawing board
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			rectangle.setX(event.getX()-width/2);
			rectangle.setY(event.getY()-height/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		//stores the old color data to the oldAction array to facilitate undo
		color=(Color) rectangle.getFill();
		Color oldColor=color;
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		//sets the new color
		rectangle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		//stores the old size data to the oldAction array to facilitate undo
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=rectangle.getWidth();
		ui.Controller.oldY=rectangle.getHeight();
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedHeight;
		//sets the new size
		rectangle.setWidth(width);
		rectangle.setHeight(height);
	}
}
