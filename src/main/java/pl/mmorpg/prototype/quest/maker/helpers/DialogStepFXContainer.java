package pl.mmorpg.prototype.quest.maker.helpers;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextArea;
import pl.mmorpg.prototype.quest.maker.model.PropertyContainer;

public class DialogStepFXContainer
{
	private final TextArea playerAnwserTextArea = new TextArea();
	private final TextArea npcSpeechTextArea = new TextArea();
	private final String shownName;

	public DialogStepFXContainer()
	{
		this("New sub dialog");
	}
	
	public DialogStepFXContainer(String shownName)
	{
		this.shownName = shownName;
	}
	
	@Override
	public String toString()
	{
		return shownName;
	}
	
	public TextArea getPlayerAnwserTextArea()
	{
		return playerAnwserTextArea;
	}
	
	public TextArea getNpcSpeechTextArea()
	{
		return npcSpeechTextArea;
	}

	public PropertyContainer generatePropertyContainer()
	{
		Map<String, Object> properties = new HashMap<>();
		return new PropertyContainer(properties);
	}
}
