package pl.mmorpg.prototype.quest.maker.helpers;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import pl.mmorpg.prototype.quest.maker.QuestMakerLauncher;

public class CustomFXMLLoader
{
	public static <T> T load(String path)
	{
		return load(path, new FXMLLoader());
	}
	
	public static <T> T load(String path, FXMLLoader loader)
	{
		URL resource = QuestMakerLauncher.class.getResource("/" + path);
		try
		{
			loader.setLocation(resource);
			return loader.load();	
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

	}

	public static <T> T load(String path, Object controller)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		return load(path, loader);
	}
}
