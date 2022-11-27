package fr.LNT.storymaker.kernel.story;

import java.util.List;

import fr.LNT.storymaker.kernel.gameobject.Character;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class Location {

	private final String name;
	private final String description;
	private final List<Door> exits;
	private final List<Character> characters;
	private final String text;
	
	private List<Item> items; // NULL ?------------------------------------------- non
	

	private boolean isVisited = false;
	
	public Location(String name, String desc, List<Door> door, List<Character> characters, String text) {
		this.name = name;
		this.description = desc;
		this.exits = door;
		this.characters = characters;
		this.text = text;
	}

	// ---- Method() ---- //
	@Override
	public String toString() {
		String res = "";
		res += "Location's name : ";
		res += this.name;
		res += "\n";
		res += "Location's description :";
		res += this.description;

		return res;

	}
	
	public void printTextIfAvailable() {
		if (isVisited && text != null) {
			System.out.println(text);
		}
	}
	
	public void addCharacter(Character c) {
		this.characters.add(c);
	}

	public void removeCharacter(Character c) {
		this.characters.remove(c);
	}

	public boolean hasItem() {
		return this.items.isEmpty();
	}


	// --- A améliorer sans doute --- //
	public boolean doesDoorExist(String direction)
	{
		int i = 0;
		boolean b = false;
		while (b == false && i < this.exits.size())
		{
			b = (direction == this.exits.get(i).whereDoIGo().getName());
			i++;
		}
		return b;
	}

	public Door getDoor(String direction)
	{
			int i = 0;
			boolean b = false;
			while (b == false && i < this.exits.size())
			{
				b = (direction == this.exits.get(i).whereDoIGo().getName());
				i++;
			}
			return this.exits.get(i);
	}

	public boolean doesItemExist(String name)
	{
		int i = 0;
		boolean b = false;
		while (b == false && i < this.items.size())
		{
			b = (name == this.items.get(i).getName());
			i++;
		}
		return b;
	}

	public String getItemDescription(String direction)
	{
		int i = 0;
		boolean b = false;
		while (b == false && i < this.exits.size())
		{
			b = (direction == this.items.get(i).getName());
			i++;
		}
		return this.items.get(i).getDesc();
	}

	public Item getItem(String direction)
	{
		int i = 0;
		boolean b = false;
		while (b == false && i < this.exits.size())
		{
			b = (direction == this.items.get(i).getName());
			i++;
		}
		return this.items.get(i);
	}

	public void deleteItem(Item item)
	{
		this.items.remove(item);
	}

	public void printDoors()
	{
		this.exits.forEach(exit -> System.out.println("From : " + exit.whereAmI() + " to : " + exit.whereDoIGo()));
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

	public List<Character> getCharacters() {
		return this.characters;
	}

	public List<Item> getItems() {
		return this.items;
	}

}