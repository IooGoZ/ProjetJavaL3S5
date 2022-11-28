package fr.LNT.storymaker.kernel.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.utils.Dialog;

public class Character {

	public static final String ALWAYS_AVAILABLE = "always";
	private static final int DEFAULT_HEALTH = 20;

	private final String name;
	private final String desc;
	private final Location room;
	private final HashMap<String, Dialog> script2dialog;
	private final List<Item> inventory = new ArrayList<>();
	private final List<String> available;

	private int health = DEFAULT_HEALTH; // damage during the fight with the main player

	public Character(String name, String desc, Location room, List<String> available, HashMap<String, Dialog> dialog) {
		this.name = name;
		this.desc = desc;
		this.room = room;
		this.script2dialog = dialog;
		this.available = available;
		room.addCharacter(this);
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

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return room;
	}

	public Dialog getCurrentDialog(String script_id) {
		return script2dialog.get(script_id);
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public int getHealth() {
		return health;
	}
}
