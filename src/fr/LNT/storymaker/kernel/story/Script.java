package fr.LNT.storymaker.kernel.story;
import fr.LNT.storymaker.kernel.utils.NodeObject;
import fr.LNT.storymaker.kernel.utils.TreeObject;

public class Script extends TreeObject {

	protected Script(NodeObject starter_node) {
		super(starter_node);
	}
	
	public Object[] getAllChildren() {
		int len = super.current_node.getMaxChildrensIndex();
		Object[] list = new Object[len];
		for (int i = 0; i < len; i++)
		{
			list[i] = super.current_node.getChildren(i).getNodeValue();
		}	
		return list;
	}
	
	
}
