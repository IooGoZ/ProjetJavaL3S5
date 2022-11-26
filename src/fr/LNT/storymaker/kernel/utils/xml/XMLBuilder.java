package fr.LNT.storymaker.kernel.utils.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

//Inspiré par : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

public class XMLBuilder {

	private static final String DEFAULT_XML_PATHNAME = "test.xml";

	private Document xml;

	public XMLBuilder(File file) throws ParserConfigurationException, SAXException, IOException {
		if (file == null) {
			URL url = getClass().getResource(DEFAULT_XML_PATHNAME);
			file = new File(url.getPath());
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		xml = db.parse(file);
		xml.getDocumentElement().normalize();

		Node dialog = xml.getElementsByTagName("dialog").item(0);
		try {
			DialogXMLBuilder dxb = new DialogXMLBuilder(dialog);
			dxb.buildDialog().execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public XMLBuilder() throws ParserConfigurationException, SAXException, IOException {
		this(null);
	}

}
