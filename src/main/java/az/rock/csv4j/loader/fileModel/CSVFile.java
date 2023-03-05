package az.rock.csv4j.loader.fileModel;

import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    private CSVRawHeader header;
    private List<CSVRawLine> pureCSV = new ArrayList<>();

    public void setHeader(CSVRawHeader header) {
        this.header = header;
    }


    public List<CSVRawLine> getPureCSV() {
        return pureCSV;
    }

    public boolean addLine(CSVRawLine rawLine){
        return this.pureCSV.add(rawLine);
    }
}
