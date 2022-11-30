package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.gameobject.Character;


/**
 * A command to talk to a character
 * @author LNT
 *
 */
public class CommandTalk implements Command {

	
	private final Game game;
	
	/**
	 * Constructor of class CommandTalk
	 * @param game Game instance is necessary
	 */
	public CommandTalk(Game game) {
		this.game = game;
	}
	
	
	
	/**
	 * Run the command
	 * @param sender Instance of the sender
	 * @param cmdName Name use to call the command
	 * @param args Arguments of command
	 * @return True if the command was completed
	 */
	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		Location location = game.getCurrentLocation();
		if (Command.argsLenght(args) > 0) {
			String name = Command.concatArgs(args);
			Character character = location.getAvailableCharacter(game.getCurrentScriptPosition(), name);
				if (character != null) {
					character.getCurrentDialog(game.getCurrentScriptPosition()).execute(game);
					return true;
				}
		}
		System.out.println("This characters is not available.");
		return false;
	}

}
