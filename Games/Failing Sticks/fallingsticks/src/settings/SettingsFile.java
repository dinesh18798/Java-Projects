package settings;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Dinesh on 08/04/2020
 */
public class SettingsFile {

    private static final String SETTINGS_FILE_DIRECTORY = "FallingSticks";
    private static final String SETTINGS_FILE_NAME = "\\settings.xml";
    private static final String MAIN_SETTING = "Game";
    private static final String LEVEL_SETTING = "GameLevel";
    private static final String SCORE_SETTING = "GameScore";
    private static String filePath = null;
    private static File file;

    public SettingsFile() {
        // create the settings file in temporary folder
        String directory = System.getProperty("java.io.tmpdir") + SETTINGS_FILE_DIRECTORY;
        File tempDirectory = new File(directory);
        if (!tempDirectory.exists()) {
            createDirectory(tempDirectory);
        }
        if (tempDirectory.isDirectory()) {
            filePath = tempDirectory.getPath() + SETTINGS_FILE_NAME;
            file = new File(filePath);
            if (!file.exists())
                updateGameSettings(-1, 0);
        }
    }

    public void readGameSettings(int[] values) {
        int level = -1;
        int score = 0;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Element element = document.getDocumentElement();
            level = getIntValue(element, LEVEL_SETTING);
            score = getIntValue(element, SCORE_SETTING);
        } catch (Exception e) {
            System.out.println("Unable to read game settings");
            e.printStackTrace();
        }
        values[0] = level;
        values[1] = score;
        return;
    }

    public void updateGameSettings(int level, int score) {
        try {
            Document document;
            Element element = null;
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            Element rootEle = document.createElement(MAIN_SETTING);
            element = document.createElement(LEVEL_SETTING);
            element.appendChild(document.createTextNode(String.valueOf(level)));
            rootEle.appendChild(element);
            element = document.createElement(SCORE_SETTING);
            element.appendChild(document.createTextNode(String.valueOf(score)));
            rootEle.appendChild(element);
            document.appendChild(rootEle);

            // save the settings file in XML format
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            tr.transform(new DOMSource(document),
                    new StreamResult(new FileOutputStream(file)));
        } catch (Exception e) {
            System.out.println("Unable to update game settings");
            e.printStackTrace();
        }
    }

    private void createDirectory(File directory) {
        try {
            directory.mkdir();
        } catch (Exception e) {
            System.out.println("Unable to create directory");
            e.printStackTrace();
        }
    }

    private int getIntValue(Element element, String tag) {
        int value = 0;
        NodeList nl;
        nl = element.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
        }
        return value;
    }
}
