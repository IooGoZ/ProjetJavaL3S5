package fr.LNT.storymaker.kernel.story;

import fr.LNT.storymaker.kernel.utils.NodeObject;
import fr.LNT.storymaker.kernel.utils.TreeObject;


/**
 * Represent the script of the game
 * @author LNT
 *
 */
public class Script extends TreeObject {

	/**
	 * Constructor of class Script
	 * @param starter_node Node which start the tree
	 */
	public Script(NodeObject starter_node) {
		super(starter_node);
	}

	/**
	 * Get all children node id of current node
	 * @return List of node id
	 */
	public String[] getAllChildren() {
		int len = super.current_node.getChildrensLenght();
		String[] list = new String[len];
		for (int i = 0; i < len; i++) {
			list[i] = (String) super.current_node.getChildren(i).getNodeValue();
		}
		return list;
	}
	
	/**
	 * Get the id of current node
	 */
	@Override
	public String getCurrentValue() {
		return (String) super.getCurrentValue();
	}

}
