package fr.LNT.storymaker.kernel.commands;

/**
 * A command to stop the game
 * @author LNT
 *
 */
public class CommandQuit  implements Command{

	/**
	 * Run the command
	 * 
	 * @param sender  Instance of the sender
	 * @param cmdName Name use to call the command
	 * @param args    Arguments of command
	 * @return True if the command was completed
	 */
	@Override
    public boolean execute(Sender sender, String cmdName, String[] args) {
        System.exit(0);
		return true;
	}
    
}
