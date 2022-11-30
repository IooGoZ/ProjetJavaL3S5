package fr.LNT.storymaker.kernel.gameobject;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.commands.Sender;
import fr.LNT.storymaker.kernel.story.Location;

/**
 * Represent a door
 * @author LNT
 *
 */
public class Door implements Sender {
	private Location from;
	private Location to;
	private String command;

	/**
	 * Constructor of class Door
	 * @param from Location where the door is
	 * @param to Location where the door go
	 * @param execute The command that must be run when player interact with door 
	 */
	public Door(Location from, Location to, String execute) {
		this.from = from;
		this.to = to;
		this.command = execute;
	}

	/**
	 * Run a command from the door
	 * @param game instance of game
	 */
	public void executeCommand(Game game) {
		if (command != null)
			game.executeCommand(this, command);
	}
	
	/**
	 * Get location where the door go
	 * @return location where the door go
	 */
	public Location whereDoIGo() {
		return this.to;
	}

	/**
	 * Get location where the door is
	 * @return location where the door is
	 */
	public Location whereAmI() {
		return this.from;
	}
}
