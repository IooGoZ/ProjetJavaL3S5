package fr.LNT.storymaker.kernel.utils.tests;

import org.junit.Test;

import fr.LNT.storymaker.kernel.utils.NodeObject;
import fr.LNT.storymaker.kernel.utils.builders.TreeBuilder;

import static org.junit.Assert.*;

import org.junit.Before;

public class TestTreeBuilder {
	
	private String obj1, obj2, obj3, obj4, obj5;

	@Before
	public void setUp() {
		obj1 = "1";
		obj2 = "2";
		obj3 = "3";
		obj4 = "4";
		obj5 = "5";
	}
	
	@Test
	public void testFirstNode() {
		TreeBuilder tb = new TreeBuilder(obj1);
		tb.addNode(obj2);
		tb.addNode(obj3);
		tb.addNode(obj4);
		tb.addNode(obj5);
		tb.createBilink(obj1, obj2);
		tb.createUnilink(obj1, obj3);
		tb.createUnilink(obj2, obj3);
		tb.createUnilink(obj3, obj4);
		tb.createBilink(obj2, obj5);
		tb.createUnilink(obj4, obj1);
		assertSame(tb.build().getNodeValue(), obj1);
	}
	
	@Test
	public void testChildrenLenghtNode() {
		TreeBuilder tb = new TreeBuilder(obj1);
		tb.addNode(obj2);
		tb.addNode(obj3);
		tb.addNode(obj4);
		tb.addNode(obj5);
		tb.createBilink(obj1, obj2);
		tb.createUnilink(obj1, obj3);
		tb.createUnilink(obj2, obj3);
		tb.createUnilink(obj3, obj4);
		tb.createBilink(obj2, obj5);
		tb.createUnilink(obj4, obj1);
		NodeObject tree = tb.build();
		int len = tree.getChildrensLenght();
		assertTrue(len == 2);
	}
	
	
}
