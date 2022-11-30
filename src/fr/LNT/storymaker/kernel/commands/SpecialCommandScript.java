package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;


/**
 * Special command to move in the script
 * @author LNT
 *
 */
public class SpecialCommandScript implements Command {

	
	private final Game game;

	/**
	 * Constructor of class SpecialCommandScript
	 * @param game Game instance is necessary
	 */
	public SpecialCommandScript(Game game) {
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
		if (!(sender instanceof Player)) {
			if (Command.argsLenght(args) > 0) {
				String id = args[0];
				if (game.tryToMoveInScript(id))
					return true;
			}
			return false;
		}
		System.err.println("Args : " + Command.concatArgs(args));
		System.err.println("Error : Special command script cannot be used, because arguments are invalid.");
		System.exit(-1);
		return false;
	}
	
}
