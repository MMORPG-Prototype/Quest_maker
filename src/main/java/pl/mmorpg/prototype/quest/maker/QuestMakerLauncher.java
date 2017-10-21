package pl.mmorpg.prototype.quest.maker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.helpers.CustomFXMLLoader;

public class QuestMakerLauncher extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		BorderPane anchorPane = CustomFXMLLoader.load("RootLayout.fxml");;
		Scene scene = new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Quest maker");
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
