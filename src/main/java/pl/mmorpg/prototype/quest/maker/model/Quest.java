package pl.mmorpg.prototype.quest.maker.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mmorpg.prototype.server.quests.QuestTask;

@Data
@AllArgsConstructor
public class Quest
{
	private final String name;
	private final String description;
	private final QuestTask questTask;
	private final Integer goldReward;
	private final Collection<ItemInfo> itemReward;
}
