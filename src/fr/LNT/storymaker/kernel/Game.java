package fr.LNT.storymaker.kernel;

import java.util.HashMap;
import java.util.Scanner;

import fr.LNT.storymaker.kernel.commands.Command;
import fr.LNT.storymaker.kernel.commands.CommandGo;
import fr.LNT.storymaker.kernel.commands.CommandHelp;
import fr.LNT.storymaker.kernel.commands.CommandLook;
import fr.LNT.storymaker.kernel.commands.CommandParser;
import fr.LNT.storymaker.kernel.commands.CommandTake;
import fr.LNT.storymaker.kernel.commands.CommandTalk;
import fr.LNT.storymaker.kernel.commands.Sender;
import fr.LNT.storymaker.kernel.commands.SpecialCommandGive;
import fr.LNT.storymaker.kernel.commands.SpecialCommandHealth;
import fr.LNT.storymaker.kernel.commands.SpecialCommandScript;
import fr.LNT.storymaker.kernel.gameobject.ClosedDoor;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.story.Script;

public class Game {

	public static final Scanner stdinScanner = new Scanner(System.in);
	
	private final String[] cmdName = {
			"go",
			"help",
			"look",
			"take",
			"talk",
			"script",
			"give",
			"health-set",
			"health-add"
	};
	private final Command[] cmds = {
			new CommandGo(this),
			new CommandHelp(),
			new CommandLook(this),
			new CommandTake(this),
			new CommandTalk(this),
			new SpecialCommandScript(this),
			new SpecialCommandGive(this),
			new SpecialCommandHealth(this),
			new SpecialCommandHealth(this)
	};

	private final String gameName;
	private final Script script;
	private Location location;
	private final Player player;
	private final HashMap<String, Item> id2Item;
	
	private final CommandParser parser = new CommandParser();
	
	private boolean isFinished = false;
	
	public Game(String gameName, Player player, Script script, Location firstLocation, HashMap<String, Item> id2Item) {
		this.gameName = gameName;
		this.player = player;
		this.script = script;
		this.location = firstLocation;
		this.id2Item = id2Item;
		
		if (cmdName.length == cmds.length) {
			for (int i = 0; i < cmdName.length; i++) {
				parser.addCommand(cmdName[i], cmds[i]);
			}
		} else {
			System.err.println("Cannot initialize the game : error in command array.");
			System.exit(-1);
		}
	}
	
	public boolean executeCommand(Sender sender, String cmd) {
		return parser.parse(sender, cmd);
	}
	
	public void runGame() {
		System.out.println("Launching of " + gameName);
		location.printTextIfAvailable();
		while(!isFinished) {
			String cmd = stdinScanner.nextLine();
			if (!parser.parse(player, cmd))
				System.err.println("The command was not completed.");
		}
	}
	
	public Item getItemById(String id) {
		return id2Item.getOrDefault(id, null);
	}
	
	public String getCurrentScriptPosition() {
		return script.getCurrentValue();
	}

	public Location getCurrentLocation()
	{
		return this.location;
	}

	public Player getCurrentPlayer()
	{
		return this.player;
	}
	
	public boolean tryToUseDoor(Door door) {
		if (location.getExits().contains(door)) {
			if (door instanceof ClosedDoor) {
				ClosedDoor closedDoor = (ClosedDoor) door;
				if (!closedDoor.isOpen())
					if (!closedDoor.openSesame(player))
						return false;
			}
			this.location = door.whereDoIGo();
			location.printTextIfAvailable();
			return true;
		}
		return false;
	}
	
	public boolean tryToMoveInScript(String node_id) {
		String[] nodes_ids = script.getAllChildren();
		for (int i = 0; i < nodes_ids.length; i++) {
			String curr_id = nodes_ids[i];
			if (curr_id == node_id) {
				if (script.moveToChild(i))
					return true;
				else break;
			}
		}
		return false;
	}
}