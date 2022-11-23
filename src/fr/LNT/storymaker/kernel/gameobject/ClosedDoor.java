package fr.LNT.storymaker.kernel.gameobject;

import java.util.List;

import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.story.Location;

public class ClosedDoor extends Door{
	private final Item key;
	private boolean open = false;
	
	public ClosedDoor(Location from, Location to, Item key) {
		super(from, to);
		this.key = key;
	}
	
	@Override
	public boolean isOpen()
	{
		return this.open;
	}
	
	public void openSesame(Player player)
	{
		List<Item> inventory = player.getInventory();
		if (inventory.contains(this.key)) {
			this.open = true;
		}
	}
}
