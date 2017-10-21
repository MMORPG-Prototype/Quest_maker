package pl.mmorpg.prototype.quest.maker.reflection;

public class HumanReadableClass<T>
{
	private final Class<T> type;

	public HumanReadableClass(Class<T> type)
	{
		this.type = type;		
	}
	
	public Class<T> getRealType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return type.getSimpleName();
	}
}
