package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;

/**
 * A special command to manage the player health
 * @author LNT
 *
 */
public class SpecialCommandHealth implements Command {

	private final Game game;

	/**
	 * Constructor of class SpecialCommandHealth
	 * @param game Game instance is necessary
	 */
	public SpecialCommandHealth(Game game) {
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
				try {
					int value = Integer.parseInt(args[0]);
					if (cmdName.equalsIgnoreCase("health-set")) {
						game.getPlayer().setHealth(value);
					} else if (cmdName.equalsIgnoreCase("health-add")) {
						game.getPlayer().addHealth(value);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			System.err.println("Error : Special command health cannot be used, because arguments are invalid.");
			System.exit(-1);
		}
		return false;
	}

}
