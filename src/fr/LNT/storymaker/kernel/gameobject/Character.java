package fr.LNT.storymaker.kernel.gameobject;

import java.util.HashMap;
import java.util.List;

import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.utils.Dialog;

/**
 * Represent a non-player character
 * @author LNT
 *
 */
public class Character {

	public static final String ALWAYS_AVAILABLE = "always";

	private final String name;
	private final String desc;
	private final Location room;
	private final HashMap<String, Dialog> script2dialog;
	private final List<String> available;

	/**
	 * Constructor of class Character
	 * @param name Name of character
	 * @param desc Description of character
	 * @param room Location where the character is
	 * @param available List of script nodes ids when the character is available
	 * @param dialog Dialog associated with script node id
	 */
	public Character(String name, String desc, Location room, List<String> available, HashMap<String, Dialog> dialog) {
		this.name = name;
		this.desc = desc;
		this.room = room;
		this.script2dialog = dialog;
		this.available = available;
		room.addCharacter(this);
	}

	/**
	 * Check if player is available for specific script node id
	 * @param script_id Script node id that must be verified
	 * @return True if available
	 */
	public boolean isAvailable(String script_id) {
		
		return available.contains(script_id) || available.size() == 0;
	}

	/**
	 * Get the name of character
	 * @return Name of character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get location of character
	 * @return location of character
	 */
	public Location getLocation() {
		return room;
	}

	
	/**
	 * Get the available dialog for specific value of script node id
	 * @param script_id Script node id that must be verified
	 * @return Dialog that is available or default
	 */
	public Dialog getCurrentDialog(String script_id) {
		Dialog res = script2dialog.getOrDefault(script_id, null);
		if (res == null)
			res = script2dialog.getOrDefault(ALWAYS_AVAILABLE, null);
		return res;
	}

	/**
	 * Get the description of character
	 * @return Description of character
	 */
	public String getDescription() {
		return desc;
	}
}
