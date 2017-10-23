package pl.mmorpg.prototype.quest.maker.controller;

import javafx.fxml.FXML;

public class RootLayoutController
{
	@FXML
	private QuestOverviewController questOverviewController;
	
	@FXML
	private void onSaveOption()
	{
		questOverviewController.save();
	}
	
	@FXML
	private void onLoadOption()
	{
		
	}
}
