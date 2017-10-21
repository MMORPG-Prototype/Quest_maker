package pl.mmorpg.prototype.quest.maker.controller;

import pl.mmorpg.prototype.server.quests.AcceptQuestTask;
import pl.mmorpg.prototype.server.quests.KillMonstersTask;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskType
{
	private final String questSimpleName;
	
	public QuestTaskType(Class<? extends QuestTask> questTask)
	{
		questSimpleName = questTask.getSimpleName();
	}
	
	@Override
	public String toString()
	{ 
		return questSimpleName;
	}
	
	public static class Instances
	{
		public static final QuestTaskType acceptQuestTaskType = new QuestTaskType(AcceptQuestTask.class);
		public static final QuestTaskType killMonstersTaskType = new QuestTaskType(KillMonstersTask.class);
	}
}
