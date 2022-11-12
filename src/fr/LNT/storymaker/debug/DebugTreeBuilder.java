package fr.LNT.storymaker.debug;

import fr.LNT.storymaker.kernel.utils.TreeObject;
import fr.LNT.storymaker.kernel.utils.builders.TreeBuilder;

public class DebugTreeBuilder extends TreeBuilder {
	
	private static String[] tab = {"Zero", "One", "Two", "Three"};
	
	public DebugTreeBuilder() {
		
		super(tab[0]);
		for (int i = 1; i < tab.length; i++)
			super.addNode(tab[i]);
		
		super.createBilink(tab[0], tab[1]);// 0 <-> 1
		super.createUnilink(tab[0], tab[2]);// 0 -> 2
		super.createBilink(tab[1], tab[2]);// 1 <-> 2
		super.createUnilink(tab[2], tab[3]);// 2 -> 3
		
		TreeObject node = super.build();
		
		System.out.println(node.getCurrentValue());
		
	}
}
