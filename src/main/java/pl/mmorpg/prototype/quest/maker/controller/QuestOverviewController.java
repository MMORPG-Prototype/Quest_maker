package pl.mmorpg.prototype.quest.maker.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
			@Override
			public void changed(ObservableValue<? extends TreeItem<QuestTaskFXContainer>> observable,
					TreeItem<QuestTaskFXContainer> oldValue, TreeItem<QuestTaskFXContainer> newValue)
			{
				questPropertyControlsContainer.getChildren().add(new Button("Asdd"));
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
				ContextMenuTreeCell cell = new ContextMenuTreeCell();
				return cell;
			}
		});
	}

}
