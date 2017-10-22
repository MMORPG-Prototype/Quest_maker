package pl.mmorpg.prototype.quest.maker.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dictionary
{
	private final Map<String, String> dictionary;
	
	public Dictionary()
	{
		this.dictionary = Collections.unmodifiableMap(makeDictionary());
	}

	private Map<String, String> makeDictionary()
	{
		Map<String, String> dictionary = new HashMap<>();
		dictionary.put("questName", "Quest name");
		dictionary.put("monsterIdentifier", "Monster type");
		dictionary.put("npcName", "Npc name");
		dictionary.put("dialogEntryPoint", "Dialog...");
		dictionary.put("totalMonstersToKill", "How many to kill");
		return dictionary;
	}
	
	public String translate(String text)
	{
		return dictionary.containsKey(text) ? dictionary.get(text) : text;
	}
}
