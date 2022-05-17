import memgroup.ListaDiListe;
import memgroup.Nodo;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static String outputPath = "./grafica";

    public static HashMap<String, String> names = new HashMap<>();

    // Initializing naming scheme
    public static void namesInit() {
        names.put("XI","bs.xml");
        // names.put("","no.xml"); // nove
        names.put("WM","m.xml");
        names.put("WE","ap.xml");
        // names.put("","v.xml"); // verona
        // names.put("","dg.xml"); // desenzano
        names.put("WD","sr.xml");
        //names.put("","ms.xml"); // massa
        names.put("WG","al.xml");
        //names.put("","pn.xml"); // pontendera
    }

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
                writeLog(innerNode.getIeri());
                innerNode = innerNode.getNextSublist();
            }
            n = n.getNextList();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        init();
        writeLog("GIANO II - Comparator (Groups II & III) starting up!");
        writeLog("Working directory : " + new File("./").getAbsolutePath());
        writeLog("Initializing Group II - Memory data structure...");
        memgroupInit();
        namesInit();
        writeLog("Data structure initialized. " + listDimension(ldl) + " groups present");

        writeLog("Executing Group III - Comparison procedure...");

        // Argument parser
        // Defining *what* we need to have

        if (new File(outputPath).exists()) {
            writeLog("Output path exists...");
        } else {
            writeLog("Output path missing, creating anew...");
            new File(outputPath).mkdir();
        }



        writeLog("Saving meshed stations...");
        // Task loop, loops for each list and works with that
        Nodo n = ldl.getHead();
        Nodo innerNode;

        while (n != null) {
            innerNode = n;
            while (innerNode != null) {
                writeLog("Saving " + innerNode.getStazione());
                saveMeshedFile(innerNode.getIeri());
                innerNode = innerNode.getNextSublist();
            }
            n = n.getNextList();
        }

        writeLog("Meshed stations saved. Saving merged stations...");

        Nodo currentSet = ldl.getHead();

        while (currentSet != null) {
            writeLog("Saving station " + currentSet.getStazione());
            saveFile(currentSet);
            currentSet = currentSet.getNextList();
        }

        writeLog("Task completed");
    }

    // Vertical list size
    public static int listDimension(ListaDiListe list) {
        int size = 0;
        Nodo start = list.getHead();
        while (start != null) {
            size++;
            start = start.getNextList();
        }
        return size;
    }

    // Horizontal list size
    public static int sublistDimension(Nodo node) {
        int size = 0;
        Nodo start = node;
        while (start != null) {
            size++;
            start = start.getNextSublist();
        }
        return size;
    }


    public static void saveMeshedFile(String inputFile) {
        XMLReader reader = new XMLReader(inputFile);
        Thread thread = null;
        try {
            thread = reader.load();
        } catch (Exception e) {
            writeLog("---------------------------------------------------------------------------");
            writeLog("A RECOVERABLE error occurred, details follow");
            writeLog(e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                writeLog(elem.toString());
            }
            writeLog("This error occurred inside the saveFile method, details of variables follow");
            writeLog("Input file > " + inputFile);
            writeLog("---------------------------------------------------------------------------");
            return;
        }

        while (thread.isAlive()) {}

        writeLog("Pushing...");
        DataParser dataParser = new DataParser(1);
        dataParser.pushData(reader);

        String output = names.containsKey(String.valueOf(inputFile.toCharArray()[0]) + inputFile.toCharArray()[1])?outputPath + "/" + names.get(String.valueOf(inputFile.toCharArray()[0]) + inputFile.toCharArray()[1]):outputPath + "/" + new File(inputFile).getName();
        try {
            writeLog("Writing...");
            XMLWriter.write(dataParser,output);
        } catch (Exception e) {
            writeLog("---------------------------------------------------------------------------");
            writeLog("A RECOVERABLE error occurred, details follow");
            writeLog(e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                writeLog(elem.toString());
            }
            writeLog("This error occurred inside the saveFile method, details of variables follow");
            writeLog("Input file > " + inputFile);
            writeLog("Output File > " + output);
            writeLog("---------------------------------------------------------------------------");
            return;
        }
    }

    // Save file via inputNode
    public static void saveFile(Nodo inputNode) {

        /*
        if (inputNode.getStazione() == null || inputNode.getIeri() == null || inputNode.getAltroieri() == null || inputNode.getNextList() == null || inputNode.getNextSublist() == null) {
            writeLog("---------------------------------------------------------------------------");
            writeLog("RECOVERABLE error occurred, skipping current elaboration. Details follow");
            writeLog("Error > one of inputNode's components is null");
            writeLog("getStazione() > " + inputNode.getStazione());
            writeLog("getIeri() > " + inputNode.getIeri());
            writeLog("getAltroieri() > " + inputNode.getAltroieri());
            writeLog("getNextList() > " + inputNode.getNextList());
            writeLog("getNextSublist > " + inputNode.getNextSublist());
            writeLog("---------------------------------------------------------------------------");
            return;
        }
        */

        List<XMLReader> yesterdayReaders = new ArrayList<>();
        List<Thread> yesterdayThreads = new ArrayList<>();

        List<XMLReader> dyesterdayReaders = new ArrayList<>();
        List<Thread> dyesterdayThreads = new ArrayList<>();

        Nodo node = inputNode;

        for (int i = 0; i < sublistDimension(node); i++) {
            if (node == null) {
                writeLog("---------------------------------------------------------------------------");
                writeLog("RECOVERABLE error occurred, skipping current elaboration. Details follow");
                writeLog("Error > malformed node");
                writeLog("---------------------------------------------------------------------------");
                return;
            }
            writeLog("[" + node.getStazione() + "] Creating Readers...");
            writeLog("[" + node.getStazione() + "] Ieri > " + node.getIeri());
            writeLog("[" + node.getStazione() + "] Altroieri > " + node.getAltroieri());

            XMLReader yesterday = new XMLReader(node.getIeri());
            XMLReader dyesterday = new XMLReader(node.getAltroieri());

            try {
                yesterdayThreads.add(yesterday.load());
                dyesterdayThreads.add(dyesterday.load());
            } catch (Exception e) {
                writeLog("---------------------------------------------------------------------------");
                writeLog("RECOVERABLE error occurred, skipping current elaboration. Details follow");
                writeLog(e.getMessage());
                for (StackTraceElement elem : e.getStackTrace()) {
                    writeLog(elem.toString());
                }
                writeLog("This error occurred inside the saveFile method, details of variables follow");
                writeLog("Node : " + node.getStazione());
                writeLog("Lenght of node : " + sublistDimension(node));
                writeLog("Ieri : " + node.getIeri());
                writeLog("Altroieri : " + node.getAltroieri());
                writeLog("---------------------------------------------------------------------------");
                return;
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
            writeLog("[" + node.getStazione() + "] Pushing data of " + yesterdayReaders.get(0).getInputFilename());
            yesterdayParser.pushData(yesterdayReaders.get(0));
            yesterdayReaders.remove(0);
            System.gc();
        }

        while (dyesterdayReaders.size() > 0) {
            writeLog("[" + node.getStazione() + "] Pushing data of " + dyesterdayReaders.get(0).getInputFilename());
            dyesterdayParser.pushData(dyesterdayReaders.get(0));
            dyesterdayReaders.remove(0);
            System.gc();
        }



        try {
            XMLWriter.write(yesterdayParser,outputPath + "/mesh_" + new File(inputNode.getIeri()).getName());
            XMLWriter.write(dyesterdayParser,outputPath + "/mesh_" + new File(inputNode.getAltroieri()).getName());
        } catch (Exception e) {
            writeLog("---------------------------------------------------------------------------");
            writeLog("RECOVERABLE error occurred, skipping current elaboration. Details follow");
            writeLog(e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                writeLog(elem.toString());
            }
            writeLog("This error occurred inside the saveFile method, details of variables follow");
            writeLog("Node : " + node.getStazione());
            writeLog("Lenght of node : " + sublistDimension(node));
            writeLog("Ieri : " + node.getIeri());
            writeLog("Altroieri : " + node.getAltroieri());
            writeLog("---------------------------------------------------------------------------");

            writeLog("Size of yesterday readers : " + yesterdayReaders.size());
            writeLog("Size of d yesterday readers : " + dyesterdayReaders.size());


            writeLog("Size of yesterday parser : " + yesterdayParser.getAmountOfParsedXMLs());
            writeLog("Size of d yesterday parser : " + dyesterdayParser.getAmountOfParsedXMLs());

            writeLog("---------------------------------------------------------------------------");
            return;
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
