package fr.LNT.storymaker.kernel.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DialogNode extends NodeObject {

	private final String text;
	private final LinkedHashMap<String, String> answers;
	private final String command;
	
	private static final String NEW_LINE = "\n";
	private static final String ANSWER_SEPARATOR = " = ";
	public static final int ANSWER_MIN = 1;
	
	private static final int ANSWER_TO_CHILD_ERROR = -1;
	
	public DialogNode(String id, String text, LinkedHashMap<String, String> answers, String command) {
		super(id);
		
		this.text = text;
		this.answers = answers;
		this.command = command;
		
	}
	
	
	
	public int answerToChild(int answer) {
		List<String> answerTarget = new ArrayList<>(answers.values());
		String child_id = answerTarget.get(answer - ANSWER_MIN);
		
		int i = 0;
		for (NodeObject child : super.childrens) {
			if (child.getNodeValue().equals(child_id)) return i;
			i++;
		}
		
		return ANSWER_TO_CHILD_ERROR;
	}
	
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
	
	public void printNode() {
		System.out.println(toString());
	}
	
	@Override
	public DialogNode getChildren(int id) {
		return (DialogNode) super.getChildren(id);
	}
	
	@Override
	public String getNodeValue() {
		return (String) node_value;
	}
	
	public void addChildren(DialogNode child) {
		super.addChildren(child);
	}



	public String getCommand() {
		return command;
	}
}
