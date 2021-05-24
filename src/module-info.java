module Paint {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	exports application;
	opens application to javafx.graphics;
}