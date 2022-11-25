package fr.LNT.storymaker.kernel.commands;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.utils.Dialog;

public class CommandTest implements Command {
	
	private Game game;
	
	public CommandTest(Game game) {
		this.game = game;
	}

	@Override
	public boolean execute(Sender sender, String cmdName, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			return true;//S'il n'y a pas d'erreur dans l'execution de la commande
		}
		if (sender instanceof Dialog) {
			Dialog dialog = (Dialog) sender;
			
			return false;
		}
		return false;//Erreur dans la commande
	}

}
