package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;

/**
 * A special command to give an item
 * @author LNT
 *
 */
public class SpecialCommandEnd implements Command {

	private final Game game;

	/**
	 * Constructor of class SpecialCommandEnd
	 * @param game Game instance is necessary
	 */
	public SpecialCommandEnd(Game game) {
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
		if (!(sender instanceof Player) && Command.argsLenght(args) > 0) {
			game.finish();
			String text = Command.concatArgs(args);
			System.out.println(text);
			return true;
		}
		return false;
	}

}
