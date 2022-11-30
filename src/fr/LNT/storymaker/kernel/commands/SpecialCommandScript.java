package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;

public class SpecialCommandScript implements Command {

	
	private final Game game;

	public SpecialCommandScript(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		if (!(sender instanceof Player)) {
			if (Command.argsLenght(args) > 0) {
				String id = args[0];
				if (game.tryToMoveInScript(id))
					return true;
			}
			System.err.println("Error : Special command script cannot be used, because arguments are invalid.");
			System.exit(-1);
		}
		return false;
	}
	
}
