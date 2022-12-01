package fr.LNT.storymaker.kernel.utils.xml;

import java.util.LinkedHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.LNT.storymaker.kernel.utils.Dialog;
import fr.LNT.storymaker.kernel.utils.builders.DialogBuilder;

/**
 * Allows to instantiate a dialog from xml
 * @author LNT
 *
 */
public class DialogXMLBuilder implements XMLBuilder {

	private static final String DIALOG_NODE_NAME = "dialog";
	private static final String BEGIN_ATTR_NAME = "begin";

	private static final String DIALOGNODE_NODE_NAME = "dialog-node";

	private static final String TEXT_NODE_NAME = "text";

	private static final String ANSWER_NODE_NAME = "answer";
	private static final String ANSWER_REDIRECT_ATTR_NAME = "redirect";
	private static final String ANSWER_EXECUTE_ATTR_NAME = "execute";

	private static final String ID_ATTR_NAME = "id";
	
	private static final int FIRST_INDEX = 0;
	
	private final DialogBuilder builder;
	private final Element begin_node;

	/**
	 * Constructor of DialogXMLBuilder
	 * @param xml Input XML document
	 * @param node Input XML node
	 * @throws Exception If the input node is invalid 
	 */
	public DialogXMLBuilder(Document xml, Node node) throws Exception {
		if (node.getNodeName() != DIALOG_NODE_NAME)
			throw new Exception();
		
		String begin_id = node.getAttributes().getNamedItem(BEGIN_ATTR_NAME).getTextContent();
		this.begin_node = xml.getElementById(begin_id);
		
		this.builder = createDialogBuilder(begin_node);
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == DIALOGNODE_NODE_NAME) {
				if (child == begin_node)
					continue;
				addNodeToBuilder(child);
			}
		}
	}

	/**
	 * Build the dialog
	 */
	@Override
	public Dialog build() {
		return builder.buildDialog();
	}

	private static DialogBuilder createDialogBuilder(Element node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String text = node.getElementsByTagName(TEXT_NODE_NAME).item(FIRST_INDEX).getTextContent();
		
		LinkedHashMap<String, String> answers = nodeToAnswers(node);
		Node execute_attribute = node.getAttributes().getNamedItem(ANSWER_EXECUTE_ATTR_NAME);
		String execute = null;
		if (execute_attribute != null)
			execute = execute_attribute.getTextContent();
		return new DialogBuilder(id, text, answers, execute);
	}

	private void addNodeToBuilder(Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String text = null;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == TEXT_NODE_NAME) {
				text = child.getTextContent().replace(" \\n ", "\n");
				break;
			}
		}
		LinkedHashMap<String, String> answers = nodeToAnswers(node);
		Node execute_attribute = node.getAttributes().getNamedItem(ANSWER_EXECUTE_ATTR_NAME);
		String execute = null;
		if (execute_attribute != null)
			execute = execute_attribute.getTextContent();
		builder.addNode(id, text, answers, execute);
	}

	private static LinkedHashMap<String, String> nodeToAnswers(Node node) {
		LinkedHashMap<String, String> answers = new LinkedHashMap<>();
		NodeList childrens = node.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			Node child = childrens.item(i);
			if (child.getNodeName() == ANSWER_NODE_NAME) {
				String answer = child.getTextContent().replace(" \\n ", "\n");
				Node redirect_attr = child.getAttributes().getNamedItem(ANSWER_REDIRECT_ATTR_NAME);
				String redirect = null;
				if (redirect_attr != null)
					redirect = redirect_attr.getTextContent();
				answers.put(answer, redirect);
			}
		}
		return answers;
	}
}
