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
	private final List<Item> item;
	private final List<Character> characters = new ArrayList<>();
	

	private boolean isVisited = false;
	
	public Location(String name, String text, String desc, List<Item> item ) {
		this.name = name;
		this.description = desc;
		this.text = text;
		this.item = item;
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

	public boolean hasItem() {
		return this.item.isEmpty();
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

	public List<Item> getItem() {
		return this.item;
	}

}