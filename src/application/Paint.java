package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Paint extends Application{
	//loads the main fxml borderpane
	@FXML
	private BorderPane mainPane;

	@Override
    public void start(Stage primaryStage) throws Exception {
       try {
	    //creates new loader and loads the file in the giving location
    	    FXMLLoader load = new FXMLLoader();
    	    load.setLocation(getClass().getResource("/ui/Home.fxml"));
            mainPane=load.load();
	    //sets the scene and shows it
            Scene scene = new Scene(mainPane);
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
