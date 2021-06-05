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
	}

	@Override
	public void move(MouseEvent event) {
		if(event.getX()>radius&&event.getX()<1000-radius&&event.getY()<700-radius&&event.getY()>radius) {
			circle.setCenterX(event.getX());
			circle.setCenterY(event.getY());
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=ui.Controller.selectedColor;
		circle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
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
