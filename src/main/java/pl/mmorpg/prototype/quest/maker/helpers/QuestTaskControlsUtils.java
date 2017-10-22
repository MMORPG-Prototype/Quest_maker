package pl.mmorpg.prototype.quest.maker.helpers;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ClassUtils;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import pl.mmorpg.prototype.quest.maker.modelfx.DialogMakerStarterButton;
import pl.mmorpg.prototype.quest.maker.modelfx.IntegerTextField;
import pl.mmorpg.prototype.quest.maker.modelfx.MonsterIdentifierComboBox;
import pl.mmorpg.prototype.server.quests.dialog.DialogStep;

public class QuestTaskControlsUtils
{
	public static Control produceControl(Field field)
	{
		if(field.getName().equals("monsterIdentifier"))
			return new MonsterIdentifierComboBox();
		
		Class<?> fieldType = field.getType();
		if(fieldType.getName().equals("int") || 
				fieldType.getName().equals("long") ||
				fieldType == Integer.class ||
				fieldType == Long.class)
			return new IntegerTextField();
		
		if(fieldType == DialogStep.class)
			return new DialogMakerStarterButton();
		
		if(ClassUtils.isPrimitiveOrWrapper(fieldType) || fieldType == String.class)
			return new TextField();
		

		return new Label("Control for this field type is not implemented yet");
	}
	
	public static Object getControlInput(Control control)
	{
		if(control instanceof DialogMakerStarterButton)
			return ((DialogMakerStarterButton)control).getDialogData();
		if(control instanceof IntegerTextField)
			return ((IntegerTextField)control).getInputValue();
		if(control instanceof TextInputControl)
			return ((TextInputControl)control).getText();
		if(control instanceof Labeled)
			return ((Labeled)control).getText();
		if(control instanceof MonsterIdentifierComboBox)
			return ((MonsterIdentifierComboBox)control).getSelectionModel().getSelectedItem().getRealType().getName();
		
		throw new RuntimeException("Unrecognized control type");
	}
}
