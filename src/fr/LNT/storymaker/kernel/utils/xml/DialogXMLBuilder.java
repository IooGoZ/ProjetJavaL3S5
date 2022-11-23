package fr.LNT.storymaker.kernel.utils.xml;

import java.util.LinkedHashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DialogXMLBuilder {
	
	private static final String DIALOG_NODE_NAME = "dialog";
	private static final String ANSWER_NODE_NAME = "answer";
	private static final String ANSWER_REDIRECT_ATTR_NAME = "redirect";
	
	public DialogXMLBuilder(Node node) throws Exception {
		if (node.getNodeName() != DIALOG_NODE_NAME)
			throw new Exception();
		
		
	}
	
	private static LinkedHashMap<String, String> nodeToAnswers(Node node) {
		LinkedHashMap<String, String> answers = new LinkedHashMap<>();
		NodeList childrens = node.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			Node child = childrens.item(i);
			if (child.getNodeName() == ANSWER_NODE_NAME) {
				String answer = child.getTextContent();
				Node redirect_attr = child.getAttributes().getNamedItem(ANSWER_REDIRECT_ATTR_NAME);
				String redirect = null;
				if(redirect_attr != null) redirect = redirect_attr.getTextContent();
				answers.put(answer, redirect);
			}
		}
		return answers;
	}
}
