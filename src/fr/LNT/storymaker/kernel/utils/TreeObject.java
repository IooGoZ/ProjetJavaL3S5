package fr.LNT.storymaker.kernel.utils;

/**
 * Represent an object graph (or tree)
 * @author Tom
 *
 */
public abstract class TreeObject {

	protected NodeObject current_node;

	/**
	 * Constructor of tree
	 * @param starter_node Node which start the tree
	 */
	public TreeObject(NodeObject starter_node) {
		this.current_node = starter_node;
	}

	
	/**
	 * Get the value of current node
	 * @return
	 */
	public Object getCurrentValue() {
		return current_node.getNodeValue();
	}

	
	/**
	 * Change the current node for a child 
	 * @param i index of child
	 * @return true if can change
	 */
	public boolean moveToChild(int i) {
		boolean res = !isEndOfTree() && i < current_node.getChildrensLenght() && i >= 0;
		if (res)
			this.current_node = this.current_node.getChildren(i);
		return res;
	}

	
	/**
	 * Check if we are at the end of the tree
	 * @return true if it's the end
	 */
	public boolean isEndOfTree() {
		return current_node.getChildrensLenght() == 0;
	}
}
