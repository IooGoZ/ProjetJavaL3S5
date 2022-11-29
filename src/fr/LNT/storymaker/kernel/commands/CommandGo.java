package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;

public class CommandGo implements Command {

	private final Game game;

	public CommandGo(Game game) {
		this.game = game;

	}

	public boolean execute(Sender sender, String cmdName, String[] args) {
		Location location = this.game.getCurrentLocation();
		if (Command.argsLenght(args) > 0) {
			String name = Command.concatArgs(args);
			if (location.doesDoorExist(name)) {
				return this.game.tryToUseDoor(location.getDoor(name));
			}
		}
		System.out.println("Available exits : ");
		for (String name : location.getDoorsNames()) {
			System.out.println(name);
		}
		return false;
	}
}
