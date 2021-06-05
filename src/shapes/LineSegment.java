package shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineSegment extends Polygon{
	Line line;
	public LineSegment(double x,double y,int width,Color color) {
		positionX=x;
		positionY=y;
		this.numberOfEdges=1;
		this.width=width;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		line = new Line(positionX-width/2,positionY,positionX+width/2,positionY);
		line.setStroke(color);
		board.getChildren().add(line);
		line.setOnMouseClicked(event->processAction(event));
		line.setOnMouseDragged(event->processAction(event));
	}

	@Override
	public void move(MouseEvent event) {
		if(event.getX()>width/2&&event.getX()<1000-width/2&&event.getY()<700&&event.getY()>0) {
			line.setStartX(event.getX()-width/2);
			line.setStartY(event.getY());
			line.setEndY(event.getY());
			line.setEndX(event.getX()+width/2);
		}
	}

	@Override
	public void changeColor(MouseEvent event) {
		color=ui.Controller.selectedColor;
		line.setStroke(color);
	}

	@Override
	public void resize(MouseEvent event) {
		line.setStartX(line.getStartX()-ui.Controller.selectedWidth/2);
		line.setEndX(line.getEndX()+ui.Controller.selectedWidth/2);
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