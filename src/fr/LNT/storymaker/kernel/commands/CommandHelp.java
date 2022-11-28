package fr.LNT.storymaker.kernel.commands;

public class CommandHelp implements Command{

    public void help()
    {
         System.out.println("GO arg : Allows you to move to the arg location. A door to the arg location must exist so you can move. You can check by using 'LOOK Door' command.\n");
         System.out.println("INVENTORY : Allows you to check your inventory. Returns the list of all the items you own.\n");
         System.out.println("HELP : If you're here, you must know what it does, right ?\n");
         System.out.println("LOOK [arg] : Allows you to take a look to the room and lists all the things you can find inside. By adding one or more arguments (name of item), you can take a closer look at said items. Use LOOK Door to get the door's list.\n");
         System.out.println("TAKE arg : Adds (if possible) the item to your inventory.\n");
         System.out.println("QUIT : Quits the game.\n");
         System.out.println("USE arg1 [arg2] : Uses the first arg (if possible). If there is a second argument, arg2 must be coupled with arg2, and the combination of the two lead to a new action.\n");
    }
    
    
    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        help();
        return true;
    }
}
