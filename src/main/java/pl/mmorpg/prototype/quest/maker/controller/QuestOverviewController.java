package pl.mmorpg.prototype.quest.maker.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import pl.mmorpg.prototype.quest.maker.helpers.Dictionary;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.model.PropertyContainer;
import pl.mmorpg.prototype.quest.maker.modelfx.ContextMenuTreeCell;
import pl.mmorpg.prototype.quest.maker.modelfx.QuestTaskFXContainerContextMenuTreeCell;
import pl.mmorpg.prototype.quest.maker.modelfx.QuestTaskTreeItem;
import pl.mmorpg.prototype.server.quests.AcceptQuestTask;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestOverviewController
{
	@FXML
	private TreeView<QuestTaskFXContainer> treeQuestView;

	@FXML
	private GridPane questPropertyControlsContainer;
	
	private final Dictionary dictionary = new Dictionary(); 

	public void initialize()
	{
		setTreeQuestViewCellFactory();
		QuestTaskTreeItem rootItem = new QuestTaskTreeItem(AcceptQuestTask.class);
		treeQuestView.setRoot(rootItem);
		treeQuestView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<QuestTaskFXContainer>>()
		{

			@Override
			public void changed(ObservableValue<? extends TreeItem<QuestTaskFXContainer>> observable,
					TreeItem<QuestTaskFXContainer> oldValue, TreeItem<QuestTaskFXContainer> newValue)
			{
				questPropertyControlsContainer.getChildren().clear();
				Map<String, Control> controls = newValue.getValue().getControls();
				Iterator<Entry<String, Control>> iterator = controls.entrySet().iterator();
				for(int i=0; iterator.hasNext(); i++)
				{
					Entry<String, Control> entry = iterator.next();
					Label nameLabel = new Label(dictionary.translate(entry.getKey()));
					questPropertyControlsContainer.addRow(i, nameLabel, entry.getValue());
				}
			}
		});
		treeQuestView.getSelectionModel().select(0);
		treeQuestView.setEditable(true);
	}
	
	private void setTreeQuestViewCellFactory()
	{
		treeQuestView.setCellFactory(new Callback<TreeView<QuestTaskFXContainer>, TreeCell<QuestTaskFXContainer>>()
		{
			@Override
			public TreeCell<QuestTaskFXContainer> call(TreeView<QuestTaskFXContainer> treeView)
			{
				ContextMenuTreeCell<QuestTaskFXContainer> cell = new QuestTaskFXContainerContextMenuTreeCell();
				return cell;
			}
		});
	}

	public void save(String filepath)
	{
		TreeItem<QuestTaskFXContainer> root = treeQuestView.getRoot();
		PropertyContainer fullPropertyContainer = createFullPropertyContainer(root);
		String serialized = serialize(fullPropertyContainer);
		QuestTask questTask = validate(serialized);
		System.out.println(serialized);
	}

	private QuestTask validate(String serialized)
	{
		ObjectMapper jsonConverter = new ObjectMapper();
		TypeReference<QuestTask> typeRef = new TypeReference<QuestTask>() {};
		try
		{
			QuestTask readValue = (QuestTask)jsonConverter.readValue(serialized, typeRef);
			return readValue;
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	private String serialize(PropertyContainer fullPropertyContainer)
	{
		ObjectMapper jsonConverter = new ObjectMapper();
		try
		{
			return jsonConverter.writerWithDefaultPrettyPrinter().writeValueAsString(fullPropertyContainer.getProperties());
		} catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public PropertyContainer createFullPropertyContainer(TreeItem<QuestTaskFXContainer> root)
	{
		PropertyContainer fullPropertyContainer = root.getValue().generatePropertyContainer();
		for(TreeItem<QuestTaskFXContainer> child : root.getChildren())
		{
			PropertyContainer propertyContainer = createFullPropertyContainer(child);
			fullPropertyContainer.nest("nextTasks", propertyContainer);
		}
		return fullPropertyContainer;
	}

}
