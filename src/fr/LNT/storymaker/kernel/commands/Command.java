package fr.LNT.storymaker.kernel.commands;

public interface Command {

	public boolean execute(Sender sender, String cmdName, String[] args);

	public static String concatArgs(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length-1; i++)
			sb.append(' ').append(args[i]);
		
		return sb.toString().replaceFirst(" ", "");
	}
	
	public static int argsLenght(String[] args) {
		int len = args.length;
		if (len > 0) {
			if (args[len-1] == null)
				return len-1;
		}
		return len;
	}
	
}
