package shapes;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public abstract class Shape extends Node {
	double positionX=1000/2, positionY=700/2; // center of the shape
	Color color;
	abstract public void draw(AnchorPane board); //draw the shape
	abstract public void move(MouseEvent event); //move the shape
	abstract public void changeColor(MouseEvent event); //change the color of the shape
	abstract public void resize(MouseEvent event); //resize the shape
	public void processAction(MouseEvent event) {//Code that checks the state of the program and adjusts methods
		switch(ui.Controller.state) {
		case 0:
			if(event.getEventType().toString().equals("MOUSE_RELEASED"))
				move(event);
			break;
		case 1:
			if(event.getEventType().toString().equals("MOUSE_CLICKED"))
				resize(event);
			break;
		case 2:
			if(event.getEventType().toString().equals("MOUSE_CLICKED"))
				changeColor(event);
			break;
		}
	}
}
