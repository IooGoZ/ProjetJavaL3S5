package fr.LNT.storymaker.kernel.gameobject;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.commands.Sender;

public class Item implements Sender {
	
	private final String id;
	private final String name;
	private final String desc;
	private final String execute;
	
	public Item(String id, String name, String desc, String execute) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.execute = execute;
	}

	public void use(Game game) {
		if (this.execute != null)
			game.executeCommand(this, this.execute);
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
	
}
