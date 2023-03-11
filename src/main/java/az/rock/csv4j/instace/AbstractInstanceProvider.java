package az.rock.csv4j.instace;

public abstract class AbstractInstanceProvider {
    public abstract Object newInstance(Class<?> type);
}
