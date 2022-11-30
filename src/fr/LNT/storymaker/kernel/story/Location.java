package fr.LNT.storymaker.kernel.story;

import java.util.ArrayList;
import java.util.List;

import fr.LNT.storymaker.kernel.gameobject.Character;
import fr.LNT.storymaker.kernel.gameobject.ClosedDoor;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;

/**
 * Represent a location
 * @author LNT
 *
 */
public class Location {

	private final String name;
	private final String description;
	private final List<Door> exits = new ArrayList<>();
	private final String text;
	private final List<Item> items;
	private final List<Character> characters = new ArrayList<>();
	

	private boolean isVisited = false;
	
	/**
	 * Constructor of class location
	 * @param name Name of location
	 * @param text Text that must be displayed when the player visit location for the first time
	 * @param desc Description of location
	 * @param items Items available in the location
	 */
	public Location(String name, String text, String desc, List<Item> items) {
		this.name = name;
		this.description = desc;
		this.text = text;
		this.items = items;
	}

	
	/**
	 * Print the description of location
	 * @param sc_node Script node id 
	 */
	public void printDescription(String sc_node) {
		String res = "";
		res += "Location's name : ";
		res += this.name;
		res += "\n";
		res += "Location's description : ";
		res += this.description;
		res += "\n";
		res += "Location's character :";
		for (Character character : characters)
			if (character.isAvailable(sc_node))
				res += " " + character.getName();
		
		System.out.println(res);
	}
	
	/**
	 * Print the text when player visit the location for the first time
	 */
	public void printTextIfAvailable() {
		if (!isVisited && text != null) {
			System.out.println(text);
			isVisited = true;
		}
	}

	/**
	 * Check if location contains items
	 * @return true if it contains items
	 */
	public boolean hasItem() {
		return !this.items.isEmpty();
	}

	/**
	 * Get the names of all doors
	 * @return
	 */
	public List<String> getDoorsNames()
	{
		List<String> res = new ArrayList<>();
		for (Door door : exits) {
			res.add(door.whereDoIGo().getName());
		}
		return res;
	}
	
	
	/**
	 * Check if a door exists
	 * @param direction direction that must be check
	 * @return true if door exists
	 */
	public boolean doesDoorExist(String direction)
	{
		for (int i = 0; i < this.exits.size(); i++) {
		  if (direction.equalsIgnoreCase(this.exits.get(i).whereDoIGo().getName()))
		    return true;
		}
		return false;
	}

	/**
	 * Get door with the name of direction
	 * @param direction Name of direction
	 * @return Door correspond to the direction
	 */
	public Door getDoor(String direction)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
		  if (direction.equalsIgnoreCase(this.exits.get(i).whereDoIGo().getName()))
		    break;
		}
		return this.exits.get(i);
	}

	
	/**
	 * Get the names of all items available in the location
	 * @return List of items names available
	 */
	public List<String> getItemsNames()
	{
		List<String> res = new ArrayList<>();
		for (Item item : this.items) {
			res.add(item.getName());
		}
		return res;
	}
	
	/**
	 * Check if an item item exists in the location
	 * @param name Name of item
	 * @return true if item is available 
	 */
	public boolean doesItemExist(String name)
	{
		int i;
		for (i = 0; i < this.items.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				return true;
		}
		return false;
	}

	/**
	 * Get the description of item identified by his name
	 * @param name name of item
	 * @return Description of item
	 */
	public String getItemDescription(String name)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				break;
		}
		return this.items.get(i).getDesc();
	}

	/**
	 * Get an item with his name
	 * @param name name of item
	 * @return Item identified by the name
	 */
	public Item getItem(String name)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				break;
		}
		return this.items.get(i);
	}

	/**
	 * Delete an item from the location
	 * @param item Item must be deleted
	 */
	public void deleteItem(Item item)
	{
		this.items.remove(item);
	}

	
	/**
	 * Print the doors of the location
	 */
	public void printDoors()
	{
		this.exits.forEach(exit -> System.out.println("From : " + exit.whereAmI().getName() + "\nTo : " + exit.whereDoIGo().getName() + "\n"));
	}
	
	/**
	 * Add an exit to the location
	 * @param to exit direction
	 * @param execute command that must be run when player interact with door
	 * @param key Key use for door, can be null
	 */
	public void addExit(Location to, String execute, Item key) {
		Door door;
		if (key == null) {
			door = new Door(this, to, execute);
		} else {
			door = new ClosedDoor(this, to, execute, key);
		}
		exits.add(door);
	}
	
	/**
	 * Add character to the location
	 * @param character Character that must be added
	 */
	public void addCharacter(Character character) {
		characters.add(character);
	}
	
	/**
	 * Get the name of location
	 * @return name of location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the description of location
	 * @return description of location
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get all exits of location
	 * @return exits of location
	 */
	public List<Door> getExits() {
		return this.exits;
	}

	/**
	 * Get a character that must be available
	 * @param script_id Script node id
	 * @param name Name of character
	 * @return Character that is identifiedS
	 */
	public Character getAvailableCharacter(String script_id, String name) {
		for (Character character : characters)
			if (character.isAvailable(script_id) && character.getName().equalsIgnoreCase(name))
				return character;
		return null;
	}

	/**
	 * Get all items available in the location
	 * @return items available in the location
	 */
	public List<Item> getItems() {
		return this.items;
	}

}