package pl.mmorpg.prototype.quest.maker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PropertyContainer
{
	private final Map<String, Object> properties;

	public PropertyContainer(Map<String, Object> properties)
	{
		this.properties = properties;
	}

	public Map<String, Object> getProperties()
	{
		return properties;
	}
	
	public void nest(String key, PropertyContainer propertyContainer)
	{
		Collection<Map<String, Object>> nestedProperties = getNestedPropertiesCollection(key);
		nestedProperties.add(propertyContainer.getProperties());
	}

	private Collection<Map<String, Object>> getNestedPropertiesCollection(String key)
	{
		if(!properties.containsKey(key))
		{
			Collection<Map<String, Object>> nestedProperties = new ArrayList<>();
			properties.put(key, nestedProperties);
			return nestedProperties;
		}
		return (Collection<Map<String, Object>>)properties.get(key);
	}
}
