package shapes;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public abstract class Shape extends Node {
	double positionX=1000/2, positionY=700/2; // center of the shape
	Color color;
	abstract public void draw(AnchorPane board); //draw the shape
	abstract public void move(int x,int y); //move the shape
	abstract public void changeColor(); //change the color of the shape
	abstract public void resize(); //resize the shape
}
