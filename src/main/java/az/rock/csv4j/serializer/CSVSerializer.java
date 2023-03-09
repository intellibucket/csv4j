package az.rock.csv4j.serializer;

import java.io.File;
import java.util.List;

public class CSVSerializer <T> implements Serializer<T>{

    public CSVSerializer(List<T> data, File file){

    }

    public CSVSerializer(List<T> data, String path){

    }

    @Override
    public void write() {

    }
}
