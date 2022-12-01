package fr.LNT.storymaker.kernel;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.LNT.storymaker.kernel.utils.xml.GameXMLBuilder;

/**
 * Launcher of game
 * @author LNT
 *
 */
public abstract class Launcher {
	
	
	/**
	 * Main method
	 * @param args If args contains a XML file pathname, the launcher try to read it to play a different game.
	 */
	public static void main(String[] args) {
		try {
			GameXMLBuilder game_builder;
			if (args.length == 0)
				game_builder = new GameXMLBuilder();
			else game_builder = new GameXMLBuilder(new File(args[0]));
			Game game = game_builder.build();
			game.runGame();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Le jeu ne peut pas être lue.");
			e.printStackTrace();
		}
	}
}
