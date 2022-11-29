package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class CommandTake implements Command {

	private Player player;
	private Game game;

	public CommandTake(Game game) {
		this.player = game.getCurrentPlayer();
		this.game = game;

	}

	public boolean execute(Sender sender, String cmdName, String[] args) {
		Location location = game.getCurrentLocation();
		if (Command.argsLenght(args) > 0) {
			String name = Command.concatArgs(args);
			if (location.doesItemExist(name)) {
				Item item = location.getItem(name);
				this.player.addInventory(item);
				location.deleteItem(item);
				return true;
			}
		}
		System.out.println("Available items : ");
		for (String name : location.getItemsNames()) {
			System.out.println(name);
		}
		return false;
	}

}
