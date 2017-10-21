package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.scene.control.TreeItem;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskUtils;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestTaskTreeItem extends TreeItem<QuestTaskFXContainer>
{
	
	public QuestTaskTreeItem(Class<? extends QuestTask> questType)
	{
		super(QuestTaskUtils.produceQuestTaskFxContainer(questType));
	}
}
