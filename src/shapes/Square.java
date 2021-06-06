package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Square extends Polygon{
	javafx.scene.shape.Rectangle square;
	public Square(double x,double y,double width,Color color) {
		positionX=x;
		positionY=y;
		this.numberOfEdges=4;
		this.width=width;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		square = new javafx.scene.shape.Rectangle(positionX-width/2,positionY-width/2,width,width);
		square.setFill(color);
		board.getChildren().add(square);
		square.setOnMouseClicked(event->processAction(event));
		square.setOnMouseDragged(event->processAction(event));
	}

	@Override
	public void move(MouseEvent event) {
		double posX,posY;
		posX=positionX;posY=positionY;
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="move";
		ui.Controller.oldX=posX;
		ui.Controller.oldY=posY;
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700-width/2&&event.getY()>width/2) {
			square.setX(event.getX()-width/2);
			square.setY(event.getY()-width/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=(Color) square.getFill();
		Color oldColor=color;
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="color";
		ui.Controller.oldAction[2]= oldColor;
		color=ui.Controller.selectedColor;
		square.setFill(color);
	}

	@Override
	public void resize(MouseEvent event) {
		ui.Controller.oldAction[0]=square;
		ui.Controller.oldAction[1]="resize";
		ui.Controller.oldX=square.getWidth();
		ui.Controller.oldY=square.getHeight();
		width=ui.Controller.selectedWidth;
		height=ui.Controller.selectedWidth;
		square.setWidth(width);
		square.setHeight(height);
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
