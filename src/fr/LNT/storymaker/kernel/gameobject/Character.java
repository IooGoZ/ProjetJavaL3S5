package fr.LNT.storymaker.kernel.gameobject;

import java.util.HashMap;

import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.utils.Dialog;

public class Character {
	
	private final String name;
	private Location room; 
	private HashMap <Integer, Dialog> dialog;
	
	public Character(String n, Location r, HashMap<Integer, Dialog> d) 
	{
		this.name = n;
		this.room = r;
		this.dialog = d;
	}
	
	
	
}
