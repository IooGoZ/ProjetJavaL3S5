package fr.LNT.storymaker.kernel.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represent a part of dialog, with a text and some answers
 * @author LNT
 *
 */
public class DialogNode extends NodeObject {

	private final String text;
	private final LinkedHashMap<String, String> answers;
	private final String command;

	private static final String NEW_LINE = "\n";
	private static final String ANSWER_SEPARATOR = " = ";
	public static final int ANSWER_MIN = 1;

	private static final int ANSWER_TO_CHILD_ERROR = -1;

	/**
	 * Constructor of class DialogNode
	 * @param id Id of the node
	 * @param text Text of the node
	 * @param answers List answers link to the redirection
	 * @param command Command that must executed when the dialog node is execute
	 */
	public DialogNode(String id, String text, LinkedHashMap<String, String> answers, String command) {
		super(id);

		this.text = text;
		this.answers = answers;
		this.command = command;

	}

	/**
	 * Convert an answer to child
	 * @param answer answer id
	 * @return return the index of node answer
	 */
	public int answerToChild(int answer) {
		List<String> answerTarget = new ArrayList<>(answers.values());
		String child_id = answerTarget.get(answer - ANSWER_MIN);

		int i = 0;
		for (NodeObject child : super.childrens) {
			if (child.getNodeValue().equals(child_id))
				return i;
			i++;
		}

		return ANSWER_TO_CHILD_ERROR;
	}

	/**
	 * Convert object to string 
	 */
	@Override
	public String toString() {
		StringBuilder strbld = new StringBuilder(text);
		int i = ANSWER_MIN;
		for (String answer : answers.keySet()) {
			strbld.append(NEW_LINE);
			strbld.append(i);
			strbld.append(ANSWER_SEPARATOR);
			strbld.append(answer);
			i++;
		}
		return strbld.toString();
	}

	/**
	 * Print the object
	 */
	public void printNode() {
		System.out.println(toString());
	}

	
	/**
	 * Get the child with id
	 */
	@Override
	public DialogNode getChildren(int id) {
		return (DialogNode) super.getChildren(id);
	}

	/**
	 * Get the id of dialog node
	 */
	@Override
	public String getNodeValue() {
		return (String) super.node_value;
	}

	
	/**
	 * Add children to the node
	 * @param child Children that must be added
	 */
	public void addChildren(DialogNode child) {
		super.addChildren(child);
	}

	/**
	 * Get the command
	 * @return command
	 */
	public String getCommand() {
		return command;
	}
}
