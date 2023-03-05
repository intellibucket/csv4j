package az.rock.csv4j.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LineReader<T> {

    private final String header;
    private final T pojo;

    public LineReader(String header,T pojo){
        this.pojo  = pojo;
        this.header = header;
    }

    public T mapLine(String line){
        this.setStatePojo(line);
        return this.pojo;
    }

    private synchronized void setStatePojo(String line){
        POJOFieldPool<T> pojoFieldPool = new POJOFieldPool<>(this.header,this.pojo);
        var headerValueFieldContainer = pojoFieldPool.getContainer();
        var csvColumnValueMap = this.prepareColumnValue(line);
        var csvHeaderValueSet  = csvColumnValueMap.keySet();
        for (String csvHeaderValue: csvHeaderValueSet){
            Field field = headerValueFieldContainer.get(csvHeaderValue).getField();
            try {
                field.setAccessible(true);
                field.set(this.pojo,csvColumnValueMap.get(csvHeaderValue).getValue());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }finally {
                field.setAccessible(false);
            }
        }
    }


    private  Map<String, CSVFieldReference> prepareColumnValue(String line){
        final Map<String, CSVFieldReference> columnValue = new HashMap<>();
        String[] separatedHeader = this.header.split(",");
        String[] separatedLine = line.split(",");
        for (var i = 0;i < separatedHeader.length;i++){
            columnValue.put(separatedHeader[i], CSVFieldReference.of(separatedLine[i]));
        }
        return columnValue;
    }

}
