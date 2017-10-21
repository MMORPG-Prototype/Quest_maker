package pl.mmorpg.prototype.quest.maker.model;

import java.util.Collection;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mmorpg.prototype.server.objects.items.ItemIdentifier;
import pl.mmorpg.prototype.server.quests.QuestTask;

@Data
@AllArgsConstructor
public class Quest
{
	private StringProperty name;
	private StringProperty description;
	private ObjectProperty<QuestTask> questTask;
	private IntegerProperty goldReward;
	private ObservableList<ItemIdentifier> itemReward;

	public Quest(String questName, String description, QuestTask questTask, Integer goldReward,
			Collection<ItemIdentifier> itemReward)
	{
		this.name = new SimpleStringProperty(questName);
		this.description = new SimpleStringProperty(description);
		this.goldReward = new SimpleIntegerProperty(goldReward);
		this.itemReward = FXCollections.observableArrayList(itemReward);
	}
}
