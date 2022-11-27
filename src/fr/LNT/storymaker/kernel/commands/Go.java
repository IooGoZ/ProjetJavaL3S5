package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.story.Location;


public class Go implements Command{

    private Game game; 
    private Location location;

    public Go (Game game){
        this.game = game;
        this.location = this.game.getCurrentLocation();
    }

    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        if (this.location.doesDoorExist(args[0]))
        {
            this.game.tryToUseDoor(this.location.getDoor(args[0]));
            return true;
        }
        else return false;
    }
}
