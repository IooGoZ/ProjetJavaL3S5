package fr.LNT.storymaker.kernel.utils;

import java.util.Scanner;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.commands.Sender;

public class Dialog extends TreeObject implements Sender{

	private static final Scanner sc = Game.stdinScanner;
	
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