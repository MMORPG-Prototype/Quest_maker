package pl.mmorpg.prototype.quest.maker.helpers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.ReflectionUtils;

import javafx.scene.control.Control;
import pl.mmorpg.prototype.quest.maker.model.PropertyContainer;
import pl.mmorpg.prototype.server.quests.QuestMakerIgnore;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskFXContainer
{
	private final Class<? extends QuestTask> questTaskType;
	private Map<String, Control> controls = null;

	public QuestTaskFXContainer(Class<? extends QuestTask> questTaskType)
	{
		this.questTaskType = questTaskType;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Control> produceControls(Class<? extends QuestTask> questTaskType)
	{
		Set<Field> allFields = ReflectionUtils.getAllFields(questTaskType);
		return allFields.stream()
				.filter(field -> field.getAnnotation(QuestMakerIgnore.class) == null)
				.collect(Collectors.toMap(
						field -> field.getName(), QuestTaskControlsUtils::produceControl));
	}

	@Override
	public String toString()
	{
		return questTaskType.getSimpleName();
	}

	public Map<String, Control> getControls()
	{
		if (controls == null)
			controls = produceControls(questTaskType);

		return controls;
	}

	public PropertyContainer generatePropertyContainer()
	{
		if (controls == null)
			controls = produceControls(questTaskType);

		Map<String, Object> properties = controls.entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> QuestTaskControlsUtils.getControlInput(entry.getValue())));
		properties.put("@class", questTaskType.getName());
		return new PropertyContainer(properties);
	}

	public Class<? extends QuestTask> getQuestTaskType()
	{
		return questTaskType;
	}

}
