package fr.LNT.storymaker.kernel.utils.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import fr.LNT.storymaker.kernel.story.Location;
import fr.LNT.storymaker.kernel.utils.Dialog;
import fr.LNT.storymaker.kernel.gameobject.Character;

public class CharactersXMLBuilder implements XMLBuilder {

	private static final String CHARACTER_LIST_NODE_NAME = "characters-list";
	private static final String CHARACTER_NODE_NAME = "character";
	private static final String NAME_NODE_NAME = "name";
	private static final String DESCRIPTION_NODE_NAME = "desc";
	private static final String DIALOG_NODE_NAME = "dialog";
	
	private static final String LOCATION_ATTR_NAME = "location";
	private static final String AVAILABLE_ATTR_NAME = "available";

	private static final String XML_LIST_SEPARATOR = " ";
	
	
	private final HashMap<String, Location> id2Location;
	private final Document xml;
	
	private final List<Character> characters = new ArrayList<>();
	
	private final Node default_node;
	
	public CharactersXMLBuilder(Document xml, Node node, MapXMLBuilder map_builder) throws Exception {
		if (node.getNodeName() != CHARACTER_LIST_NODE_NAME)
			throw new Exception();
		
		this.xml = xml;
		this.id2Location = map_builder.getIdToLocation();
		
		this.default_node = node;
		
		
	}
	
	@Override
	public List<Character> build() {
		for (int i = 0; i < this.default_node.getChildNodes().getLength(); i++) {
			Node child = this.default_node.getChildNodes().item(i);
			if (child.getNodeName() == CHARACTER_NODE_NAME) {
				try {
					addCharacterNode(child);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return characters;
	}
	
	private void addCharacterNode(Node node) throws Exception {
		String loc_id = node.getAttributes().getNamedItem(LOCATION_ATTR_NAME).getTextContent();
		Location loc = id2Location.get(loc_id);
		String name = null, desc = null;
		
		Node available_node = node.getAttributes().getNamedItem(AVAILABLE_ATTR_NAME);
		List<String> available;
		
		HashMap<String, Dialog> dialog = new HashMap<>();
		
		if (available_node != null) {
			String[] available_ids = available_node.getTextContent().split(XML_LIST_SEPARATOR);
			available = Arrays.asList(available_ids);
		} else available = new ArrayList<>();
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			switch (child.getNodeName()) {
				case NAME_NODE_NAME : 
					name = child.getTextContent();
					break;
				case DESCRIPTION_NODE_NAME :
					desc = child.getTextContent();
					break;
				case DIALOG_NODE_NAME :
					DialogXMLBuilder dialog_builder = new DialogXMLBuilder(this.xml, child);
					Dialog curr_dialog = dialog_builder.build();
					Node dialog_available = child.getAttributes().getNamedItem(AVAILABLE_ATTR_NAME);
					if (dialog_available != null) {
						String[] available_ids = dialog_available.getTextContent().split(XML_LIST_SEPARATOR);
						for (String ava_id : available_ids) {
							dialog.put(ava_id, curr_dialog);
						}
					} else dialog.put(Character.ALWAYS_AVAILABLE, curr_dialog);
					break;
			}
		}
		Character character = new Character(name, desc, loc, available, dialog);
		characters.add(character);
	}
	
}
