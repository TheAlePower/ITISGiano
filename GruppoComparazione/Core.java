import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.LocalDateTime;
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

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        init();
        // Argument parser
        // Defining *what* we need to have
        String outputPath = "";
        List<String> inputPaths = new ArrayList<>();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                // Checking if argument is input and if there is any actual input
                if ((args[i].equalsIgnoreCase("--in") || (args[i].equalsIgnoreCase("--input")) && args.length < i + 1)) {
                    inputPaths = Arrays.asList(args[i + 1]);
                }
                if ((args[i].equalsIgnoreCase("--out") || (args[i].equalsIgnoreCase("--output")) && args.length < i + 1)) {
                    outputPath = args[i + i];
                }
            }
        }

        if (inputPaths.size() < 1 || outputPath.equals("")) {
            writeLog("Error : incorrect parameters. Check section B of the manual for info");
            return;
        }



        List<XMLReader> files = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < inputPaths.size(); i++) {
            writeLog("reading path " + inputPaths.get(i));
            XMLReader reader = new XMLReader(inputPaths.get(i));
            writeLog("the path has been read correctly, " + reader.getDataSize() + " data sequences");
            files.add(reader);
            reader.reload();
        }

        for (int i = 0; i < threads.size(); i++) {
            writeLog("waiting for thread " + i + " to finish");
            while (threads.get(i).isAlive()) {}
        }

        writeLog("read phase completed, starting compare phase");
        DataParser dp = new DataParser();
        

        for (int i = 0; i < files.size(); i++) {
            dp.pushData(files.get(i));
        }

        writeLog("compare phase completed, starting output phase");
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(dp,outputPath);
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
