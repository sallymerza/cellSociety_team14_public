/**
 *
 */
package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUI.GUI;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Sally Al
 *
 */
public class FirstScene {
	private static final int width = 800, height = 700, TWO = 2;

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/English";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);

	Button uploadButton = new Button();

	public Scene init(Stage stage, Scene scene, int width, int height, Group root) {
		/*
		 * myStage=stage; myScene=scene; myRoot=root;
		 */
		List<Object> list = new ArrayList<Object>(0);
		list.add(stage);
		list.add(scene);
		list.add(root);

		uploadButton.setLayoutX((width / TWO) - 100);
		uploadButton.setLayoutY(height / TWO);
		uploadButton.setOnAction(e -> ButtonClicked(list));
		uploadButton.setText("Click to upload an xml file");
		root.getChildren().add(uploadButton);
		return scene;

	}

	public void ButtonClicked(List<Object> list) {

		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		String label = null;

		if (selectedFile != null && selectedFile.getName().endsWith("xml")) {
			callGUI(list, selectedFile.getName());
		}

		else {
			if (selectedFile == null) {

				label = myResources.getString("UploadCanceled");
				alert.setContentText(label);

				alert.showAndWait();

			} else {

				label = myResources.getString("WrongFileFormat");

				alert.setContentText(label);

				alert.showAndWait();

			}
		}

	}

	public void callGUI(List<Object> list, String selectedFile) {
		GUI gui = new GUI();
		Stage stage = (Stage) list.get(0);
		Scene scene = (Scene) list.get(1);

		scene = gui.init(selectedFile, width, height, list);
		stage.setScene(scene);
		stage.show();
	}

}