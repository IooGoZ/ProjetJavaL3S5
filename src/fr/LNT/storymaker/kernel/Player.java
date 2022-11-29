package fr.LNT.storymaker.kernel;
import java.util.ArrayList;
import java.util.List;

import fr.LNT.storymaker.kernel.commands.Sender;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class Player implements Sender {

	private static final String DEFAULT_NAME = "Player";
	private static final int DEFAULT_HP = 100;
	
	private List<Item> inventory;
	private List<Character> followers;
	private int health;
	private final String name;
	
	
	public Player(List<Item> inventory, int health, String name){
		this.inventory = inventory;
		this.followers = new ArrayList<Character>();
		this.health = health;
		this.name = name;
	}
	
	public Player() {
		this(new ArrayList<>(), DEFAULT_HP, DEFAULT_NAME);
	}
	
	public void addInventory(Item item) {
		this.inventory.add(item);
	}
	
	public void removeIventory(Item item) {
		this.inventory.remove(item);
	}
	
	public void lostHealth(int damage) {
		this.health -= damage;
	}
	
	public void gainHealth (int point) {
		this.health += point;
	}
	
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
	
	public void death() {
		if (this.health == 0) {
			System.out.println("You are dead...");
		}
	}
	
	
	// ----- Getter ----- //
	public List<Item> getInventory(){
		return this.inventory;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Character> getFollowers(){
		return this.followers;
	}
	
}

