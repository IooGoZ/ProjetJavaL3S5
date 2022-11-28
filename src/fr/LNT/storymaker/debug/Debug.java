package fr.LNT.storymaker.debug;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.LNT.storymaker.kernel.utils.xml.GameXMLBuilder;

public class Debug {
	public static void main(String[] args) {
		try {
			new GameXMLBuilder();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
