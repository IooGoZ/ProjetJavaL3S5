package fr.LNT.storymaker.kernel.utils.xml;

import java.util.LinkedHashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.LNT.storymaker.kernel.utils.Dialog;
import fr.LNT.storymaker.kernel.utils.builders.DialogBuilder;

public class DialogXMLBuilder {

	private static final String DIALOG_NODE_NAME = "dialog";
	private static final String DIALOG_BEGIN_ATTR_NAME = "begin";

	private static final String DIALOGNODE_NODE_NAME = "dialog-node";

	private static final String TEXT_NODE_NAME = "text";

	private static final String ANSWER_NODE_NAME = "answer";
	private static final String ANSWER_REDIRECT_ATTR_NAME = "redirect";
	private static final String ANSWER_EXECUTE_ATTR_NAME = "execute";

	private static final String ID_ATTR_NAME = "id";

	private DialogBuilder builder;
	private Node begin_node;

	public DialogXMLBuilder(Node node) throws Exception {
		if (node.getNodeName() != DIALOG_NODE_NAME)
			throw new Exception();

		String begin_id = node.getAttributes().getNamedItem(DIALOG_BEGIN_ATTR_NAME).getTextContent();
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == DIALOGNODE_NODE_NAME) {
				String child_id = child.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
				if (child_id.equalsIgnoreCase(begin_id)) {
					begin_node = child;
					break;
				}
			}
		}
		builder = createDialogBuilder(begin_node);
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == DIALOGNODE_NODE_NAME) {
				if (child == begin_node)
					continue;
				addNodeToBuilder(builder, child);
			}
		}
	}

	public Dialog buildDialog() {
		return builder.buildDialog();
	}

	private static DialogBuilder createDialogBuilder(Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String text = null;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == TEXT_NODE_NAME) {
				text = child.getTextContent();
				break;
			}
		}
		LinkedHashMap<String, String> answers = nodeToAnswers(node);
		Node execute_attribute = node.getAttributes().getNamedItem(ANSWER_EXECUTE_ATTR_NAME);
		String execute = null;
		if (execute_attribute != null)
			execute = execute_attribute.getTextContent();
		return new DialogBuilder(id, text, answers, execute);
	}

	private static void addNodeToBuilder(DialogBuilder builder, Node node) {
		String id = node.getAttributes().getNamedItem(ID_ATTR_NAME).getTextContent();
		String text = null;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeName() == TEXT_NODE_NAME) {
				text = child.getTextContent();
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
				String answer = child.getTextContent();
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
