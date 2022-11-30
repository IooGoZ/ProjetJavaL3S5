package fr.LNT.storymaker.kernel.utils.xml;

import java.util.HashMap;

import org.w3c.dom.Node;

import fr.LNT.storymaker.kernel.gameobject.Item;

/**
 * Allows to instantiate all items from xml
 * @author LNT
 *
 */
public class ItemsXMLBuilder implements XMLBuilder{
	
	/**
	 * Name of input xml node
	 */
	public static final String ITEMS_LIST_NODE_NAME = "items-list";
	private static final String ITEM_NODE_NAME = "item";
	
	private static final String NAME_NODE_NAME = "name";
	private static final String DESC_NODE_NAME = "desc";
	
	private static final String ID_ATTR_NAME = "id";
	private static final String EXECUTE_ATTR_NAME = "execute";
	private final Node default_node;
	private final HashMap<String, Item> id2item = new HashMap<>();

	/**
	 * Constructor of ItemsXMLBuilder
	 * @param xml Input XML document
	 * @param node Input XML node
	 * @throws Exception If the input node is invalid 
	 */
	public ItemsXMLBuilder(Node node) throws Exception {
		if (node.getNodeName() != ITEMS_LIST_NODE_NAME)
			throw new Exception();
		this.default_node = node;
	}
	
	/**
	 * Build the Items list
	 */
	@Override
	public HashMap<String, Item> build() {
		for (int i = 0; i < this.default_node.getChildNodes().getLength(); i++) {
			Node child = this.default_node.getChildNodes().item(i);
			if (child.getNodeName() == ITEM_NODE_NAME) {
				addItemNode(child);
			}
		}
		return this.id2item;
	}

	private void addItemNode(Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String name = null;
		String desc = null;
		
		Node execute_node = node.getAttributes().getNamedItem(EXECUTE_ATTR_NAME);
		String execute = null;
		if (execute_node != null)
			execute = execute_node.getTextContent();
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			switch (child.getNodeName()) {
				case NAME_NODE_NAME : name = child.getTextContent();
						break;
				case DESC_NODE_NAME : desc = child.getTextContent();
						break;
			}
		}

		Item item = new Item(id, name, desc, execute);
		this.id2item.put(id, item);
	}

}
