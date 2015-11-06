package Main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** Author Sally AL **/
public class Main extends Application {

	private static final int width = 800, height = 700;

	private FirstScene firstScene;

	private Stage stage;

	Group root = new Group();
	private Scene thisScene = new Scene(root, width, height, Color.WHITE);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		firstScene = new FirstScene();
		thisScene = firstScene.init(stage, thisScene, width, height, root);
		stage.setResizable(false);
		stage.setScene(thisScene);
		stage.show();

	}

}