package fr.LNT.storymaker.kernel.utils;

import java.util.LinkedList;


/**
 * Represent a node in a tree
 * @author LNT
 *
 */
public class NodeObject {

	protected final Object node_value;
	protected final LinkedList<NodeObject> childrens = new LinkedList<>();

	/**
	 * Constructor of class NodeObject
	 * @param node_value
	 */
	public NodeObject(Object node_value) {
		this.node_value = node_value;
	}

	/**
	 * Add child to the node
	 * @param child Node that must be added to the node
	 */
	public void addChildren(NodeObject child) {
		childrens.add(child);
	}

	/**
	 * Get the number of children
	 * @return number of children
	 */
	public int getChildrensLenght() {
		return childrens.size();
	}

	/**
	 * Get a specific children with index
	 * @param id index of child
	 * @return children identified by index
	 */
	public NodeObject getChildren(int id) {
		return childrens.get(id);
	}

	
	/**
	 * Get the value if node
	 * @return value of node
	 */
	public Object getNodeValue() {
		return node_value;
	}
}