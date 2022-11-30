package fr.LNT.storymaker.kernel.gameobject;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.commands.Sender;

/**
 * Represent an item
 * @author LNT
 *
 */
public class Item implements Sender {
	
	private final String id;
	private final String name;
	private final String desc;
	private final String execute;
	
	/**
	 * Constructor of class Item
	 * @param id Id of the item
	 * @param name Name of the item
	 * @param desc Description of the item
	 * @param execute Command that must be run when the item is use
	 */
	public Item(String id, String name, String desc, String execute) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.execute = execute;
	}

	/**
	 * Run command to use it
	 * @param game game instance
	 */
	public void use(Game game) {
		if (this.execute != null)
			game.executeCommand(this, this.execute);
	}
	
	/**
	 * Get the id of item
	 * @return id of item
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the name of item
	 * @return name of item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the description of item
	 * @return description of item
	 */
	public String getDesc() {
		return desc;
	}
	
}
