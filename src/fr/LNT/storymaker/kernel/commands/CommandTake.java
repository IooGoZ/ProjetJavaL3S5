package fr.LNT.storymaker.kernel.commands;
import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.gameobject.Item;

public class CommandTake implements Command{
     
    private Player player;
    private Location location;

    public CommandTake(Game game){
        this.player = game.getCurrentPlayer();
        this.location = game.getCurrentLocation();
    }

    public boolean execute(Sender sender, String cmdName, String[] args)
    {
        if(this.location.doesItemExist(args[0]))
        {
            Item item = this.location.getItem(args[0]);
            this.player.addInventory(item);
            this.location.deleteItem(item);
            return true;
        }
        return false;
    }
    
}
