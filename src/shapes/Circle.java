package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Circle extends Elliptical {
	javafx.scene.shape.Circle circle;
	public Circle(double x,double y,int radius,Color color) {
		positionX=x;
		positionY=y;
		this.radius = radius;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		circle = new javafx.scene.shape.Circle(positionX,positionY,100,color);
		if(radius<=350) {
			circle.setRadius(radius);
		}
		board.getChildren().add(circle);
		circle.setOnMouseClicked(event->processAction(event));
		circle.setOnMouseDragged(event->processAction(event));
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		double posX=circle.getCenterX(),posY=circle.getCenterY();
		ui.Controller.oldAction[0]=circle;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		if(event.getX()>radius&&event.getX()<1000-radius&&event.getY()<700-radius&&event.getY()>radius) {
			circle.setCenterX(event.getX());
			circle.setCenterY(event.getY());
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		Color oldColor=(Color) circle.getFill();
		ui.Controller.oldAction[0]=circle;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		circle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		radius=circle.getRadius();
		ui.Controller.oldAction[0]=circle;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=radius;
		if(ui.Controller.selectedWidth<=350) {
			radius=ui.Controller.selectedWidth;
			circle.setRadius(radius);
		}
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
