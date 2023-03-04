package az.rock.csv4j.mapper;

public class FieldReference {
    public static FieldReference of(String value){
        return new FieldReference(value);
    }

    private final String value;

    private FieldReference(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
