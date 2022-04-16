
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XML {

    // Warning tresholds
    private float RX1_WARNINGLEVEL = 1;
    private float RX2_WARNINGLEVEL = 1;
    private float SDRX1_WARNINGLEVEL = 1;
    private float SDRX2_WARNINGLEVEL = 1;
    private float ATT_WARNINGLEVEL = 1;

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
    private final String outputFilename;

    public XML(String inputFilename,String outputFilename,float RX1_WARNINGLEVEL,float RX2_WARNINGLEVEL,float SDRX1_WARNINGLEVEL,float SDRX2_WARNINGLEVEL,float ATT_WARNINGLEVEL) {
        // Setting required parameters
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;

        this.RX1_WARNINGLEVEL = RX1_WARNINGLEVEL;
        this.RX2_WARNINGLEVEL = RX2_WARNINGLEVEL;
        this.SDRX1_WARNINGLEVEL = SDRX1_WARNINGLEVEL;
        this.SDRX2_WARNINGLEVEL = SDRX2_WARNINGLEVEL;

        this.ATT_WARNINGLEVEL = ATT_WARNINGLEVEL;

    }

    public void process() {

        try {
            // Deleting previous file; cleans up any corrupted / partial elaboration
            File f = new File(outputFilename);
            f.delete();
            // Reads the XML into objects
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new File(inputFilename));
            doc.getDocumentElement().normalize();

            value = (NodeList) doc.getElementsByTagName("Value");

            RX1 = (NodeList) doc.getElementsByTagName("intensRX1");
            RX2 = (NodeList) doc.getElementsByTagName("intensRX1");
            SDRX1 = (NodeList) doc.getElementsByTagName("SDRX1");
            SDRX2 = (NodeList) doc.getElementsByTagName("SDRX2");
            ATT = (NodeList) doc.getElementsByTagName("Att");

            // Constructs and starts the threads
            XMLThread thr1 = new XMLThread(0, (int) Math.floor(value.getLength()/2f),this);
            XMLThread thr2 = new XMLThread((int) Math.floor(value.getLength()/2f),value.getLength(),this);

            /* Debug purposes */
            /*
            XMLThread thr1 = new XMLThread(0, 300,this);
            XMLThread thr2 = new XMLThread(300,600,this);
            */

            thr1.start();
            thr2.start();

            // Waits for both threads to have finished
            while (!(thr1.isDone() && thr2.isDone())) {}

            // Since file was deleted, we create it anew
            f.createNewFile();

            // One-liner to save the XML into a file
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc),new StreamResult(new FileOutputStream(f)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and setters


    public Document getDoc() {
        return doc;
    }

    public float getATT_WARNINGLEVEL() {
        return ATT_WARNINGLEVEL;
    }

    public float getRX1_WARNINGLEVEL() {
        return RX1_WARNINGLEVEL;
    }

    public float getRX2_WARNINGLEVEL() {
        return RX2_WARNINGLEVEL;
    }

    public float getSDRX1_WARNINGLEVEL() {
        return SDRX1_WARNINGLEVEL;
    }

    public float getSDRX2_WARNINGLEVEL() {
        return SDRX2_WARNINGLEVEL;
    }

    public void setATT_WARNINGLEVEL(float ATT_WARNINGLEVEL) {
        this.ATT_WARNINGLEVEL = ATT_WARNINGLEVEL;
    }

    public void setRX1_WARNINGLEVEL(float RX1_WARNINGLEVEL) {
        this.RX1_WARNINGLEVEL = RX1_WARNINGLEVEL;
    }

    public void setRX2_WARNINGLEVEL(float RX2_WARNINGLEVEL) {
        this.RX2_WARNINGLEVEL = RX2_WARNINGLEVEL;
    }

    public void setSDRX1_WARNINGLEVEL(float SDRX1_WARNINGLEVEL) {
        this.SDRX1_WARNINGLEVEL = SDRX1_WARNINGLEVEL;
    }

    public void setSDRX2_WARNINGLEVEL(float SDRX2_WARNINGLEVEL) {
        this.SDRX2_WARNINGLEVEL = SDRX2_WARNINGLEVEL;
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

    public synchronized void appendAsync(Element e,int i) {
        value.item(i).appendChild(e);
    }
}

class XMLThread extends Thread {
    private int start;
    private int stop;
    private XML xml;
    private boolean done = false;

    public XMLThread(int start, int stop,XML xml) {
        this.start = start;
        this.stop = stop;
        this.xml = xml;
    }

    @Override
    public void run() {
            for (int i = start; i < stop; i++) {
                /* For debug purposes
                if (i%100 == 0) {
                    System.out.println("i is " + i);
                }
                */

                // Creates the elements
                Element rx1 = xml.getDoc().createElement("flag1");
                Element rx2 = xml.getDoc().createElement("flag2");
                Element sdrx1 = xml.getDoc().createElement("flag3");
                Element sdrx2 = xml.getDoc().createElement("flag4");
                Element att = xml.getDoc().createElement("flag5");

                // Appends a boolean value to them
                rx1.appendChild(xml.getDoc().createTextNode(String.valueOf(Float.parseFloat(xml.RX1getAtValue(i)) > xml.getRX1_WARNINGLEVEL())));
                rx2.appendChild(xml.getDoc().createTextNode(String.valueOf(Float.parseFloat(xml.RX2getAtValue(i)) > xml.getRX2_WARNINGLEVEL())));
                sdrx1.appendChild(xml.getDoc().createTextNode(String.valueOf(Float.parseFloat(xml.SDRX1getAtValue(i)) > xml.getSDRX1_WARNINGLEVEL())));
                sdrx2.appendChild(xml.getDoc().createTextNode(String.valueOf(Float.parseFloat(xml.SDRX2getAtValue(i)) > xml.getSDRX2_WARNINGLEVEL())));
                att.appendChild(xml.getDoc().createTextNode(String.valueOf(Float.parseFloat(xml.ATTgetAtValue(i)) > xml.getATT_WARNINGLEVEL())));

                // Appends the elements to the doc itself
                xml.appendAsync(rx1,i);
                xml.appendAsync(rx2,i);
                xml.appendAsync(sdrx1,i);
                xml.appendAsync(sdrx2,i);
                xml.appendAsync(att,i);
            }

            // Sets thread as "concluded" so the main method can continue
            done = true;
    }

    public synchronized boolean isDone() {
        return done;
    }
}