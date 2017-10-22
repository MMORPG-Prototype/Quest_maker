package pl.mmorpg.prototype.quest.maker.helpers;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import javafx.fxml.FXMLLoader;
import pl.mmorpg.prototype.quest.maker.QuestMakerLauncher;

//Workaround for single jar destributions
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
			try
			{
				URL url = QuestMakerLauncher.class.getProtectionDomain().getCodeSource().getLocation();
				String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
				url = new URL("jar:file://" + jarPath + "!/" + path);
				FXMLLoader newLoader = new FXMLLoader();
				if(loader.getController() != null)
					newLoader.setController(loader.getController());
				newLoader.setLocation(url);
				return newLoader.load();
			} catch (IOException e1)
			{
				throw new RuntimeException(e1);
			}
		}

	}

	public static <T> T load(String path, Object controller)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		return load(path, loader);
	}
}
