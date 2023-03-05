package az.rock.csv4j.annotation;


import az.rock.csv4j.strategy.CSVReaderPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVColumn {
    String name() default "";
    CSVReaderPolicy readPolicy() default CSVReaderPolicy.HEADER_NAME;
    boolean nullable() default true;
    String defaultValue() default "";

    ColumnType type() default ColumnType.TEXT;
}
