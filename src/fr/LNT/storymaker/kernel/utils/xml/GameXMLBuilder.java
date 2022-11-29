package fr.LNT.storymaker.kernel.utils.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.gameobject.Item;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.story.Script;
import fr.LNT.storymaker.kernel.gameobject.Character;

//Inspiré par : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

public class GameXMLBuilder implements XMLBuilder {

	private static final String DEFAULT_XML_PATHNAME = "test.xml";
	
	private static final String GAME_NODE_NAME = "game";
	private static final String NAME_ATTR_NAME = "name";
	private static final int FIRST_INDEX = 0;

	private final Document xml;
	private final String game_name;
	private final Node script_node;
	private final Node characters_node;
	private final Node map_node;
	private final Node items_node;

	public GameXMLBuilder(File file) throws ParserConfigurationException, SAXException, IOException {
		if (file == null) {
			URL url = getClass().getResource(DEFAULT_XML_PATHNAME);
			file = new File(url.getPath());
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		dbf.setValidating(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		xml = db.parse(file);
		xml.getDocumentElement().normalize();

		Element game = (Element) xml.getElementsByTagName(GAME_NODE_NAME).item(FIRST_INDEX);
		
		this.game_name = game.getAttributes().getNamedItem(NAME_ATTR_NAME).getTextContent(); 
		
		this.script_node = game.getElementsByTagName(ScriptXMLBuilder.SCRIPT_NODE_NAME).item(FIRST_INDEX);
		this.characters_node = game.getElementsByTagName(CharactersXMLBuilder.CHARACTER_LIST_NODE_NAME).item(FIRST_INDEX);
		this.map_node = game.getElementsByTagName(MapXMLBuilder.MAP_NODE_NAME).item(FIRST_INDEX);
		this.items_node = game.getElementsByTagName(ItemsXMLBuilder.ITEMS_LIST_NODE_NAME).item(FIRST_INDEX);
	}

	public GameXMLBuilder() throws ParserConfigurationException, SAXException, IOException {
		this(null);
	}

	@Override
	public Game build() {
		
		Script script = null;
		HashMap<String, Item> id2items = null;
		Location first_location = null;
		List<Character> characters;
		
		try {
			ScriptXMLBuilder script_builder = new ScriptXMLBuilder(script_node);
			script = script_builder.build();
			ItemsXMLBuilder items_builder = new ItemsXMLBuilder(items_node);
			id2items = items_builder.build();
			MapXMLBuilder map_builder = new MapXMLBuilder(xml, map_node, id2items);
			first_location = map_builder.build();
			CharactersXMLBuilder characters_builder = new CharactersXMLBuilder(xml, characters_node, map_builder);
			characters = characters_builder.build();	
			
		} catch (Exception e) {
			System.err.println("La lecture du fichier XML a échouée.");
			e.printStackTrace();
			System.exit(-1);
		}
		return new Game(game_name, new Player(), script, first_location, id2items);
	}

}
