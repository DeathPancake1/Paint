package shapes;

import javafx.scene.layout.AnchorPane;

public class LineSegment extends Polygon{
	public LineSegment() {
		this.numberOfEdges=1;
		this.width=50;
		line()
	}
	@Override
	void draw(AnchorPane board) {
		LineSegment line=new LineSegment();
		board.getChildren().add(line);
	}

	@Override
	void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void changeColor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void resize() {
		// TODO Auto-generated method stub
		
	}
}
