package az.rock.csv4j.mapper;

import az.rock.csv4j.annotation.CSVReference;

import java.lang.annotation.Annotation;
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

    private void setStatePojo(String line){
        var columnValueMap = this.prepareColumnValue(line);

    }


    private  Map<String,FieldReference> prepareColumnValue(String line){
        final Map<String,FieldReference> columnValue = new HashMap<>();
        String[] separatedHeader = this.header.split(",");
        String[] separatedLine = line.split(",");
        for (var i = 0;i < separatedHeader.length;i++){
            columnValue.put(separatedHeader[i],FieldReference.of(separatedLine[i]));
        }
        System.out.println(columnValue);
        return columnValue;
    }

    private Annotation hasAynReference(){
        CSVReference csvReference = this.pojo.getClass().getAnnotation(CSVReference.class);
        return csvReference;
    }
}
