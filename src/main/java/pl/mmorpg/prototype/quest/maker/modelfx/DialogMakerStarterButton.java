package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.mmorpg.prototype.quest.maker.controller.DialogOverviewController;
import pl.mmorpg.prototype.quest.maker.helpers.CustomFXMLLoader;
import pl.mmorpg.prototype.server.quests.dialog.DialogStep;

public class DialogMakerStarterButton extends Button
{
	private Stage stage = null;
	private final DialogOverviewController dialogMakerController = new DialogOverviewController();

	public DialogMakerStarterButton()
	{
		super("Modify...");
		setOnAction(event -> openDialogMakerDialog());
	}

	private Stage createDialogMakerDialog()
	{
		Stage stage = new Stage();
		stage.setTitle("Dialog maker");
		Scene scene = new Scene(CustomFXMLLoader.load("DialogOverview.fxml", dialogMakerController));
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.resizableProperty().setValue(false);
		return stage;
	}

	private void openDialogMakerDialog()
	{
		if(stage == null)
			stage = createDialogMakerDialog();
		
		stage.show();
	}

	public DialogStep getDialogData()
	{
		return dialogMakerController.getDialogData();
	}
}
