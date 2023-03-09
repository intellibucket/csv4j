package az.rock.csv4j.deserializer.loader.fileModel;

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

    @Override
    public String toString() {
        return "CSVFile{" +
                "header=" + header +
                ", pureCSV=" + pureCSV +
                '}';
    }

    public CSVRawHeader getHeader() {
        return header;
    }
}
