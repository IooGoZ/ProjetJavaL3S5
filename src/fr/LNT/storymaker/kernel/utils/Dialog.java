package fr.LNT.storymaker.kernel.utils;

import java.util.Scanner;

import fr.LNT.storymaker.kernel.Game;
import fr.LNT.storymaker.kernel.commands.Sender;

/**
 * Represent a dialog
 * @author LNT
 *
 */
public class Dialog extends TreeObject implements Sender {

	private static final Scanner sc = Game.stdinScanner;

	/**
	 * Constructor of class Dialog
	 * @param starter_node Node which start the tree
	 */
	public Dialog(DialogNode starter_node) {
		super(starter_node);
	}

	/**
	 * Convert an answer to an id
	 * @param maxId
	 * @return
	 */
	public int getAnswers(int maxId) {
		int res = DialogNode.ANSWER_MIN - 1;
		do {
			try {
				System.out.println("Wait an answer : ");
				res = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Wrong answer.");
			}
		} while (res < DialogNode.ANSWER_MIN || res > maxId + DialogNode.ANSWER_MIN);
		return res;
	}

	/**
	 * Run the dialog
	 * @param game game instance
	 */
	public void execute(Game game) {
		boolean loop;
		do {
			DialogNode node = (DialogNode) super.current_node;
			
			if (node.getCommand()!=null)
				game.executeCommand(this, node.getCommand());
			
			node.printNode();
			loop = !isEndOfTree();

			if (node.getChildrensLenght() != 0) {
				int answer_id = node.answerToChild(getAnswers(node.getChildrensLenght()-1));
				moveToChild(answer_id);
			}
		} while (loop);
	}
}