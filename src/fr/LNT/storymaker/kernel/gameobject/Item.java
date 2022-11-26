package fr.LNT.storymaker.kernel.gameobject;

public abstract class Item {
	
	private final String id;
	private final String name;
	private final String desc;
	private final ItemType type;
	
	public Item(String id, String name, String desc, ItemType type) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public ItemType getType() {
		return type;
	}
	
}
