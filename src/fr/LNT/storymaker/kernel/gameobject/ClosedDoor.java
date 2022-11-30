package fr.LNT.storymaker.kernel.gameobject;

import java.util.List;

import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.story.Location;

/**
 * Represent a close door
 * @author LNT
 *
 */
public class ClosedDoor extends Door {
	private final Item key;
	private boolean open = false;

	/**
	 * Constructor of class ClosedDoor
	 * @param from Location where the door is
	 * @param to Location where the door go
	 * @param execute The command that must be run when player interact with door
	 * @param key The item that must be used like a key 
	 */
	public ClosedDoor(Location from, Location to, String execute, Item key) {
		super(from, to, execute);
		this.key = key;
	}

	/**
	 * Check if door is opened
	 * @return true if it is opened
	 */
	public boolean isOpen() {
		return this.open;
	}

	/**
	 * Try to open the door
	 * @param player Player that try to use door
	 * @return true if the door is opened
	 */
	public boolean openSesame(Player player) {
		List<Item> inventory = player.getInventory();
		if (inventory.contains(this.key)) {
			this.open = true;
			return true;
		}
		return false;
	}
}
