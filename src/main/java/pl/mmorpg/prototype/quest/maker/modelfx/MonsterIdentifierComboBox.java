package pl.mmorpg.prototype.quest.maker.modelfx;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import pl.mmorpg.prototype.quest.maker.reflection.HumanReadableClass;
import pl.mmorpg.prototype.server.objects.PlayerCharacter;
import pl.mmorpg.prototype.server.objects.monsters.Monster;
import pl.mmorpg.prototype.server.objects.monsters.npcs.Npc;

public class MonsterIdentifierComboBox extends ComboBox<HumanReadableClass<? extends Monster>>
{
	public MonsterIdentifierComboBox()
	{
		ObservableList<HumanReadableClass<? extends Monster>> monsterTypes = getSuiteMonsterTypes();
		setItems(monsterTypes);
		getSelectionModel().select(0);
	}

	private ObservableList<HumanReadableClass<? extends Monster>> getSuiteMonsterTypes()
	{
		Reflections reflections = new Reflections("pl.mmorpg.prototype.server.objects.monsters");
		Set<Class<? extends Monster>> monsterSubTypes = reflections.getSubTypesOf(Monster.class);
		monsterSubTypes.removeIf(type -> Modifier.isAbstract(type.getModifiers()) 
				|| Npc.class.isAssignableFrom(type)
				|| PlayerCharacter.class.isAssignableFrom(type));
		@SuppressWarnings("unchecked")
		List<HumanReadableClass<? extends Monster>> humanReadableMonsterTypes = monsterSubTypes.stream()
				.map(HumanReadableClass::new)
				.collect(Collectors.toList());
		ObservableList<HumanReadableClass<? extends Monster>> monsterTypes = FXCollections.observableArrayList(humanReadableMonsterTypes);
		return monsterTypes;
	}
}
