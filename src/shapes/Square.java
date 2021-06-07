package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Square extends Polygon{
	javafx.scene.shape.Rectangle square;
	//constor for new square taking neccesarry data
	public Square(double x,double y,double width,Color color) {
		positionX=x;
		positionY=y;
		this.numberOfEdges=4;
		this.width=width;
		this.height=width;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		//adds the data to a new javafx square and adds it to the drawing board
		square = new javafx.scene.shape.Rectangle(positionX-width/2,positionY-width/2,width,width);
		square.setFill(color);
		board.getChildren().add(square);
		square.setOnMouseClicked(event->processAction(event));
		square.setOnMouseReleased(event->processAction(event));
		//sets the action for undo to draw
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		//gets the mouse location and sets the location of the square to it
		positionX=square.getX();positionY=square.getY();
		double posX,posY;
		posX=positionX;posY=positionY;
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		//checks if the shape is out of bounds for the drawing board
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			square.setX(event.getX()-width/2);
			square.setY(event.getY()-width/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		//stores the old color data to the oldAction array to facilitate undo
		color=(Color) square.getFill();
		Color oldColor=color;
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		//sets the new color
		square.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		//stores the old size data to the oldAction array to facilitate undo
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=square.getWidth();
		ui.Controller.oldY=square.getHeight();
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedWidth;
		//sets the new size
		square.setWidth(width);
		square.setHeight(height);
	}
}
