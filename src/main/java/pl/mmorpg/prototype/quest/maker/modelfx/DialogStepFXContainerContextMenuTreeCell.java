package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import pl.mmorpg.prototype.quest.maker.helpers.DialogStepFXContainer;

public class DialogStepFXContainerContextMenuTreeCell extends ContextMenuTreeCell<DialogStepFXContainer>
{
	public DialogStepFXContainerContextMenuTreeCell()
	{
		super("Add sub dialog");
	}

	@Override
	public EventHandler<ActionEvent> getOnAddOptionAction()
	{
		return a -> onAddOptionAction();
	}
	
	private void onAddOptionAction()
	{
		TreeItem<DialogStepFXContainer> newDialogStepItem = new TreeItem<DialogStepFXContainer>(new DialogStepFXContainer());
		TreeItem<DialogStepFXContainer> selectedItem = getTreeView().getSelectionModel().getSelectedItem();
		selectedItem.getChildren().add(newDialogStepItem);
		selectedItem.setExpanded(true);
		getTreeView().getSelectionModel().select(newDialogStepItem);
	}

}
