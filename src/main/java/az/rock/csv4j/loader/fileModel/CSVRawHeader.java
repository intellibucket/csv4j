package az.rock.csv4j.loader.fileModel;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CSVRawHeader {
    private final Map<Integer,String> indexHeaderValue = new HashMap<>();

    private final String headerLine;

    public static CSVRawHeader of(String headerLine){
        CSVRawHeader csvRawHeader = new CSVRawHeader(headerLine);
        csvRawHeader.prepareHeader();
        return csvRawHeader;
    }


    private CSVRawHeader(String headerLine){
        this.headerLine  = headerLine;
    }

    public void prepareHeader(){
        this.indexHeaderValue.clear();
        String[] headerElements = this.headerLine.split(",");
        IntStream.range(0,headerElements.length).forEach(i-> this.indexHeaderValue.put(i,headerElements[i]));
    }

    @Override
    public String toString() {
        return "CSVRawHeader{" +
                "indexHeaderValue=" + indexHeaderValue +
                '}';
    }
}
