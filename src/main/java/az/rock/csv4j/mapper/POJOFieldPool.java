package az.rock.csv4j.mapper;

import az.rock.csv4j.model.CSVField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class POJOFieldPool<T> {
    //String is a header value;
    private final Map<String, POJOFieldReference> container = new HashMap<>();
    private final T pojo;
    private final String[] csvHeaders;

    public POJOFieldPool(String csvHeader,T pojo){
        this.pojo = pojo;
        this.csvHeaders = csvHeader.split(",");
    }


    private void initContainer(){
        var filedReferences = Arrays.stream(this.pojo.getClass().getDeclaredFields())
                .map(StringFieldReference::new)
                .toList();

        for (String schHeader : csvHeaders){
            filedReferences
                    .stream()
                    .filter(e->e.match(schHeader))
                    .findAny()
                    .ifPresent(stringFieldReference -> this.container.put(schHeader, stringFieldReference));
        }
    }



    public Map<String, POJOFieldReference> getContainer() {
        this.initContainer();
        return container;
    }
}
