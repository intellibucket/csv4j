package az.rock.csv4j.inspector;

public class POJOInspector {
    private final Class<?> csvModelType;


    public static <T> POJOInspector of(Class<T> tClass) {
        return new POJOInspector(tClass);
    }

    private POJOInspector(Class<?> tClass){
        this.csvModelType = tClass;
    }
}
