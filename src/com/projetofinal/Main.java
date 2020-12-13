/**
 * ESTE PROOJETO É UM GERENTE DE FIADOS.
 * @author: Breno Carvalho da Silva <201911648@uesb.edu.br>
 * @version: v1.0
*/

package com.projetofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/projetofinal/gui/Main.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("PenduraAi");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(620);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}