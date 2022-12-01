package fr.LNT.storymaker.kernel.commands;

import java.util.HashMap;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;

public class SpecialCommandDev implements Command, Sender {

	private static final int DEFAULT_VALUE = 0;
	
	private HashMap<String, Integer> id2value = new HashMap<>();
	private Game game;
	
	public SpecialCommandDev(Game game) {
		this.game = game;
	}
	
	private void setValue(String id, int val) {
		if (id2value.containsKey(id)) {
			id2value.replace(id, val);
		} else id2value.put(id, val);
	}
	
	private void addValue(String id, int val) {
		if (id2value.containsKey(id)) {
			int curr_val = id2value.get(id);
			id2value.replace(id, curr_val+val);
		} else id2value.put(id, DEFAULT_VALUE + val);
	}
	
	private boolean ifCondition(String id_value, String condition, int val) throws Exception {
		int curr_val = id2value.getOrDefault(id_value, DEFAULT_VALUE);
		switch (condition) {
		case "=" : return curr_val == val;
		case "<=" : return curr_val <= val;
		case ">=" : return curr_val >= val;
		case "!=" : return curr_val != val;
		case "<" : return curr_val < val;
		case ">" : return curr_val > val;
		default : throw new Exception();
		}
	}
	
	private String getCommand(int start_index, String[] args) {
		StringBuilder sc = new StringBuilder();
		for (int i = start_index; i < args.length; i++) {
			if (args[i] != null)
				if (args[i].equalsIgnoreCase("else"))
					break;
				else sc.append(args[i]).append(' ');
		}
		return sc.toString();
	}
	
	private int getElseIndex(String[] args) {
		for (int i = 4; i < args.length; i++) {
			if (args[i] != null)
				if (args[i].equalsIgnoreCase("else"))
					return i;
		}
		return -1;
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
		if (!(sender instanceof Player)) {
			String id = args[0];
			String condition = args[1];
			int val = Integer.parseInt(args[2]);
			if (cmdName.equalsIgnoreCase("if")) {
				try {
					int elseIndex = getElseIndex(args);
					if (ifCondition(id, condition, val)) {
						
						String[] commands = getCommand(4, args).split(" % ");
						for (String command : commands)
							game.executeCommand(this, command);
					} else if (elseIndex != -1) {
						String[] commands = getCommand(elseIndex, args).split(" % ");
						for (String command : commands)
							game.executeCommand(this, command);
					} 
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if (cmdName.equalsIgnoreCase("var")) {
				if (condition.equalsIgnoreCase("add"))
					addValue(id, val);
				else setValue(id, val);
				return true;
			}
		}
		return false;
	}

}
