package pl.mmorpg.prototype.quest.maker.model;

import lombok.Data;
import pl.mmorpg.prototype.server.objects.items.Item;
import pl.mmorpg.prototype.server.objects.items.ItemIdentifier;

@Data
public class GameItem
{
	private final Class<? extends Item> itemType;
	private final Integer howMany;

	public GameItem(Class<? extends Item> itemType)
	{
		this(itemType, 0);
	}
	
	public GameItem(Class<? extends Item> itemType, Integer howMany)
	{
		this.itemType = itemType;
		this.howMany = howMany;
	}
	
	@Override
	public String toString()
	{
		boolean shouldAddNumber = howMany > 0;
		String name = itemType.getSimpleName();
		if(shouldAddNumber)
			name += ": " + howMany;
		return name;
	}
	
	public boolean isBaseType()
	{
		return itemType == Item.class;
	}
	
	public ItemInfo generateItemInfo()
	{
		return new ItemInfo(ItemIdentifier.getIdentifier(itemType), howMany);
	}
}
