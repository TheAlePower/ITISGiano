import java.util.ArrayList;
import java.util.List;

public class DataParser {
    // The divisor / data size. DO NOT remove the final as this will break everything
    private int divisor;

    private List<Float> rx1 = new ArrayList<>();
    private List<Float> rx2 = new ArrayList<>();

    private List<Float> sdrx1 = new ArrayList<>();
    private List<Float> sdrx2 = new ArrayList<>();

    private List<Float> att = new ArrayList<>();

    private XMLReader data;

    public void pushData(XMLReader data) {
        divisor++;
        this.data = data;

        for (int i = 0; i < data.getDataSize(); i++) {
            if ((rx1.size() - 1) < i) {
                rx1.add(Float.parseFloat(data.RX1getAtValue(i))/divisor);
            } else {
                rx1.set(i,Float.parseFloat(data.RX1getAtValue(i))/divisor);
            }

            if ((rx2.size() - 1) < i) {
                rx2.add(Float.parseFloat(data.RX2getAtValue(i))/divisor);
            } else {
                rx2.set(i,Float.parseFloat(data.RX2getAtValue(i))/divisor);
            }

            if ((sdrx1.size() - 1) < i) {
                sdrx1.add(Float.parseFloat(data.SDRX1getAtValue(i))/divisor);
            } else {
                sdrx1.set(i,Float.parseFloat(data.SDRX1getAtValue(i))/divisor);
            }

            if ((sdrx2.size() - 1) < i) {
                sdrx2.add(Float.parseFloat(data.SDRX2getAtValue(i))/divisor);
            } else {
                sdrx2.set(i,Float.parseFloat(data.SDRX2getAtValue(i))/divisor);
            }

            if ((att.size() - 1) < i) {
                att.add(Float.parseFloat(data.ATTgetAtValue(i))/divisor);
            } else {
                att.set(i,Float.parseFloat(data.ATTgetAtValue(i))/divisor);
            }
        }
    }

    // Getters and setters


    public List<Float> getAtt() {
        return att;
    }

    public List<Float> getRx1() {
        return rx1;
    }

    public List<Float> getRx2() {
        return rx2;
    }

    public List<Float> getSdrx1() {
        return sdrx1;
    }

    public List<Float> getSdrx2() {
        return sdrx2;
    }

    public int getAmountOfParsedXMLs() {
        return divisor;
    }

    public XMLReader getData() {
        return data;
    }
}
