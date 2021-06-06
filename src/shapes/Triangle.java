package shapes;

import java.util.Arrays;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Triangle extends Polygon{
	javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon();
	public Triangle(double x,double y,double width,double height,Color color) {
		this.positionX=x;
		this.positionY=y;
		this.numberOfEdges=3;
		this.width=width;
		this.height=height;
		Double[] temp = new Double[] {0d,0d,0d,0d,0d,0d};
		triangle.getPoints().setAll(temp);
		triangle.getPoints().set(0, positionX);triangle.getPoints().set(1, positionY-height/2);
		triangle.getPoints().set(2, positionX-width/2);triangle.getPoints().set(3, positionY+height/2);
		triangle.getPoints().set(4, positionX+width/2);triangle.getPoints().set(5, positionY+height/2);
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		triangle.setFill(color);
		board.getChildren().add(triangle);
		triangle.setOnMouseClicked(event->processAction(event));
		triangle.setOnMouseReleased(event->processAction(event));
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		positionX=event.getX();positionY=event.getY();
		double x=positionX,y=positionY;
		width=triangle.getPoints().get(2)-triangle.getPoints().get(4);
		height=triangle.getPoints().get(3)-triangle.getPoints().get(1);
		ui.Controller.oldAction[0]=triangle;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldAction[2]=new Double[] {
				triangle.getPoints().get(0),triangle.getPoints().get(1),
				triangle.getPoints().get(2),triangle.getPoints().get(3),
				triangle.getPoints().get(4),triangle.getPoints().get(5)};
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			triangle.getPoints().set(0, x);triangle.getPoints().set(1, y-height/2);triangle.getPoints().set(2, x-width/2);
			triangle.getPoints().set(3, y+height/2);triangle.getPoints().set(4, x+width/2);triangle.getPoints().set(5, y+height/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=(Color) triangle.getFill();
		Color oldColor=color;
		ui.Controller.oldAction[0]=triangle;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		triangle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		ui.Controller.oldAction[2]=new Double[] {
				triangle.getPoints().get(0),triangle.getPoints().get(1),
				triangle.getPoints().get(2),triangle.getPoints().get(3),
				triangle.getPoints().get(4),triangle.getPoints().get(5)};
		ui.Controller.oldAction[0]=triangle;
		ui.Controller.oldAction[1]="resize";
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedHeight;
		triangle.getPoints().set(0, positionX);triangle.getPoints().set(1, positionY-height/2);
		triangle.getPoints().set(2, positionX-width/2);triangle.getPoints().set(3, positionY+height/2);
		triangle.getPoints().set(4, positionX+width/2);triangle.getPoints().set(5, positionY+height/2);	}
}
