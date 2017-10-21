package pl.mmorpg.prototype.quest.maker.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskUtils;
import pl.mmorpg.prototype.quest.maker.modelfx.QuestTaskTreeItem;
import pl.mmorpg.prototype.server.quests.AcceptQuestTask;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class ChooseQuestTaskTypeController
{
	@FXML
	private ComboBox<QuestTaskFXContainer> questTaskTypesDropDownList;
	private TreeView<QuestTaskFXContainer> treeView;

	public void initialize()
	{
		intializeComboBox();
	}
	
	public ChooseQuestTaskTypeController(TreeView<QuestTaskFXContainer> treeView)
	{
		this.treeView = treeView;
	}

	private void intializeComboBox()
	{
		Map<Class<? extends QuestTask>, QuestTaskFXContainer> questTaskContainers = new HashMap<>(
				QuestTaskUtils.getAllQuestTaskContainers());
		questTaskContainers.remove(AcceptQuestTask.class);
		ObservableList<QuestTaskFXContainer> observableArrayList = FXCollections
				.observableArrayList(questTaskContainers.values());
		questTaskTypesDropDownList.setItems(observableArrayList);
		questTaskTypesDropDownList.getSelectionModel().select(0);
	}

	@FXML
	private void onOkButton()
	{
		Class<? extends QuestTask> selectedQuestType = getSelectedQuestType();
		QuestTaskTreeItem newQuestTask = new QuestTaskTreeItem(selectedQuestType);
		treeView.getSelectionModel().getSelectedItem().getChildren().add(newQuestTask);
		treeView.getSelectionModel().select(newQuestTask);
		closeWindow();
	}

	private Class<? extends QuestTask> getSelectedQuestType()
	{
		return questTaskTypesDropDownList.getSelectionModel().getSelectedItem().getQuestTaskType();
	}

	private void closeWindow()
	{
		Stage stage = (Stage) questTaskTypesDropDownList.getScene().getWindow();
		stage.close();
	}

}
