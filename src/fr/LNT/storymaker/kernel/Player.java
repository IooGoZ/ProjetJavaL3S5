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
	private static final int DEFAULT_HP = 100;
	
	private List<Item> inventory;
	private List<Character> followers;
	private int health;
	private final String name;
	
	
	/**
	 * Constructor of player class
	 * @param inventory Default inventory of player
	 * @param health Default health of player
	 * @param name Name of player
	 */
	public Player(List<Item> inventory, int health, String name){
		this.inventory = inventory;
		this.followers = new ArrayList<Character>();
		this.health = health;
		this.name = name;
	}
	
	/**
	 * Constructor of player class.
	 * Use defaults parameters
	 */
	public Player() {
		this(new ArrayList<>(), DEFAULT_HP, DEFAULT_NAME);
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
	 * Add health points value to player
	 * @param health_point Health can be negative
	 */
	public void addHealth(int health_point) {
		this.health += health_point;
	}
	
	
	/**
	 * Set health point value of player
	 * @param point
	 */
	public void setHealth (int point) {
		this.health = point;
	}
	
	/**
	 * Print the state of player based on his health point
	 */
	public void printStatePlayer() {
		if ((this.health <= 100) && (this.health >= 80)) {
			System.out.println("You are the best ! ");
		} 
		else if ((this.health < 80) && (this.health >= 60 )) {
			System.out.println("You are doing great !");
		}
		else if (this.health == 50) {
			System.out.println("You are not fine but not sad either");
		}
		else if ((this.health < 50) && (this.health >= 30)) {
			System.out.println("You are trying your best, keep going");
		}
		else if ((this.health < 30) && (this.health < 10)) {
			System.out.println("You are in depression");
		}
		else System.out.println("You are having a mental breakdown");
	}
	
	
	/**
	 * Check if player is died and print message if it's true
	 * @return true if player id died
	 */
	public boolean death() {
		if (this.health == 0) {
			System.out.println("You are dead...");
			return true;
		}
		return false;
	}
	
	/**
	 * Get the player inventory
	 * @return player inventory
	 */
	public List<Item> getInventory(){
		return this.inventory;
	}
	
	/**
	 * Get health points of player
	 * @return
	 */
	public int getHealth() {
		return this.health;
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

