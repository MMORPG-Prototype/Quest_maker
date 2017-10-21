package pl.mmorpg.prototype.quest.maker.controller;

import java.util.Collection;
import java.util.Iterator;

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
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.modelfx.ContextMenuTreeCell;
import pl.mmorpg.prototype.quest.maker.modelfx.QuestTaskTreeItem;
import pl.mmorpg.prototype.server.quests.AcceptQuestTask;

public class QuestOverviewController
{
	@FXML
	private TreeView<QuestTaskFXContainer> treeQuestView;

	@FXML
	private GridPane questPropertyControlsContainer;

	public void initialize()
	{
		setTreeQuestViewCellFactory();
		QuestTaskTreeItem rootItem = new QuestTaskTreeItem(AcceptQuestTask.class);
		treeQuestView.setRoot(rootItem);
		treeQuestView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<QuestTaskFXContainer>>()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void changed(ObservableValue<? extends TreeItem<QuestTaskFXContainer>> observable,
					TreeItem<QuestTaskFXContainer> oldValue, TreeItem<QuestTaskFXContainer> newValue)
			{
				questPropertyControlsContainer.getChildren().clear();
				Collection<Control> controls = newValue.getValue().produceControls();
				Iterator<Control> iterator = controls.iterator();
				for(int i=0; iterator.hasNext(); i++)
				{
					questPropertyControlsContainer.add(iterator.next(), 0, i);
					questPropertyControlsContainer.add(new Label("Some value"), 1, i);
				}
			}
		});
		treeQuestView.getSelectionModel().select(0);
		treeQuestView.setEditable(true);
	}
	
	static String splitCamelCase(String source) 
	{
		String firstLetterLowercase = source.replaceAll
				   (
		      String.format("%s|%s|%s",
		         "(?<=[A-Z])(?=[A-Z][a-z])",
		         "(?<=[^A-Z])(?=[A-Z])",
		         "(?<=[A-Za-z])(?=[^A-Za-z])"
		      ),
		      " "
		   );
		return firstLetterLowercase.substring(0, 1).toUpperCase() + firstLetterLowercase.substring(1);
	}

	private void setTreeQuestViewCellFactory()
	{
		treeQuestView.setCellFactory(new Callback<TreeView<QuestTaskFXContainer>, TreeCell<QuestTaskFXContainer>>()
		{
			@Override
			public TreeCell<QuestTaskFXContainer> call(TreeView<QuestTaskFXContainer> treeView)
			{
				ContextMenuTreeCell cell = new ContextMenuTreeCell();
				return cell;
			}
		});
	}

}
