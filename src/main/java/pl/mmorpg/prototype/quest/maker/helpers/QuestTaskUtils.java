package pl.mmorpg.prototype.quest.maker.helpers;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import com.rits.cloning.Cloner;

import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskUtils
{
	private static final Cloner cloner = new Cloner();
	private static final Map<Class<? extends QuestTask>, QuestTaskFXContainer> questTaskContainers = Collections
			.unmodifiableMap(reflectiveScan());
	private static Set<Class<? extends QuestTask>> questTaskTypes;

	private static Map<Class<? extends QuestTask>, QuestTaskFXContainer> reflectiveScan()
	{
		questTaskTypes = Collections.unmodifiableSet(getQuestTaskTypes());
		Map<Class<? extends QuestTask>, QuestTaskFXContainer> questTaskContainers = questTaskTypes.stream()
				.map(QuestTaskFXContainer::new)
				.collect(Collectors.toMap(QuestTaskFXContainer::getQuestTaskType, Function.identity()));
		return questTaskContainers;
	}

	private static Set<Class<? extends QuestTask>> getQuestTaskTypes()
	{
		Reflections reflection = new Reflections(QuestTask.class.getPackage().getName());
		Set<Class<? extends QuestTask>> questTasks = reflection.getSubTypesOf(QuestTask.class);
		questTasks.removeIf(type -> Modifier.isAbstract(type.getModifiers()));
		return questTasks;
	}

	public static QuestTaskFXContainer produceQuestTaskFxContainer(Class<? extends QuestTask> questType)
	{
		QuestTaskFXContainer questTask = questTaskContainers.get(questType);
		QuestTaskFXContainer clone = cloner.deepClone(questTask);
		return clone;
	}
	
	public static Set<Class<? extends QuestTask>> getAllQuestTaskTypes()
	{
		return questTaskTypes;
	}
	
	public static Map<Class<? extends QuestTask>, QuestTaskFXContainer> getAllQuestTaskContainers()
	{
		return questTaskContainers;
	}
}
