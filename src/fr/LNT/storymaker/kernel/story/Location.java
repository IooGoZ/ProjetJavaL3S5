package fr.LNT.storymaker.kernel.story;
import java.util.List;

import fr.LNT.storymaker.kernel.gameobject.Character;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class Location {

	private final String name;
	private final String description;
	private List<Door> exits;
	private List<Character> characters;
	private List<Item> item;

	public Location(String n, String d, List<Door> door, List<Character> c) 
	{
		this.name = n;
		this.description = d;
		this.exits = door;
		this.characters = c;
	}
	
	// ---- Method() ---- //
	@Override
	public String toString ()
	{
		String res = "";
		res += "Room's name : ";
		res += this.name;
		res += "\n";
		res += "Room's description";
		res += this.description;

		return res;
		
	}
	
	public void addCharacter(Character c)
	{
		this.characters.add(c);
	}
	
	public void removeCharacter (Character c)
	{
		this.characters.remove(c);
	}
	
	public boolean hasItem ()
	{
		return this.item.isEmpty();
	}
	
	//---- Getter ---- //
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription() 
	{
		return this.description;
	}
	
	public List<Door> getExits()
	{
		return this.exits;
	}
	
	public List<Character> getCharacters()
	{
		return this.characters;
	}
	
	public List<Item> getItem()
	{
		return this.item;
	}
	
	
	
}