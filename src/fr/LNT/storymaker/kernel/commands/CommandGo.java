package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;

/**
 * Instance of command to change current location
 * @author LNT
 *
 */
public class CommandGo implements Command {

	private final Game game;

	/**
	 * Constructor of class CommandGo
	 * @param game Game instance is necessary
	 */
	public CommandGo(Game game) {
		this.game = game;

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
		Location location = this.game.getCurrentLocation();
		if (Command.argsLenght(args) > 0) {
			String name = Command.concatArgs(args);
			if (location.doesDoorExist(name)) {
				return this.game.tryToUseDoor(location.getDoor(name));
			}
		}
		System.out.println("Available exits : ");
		for (String name : location.getDoorsNames()) {
			System.out.println(name);
		}
		return false;
	}
}
