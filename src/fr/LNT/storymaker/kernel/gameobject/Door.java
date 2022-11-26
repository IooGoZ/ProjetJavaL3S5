package fr.LNT.storymaker.kernel.gameobject;

import fr.LNT.storymaker.kernel.story.Location;

public class Door {
	private Location from;
	private Location to;

	public Door(Location from, Location to) {
		this.from = from;
		this.to = to;
	}

	public Location whereDoIGo() {
		return this.to;
	}

	public Location whereAmI() {
		return this.from;
	}
}
