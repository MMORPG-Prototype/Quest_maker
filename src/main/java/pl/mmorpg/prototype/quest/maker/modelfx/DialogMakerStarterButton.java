package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.controller.DialogOverviewController;
import pl.mmorpg.prototype.quest.maker.helpers.CustomFXMLLoader;
import pl.mmorpg.prototype.server.quests.dialog.DialogStep;

public class DialogMakerStarterButton extends Button
{
	private final DialogOverviewController dialogMakerController = new DialogOverviewController();
	
	public DialogMakerStarterButton()
	{
		super("Modify...");
		setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				openDialogMakerDialog();
			}
		});
	}
	
	private void openDialogMakerDialog()
	{
		Stage stage = new Stage();
		stage.setTitle("Dialog maker");
		Scene scene = new Scene(CustomFXMLLoader.load("DialogOverview.fxml", dialogMakerController));
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.resizableProperty().setValue(false);
		stage.show();
	}
	
	public DialogStep getDialogData()
	{
		return dialogMakerController.getDialogData();
	}
}
