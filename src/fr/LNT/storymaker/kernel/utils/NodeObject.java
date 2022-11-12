package fr.LNT.storymaker.kernel.utils;
import java.util.ArrayList;
import java.util.List;

public class NodeObject {

	protected final Object node_value;
	protected final List<NodeObject> childrens = new ArrayList<>();
	
	public NodeObject(Object node_value) {
		this.node_value = node_value;
	}
	
	public void addChildren(NodeObject child) {
		childrens.add(child);
	}
	
	public int getMaxChildrensIndex() {
		return childrens.size()-1;
	}
	
	public NodeObject getChildren(int id) {
		return childrens.get(id);
	}
	
	public Object getNodeValue() {
		return node_value;
	}
}