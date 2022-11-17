package fr.LNT.storymaker.kernel.utils;

public abstract class TreeObject {
	
	protected NodeObject current_node;
	
	public TreeObject(NodeObject starter_node) {
		this.current_node = starter_node;
	}
	
	public Object getCurrentValue() {
		return current_node.getNodeValue();
	}
	
	public boolean moveToChild(int i) {
		boolean res = !isEndOfTree() && i <= current_node.getMaxChildrensIndex() && i >= 0;
		if (res)
			this.current_node = this.current_node.getChildren(i);
		return res;
	}
	
	public boolean isEndOfTree() {
		return current_node.getMaxChildrensIndex() == -1;
	}
}
