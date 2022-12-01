package fr.LNT.storymaker.kernel.commands;

import java.util.List;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.gameobject.Item;

/**
 * A command to use an item
 * @author LNT
 *
 */
public class CommandUse implements Command {

	private final Game game;
	
	/**
	 * Constructor of class CommandUse
	 * @param game Instance of game
	 */
	public CommandUse(Game game) {
		this.game = game;
	}
	
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
    	List<Item> inventory = game.getPlayer().getInventory();
    	String name = Command.concatArgs(args);
    	for (Item item : inventory) {
    		if (name.equalsIgnoreCase(item.getName())) {
    			item.use(game);
    			return true;
    		}
    	}
		return false;
	}
    
}
 