package az.rock.csv4j.deserializer;

import az.rock.csv4j.exception.ElementManyAnnotatedException;

import java.util.List;

public interface Deserializer <T>{
    List<T> read() throws ElementManyAnnotatedException ;
}
