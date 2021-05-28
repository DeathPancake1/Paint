package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Paint extends Application{
	@FXML
	private BorderPane mainPane;

	@Override
    public void start(Stage primaryStage) throws Exception {
       try {
    	    FXMLLoader load = new FXMLLoader();
    	    load.setLocation(getClass().getResource("/ui/Home.fxml"));
            Pane pane = FXMLLoader.load(getClass().getResource("/ui/Drawing.fxml"));
            mainPane=load.load();
            Scene scene = new Scene(mainPane);
            mainPane.setCenter(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
       } catch(Exception e) {
            e.printStackTrace();
       } 
   }

   public static void main(String[] args) {
                launch(args);
   }
}
