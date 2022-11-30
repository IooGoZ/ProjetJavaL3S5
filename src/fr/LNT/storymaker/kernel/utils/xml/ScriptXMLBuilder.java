package fr.LNT.storymaker.kernel.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;

import fr.LNT.storymaker.kernel.story.Script;
import fr.LNT.storymaker.kernel.utils.builders.TreeBuilder;

/**
 * Allows to instantiate the script from xml
 * @author LNT
 *
 */
public class ScriptXMLBuilder implements XMLBuilder {
	
	/**
	 * Name of input xml node
	 */
	public static final String SCRIPT_NODE_NAME = "script";
	private static final String BEGIN_ATTR_NAME = "begin";
	
	private static final String SCRIPTNODE_NODE_NAME = "script-node";
	private static final String CHILD_NODE_NAME = "child";
	private static final String CHILDID_ATTR_NAME = "child-id";
	
	private static final String ID_ATTR_NAME = "id";
	
	private final Node default_node;
	private final String begin_id;
	private final HashMap<String, List<String>> id2childs = new HashMap<>();
	
	/**
	 * Constructor of CharactersXMLBuilder
	 * @param node Input XML node
	 * @throws Exception If the input node is invalid 
	 */
	public ScriptXMLBuilder(Node node) throws Exception {
		if (node.getNodeName() != SCRIPT_NODE_NAME)
			throw new Exception();
		this.begin_id = node.getAttributes().getNamedItem(BEGIN_ATTR_NAME).getTextContent();
		this.default_node = node;
	}
	
	
	private void addScriptNode(Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		List<String> childs = new ArrayList<>();
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == CHILD_NODE_NAME) {
				childs.add(child.getAttributes().getNamedItem(CHILDID_ATTR_NAME).getTextContent());
			}
		}
		id2childs.put(id, childs);
	}
	
	/**
	 * Build the script
	 */
	@Override
	public Script build() {
		for (int i = 0; i < this.default_node.getChildNodes().getLength(); i++) {
			Node child = this.default_node.getChildNodes().item(i);
			if (child.getNodeName() == SCRIPTNODE_NODE_NAME) {
				addScriptNode(child);
			}
		}
		
		TreeBuilder builder = new TreeBuilder((Object) this.begin_id);
		for (String id : id2childs.keySet()) {
			if (id != begin_id)
				builder.addNode(id);
		}
		
		for (String id : id2childs.keySet()) {
			List<String> childs = id2childs.get(id);
			for (String child : childs) {
				builder.createUnilink(id, child);
			}
		}
		
		return new Script(builder.build());
	}

}
