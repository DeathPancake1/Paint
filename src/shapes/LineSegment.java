package shapes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineSegment extends Polygon{
	public LineSegment(int width,Color color) {
		this.numberOfEdges=1;
		this.width=width;
		this.color=color;
	}
	@Override
	public void draw(AnchorPane board) {
		Line line = new Line(positionX-width/2,positionY,positionX+width/2,positionY);
		line.setStroke(color);
		board.getChildren().add(line);
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize() {
		// TODO Auto-generated method stub
		
	}
}
