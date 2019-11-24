package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.scene.control.TextField;

public class IntegerTextField extends TextField
{
	public IntegerTextField()
	{
		textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*"))
				setText(newValue.replaceAll("[^\\d]", ""));
		});
	}
	
	public Integer getInputValue()
	{
		return Integer.valueOf(getText());
	}
}
