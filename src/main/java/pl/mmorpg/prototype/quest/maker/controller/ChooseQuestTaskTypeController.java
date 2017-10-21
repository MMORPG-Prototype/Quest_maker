package pl.mmorpg.prototype.quest.maker.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskUtils;
import pl.mmorpg.prototype.server.quests.AcceptQuestTask;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class ChooseQuestTaskTypeController
{
	@FXML
	private ComboBox<QuestTaskFXContainer> questTaskTypesDropDownList;

	public void initialize()
	{
		intializeComboBox();
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
		closeWindow();
	}

	private void closeWindow()
	{
		Stage stage = (Stage) questTaskTypesDropDownList.getScene().getWindow();
		stage.close();
	}

}
