package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.gameobject.Item;

import java.util.List;

/**
 * A command to see your inventory
 * @author LNT
 *
 */
public class CommandInventory implements Command{

    private final Game game;

    /**
     * Constructor of class CommandInventory
     * @param game Instance of game
     */
    public CommandInventory(Game game)
    {
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
    public boolean execute(Sender sender, String cmdName, String[] args)
    {
    	List<Item> inventory = game.getPlayer().getInventory();
    	System.out.println("Your inventory contains :");
        for (Item item : inventory) {
			System.out.println(" -" + item.getName());
		}
        return true;
    }
}