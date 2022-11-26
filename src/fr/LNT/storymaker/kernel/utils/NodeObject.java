package fr.LNT.storymaker.kernel.utils;

import java.util.LinkedList;

public class NodeObject {

	protected final Object node_value;
	protected final LinkedList<NodeObject> childrens = new LinkedList<>();

	public NodeObject(Object node_value) {
		this.node_value = node_value;
	}

	public void addChildren(NodeObject child) {
		childrens.add(child);
	}

	public int getMaxChildrensIndex() {
		return childrens.size() - 1;
	}

	public NodeObject getChildren(int id) {
		return childrens.get(id);
	}

	public Object getNodeValue() {
		return node_value;
	}
}