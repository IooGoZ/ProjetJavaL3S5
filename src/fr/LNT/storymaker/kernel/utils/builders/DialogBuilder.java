package fr.LNT.storymaker.kernel.utils.builders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.LNT.storymaker.kernel.utils.Dialog;
import fr.LNT.storymaker.kernel.utils.DialogNode;
import fr.LNT.storymaker.kernel.utils.NodeObject;

public class DialogBuilder extends TreeBuilder {

	private HashMap<String, String> id2text = new HashMap<>();
	private HashMap<String, String> id2command = new HashMap<>();
	private HashMap<String, LinkedHashMap<String, String>> id2answers = new HashMap<>();
	
	public DialogBuilder(String id, String text, LinkedHashMap<String, String> answers, String command) {
		super(id);
		id2text.put(id, text);
		id2answers.put(id, answers);
		id2command.put(id, command);
	}
	
	
	public void addNode(String id, String text, LinkedHashMap<String, String> answers, String command) {
		super.addNode(id);
		id2text.put(id, text);
		id2answers.put(id, answers);
		id2command.put(id, command);
	}
	
	public Dialog buildDialog() {
		HashMap<Object, NodeObject> obj2node = new HashMap<>();
		
		for (String id : id2answers.keySet()) {
			HashMap<String, String> answers = id2answers.get(id);
			for (String answer : answers.keySet()) {
				String child_id = answers.get(answer);
				if (child_id != null) super.createUnilink(id, child_id);
			}
		}
		
		for (Object obj : super.obj2lst.keySet()) {
			String id = (String) obj;
			obj2node.put(id, new DialogNode(id, id2text.get(id), id2answers.get(id), id2command.get(id)));
		}
		
		for (Object parent_obj : obj2lst.keySet()) {
			List<Object> childs_obj = obj2lst.get(parent_obj);
			for (Object child : childs_obj) {
				obj2node.get(parent_obj).addChildren(obj2node.get(child));
			}
		}
		
		return new Dialog((DialogNode) obj2node.get(super.starter_object));
	}
	
	
	
}
