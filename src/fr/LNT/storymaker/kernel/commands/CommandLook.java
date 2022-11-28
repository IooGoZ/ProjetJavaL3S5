package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;

public class CommandLook implements Command{

    private Location location;

    public CommandLook(Game game)
    {
        this.location = game.getCurrentLocation();
    }

    public void lookAround()
    {
        System.out.println(this.location.getDescription());
    }

    public boolean lookClosely(String[] names)
    {
        for(String name : names){
            if (name == "Door")
            {
                this.location.printDoors();
                return true;
            }
            else 
            {
                if (this.location.doesItemExist(name))
                {
                    this.location.getItemDescription(name);
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        if (args.length == 0)
        {
            lookAround();
            return true;
        }
        else return lookClosely(args);
    }
    
}
