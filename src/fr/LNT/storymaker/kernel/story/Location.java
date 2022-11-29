package fr.LNT.storymaker.kernel.story;

import java.util.ArrayList;
import java.util.List;

import fr.LNT.storymaker.kernel.gameobject.Character;
import fr.LNT.storymaker.kernel.gameobject.ClosedDoor;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class Location {

	private final String name;
	private final String description;
	private final List<Door> exits = new ArrayList<>();
	private final String text;
	private final List<Item> items;
	private final List<Character> characters = new ArrayList<>();
	

	private boolean isVisited = false;
	
	public Location(String name, String text, String desc, List<Item> items) {
		this.name = name;
		this.description = desc;
		this.text = text;
		this.items = items;
	}

	// ---- Method() ---- //
	@Override
	public String toString() {
		String res = "";
		res += "Location's name : ";
		res += this.name;
		res += "\n";
		res += "Location's description : ";
		res += this.description;

		return res;

	}
	
	public void printTextIfAvailable() {
		if (!isVisited && text != null) {
			System.out.println(text);
			isVisited = true;
		}
	}

	public boolean hasItem() {
		return this.items.isEmpty();
	}

	public List<String> getDoorsNames()
	{
		List<String> res = new ArrayList<>();
		for (Door door : exits) {
			res.add(door.whereDoIGo().getName());
		}
		return res;
	}
	
	public boolean doesDoorExist(String direction)
	{
		for (int i = 0; i < this.exits.size(); i++) {
		  if (direction.equalsIgnoreCase(this.exits.get(i).whereDoIGo().getName()))
		    return true;
		}
		return false;
	}

	public Door getDoor(String direction)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
		  if (direction.equalsIgnoreCase(this.exits.get(i).whereDoIGo().getName()))
		    break;
		}
		return this.exits.get(i);
	}

	
	public List<String> getItemsNames()
	{
		List<String> res = new ArrayList<>();
		for (Item item : this.items) {
			res.add(item.getName());
		}
		return res;
	}
	
	public boolean doesItemExist(String name)
	{
		int i;
		for (i = 0; i < this.items.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				return true;
		}
		return false;
	}

	public String getItemDescription(String direction)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				break;
		}
		return this.items.get(i).getDesc();
	}

	public Item getItem(String direction)
	{
		int i;
		for (i = 0; i < this.exits.size(); i++) {
			if (name.equalsIgnoreCase(this.items.get(i).getName()))
				break;
		}
		return this.items.get(i);
	}

	public void deleteItem(Item item)
	{
		this.items.remove(item);
	}

	public void printDoors()
	{
		this.exits.forEach(exit -> System.out.println("From : " + exit.whereAmI() + "\nTo : " + exit.whereDoIGo() + "\n"));
	}
	
	public void addExit(Location to, Item key) {
		Door door;
		if (key == null) {
			door = new Door(this, to);
		} else {
			door = new ClosedDoor(this, to, key);
		}
		exits.add(door);
	}
	
	public void addCharacter(Character character) {
		characters.add(character);
	}

	// ---- Getter ---- //
	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public List<Door> getExits() {
		return this.exits;
	}

	public List<Character> getAvailableCharacters(String script_id) {
		List<Character> res = new ArrayList<>();
		for (Character character : characters)
			if (character.isAvailable(script_id))
				res.add(character);
		return this.characters;
	}

	public List<Item> getItems() {
		return this.items;
	}

}