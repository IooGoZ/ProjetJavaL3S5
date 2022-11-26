package fr.LNT.storymaker.kernel.commands;

public interface Command {

	public boolean execute(Sender sender, String cmdName, String[] args);

}
