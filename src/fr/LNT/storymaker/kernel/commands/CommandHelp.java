package fr.LNT.storymaker.kernel.commands;

/**
 * A command that can helped you
 * @author LNT
 *
 */
public class CommandHelp implements Command{

	
    /**
     * Print all player available commands scpecs
     */
    public void help()
    {
         System.out.println("GO <location> : Allows you to move to the arg location. A door to the arg location must exist so you can move. You can check by using 'LOOK Door' command.");
         System.out.println("INVENTORY : Allows you to check your inventory. Returns the list of all the items you own.");
         System.out.println("HELP : If you're here, you must know what it does, right ?");
         System.out.println("LOOK [\"Door\"|Item] : Allows you to take a look to the room and lists all the things you can find inside. By adding one or more arguments (name of item), you can take a closer look at said items. Use LOOK Door to get the door's list.");
         System.out.println("TAKE <Item> : Adds (if possible) the item to your inventory.");
         System.out.println("QUIT : Quits the game.");
         System.out.println("USE arg1 [arg2] : Uses the first arg (if possible). If there is a second argument, arg2 must be coupled with arg2, and the combination of the two lead to a new action.");
         System.out.println("TALK <Character> : Talk to a character.");
    }
    
    /**
	 * Run the command
	 * @param sender Instance of the sender
	 * @param cmdName Name use to call the command
	 * @param args Arguments of command
	 * @return True if the command was completed
	 */
    @Override
    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        help();
        return true;
    }
}
