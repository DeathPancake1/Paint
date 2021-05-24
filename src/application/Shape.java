package application;

public interface Shape {
	public void create();
	public void color(String hexColor);
	public void move(int x,int y);
	public void resize(int width,int height);
}
