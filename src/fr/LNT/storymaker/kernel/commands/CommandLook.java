package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;

/**
 * 
 * @author LNT
 *
 */
public class CommandLook implements Command {

	private Location location;
	private Game game;

	/**
	 * Constructor of class Command look
	 * @param game Game instance is necessary
	 */
	public CommandLook(Game game) {
		this.game = game;
	}

	
	/**
	 * Look around you
	 */
	public void lookAround() {
		this.location.printDescription(game.getCurrentScriptPosition());
	}

	/**
	 * Look specific object
	 * @param names Name of object that you want to look
	 * @return Return if command is completed
	 */
	public boolean lookClosely(String[] names) {
		for (String name : names) {
			if (name.equalsIgnoreCase("Door")) {
				this.location.printDoors();
				return true;
			} else {
				if (this.location.doesItemExist(name)) {
					this.location.getItemDescription(name);
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	
	/**
	 * Run the command
	 * @param sender Instance of the sender
	 * @param cmdName Name use to call the command
	 * @param args Arguments of command
	 * @return True if the command was completed
	 */
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		this.location = game.getCurrentLocation();
		if (Command.argsLenght(args) == 0) {
			lookAround();
			return true;
		} else
			return lookClosely(args);
	}

}
