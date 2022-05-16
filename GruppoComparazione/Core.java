import logger.LightweightLogger;
import memgroup.ListaDiListe;
import memgroup.Nodo;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static logger.LightweightLogger.init;
import static logger.LightweightLogger.writeLog;

public class Core {
    private static float rx1_tollerance = 0.75f;
    private static float rx2_tollerance = 0.75f;
    private static float sdrx1_tollerance = 0.75f;
    private static float sdrx2_tollerance = 0.75f;

    private static float att_tollerance = 0.75f;

    private static ListaDiListe ldl;
    private static String outputPath = "./data";


    // Initializing memory group data structure
    public static void memgroupInit() {
        ldl = new ListaDiListe();
        ldl.createList();
        ldl.RiempiLista(ldl.getHead());

        Nodo n = ldl.getHead();
        Nodo innerNode;

        while (n != null) {
            innerNode = n;
            while (innerNode != null) {
                innerNode = innerNode.getNextSublist();
            }
            n = n.getNextList();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        init();
        writeLog("Working directory : " + new File("./").getAbsolutePath());
        writeLog("Initializing Group II - Memory data structure...");
        memgroupInit();
        writeLog("Data structure initialized. " + listDimension(ldl) + " groups present");

        writeLog("Executing Group III - Comparison procedure...");

        // Argument parser
        // Defining *what* we need to have

        if (new File(outputPath).exists()) {
            writeLog("output path exists");
        } else {
            writeLog("output path missing, creating anew...");
            new File(outputPath).mkdir();
        }

        // Task loop, loops for each list and works with that
        Nodo currentSet = ldl.getHead();

        while (currentSet != null) {
            writeLog("Saving station " + currentSet.getStazione());
            saveFile(currentSet);
            currentSet = currentSet.getNextList();
        }

        writeLog("Task completed");
    }

    // Vertical list size
    public static int listDimension(ListaDiListe ldl) {
        int size = 0;
        Nodo start = ldl.getHead();
        while (start != null) {
            size++;
            start = start.getNextList();
        }
        return size;
    }

    // Horizontal list size
    public static int sublistDimension(Nodo node) {
        int size = 0;
        Nodo start = ldl.getHead();
        while (start != null) {
            size++;
            start = start.getNextSublist();
        }
        return size;
    }

    public static void saveFile(Nodo inputNode) {
        List<XMLReader> yesterdayReaders = new ArrayList<>();
        List<Thread> yesterdayThreads = new ArrayList<>();

        List<XMLReader> dyesterdayReaders = new ArrayList<>();
        List<Thread> dyesterdayThreads = new ArrayList<>();

        Nodo node = inputNode;

        for (int i = 0; i < sublistDimension(node); i++) {
            XMLReader yesterday = new XMLReader(node.getIeri());
            XMLReader dyesterday = new XMLReader(node.getAltroieri());

            try {
                yesterdayThreads.add(yesterday.reload());
                dyesterdayThreads.add(dyesterday.reload());
            } catch (Exception e) {
                writeLog("---------------------------------------------------------------------------");
                writeLog("An unrecoverable exception has occurred and the program must be terminated");
                writeLog(e.getMessage());
                writeLog("This error occurred inside the saveFile method, details of variables follow");
                writeLog("Node : " + node.getStazione());
                writeLog("Lenght of node : " + sublistDimension(node));
                writeLog("---------------------------------------------------------------------------");
            }

            yesterdayReaders.add(yesterday);
            dyesterdayReaders.add(dyesterday);

            node = node.getNextSublist();
        }

        for (int i = 0; i < yesterdayThreads.size(); i++) {
            while (yesterdayThreads.get(i).isAlive());
        }
        for (int i = 0; i < dyesterdayThreads.size(); i++) {
            while (dyesterdayThreads.get(i).isAlive());
        }

        DataParser yesterdayParser = new DataParser(yesterdayReaders.size());
        DataParser dyesterdayParser = new DataParser(dyesterdayReaders.size());



        while (yesterdayReaders.size() > 0)  {
            writeLog("Pushing data of " + yesterdayReaders.get(0).getInputFilename());
            yesterdayParser.pushData(yesterdayReaders.get(0));
            yesterdayReaders.remove(0);
            System.gc();
        }

        while (dyesterdayReaders.size() > 0) {
            writeLog("Pushing data of " + dyesterdayReaders.get(0).getInputFilename());
            dyesterdayParser.pushData(dyesterdayReaders.get(0));
            dyesterdayReaders.remove(0);
            System.gc();
        }

        try {
            XMLWriter.write(yesterdayParser,outputPath + "/" + new File(inputNode.getIeri()).getName());
            XMLWriter.write(dyesterdayParser,outputPath + "/" + new File(inputNode.getAltroieri()).getName());
        } catch (Exception e) {
            writeLog("---------------------------------------------------------------------------");
            writeLog("An unrecoverable exception has occurred and the program must be terminated");
            writeLog(e.getMessage());
            writeLog("This error occurred inside the saveFile method, details of variables follow");
            writeLog("Node : " + node.getStazione());
            writeLog("Lenght of node : " + sublistDimension(node));

            writeLog("Size of yesterday readers : " + yesterdayReaders.size());
            writeLog("Size of d yesterday readers : " + dyesterdayReaders.size());


            writeLog("Size of yesterday parser : " + yesterdayParser.getAmountOfParsedXMLs());
            writeLog("Size of d yesterday parser : " + dyesterdayParser.getAmountOfParsedXMLs());

            writeLog("---------------------------------------------------------------------------");
        }

    }

    // Getters and setters

    public static float getAtt_tollerance() {
        return att_tollerance;
    }

    public static float getRx1_tollerance() {
        return rx1_tollerance;
    }

    public static float getRx2_tollerance() {
        return rx2_tollerance;
    }

    public static float getSdrx1_tollerance() {
        return sdrx1_tollerance;
    }

    public static float getSdrx2_tollerance() {
        return sdrx2_tollerance;
    }

}
