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
	
	private List<Item> item; // NULL ?-------------------------------------------
	

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
		return this.item.isEmpty();
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

	public List<Item> getItem() {
		return this.item;
	}

}