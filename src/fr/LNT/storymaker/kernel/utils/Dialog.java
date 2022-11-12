package fr.LNT.storymaker.kernel.utils;

import java.util.Scanner;

public class Dialog extends TreeObject {

	private static final Scanner sc = new Scanner(System.in);
	
	public Dialog(DialogNode starter_node) {
		super(starter_node);
	}
	
	
	public int getAnswers(int maxId) {
		int res;
		do {
			System.out.println("Wait an answer : ");
			res = sc.nextInt();
			System.out.println(res);
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