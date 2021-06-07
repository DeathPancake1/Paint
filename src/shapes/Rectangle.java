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
		rectangle.setOnMouseReleased(event->processAction(event));
		ui.Controller.oldAction[1]="draw";
	}

	@Override
	public void move(MouseEvent event) {
		positionX=rectangle.getX();positionY=rectangle.getY();
		double posX,posY;
		posX=positionX;posY=positionY;
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-height/2&&event.getY()>height/2) {
			rectangle.setX(event.getX()-width/2);
			rectangle.setY(event.getY()-height/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=(Color) rectangle.getFill();
		Color oldColor=color;
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		rectangle.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		ui.Controller.oldAction[0]=rectangle;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=rectangle.getWidth();
		ui.Controller.oldY=rectangle.getHeight();
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedHeight;
		rectangle.setWidth(width);
		rectangle.setHeight(height);
	}
}
