
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLReader {

    // The document itself
    private Document doc;

    // Node lists of all the various values, with the general value set as the first list
    private NodeList value;
    private NodeList RX1;
    private NodeList RX2;
    private NodeList SDRX1;
    private NodeList SDRX2;
    private NodeList ATT;

    // The two filenames needed to load and save the data
    private final String inputFilename;

    public XMLReader(String inputFilename) {
        // Setting required parameters
        this.inputFilename = inputFilename;
    }

    public Thread reload() throws ParserConfigurationException, IOException, SAXException {
        // Reads the XML into objects
        Thread runner = new Thread() {
            @Override
            public void run() {
                DocumentBuilder db = null;
                try {
                    db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                try {
                    doc = db.parse(new File(inputFilename));
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                doc.getDocumentElement().normalize();


                value = (NodeList) doc.getElementsByTagName("Value");

                RX1 = (NodeList) doc.getElementsByTagName("intensRX1");
                RX2 = (NodeList) doc.getElementsByTagName("intensRX2");
                SDRX1 = (NodeList) doc.getElementsByTagName("SDRX1");
                SDRX2 = (NodeList) doc.getElementsByTagName("SDRX2");
                ATT = (NodeList) doc.getElementsByTagName("Att");
            }
        };

        return runner;
    }

    // Getters and setters

    public String getInputFilename() {
        return inputFilename;
    }

    public Document getDoc() {
        return doc;
    }

    // Async getters

    public synchronized String RX1getAtValue(int i) {
        return RX1.item(i).getTextContent();
    }

    public synchronized String RX2getAtValue(int i) {
        return RX2.item(i).getTextContent();
    }

    public synchronized String SDRX1getAtValue(int i) {
        return SDRX1.item(i).getTextContent();
    }

    public synchronized String SDRX2getAtValue(int i) {
        return SDRX2.item(i).getTextContent();
    }

    public synchronized String ATTgetAtValue(int i) {
        return ATT.item(i).getTextContent();
    }

    public synchronized int getDataSize() {
        return ((NodeList) doc.getElementsByTagName("Value")).getLength();
    }
}