package fr.LNT.storymaker.kernel.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import fr.LNT.storymaker.kernel.gameobject.Item;
import fr.LNT.storymaker.kernel.story.Location;

/**
 * Allows to instantiate all location from xml
 * @author LNT
 *
 */
public class MapXMLBuilder implements XMLBuilder {
	
	/**
	 * Name of input xml node
	 */
	public static final String MAP_NODE_NAME = "map";
	private static final String MAP_BEGIN_ATTR_NAME = "begin";
	private static final String LOCATION_NODE_NAME = "location";
	private static final String NAME_NODE_NAME = "name";
	private static final String TEXT_NODE_NAME = "text";
	private static final String DESCRIPTION_NODE_NAME = "desc";
	private static final String DOOR_NODE_NAME = "door";
	private static final String ID_ATTR_NAME = "id";
	private static final String TO_ATTR_NAME = "to";
	private static final String EXECUTE_ATTR_NAME = "execute";
	private static final String KEY_ATTR_NAME = "key";
	private static final String ITEM_ATTR_NAME = "item";
	
	private static final String XML_LIST_SEPARATOR = " ";
	
	
	private final HashMap<String, Item> id2item;
	private final HashMap<String, Location> id2Location = new HashMap<>();
	
	private final HashMap<String, String> door2locFrom = new HashMap<>();
	private final HashMap<String, String> door2cmd = new HashMap<>();
	private final HashMap<String, String> door2locTo = new HashMap<>();
	private final HashMap<String, Item> door2key = new HashMap<>();
	
	private final Location first_location;
	private final Node default_node;
	private final Node begin_node;
	
	/**
	 * Constructor of MapXMLBuilder
	 * @param xml Input XML document
	 * @param node Input XML node
	 * @param id2item Item list
	 * @throws Exception If the input node is invalid 
	 */
	public MapXMLBuilder(Document xml, Node node, HashMap<String, Item> id2item) throws Exception {
		if (node.getNodeName() != MAP_NODE_NAME)
			throw new Exception();
		
		this.id2item = id2item;
		this.default_node = node;
		String begin_id = node.getAttributes().getNamedItem(MAP_BEGIN_ATTR_NAME).getTextContent();
		this.begin_node = xml.getElementById(begin_id);
		this.first_location = addLocationNode(begin_node);
		
		
	}

	@Override
	public Location build() {
		for (int i = 0; i < this.default_node.getChildNodes().getLength(); i++) {
			Node child = this.default_node.getChildNodes().item(i);
			if (child.getNodeName() == LOCATION_NODE_NAME && child != begin_node) {
				addLocationNode(child);
			}
		}
		
		List<String> doors_ids = new ArrayList<>(door2locFrom.keySet());
		
		for (String door_id : doors_ids) {
			Location from = id2Location.get(door2locFrom.get(door_id));
			Location to = id2Location.get(door2locTo.get(door_id));
			Item key = door2key.getOrDefault(door_id, null);
			from.addExit(to, door2cmd.get(door_id), key);
		}
		
		return first_location;
	}
	
	private Location addLocationNode(Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String name = null, text = null, desc = null;
		List<Item> item = new ArrayList<>();
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			switch (child.getNodeName()) {
				case NAME_NODE_NAME : 
					name = child.getTextContent();
					break;
				case TEXT_NODE_NAME :
					text = child.getTextContent();
					break;
				case DESCRIPTION_NODE_NAME :
					desc = child.getTextContent();
					break;
				case DOOR_NODE_NAME :
					String door_id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
					door2locFrom.put(door_id, id);
					door2locTo.put(door_id, child.getAttributes().getNamedItem(TO_ATTR_NAME).getTextContent());
					Node execute_node = child.getAttributes().getNamedItem(EXECUTE_ATTR_NAME);
					if (execute_node != null)
						door2cmd.put(door_id, execute_node.getTextContent());
					Node key_node = child.getAttributes().getNamedItem(KEY_ATTR_NAME);
					if (key_node != null)
						door2key.put(door_id, id2item.get(key_node.getTextContent()));
					break;
			}
		}
		
		Node item_node = node.getAttributes().getNamedItem(ITEM_ATTR_NAME);
		if (item_node != null) {
			String[] items_ids = item_node.getTextContent().split(XML_LIST_SEPARATOR);
			for (String item_id : items_ids) {
				item.add(id2item.get(item_id));
			}
		}
		
		Location loc = new Location(name, text, desc, item);
		id2Location.put(id, loc);
		return loc;
	}
	
	public HashMap<String, Location> getIdToLocation() {
		return id2Location;
	}
}
