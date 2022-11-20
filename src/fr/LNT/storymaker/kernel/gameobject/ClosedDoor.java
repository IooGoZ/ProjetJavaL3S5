package fr.LNT.storymaker.kernel.gameobject;

import java.util.List;

public class ClosedDoor extends Door{
	private Item key;
	private Boolean open;
	
	public ClosedDoor(Location from, Location to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean isOpen()
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
