package pl.mmorpg.prototype.quest.maker.helpers;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.ReflectionUtils;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskFXContainer
{
	private final Class<? extends QuestTask> questTaskType;

	public QuestTaskFXContainer(Class<? extends QuestTask> questTaskType)
	{
		this.questTaskType = questTaskType;
	}
	
	@Override
	public String toString()
	{
		return questTaskType.getSimpleName();
	}

	@SuppressWarnings("unchecked")
	public Collection<Control> produceControls()
	{
		Set<Field> allFields = ReflectionUtils.getAllFields(questTaskType);
		return allFields.stream()
			.filter(field -> !field.getName().equals("nextTasks"))
			.filter(field -> !field.getName().equals("sourceTask"))
			.filter(field -> !field.getName().equals("desiredEventType"))
			.filter(field -> !field.getName().equals("finished"))
			.map(field -> new Button(field.getName()))
			.collect(Collectors.toList());
		
		//Label keyLabel = new Label(fieldType);
//		String fieldValue = "Insert value";
//		Label valueLabel = new Label(fieldValue);
//		questPropertyControlsContainer.add(keyLabel, 0, i);
//		questPropertyControlsContainer.add(valueLabel, 1, i);
		//Set<Field> allFields = ReflectionUtils.getAllFields(newValue.getValue().getClass());
	}
	
	public Class<? extends QuestTask> getQuestTaskType()
	{
		return questTaskType;
	}
	
}
