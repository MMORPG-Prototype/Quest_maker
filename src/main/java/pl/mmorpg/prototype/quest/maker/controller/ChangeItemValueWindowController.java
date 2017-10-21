package pl.mmorpg.prototype.quest.maker.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class ChangeItemValueWindowController
{
	@FXML
	private TextField newNameField;
	
	private TreeItem<String> treeItem;

	public ChangeItemValueWindowController(TreeItem<String> treeItem)
	{
		this.treeItem = treeItem;
	}
	
	public void initialize()
	{
	}

	@FXML
	private void onCancelButton()
	{
		closeWindow();
	}

	@FXML
	private void onOkButton()
	{
		treeItem.setValue(newNameField.getText());
		closeWindow();
	}

	private void closeWindow()
	{
		Stage stage = (Stage) newNameField.getScene().getWindow();
		stage.close();
	}
	
}
