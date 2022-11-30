package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.gameobject.Character;

public class CommandTalk implements Command {

	
	private final Game game;
	
	public CommandTalk(Game game) {
		this.game = game;
	}
	
	
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		Location location = game.getCurrentLocation();
		if (Command.argsLenght(args) > 0) {
			String name = Command.concatArgs(args);
			Character character = location.getAvailableCharacter(game.getCurrentScriptPosition(), name);
				if (character != null) {
					character.getCurrentDialog(game.getCurrentScriptPosition()).execute();
					return true;
				}
		}
		System.out.println("This characters is not available.");
		return false;
	}

}
