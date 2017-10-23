package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;

public abstract class ContextMenuTreeCell<T> extends TreeCell<T>
{
	private ContextMenu addMenu = new ContextMenu();

	public ContextMenuTreeCell(String addMenuItemMessage)
	{
		MenuItem addMenuItem = createAddMenuItem(addMenuItemMessage);
		addMenu.getItems().add(addMenuItem); 
		setContextMenu(addMenu);
	}

	private MenuItem createAddMenuItem(String addMenuItemMessage)
	{
		MenuItem addMenuItem = new MenuItem(addMenuItemMessage);
		addMenuItem.setOnAction(getOnAddOptionAction());
		return addMenuItem;
	}
	
	public abstract EventHandler<ActionEvent> getOnAddOptionAction();

	@Override
	public void updateItem(T item, boolean empty)
	{
		super.updateItem(item, empty);

		if (!empty)
			setText(item.toString());
		else
		{
			setText(null);
		}
	}
}