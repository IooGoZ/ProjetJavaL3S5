package fr.LNT.storymaker.debug;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.utils.xml.GameXMLBuilder;

public class Debug {
	public static void main(String[] args) {
		try {
			GameXMLBuilder game_builder = new GameXMLBuilder();
			Game game = game_builder.build();
			
			System.out.println(game);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Le jeu ne peut pas être lue.");
			e.printStackTrace();
		}
	}
}
