package fr.LNT.storymaker.kernel.gameobject;
import java.util.List;

public class Player {

	private Location position;
	private List<Item> inventory;
	private List<Character> followers;
	private int health;
	private String name;
	
	public List<Item> getInventory()
	{
		return this.inventory;
	}
	
	public void addInventory(Item item) {
		this.inventory.add(item);
	}
	
	public void imDying(int damage) {
		this.health -= damage;
	}
	
	public void move(Door door) {
		if (door.whereAmI() == this.position) {
			this.position = door.whereDoIGo();
		}
	}
	
}
