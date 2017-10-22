package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.controller.ChooseQuestTaskTypeController;
import pl.mmorpg.prototype.quest.maker.helpers.CustomFXMLLoader;
import pl.mmorpg.prototype.quest.maker.helpers.QuestTaskFXContainer;

public class QuestTaskFXContainerContextMenuTreeCell extends ContextMenuTreeCell<QuestTaskFXContainer>
{
	
	public QuestTaskFXContainerContextMenuTreeCell()
	{
		super("Add quest sub task");
	}

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

	@Override
	public EventHandler<ActionEvent> getOnAddOptionAction()
	{
		return this::handle;
	}

}
