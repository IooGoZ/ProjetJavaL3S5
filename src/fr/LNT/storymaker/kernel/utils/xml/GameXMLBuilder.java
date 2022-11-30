package fr.LNT.storymaker.kernel.utils.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.Player;
import fr.LNT.storymaker.kernel.gameobject.Item;
import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.story.Script;
import fr.LNT.storymaker.kernel.gameobject.Character;

//Inspiré par : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

/**
 * Allows to instantiate a game from xml
 * @author LNT
 *
 */
public class GameXMLBuilder implements XMLBuilder {

	private static final String DEFAULT_XML_PATHNAME = "fr/LNT/storymaker/kernel/utils/xml/test.xml";
	private static final String DEFAULT_DTD_NAME = "game.dtd";
	private static final String DEFAULT_DTD_PATHNAME = "fr/LNT/storymaker/kernel/utils/xml/game.dtd";

	private static final String GAME_NODE_NAME = "game";
	private static final String NAME_ATTR_NAME = "name";
	private static final int FIRST_INDEX = 0;

	private final Document xml;
	private final String game_name;
	private final Node script_node;
	private final Node characters_node;
	private final Node map_node;
	private final Node items_node;

	/**
	 * Constructor of GameXMLBuilder
	 * @param file Input XML file (if null use default resource)
	 * @throws ParserConfigurationException XML don't respect the DTD
	 * @throws SAXException Cannot parse file
	 * @throws IOException Cannot open file
	 */
	public GameXMLBuilder(File file) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		dbf.setValidating(true);

		DocumentBuilder db = dbf.newDocumentBuilder();

		InputStream test = getClass().getClassLoader().getResourceAsStream(DEFAULT_DTD_PATHNAME);
		db.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				if (systemId.contains(DEFAULT_DTD_NAME)) {
					return new InputSource(test);
				} else {
					return null;
				}
			}
		});

		if (file == null) {
			InputStream path = getClass().getClassLoader().getResourceAsStream(DEFAULT_XML_PATHNAME);
			xml = db.parse(path);
		} else
			xml = db.parse(file);

		xml.getDocumentElement().normalize();

		Element game = (Element) xml.getElementsByTagName(GAME_NODE_NAME).item(FIRST_INDEX);

		this.game_name = game.getAttributes().getNamedItem(NAME_ATTR_NAME).getTextContent();

		this.script_node = game.getElementsByTagName(ScriptXMLBuilder.SCRIPT_NODE_NAME).item(FIRST_INDEX);
		this.characters_node = game.getElementsByTagName(CharactersXMLBuilder.CHARACTER_LIST_NODE_NAME)
				.item(FIRST_INDEX);
		this.map_node = game.getElementsByTagName(MapXMLBuilder.MAP_NODE_NAME).item(FIRST_INDEX);
		this.items_node = game.getElementsByTagName(ItemsXMLBuilder.ITEMS_LIST_NODE_NAME).item(FIRST_INDEX);
	}

	/**
	 * Constructor of GameXMLBuilder (use default resource)
	 * @throws ParserConfigurationException XML don't respect the DTD
	 * @throws SAXException Cannot parse file
	 * @throws IOException Cannot open file
	 */
	public GameXMLBuilder() throws ParserConfigurationException, SAXException, IOException {
		this(null);
	}

	
	/**
	 * Build the game
	 */
	@Override
	public Game build() {

		Script script = null;
		HashMap<String, Item> id2items = null;
		Location first_location = null;

		try {
			ScriptXMLBuilder script_builder = new ScriptXMLBuilder(script_node);
			script = script_builder.build();
			ItemsXMLBuilder items_builder = new ItemsXMLBuilder(items_node);
			id2items = items_builder.build();
			MapXMLBuilder map_builder = new MapXMLBuilder(xml, map_node, id2items);
			first_location = map_builder.build();
			CharactersXMLBuilder characters_builder = new CharactersXMLBuilder(xml, characters_node, map_builder);
			characters_builder.build();

		} catch (Exception e) {
			System.err.println("La lecture du fichier XML a échouée.");
			e.printStackTrace();
			System.exit(-1);
		}
		return new Game(game_name, new Player(), script, first_location, id2items);
	}

}
