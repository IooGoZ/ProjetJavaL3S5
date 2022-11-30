package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.gameobject.Item;

/**
 * A special command to give an item
 * @author LNT
 *
 */
public class SpecialCommandGive implements Command {

	private final Game game;

	/**
	 * Constructor of class SpecialCommandGive
	 * @param game Game instance is necessary
	 */
	public SpecialCommandGive(Game game) {
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
				Item item = game.getItemById(args[0]);
				if (item != null)
					this.game.getPlayer().addInventory(item);
			}
			System.err.println("Error : Special command give cannot be used, because arguments are invalid.");
			System.exit(-1);
		}
		return false;
	}

}
