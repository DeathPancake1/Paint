package shapes;

import java.util.Arrays;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Triangle extends Polygon{
	javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon();
	Double[] points = new Double[6];
	public Triangle(double x,double y,double width,double height,Color color) {
		this.positionX=x;
		this.positionY=y;
		this.numberOfEdges=3;
		this.width=width;
		this.height=height;
		points[0]=positionX;points[1]=positionY-height/2;points[2]=positionX-width/2;points[3]=positionY+height/2;points[4]=positionX+width/2;points[5]=positionY+height/2;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		triangle.getPoints().addAll(points);
		triangle.setFill(color);
		board.getChildren().add(triangle);
		triangle.setOnMouseClicked(event->processAction(event));
		triangle.setOnMouseDragged(event->processAction(event));
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		double posX,posY;
		posX=positionX;posY=positionY;
		ui.Controller.oldAction[0]=triangle;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		double x=event.getX(),y=event.getY();
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			points[0]=x;points[1]=y-height/2;points[2]=x-width/2;points[3]=y+height/2;points[4]=x+width/2;points[5]=y+height/2;
			triangle.getPoints().setAll(points);
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
		width=points[2]-points[4];
		height=points[1]-points[3];
		ui.Controller.oldAction[2]=new Double[] {points[0],points[1],points[2],points[3],points[4],points[5]};
		ui.Controller.oldAction[0]=triangle;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=width;
		ui.Controller.oldY=height;
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedHeight;
		points[0]=positionX;points[1]=positionY-height/2;points[2]=positionX-width/2;points[3]=positionY+height/2;points[4]=positionX+width/2;points[5]=positionY+height/2;
		triangle.getPoints().setAll(points);
	}

	@Override
	public void processAction(MouseEvent event) {
		switch(ui.Controller.state) {
		case 0:
			move(event);
			break;
		case 1:
			resize(event);
			break;
		case 2:
			changeColor(event);
			break;
		}
	}

}
