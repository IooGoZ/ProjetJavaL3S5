package fr.LNT.storymaker.kernel;
import java.util.ArrayList;
import java.util.List;

import fr.LNT.storymaker.kernel.commands.Sender;
import fr.LNT.storymaker.kernel.gameobject.Item;

/**
 * @author LNT
 *
 */
public class Player implements Sender {

	private static final String DEFAULT_NAME = "Player";
	
	private List<Item> inventory;
	private List<Character> followers;
	private final String name;
	
	
	/**
	 * Constructor of player class
	 * @param inventory Default inventory of player
	 * @param name Name of player
	 */
	public Player(List<Item> inventory, String name){
		this.inventory = inventory;
		this.followers = new ArrayList<Character>();
		this.name = name;
	}
	
	/**
	 * Constructor of player class.
	 * Use defaults parameters
	 */
	public Player() {
		this(new ArrayList<>(), DEFAULT_NAME);
	}
	
	/**
	 * Add an item to player's inventory
	 * @param item Item that must be added
	 */
	public void addInventory(Item item) {
		this.inventory.add(item);
	}
	
	/**
	 * Remove item from player's inventory
	 * @param item Item that must be removed
	 */
	public void removeIventory(Item item) {
		this.inventory.remove(item);
	}

	/**
	 * Get the player inventory
	 * @return player inventory
	 */
	public List<Item> getInventory(){
		return this.inventory;
	}
	
	/**
	 * Get the name of player
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the list of characters that follow the player
	 * @return list can be empty
	 */
	public List<Character> getFollowers(){
		return this.followers;
	}
	
}

