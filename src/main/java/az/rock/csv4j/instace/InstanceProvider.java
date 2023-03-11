package az.rock.csv4j.instace;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceProvider extends AbstractInstanceProvider{
    @Override
    public Object newInstance(Class<?> type) {
        try {
            Constructor<?> constructor =  type.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
