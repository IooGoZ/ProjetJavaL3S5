package fr.LNT.storymaker.kernel.utils;

import java.util.Scanner;

public class Dialog extends TreeObject {

	private static Scanner sc = new Scanner(System.in);
	
	public Dialog(DialogNode starter_node) {
		super(starter_node);
	}
	
	
	public int getAnswers(int maxId) {
		int res = DialogNode.ANSWER_MIN-1;
		do {
			try {
				System.out.println("Wait an answer : ");
				res = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException  e) {
				System.out.println("Wrong answer.");
			}
		} while (res < DialogNode.ANSWER_MIN || res > maxId+DialogNode.ANSWER_MIN);
		return res;
	}
	
	public void execute() {
		boolean loop;
		do {
			DialogNode node = (DialogNode) super.current_node;
			node.printNode();
			loop = !isEndOfTree();
			
			if (node.getMaxChildrensIndex() > 0) {
				int answer_id = node.answerToChild(getAnswers(node.getMaxChildrensIndex()));
				moveToChild(answer_id);
			} else if (node.getMaxChildrensIndex() == 0) {
				moveToChild(node.getMaxChildrensIndex());
			}
		} while(loop);
	}
}