package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Player;

/**
 * A special command to print a text
 * @author LNT
 *
 */
public class SpecialCommandPrint implements Command {

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
			String text = Command.concatArgs(args);
			System.out.println(text);
			return true;
		}
		return false;
	}

}
