public class Core {
    public static void main(String[] args) {
        XML xml = new XML(".\\Data.xml",".\\Data_Processed.xml",1,1,1,1,1);
        xml.process();
    }
}
