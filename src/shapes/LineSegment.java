package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineSegment extends Polygon{
	Line line;
	//constor for new line taking neccesarry data
	public LineSegment(double x,double y,int width,Color color) {
		positionX=x;
		positionY=y;
		this.numberOfEdges=1;
		this.width=width;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		//code that calculates the points based on width and height
		line = new Line(positionX-width/2,positionY,positionX+width/2,positionY);
		line.setStroke(color);
		board.getChildren().add(line);
		line.setOnMouseClicked(event->processAction(event));
		line.setOnMouseReleased(event->processAction(event));
		//sets the action for undo to draw
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		//gets the mouse location and sets the location of the line to it
		ui.Controller.oldAction[0]=line;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldAction[2]= line.getStartX();
		ui.Controller.oldAction[3]= line.getStartY();
		ui.Controller.oldAction[4]= line.getEndX();
		ui.Controller.oldAction[5]= line.getEndY();
		//checks if the shape is out of bounds for the drawing board
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700&&event.getY()>0) {
			line.setStartX(event.getX()-width/2);
			line.setStartY(event.getY());
			line.setEndY(event.getY());
			line.setEndX(event.getX()+width/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		//stores the old color data to the oldAction array to facilitate undo
		color=(Color) line.getStroke();
		Color oldColor=color;
		ui.Controller.oldAction[0]=line;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		//sets the new color
		line.setStroke(color);
	}

	@Override
	public void resize(MouseEvent event) {
		//stores the old size data to the oldAction array to facilitate undo
		width=line.getEndX()-line.getStartX();
		ui.Controller.oldAction[0]=line;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=width;
		width=ui.Controller.selectedWidth;
		//sets the new size
		line.setStartX(line.getStartX()-width/2);
		line.setEndX(line.getEndX()+width/2);
	}
}
