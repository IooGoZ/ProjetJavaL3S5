package fr.LNT.storymaker.kernel.story;

import fr.LNT.storymaker.kernel.utils.NodeObject;
import fr.LNT.storymaker.kernel.utils.TreeObject;

public class Script extends TreeObject {

	public Script(NodeObject starter_node) {
		super(starter_node);
	}

	public String[] getAllChildren() {
		int len = super.current_node.getMaxChildrensIndex();
		String[] list = new String[len];
		for (int i = 0; i <= len; i++) {
			list[i] = (String) super.current_node.getChildren(i).getNodeValue();
		}
		return list;
	}
	
	@Override
	public String getCurrentValue() {
		return (String) super.getCurrentValue();
	}

}
