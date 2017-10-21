package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FieldEditableTreeCell extends TreeCell<String>
{
	private TextField textField = new TextField();
	private ContextMenu addMenu = new ContextMenu();

	public FieldEditableTreeCell()
	{
		MenuItem addMenuItem = new MenuItem("Add quest sub task");
		addMenu.getItems().add(addMenuItem);
		addMenuItem.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent t)
			{
				if (getTreeItem() == null)
					return;
				TreeItem<String> questSubTask = new TreeItem<String>("Quest sub task");
				getTreeItem().getChildren().add(questSubTask);
				getTreeItem().setExpanded(true);
				getTreeView().getSelectionModel().select(questSubTask);
			}
		});
		setContextMenu(addMenu);

		textField.setOnKeyReleased(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				if (event.getCode() == KeyCode.ENTER)
					commitEdit(textField.getText());
				else if (event.getCode() == KeyCode.ESCAPE)
					cancelEdit();
			}
		});
		textField.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue)
			{
				if (!newPropertyValue)
					FieldEditableTreeCell.this.commitEdit(textField.getText());
			}
		});
	}

	@Override
	public void startEdit()
	{
		super.startEdit();

		TreeItem<String> treeItem = getTreeItem();
		if (treeItem == null)
			return;
		textField.setText(treeItem.getValue());
		setGraphic(textField);
		textField.selectAll();
	}

	@Override
	public void cancelEdit()
	{
		super.cancelEdit();
		setText(getItem());
		setGraphic(getTreeItem().getGraphic());
	}

	@Override
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item, empty);

		setText(null);
		if (empty || item == null)
			setGraphic(null);
		else if (isEditing())
			setGraphic(textField);
		else
		{
			String string = getString();
			setText(string);
			setGraphic(getTreeItem().getGraphic());
		}
	}

	private String getString()
	{
		return getItem() == null ? "" : getItem().toString();
	}
}