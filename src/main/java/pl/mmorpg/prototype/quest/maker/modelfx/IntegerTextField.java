package pl.mmorpg.prototype.quest.maker.modelfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntegerTextField extends TextField
{
	public IntegerTextField()
	{
		textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) 
		            setText(newValue.replaceAll("[^\\d]", ""));
		    }
		});
		setText("1");
	}
	
	public Integer getInputValue()
	{
		return Integer.valueOf(getText());
	}
}
