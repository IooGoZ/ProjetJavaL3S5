package fr.LNT.storymaker.kernel.utils.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.LNT.storymaker.kernel.utils.NodeObject;

public abstract class TreeBuilder {

	protected HashMap<Object, List<Object>> obj2lst = new HashMap<>();

	protected Object starter_object;

	protected TreeBuilder(Object starter_object) {
		this.obj2lst.put(starter_object, new ArrayList<>());
		this.starter_object = starter_object;
	}

	protected void addNode(Object obj) {
		obj2lst.put(obj, new ArrayList<>());
	}

	protected void createUnilink(Object from, Object to) {
		if (obj2lst.containsKey(to) && obj2lst.containsKey(to)) {
			obj2lst.get(from).add(to);
		} else {
			System.err.println("createUnilink error : try to link unregistred object.");
			System.exit(-1);
		}
	}

	protected void createBilink(Object obj1, Object obj2) {
		if (obj2lst.containsKey(obj1) && obj2lst.containsKey(obj2)) {
			obj2lst.get(obj1).add(obj2);
			obj2lst.get(obj2).add(obj1);
		} else {
			System.err.println("createBilink error : try to link unregistred object.");
			System.exit(-1);
		}
	}

	protected NodeObject build() {
		HashMap<Object, NodeObject> obj2node = new HashMap<>();
		for (Object obj : obj2lst.keySet()) {
			obj2node.put(obj, new NodeObject(obj));
		}

		for (Object parent_obj : obj2lst.keySet()) {
			List<Object> childs_obj = obj2lst.get(parent_obj);
			for (Object child : childs_obj) {
				obj2node.get(parent_obj).addChildren(obj2node.get(child));
			}
		}

		return obj2node.get(this.starter_object);
	}

}