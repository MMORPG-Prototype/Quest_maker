package pl.mmorpg.prototype.quest.maker;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class QuestMakerLaucher extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		BorderPane anchorPane = tryLoadingPane();
		Scene scene = new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Quest maker");
		primaryStage.show();
	}

	private BorderPane tryLoadingPane()
	{
		try
		{
			return FXMLLoader.load(QuestMakerLaucher.class.getResource("/RootLayout.fxml"));
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
