package fr.LNT.storymaker.kernel.gameobject;

import java.util.HashMap;
import java.util.List;

import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.utils.Dialog;

public class Character {

	private final String name;
	private Location room;
	private HashMap<Integer, Dialog> dialog;
	private List<Item> inventory;
	private int damage; // damage during the fight with the main player

	public Character(String n, Location r, HashMap<Integer, Dialog> d) {
		this.name = n;
		this.room = r;
		this.dialog = d;
	}

	public boolean isInside(Item item) {

		int len = this.inventory.size();
		boolean res = false;

		for (int i = 0; i < len; i++) {
			if (this.inventory.get(i) == item) {
				res = true;
			}
		}
		return res;
	}

	public void giveItem(Item item) {
		if (isInside(item))
			this.inventory.remove(item);
	}

}
