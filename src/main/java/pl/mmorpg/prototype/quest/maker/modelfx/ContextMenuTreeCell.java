package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.controller.ChooseQuestTaskTypeController;
import pl.mmorpg.prototype.quest.maker.helpers.CustomFXMLLoader;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;

public class ContextMenuTreeCell extends TreeCell<QuestTaskFXContainer>
{
	private ContextMenu addMenu = new ContextMenu();

	public ContextMenuTreeCell()
	{
		MenuItem addMenuItem = createAddMenuItem();
		addMenu.getItems().add(addMenuItem);
		setContextMenu(addMenu);
	}

	private MenuItem createAddMenuItem()
	{
		MenuItem addMenuItem = new MenuItem("Add quest sub task");
		addMenuItem.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent t)
			{
				if (getTreeItem() == null)
					return;
				Scene scene = tryLoadingScene("ChooseQuestTaskType.fxml");
				Stage stage = new Stage();
				stage.setTitle("New quest task");
				stage.setScene(scene);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(getTreeView().getScene().getWindow());
				stage.resizableProperty().setValue(Boolean.FALSE);
				stage.show();
			}

			private Scene tryLoadingScene(String location)
			{
				return new Scene(CustomFXMLLoader.load(location, new ChooseQuestTaskTypeController(getTreeView())));
			}
		});
		return addMenuItem;
	}

	@Override
	public void updateItem(QuestTaskFXContainer item, boolean empty)
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