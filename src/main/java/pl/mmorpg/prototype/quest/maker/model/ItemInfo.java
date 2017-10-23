package pl.mmorpg.prototype.quest.maker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mmorpg.prototype.clientservercommon.ItemIdentifiers;

@Data
@AllArgsConstructor
public class ItemInfo
{
	private ItemIdentifiers itemIdentifier;
	private Integer numberOfItems;
}
