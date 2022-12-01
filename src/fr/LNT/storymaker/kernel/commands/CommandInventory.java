package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.gameobject.Item;

import java.util.List;

public class CommandInventory implements Command{

    private List<Item> inventory;

    public CommandInventory(Game game)
    {
        this.inventory = game.getCurrentPlayer().getInventory();

    }
    
    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        for (Item item : inventory) {
			System.out.println(item.getName());
		}
        return true;
    }
}