package ua.com.dog.hotel.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.com.dog.hotel.model.user.UserRole;

public class DomXMLParser {

    public static Map<UserRole, Pattern> parseXML(String xmlFilePath) throws SAXException, IOException, ParserConfigurationException {
        Map<UserRole, Pattern> rolesList = new HashMap<UserRole, Pattern>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFilePath);
        Element root = document.getDocumentElement();
        NodeList items = root.getElementsByTagName("constraint");
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            NodeList urls = item.getElementsByTagName("url-pattern");
            if (urls.getLength() > 0) {
                Node roleNode = item.getElementsByTagName("role").item(0);
                UserRole role = UserRole.valueOf(roleNode.getFirstChild().getNodeValue());
                Pattern pattern = Pattern.compile(urls.item(0).getFirstChild().getNodeValue());
                rolesList.put(role, pattern);
            }
        }
        return rolesList;
    }
}
