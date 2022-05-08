import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static logger.LightweightLogger.writeLog;

public class XMLWriter {

    private Document document;

    public void write(DataParser dp,String outputPath) throws ParserConfigurationException, TransformerException, IOException {
        // Init phase
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        this.document = doc;
        Node node = doc.createElement("Values");
        doc.appendChild(node);


        File outputFile = new File(outputPath);
        writeLog("was an old file existing at the output path > " + outputFile.delete());
        writeLog("has new file been created correctly > " + outputFile.createNewFile());

        Node date = doc.createElement("date");

        String dateValue = "";
        for (int i = 2; i < 8; i++) { dateValue += new File(dp.getData().getInputFilename()).getName().toCharArray()[i]; }
        Text text = doc.createTextNode(dateValue);

        date.appendChild(text);
        node.appendChild(date);

        for (int i = 0; i < dp.getRx1().size(); i++) {
            node.appendChild(generateNode(dp.getRx1().get(i),dp.getRx2().get(i),dp.getSdrx1().get(i),dp.getSdrx2().get(i),dp.getAtt().get(i),doc));
        }

        // Write phase

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");

        transformer.transform(new DOMSource(doc), new StreamResult(outputFile));

    }

    public static Node generateNode(float rx1, float rx2, float sdrx1, float sdrx2, float attendibility, Document doc) {
        Element root = doc.createElement("Value");

        // Flags

        Node RX1 = doc.createElement("intensRX1");
        Node RX2 = doc.createElement("intensRX2");
        Node SDRX1 = doc.createElement("SDRX1");
        Node SDRX2 = doc.createElement("SDRX2");
        Node Att = doc.createElement("Att");

        RX1.appendChild(doc.createTextNode(String.valueOf(rx1)));
        RX2.appendChild(doc.createTextNode(String.valueOf(rx2)));
        SDRX1.appendChild(doc.createTextNode(String.valueOf(sdrx1)));
        SDRX2.appendChild(doc.createTextNode(String.valueOf(sdrx2)));

        Att.appendChild(doc.createTextNode(String.valueOf(attendibility)));

        // Flags

        Node noderx1 = doc.createElement("flagV1");
        Node noderx2 = doc.createElement("flagV2");
        Node nodesdrx1 = doc.createElement("flagV3");
        Node nodesdrx2 = doc.createElement("flagV4");
        Node nodeatt = doc.createElement("flagV5");

        noderx1.appendChild(doc.createTextNode(String.valueOf(rx1 >= Core.getRx1_tollerance())));
        noderx2.appendChild(doc.createTextNode(String.valueOf(rx2 >= Core.getRx2_tollerance())));
        nodesdrx1.appendChild(doc.createTextNode(String.valueOf(sdrx1 >= Core.getSdrx1_tollerance())));
        nodesdrx2.appendChild(doc.createTextNode(String.valueOf(sdrx2 >= Core.getSdrx2_tollerance())));
        nodeatt.appendChild(doc.createTextNode(String.valueOf(attendibility >= Core.getAtt_tollerance())));

        // Append

        root.appendChild(RX1);
        root.appendChild(RX2);
        root.appendChild(SDRX1);
        root.appendChild(SDRX2);
        root.appendChild(Att);

        root.appendChild(noderx1);
        root.appendChild(noderx2);
        root.appendChild(nodesdrx1);
        root.appendChild(nodesdrx2);
        root.appendChild(nodeatt);

        return root;
    }

    // Getters and setters


}
