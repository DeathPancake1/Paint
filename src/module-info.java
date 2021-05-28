module Paint {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	exports application;
	exports ui;
	opens application to javafx.graphics;
	opens ui to javafx.fxml;
}