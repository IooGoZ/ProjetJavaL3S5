package fr.LNT.storymaker.kernel.story;
import java.util.List;

import fr.LNT.storymaker.kernel.gameobject.Character;
import fr.LNT.storymaker.kernel.gameobject.Door;

public class Location {

	private String name;
	private String description;
	private List<Door> exits;
	private List<Character> characters;

}