package az.rock.csv4j.inspector;

import az.rock.csv4j.deserializer.loader.fileModel.CSVRawHeader;
import az.rock.csv4j.deserializer.loader.fileModel.CSVRawLine;

import java.util.HashMap;
import java.util.Map;

public class HeaderValueTaker {
    private final CSVRawHeader csvRawHeader;


    public HeaderValueTaker(CSVRawHeader csvRawHeader){
        this.csvRawHeader = csvRawHeader;

    }

    public Map<String, String> nextLine(CSVRawLine csvRawLine){
        Map<String, String> map = new HashMap<>();
        var indexHeaderValueMap = csvRawHeader.getIndexHeaderValue();
        var rawLineMap = csvRawLine.getCsvRawLineMap();
        var headerKeyIndexSet = indexHeaderValueMap.keySet();
        for (Integer index: headerKeyIndexSet){
            map.put(indexHeaderValueMap.get(index),rawLineMap.get(index).getRawValue());
        }
        return map;
    }


}
