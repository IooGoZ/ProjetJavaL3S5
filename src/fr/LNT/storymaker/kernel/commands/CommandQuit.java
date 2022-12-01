package fr.LNT.storymaker.kernel.commands;

public class CommandQuit  implements Command{

    public boolean execute(Sender sender, String cmdName, String[] args) {
        System.exit(0);
		return true;
	}
    
}
