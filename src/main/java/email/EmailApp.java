package email;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EmailApp extends Application {

	public static Stage primaryStage;
	private EmailController controller;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		EmailApp.primaryStage = primaryStage;
		
		controller = new EmailController();
		
		primaryStage.setTitle("Enviar email");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/email-send-icon-32x32.png")));
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
