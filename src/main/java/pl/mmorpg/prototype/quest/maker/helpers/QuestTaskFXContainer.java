package pl.mmorpg.prototype.quest.maker.helpers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskFXContainer
{
	private final Class<? extends QuestTask> questTaskType;
	private Map<String, Object> properties;

	public QuestTaskFXContainer(Class<? extends QuestTask> questTaskType)
	{
		this.questTaskType = questTaskType;
	}
	
	@Override
	public String toString()
	{
		return questTaskType.getSimpleName();
	}

	public Collection<Control> produceControls()
	{
		Field[] fields = questTaskType.getDeclaredFields();
		return Arrays.stream(fields)
			.map(field -> new Button(field.getName()))
			.collect(Collectors.toList());
	}
	
	public Class<? extends QuestTask> getQuestTaskType()
	{
		return questTaskType;
	}
	
}
