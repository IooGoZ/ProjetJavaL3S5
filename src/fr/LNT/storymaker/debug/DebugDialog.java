package fr.LNT.storymaker.debug;

import java.util.LinkedHashMap;

import fr.LNT.storymaker.kernel.utils.Dialog;
import fr.LNT.storymaker.kernel.utils.builders.DialogBuilder;

public class DebugDialog {

	String text[] = {
			"Comment t'appelles-tu ?",
			"Bnojour Léna !",
			"Bonjour Noriane !",
			"Bonjour Tom !",
			"Tu es bien la bonne personne. Enchanté !",
			"Tu mens, tu n'es pas la bonne personne."
	};
	
	String answer[] = {
			"Léna",
			"Noriane",
			"Tom",
			"Bonjour ^^",
			"Coucou",
			"Holà !",
			"Enchanté !",
			"Je ne vois pas de quoi tu parles..."
	};
	
	public DebugDialog() {
		
		LinkedHashMap<String, String> answer1 = new LinkedHashMap<>();
		answer1.put(answer[0], "2");
		answer1.put(answer[1], "3");
		answer1.put(answer[2], "4");
		
		LinkedHashMap<String, String> answer2 = new LinkedHashMap<>();
		answer2.put(answer[3], "6");
		answer2.put(answer[4], "5");
		answer2.put(answer[5], "6");
		
		LinkedHashMap<String, String> answer3 = new LinkedHashMap<>();
		answer3.put(answer[3], "5");
		answer3.put(answer[4], "6");
		answer3.put(answer[5], "6");
		
		LinkedHashMap<String, String> answer4 = new LinkedHashMap<>();
		answer4.put(answer[3], "6");
		answer4.put(answer[4], "6");
		answer4.put(answer[5], "5");
		
		LinkedHashMap<String, String> answer5 = new LinkedHashMap<>();
		answer5.put(answer[6], null);
		
		LinkedHashMap<String, String> answer6 = new LinkedHashMap<>();
		answer6.put(answer[7], null);
		
		
		DialogBuilder db = new DialogBuilder("1", text[0], answer1, null);
		db.addNode("2", text[1], answer2, null);
		db.addNode("3", text[2], answer3, null);
		db.addNode("4", text[3], answer4, null);
		db.addNode("5", text[4], answer5, null);
		db.addNode("6", text[5], answer6, null);
		
		Dialog dialog = db.buildDialog();
		dialog.execute();
		
		
	}
}
