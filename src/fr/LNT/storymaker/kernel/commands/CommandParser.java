package fr.LNT.storymaker.kernel.commands;

import java.util.HashMap;

/**
 * The parser of command
 * @author LNT
 *
 */
public class CommandParser {

	private HashMap<String, Command> name2commands = new HashMap<>();

	/**
	 * Add command to the parser
	 * @param name Name of command
	 * @param command Instance of command
	 */
	public void addCommand(String name, Command command) {
		name2commands.put(name.toLowerCase(), command);
	}

	/**
	 * Parse a command and run in the right instance
	 * @param sender Instance that have run command
	 * @param cmd The command
	 * @return Return if command is completed
	 */
	public boolean parse(Sender sender, String cmd) {
		String[] cmd_tab = cmd.split(" ");
		String[] args = new String[cmd_tab.length];
		for (int i = 1; i < cmd_tab.length; i++)
			args[i - 1] = cmd_tab[i];

		if (name2commands.containsKey(cmd_tab[0].toLowerCase())) {
			return name2commands.get(cmd_tab[0].toLowerCase()).execute(sender, cmd_tab[0], args);
		}
		return false;
	}

}
