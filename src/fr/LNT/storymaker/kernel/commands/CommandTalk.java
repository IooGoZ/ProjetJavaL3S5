package fr.LNT.storymaker.kernel.commands;

import java.util.List;

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
			List<Character> characters = location.getAvailableCharacters(game.getCurrentScriptPosition());
			for (Character character : characters)
				if (character.getName().equalsIgnoreCase(name)) {
					character.getCurrentDialog(game.getCurrentScriptPosition()).execute();
					return true;
				}
		}
		System.out.println("Available characters : ");
		List<Character> characters = location.getAvailableCharacters(game.getCurrentScriptPosition());
		for (Character character : characters)
			System.out.println(character.getName());
		
		return false;
	}

}
