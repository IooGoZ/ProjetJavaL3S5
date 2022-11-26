package fr.LNT.storymaker.kernel.commands;

import java.util.HashMap;

public class CommandParser {

	private HashMap<String, Command> name2commands = new HashMap<>();

	public void addCommand(String name, Command command) {
		name2commands.put(name, command);
	}

	public boolean parse(Sender sender, String cmd) {
		String[] cmd_tab = cmd.split(" ");
		String[] args = new String[cmd_tab.length];
		for (int i = 1; i < cmd_tab.length; i++)
			args[i - 1] = cmd_tab[i];

		if (name2commands.containsKey(cmd_tab[0])) {
			return name2commands.get(cmd_tab[0]).execute(sender, cmd_tab[0], args);
		}
		return false;
	}

}
