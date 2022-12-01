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
import fr.LNT.storymaker.kernel.commands.SpecialCommandDev;
import fr.LNT.storymaker.kernel.commands.SpecialCommandEnd;
import fr.LNT.storymaker.kernel.commands.SpecialCommandGive;
import fr.LNT.storymaker.kernel.commands.SpecialCommandPrint;
import fr.LNT.storymaker.kernel.commands.SpecialCommandScript;
import fr.LNT.storymaker.kernel.gameobject.ClosedDoor;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.gameobject.Item;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.story.Script;

/**
 * Represent a game
 * 
 * @author LNT
 *
 */
public class Game {

	public static final Scanner stdinScanner = new Scanner(System.in);

	private final String[] cmdName = { "go", "help", "look", "take", "talk", "script", "give", "print", "var", "if", "end"};

	private SpecialCommandDev specialCommandDev = new SpecialCommandDev(this);
	private final Command[] cmds = { new CommandGo(this), new CommandHelp(), new CommandLook(this),
			new CommandTake(this), new CommandTalk(this), new SpecialCommandScript(this), new SpecialCommandGive(this), new SpecialCommandPrint(), specialCommandDev, specialCommandDev, new SpecialCommandEnd(this) };

	private final String gameName;
	private final Script script;
	private Location location;
	private final Player player;
	private final HashMap<String, Item> id2Item;
	
	private static final boolean DEBUG = true;

	private final CommandParser parser = new CommandParser();

	private boolean isFinished = false;

	/**
	 * Constructor of Game class
	 * 
	 * @param gameName      The name of game
	 * @param player        Instance of player used for the game
	 * @param script        Instance of script used for the game
	 * @param firstLocation Location where the player appears
	 * @param id2Item       A Hashmap which allowed to convert id of item to item
	 *                      instance
	 */
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

	/**
	 * Run a command for non-player sender
	 * 
	 * @param sender Instance that would like to run command
	 * @param cmds   Commands was separated by " / "
	 * @return Return if commmand was be completed
	 */
	public void executeCommand(Sender sender, String cmds) {
		String[] cmd_tab = cmds.split(" / ");
		for (String cmd : cmd_tab)
			if (!parser.parse(sender, cmd)) {
				if (DEBUG) System.err.println("Invalid command : " + cmd);
			}
	}

	/**
	 * Run the game
	 */
	public void runGame() {
		System.out.println("Launching of " + gameName);
		location.printTextIfAvailable();
		while (!isFinished) {
			System.out.print("Command>>");
			String cmd = stdinScanner.nextLine();
			if (!parser.parse(player, cmd))
				if (DEBUG) System.err.println("The command was not completed.");
		}
	}

	public void finish() {
		this.isFinished = true;
	}
	
	
	/**
	 * Get an id with his id
	 * 
	 * @param id Id of item
	 * @return If id is unknow, return null
	 */
	public Item getItemById(String id) {
		return id2Item.getOrDefault(id, null);
	}

	/**
	 * Get the id of the node in the Script tree
	 * 
	 * @return Id of current node
	 */
	public String getCurrentScriptPosition() {
		return script.getCurrentValue();
	}
	
	
	/**
	 * Get the location where the player is
	 * @return current location
	 */
	public Location getCurrentLocation() {
		return this.location;
	}

	/**
	 * Get the player instance
	 * @return player instance
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Try to use door to change location
	 * @param door Door that you would like to use
	 * @return true if the location is changed
	 */
	public boolean tryToUseDoor(Door door) {
		if (location.getExits().contains(door)) {
			door.executeCommand(this);
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

	/**
	 * Try to move in the script tree node
	 * @param node_id Id of node where you want to go
	 * @return true if the scipt is changed
	 */
	public boolean tryToMoveInScript(String node_id) {
		String[] nodes_ids = script.getAllChildren();
		for (int i = 0; i < nodes_ids.length; i++) {
			String curr_id = nodes_ids[i];
			if (curr_id.equalsIgnoreCase(node_id)) {
				if (script.moveToChild(i))
					return true;
				else
					break;
			}
		}
		return false;
	}
}