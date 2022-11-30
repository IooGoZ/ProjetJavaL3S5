package fr.LNT.storymaker.kernel.commands;

public interface Command {

	/**
	 * Run the command
	 * @param sender Instance of the sender
	 * @param cmdName Name use to call the command
	 * @param args Arguments of command
	 * @return True if the command was completed
	 */
	public boolean execute(Sender sender, String cmdName, String[] args);

	/**
	 * Concatenate the arguments of commands
	 * @param args Arguments array
	 * @return return the string of all argument separated with space
	 */
	public static String concatArgs(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length-1; i++)
			sb.append(' ').append(args[i]);
		
		return sb.toString().replaceFirst(" ", "");
	}
	
	
	/**
	 * Get the lenght of arguments array, ignore the null case
	 * @param args Arguments array
	 * @return Return lenght
	 */
	public static int argsLenght(String[] args) {
		int len = args.length;
		if (len > 0) {
			if (args[len-1] == null)
				return len-1;
		}
		return len;
	}
	
}
