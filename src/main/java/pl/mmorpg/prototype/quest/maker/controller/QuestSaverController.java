package pl.mmorpg.prototype.quest.maker.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.FileChooser;
import pl.mmorpg.prototype.quest.maker.model.GameItem;
import pl.mmorpg.prototype.quest.maker.model.ItemInfo;
import pl.mmorpg.prototype.quest.maker.model.Quest;
import pl.mmorpg.prototype.server.objects.items.Item;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestSaverController
{
	private final ObservableList<GameItem> gameItemsDefinitions;
	private final ObservableList<GameItem> choosenItems;

	@FXML
	private TextArea descriptionTextArea;
	@FXML
	private TextField goldRewardTextArea;
	@FXML
	private ListView<GameItem> itemRewardList;

	private QuestTask questTask;

	public QuestSaverController(QuestTask questTask)
	{
		this.questTask = questTask;
		gameItemsDefinitions = FXCollections
				.unmodifiableObservableList(FXCollections.observableArrayList(getAllGameItems()));
		choosenItems = FXCollections.observableArrayList();
		for(int i=0; i<4; i++)
			choosenItems.add(new GameItem(Item.class));
	}

	private Collection<GameItem> getAllGameItems()
	{
		Reflections reflections = new Reflections(Item.class.getPackage().getName());
		Set<Class<? extends Item>> itemTypes = reflections.getSubTypesOf(Item.class);
		return itemTypes.stream().filter(type -> !Modifier.isAbstract(type.getModifiers())).map(GameItem::new)
				.collect(Collectors.toList());
	}
	
	public void initialize()
	{
		itemRewardList.setEditable(true);		
		itemRewardList.setCellFactory(ComboBoxListCell.forListView(gameItemsDefinitions));
		itemRewardList.setItems(choosenItems);
	}
	
	@FXML
	private void onSaveButton()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose save location");
		File choosenFile = fileChooser.showSaveDialog(descriptionTextArea.getScene().getWindow());
		Quest quest = createQuestObject();
		saveQuest(choosenFile, quest);
	}

	private Quest createQuestObject()
	{
		String questName = getQuestName();
		String description = descriptionTextArea.getText();
		Integer goldReward = Integer.valueOf(goldRewardTextArea.getText());
		Collection<ItemInfo> choosenItems = getChoosenItems();
		return new Quest(questName, description, questTask, goldReward, choosenItems);
	}

	private Collection<ItemInfo> getChoosenItems()
	{
		return this.choosenItems.stream()
					.filter(item -> !item.isBaseType())
					.map(GameItem::generateItemInfo)
					.collect(Collectors.toList());
	}

	private String getQuestName()
	{
		try
		{
			Field questNameField = questTask.getClass().getDeclaredField("questName");
			questNameField.setAccessible(true);
			return (String) questNameField.get(questTask);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	private void saveQuest(File choosenFile, Quest quest)
	{
		String data = serialize(quest);
		try(PrintWriter writer = new PrintWriter(choosenFile.getAbsolutePath()))
		{
			writer.print(data);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		};
	}

	private String serialize(Object object)
	{
		ObjectMapper jsonConverter = new ObjectMapper();
		try
		{
			return jsonConverter.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
