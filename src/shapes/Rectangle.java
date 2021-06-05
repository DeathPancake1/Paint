package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Rectangle extends Polygon{
	javafx.scene.shape.Rectangle rectangle;
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
		rectangle = new javafx.scene.shape.Rectangle(positionX-width/2,positionY-height/2,width,height);
		rectangle.setFill(color);
		board.getChildren().add(rectangle);
		rectangle.setOnMouseClicked(event->processAction(event));
		rectangle.setOnMouseDragged(event->processAction(event));
	}

	@Override
	public void move(MouseEvent event) {
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			rectangle.setX(event.getX()-width/2);
			rectangle.setY(event.getY()-height/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=ui.Controller.selectedColor;
		rectangle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedHeight;
		rectangle.setWidth(width);
		rectangle.setHeight(height);
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
