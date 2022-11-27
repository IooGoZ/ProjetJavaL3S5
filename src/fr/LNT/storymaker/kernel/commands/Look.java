package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;

public class Look implements Command{

    private Game game; 
    private Location location;


    public Look(Game game)
    {
        this.game = game;
        this.location = this.game.getCurrentLocation();
    }

    public void lookAround()
    {
        System.out.println(this.location.getDescription());
    }

    public void lookClosely(String[] names)
    {
        for (int i = 0; i < names.length; i++)
        {
            if (names[i] == "Door")
            {
                this.location.printDoors();
            }
            else 
            {
                
            }
        }
    }

    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        if (args.length == 0)
        {
            lookAround();
        }
        else lookClosely(args);
        return true;
    }
    
}
