package az.rock.csv4j.mapper;

import java.util.Objects;

public class CSVFieldReference {
    public static CSVFieldReference of(String value){
        return new CSVFieldReference(Objects.requireNonNullElse(value,"none"));
    }

    private final String value;

    private CSVFieldReference(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
