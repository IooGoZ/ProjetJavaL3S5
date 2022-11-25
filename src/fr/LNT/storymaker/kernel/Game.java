package fr.LNT.storymaker.kernel;
import java.util.List;
import java.util.Scanner;

import fr.LNT.storymaker.kernel.commands.Command;
import fr.LNT.storymaker.kernel.commands.CommandTest;
import fr.LNT.storymaker.kernel.gameobject.Door;
import fr.LNT.storymaker.kernel.story.Location;

public class Game {

	public static final Scanner stdinScanner = new Scanner(System.in);
	
	private List<Location> locations;
	private Player player;
	private List<Door> doors;
	private int placeInSript;
	
	

}