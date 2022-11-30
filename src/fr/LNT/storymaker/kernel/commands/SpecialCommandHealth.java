package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;

public class SpecialCommandHealth implements Command {

	private final Game game;

	public SpecialCommandHealth(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		if (!(sender instanceof Player)) {
			if (Command.argsLenght(args) > 0) {
				try {
					int value = Integer.parseInt(args[0]);
					if (cmdName.equalsIgnoreCase("health-set")) {
						game.getCurrentPlayer().setHealth(value);
					} else if (cmdName.equalsIgnoreCase("health-add")) {
						game.getCurrentPlayer().addHealth(value);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			System.err.println("Error : Special command health cannot be used, because arguments are invalid.");
			System.exit(-1);
		}
		return false;
	}

}
