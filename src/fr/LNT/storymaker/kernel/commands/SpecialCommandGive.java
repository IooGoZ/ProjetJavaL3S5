package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class SpecialCommandGive implements Command {

	private final Game game;

	public SpecialCommandGive(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		if (!(sender instanceof Player)) {
			if (Command.argsLenght(args) > 0) {
				Item item = game.getItemById(args[0]);
				if (item != null)
					this.game.getCurrentPlayer().addInventory(item);
			}
			System.err.println("Error : Special command give cannot be used, because arguments are invalid.");
			System.exit(-1);
		}
		return false;
	}

}
