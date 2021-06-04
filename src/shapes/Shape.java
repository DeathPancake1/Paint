package shapes;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class Shape extends Node {
	double positionX, positionY; // center of the shape
	abstract void draw(AnchorPane board); //draw the shape
	abstract void move(int x,int y); //move the shape
	abstract void changeColor(); //change the color of the shape
	abstract void resize(); //resize the shape
}
