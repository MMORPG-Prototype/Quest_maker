package pl.mmorpg.prototype.quest.maker.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.mmorpg.prototype.quest.maker.helpers.DialogStepFXContainer;
import pl.mmorpg.prototype.quest.maker.modelfx.DialogStepFXContainerContextMenuTreeCell;
import pl.mmorpg.prototype.server.quests.dialog.DialogStep;

public class DialogOverviewController
{
	@FXML
	private TreeView<DialogStepFXContainer> dialogTree;

	@FXML
	private Pane playerAnwserTextAreaContainer;

	@FXML
	private Pane npcSpeechTextAreaContainer;

	public void initialize()
	{
		setDialogTreeCellFactory();
		setDialogTreeOnSelectionChange();
		addRootElementToDialogTree();
	}

	private void setDialogTreeCellFactory()
	{
		dialogTree.setCellFactory(treeView -> new DialogStepFXContainerContextMenuTreeCell());
	}

	private void setDialogTreeOnSelectionChange()
	{
		dialogTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onSelectionChange(newValue));
	}

	private void onSelectionChange(TreeItem<DialogStepFXContainer> newValue)
	{
		playerAnwserTextAreaContainer.getChildren().clear();
		npcSpeechTextAreaContainer.getChildren().clear();
		DialogStepFXContainer dialogStepFXContainer = newValue.getValue();
		TextArea playerAnwserTextArea = dialogStepFXContainer.getPlayerAnwserTextArea();
		TextArea npcSpeechTextArea = dialogStepFXContainer.getNpcSpeechTextArea();
		playerAnwserTextAreaContainer.getChildren().add(playerAnwserTextArea);
		npcSpeechTextAreaContainer.getChildren().add(npcSpeechTextArea);
		playerAnwserTextArea.setMaxHeight(75);
		npcSpeechTextArea.setMinHeight(494);
		playerAnwserTextArea.setMaxWidth(394);
		npcSpeechTextArea.setMaxWidth(394); 
	}
	
	private void addRootElementToDialogTree()
	{
		TreeItem<DialogStepFXContainer> root = new TreeItem<>(new DialogStepFXContainer("Root dialog step"));
		dialogTree.setRoot(root);
		dialogTree.getSelectionModel().select(root);
	}

	@FXML
	private void onOkButton()
	{
		Stage stage = (Stage) dialogTree.getScene().getWindow();
		stage.close();
	}

	public DialogStep getDialogData()
	{
		TreeItem<DialogStepFXContainer> root = dialogTree.getRoot();
		return makeDialogStep(root);
	}

	private DialogStep makeDialogStep(TreeItem<DialogStepFXContainer> root)
	{
		Map<String, DialogStep> furtherDialogs = new HashMap<>();
		for (TreeItem<DialogStepFXContainer> child : root.getChildren())
		{
			DialogStep dialogStep = makeDialogStep(child);
			furtherDialogs.put(child.getValue().getPlayerAnwserTextArea().getText(), dialogStep);
		}
		return new DialogStep(root.getValue().getNpcSpeechTextArea().getText(), furtherDialogs);
	}
}
